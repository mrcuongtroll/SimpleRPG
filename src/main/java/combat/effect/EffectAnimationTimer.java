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

import java.io.File;
import java.nio.file.Paths;

public class EffectAnimationTimer extends AnimationTimer {

    private long lastUpdate;
    private int currentFrame;
    private String currentFramePath;
    private Effect effect;
    private ImageView hitBox;
    private Character attacker;
    private Character defender;
    private String[] dialogTexts;
    private SimpleRPG gameInstance;
    private boolean showDialog = true;

    public EffectAnimationTimer(Effect effect, ImageView hitBox, Character attacker, Character defender, SimpleRPG gameInstance, String[] dialogTexts) {
        this.effect = effect;
        this.hitBox = hitBox;
        this.attacker = attacker;
        this.defender = defender;
        this.lastUpdate = 0;
        this.currentFrame = 1;
        this.gameInstance = gameInstance;
        this.dialogTexts = dialogTexts;
        this.start();
    }
    public EffectAnimationTimer(Effect effect, ImageView hitBox, Character attacker, Character defender, SimpleRPG gameInstance, String[] dialogTexts, boolean showDialog) {
        this( effect,  hitBox,  attacker, defender,  gameInstance,  dialogTexts);
        this.showDialog = showDialog;
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

                fadeTransitionForward.setNode(BattleMap.getBattleFrame(defender));
                fadeTransitionBackward.setNode(BattleMap.getBattleFrame(defender));

                if (attacker.getBattleSide().equals("left")) {
                    transitionForward.setNode(BattleMap.getBattleFrame(attacker));
                    transitionBackward.setNode(BattleMap.getBattleFrame(attacker));
                    transitionForward.setToX(transitionForward.getNode().getScaleX() + 100);
                } else if (attacker.getBattleSide().equals("right")) {
                    transitionForward.setNode(BattleMap.getBattleFrame(attacker));
                    transitionBackward.setNode(BattleMap.getBattleFrame(attacker));
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
            if ((currentFrame > effect.getNumEffectFrame()) ) {
                this.stop();
                if (this.showDialog){
                    BattleMap.showDialog(this.dialogTexts);
                }
            }
        }
    }
}
