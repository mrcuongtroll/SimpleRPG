package combat.effect;

import entity.Character;
import entity.Enemy;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.SimpleRPG;
import world.BattleMap;

import java.nio.file.Paths;

public class EffectAnimationTimer extends AnimationTimer {

    private long lastUpdate;
    private int currentFrame;
    private String currentFramePath;
    private Effect effect;
    private ImageView hitBox;
    private Character character;
    private String dialogText;
    private SimpleRPG gameInstance;

    public EffectAnimationTimer(Effect effect, ImageView hitBox, Character character, String dialogText, SimpleRPG gameInstance) {
        this.effect = effect;
        this.hitBox = hitBox;
        this.character = character;
        this.lastUpdate = 0;
        this.currentFrame = 1;
        this.gameInstance = gameInstance;
        this.dialogText = dialogText;
        this.start();
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 40_000_000) {
            currentFramePath = Paths.get(effect.getEffectImagePath(), currentFrame + ".png").toString();
            hitBox.setImage(new Image(currentFramePath));
            if (effect.getEffectType().equals(Effect.OPPONENT_APPLY_EFFECT)) {
                FadeTransition fadeTransitionForward = new FadeTransition(Duration.seconds(0.1));
                FadeTransition fadeTransitionBackward = new FadeTransition(Duration.seconds(0.1));

                TranslateTransition transitionForward = new TranslateTransition(Duration.seconds(0.1));
                TranslateTransition transitionBackward = new TranslateTransition(Duration.seconds(0.1));

                if (character instanceof Enemy) {
                    fadeTransitionForward.setNode(BattleMap.getEnemyFrame());
                    fadeTransitionBackward.setNode(BattleMap.getEnemyFrame());
                    transitionForward.setNode(BattleMap.getPlayerFrame());
                    transitionBackward.setNode(BattleMap.getPlayerFrame());
                    transitionForward.setToX(transitionForward.getNode().getScaleX() + 100);
                } else if (character instanceof Player) {
                    fadeTransitionForward.setNode(BattleMap.getPlayerFrame());
                    fadeTransitionBackward.setNode(BattleMap.getPlayerFrame());
                    transitionForward.setNode(BattleMap.getEnemyFrame());
                    transitionBackward.setNode(BattleMap.getEnemyFrame());
                    transitionForward.setToX(transitionForward.getNode().getScaleX() - 100);
                }

                fadeTransitionForward.setFromValue(1.0);
                fadeTransitionForward.setToValue(0.0);
                fadeTransitionForward.setCycleCount(2);

                fadeTransitionBackward.setFromValue(0.0);
                fadeTransitionBackward.setToValue(1.0);
                fadeTransitionBackward.setCycleCount(2);
                fadeTransitionBackward.setDelay(Duration.seconds(0.2));

                transitionBackward.setToX(transitionForward.getNode().getScaleX());
                transitionForward.setCycleCount(1);
                transitionBackward.setCycleCount(1);
                transitionBackward.setDelay(Duration.seconds(0.1));

                fadeTransitionForward.play();
                transitionForward.play();
                fadeTransitionBackward.play();
                transitionBackward.play();
            }
            lastUpdate = now;
            currentFrame++;
            if (currentFrame > effect.getNumEffectFrame()) {
                this.stop();
                BattleMap.showDialog(this.dialogText);
            }
        }
    }
}
