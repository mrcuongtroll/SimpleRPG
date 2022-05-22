package world;

import combat.CombatManager;
import combat.action.NormalAttack;
import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.SimpleRPG;
import sceneElement.SubSceneList;
import views.BattleView;

import java.nio.file.Paths;

public class BattleMap extends Map {

    private Rectangle playerHealthBar;
    private Rectangle playerManaBar;
    private Rectangle playerHealthBarContainer;
    private Rectangle playerManaBarContainer;
    private Rectangle enemyHealthBar;
    private Rectangle enemyManaBar;
    private Rectangle enemyHealthBarContainer;
    private Rectangle enemyManaBarContainer;

    private Text playerHealthPoint;
    private Text playerManaPoint;

    private Text enemyHealthPoint;
    private Text enemyManaPoint;
    private ImageView playerFrame;
    private ImageView enemyFrame;
    private static ImageView playerHitBox;
    private static ImageView enemyHitBox;

    private int currentFrame = 2;
    private int lastFrameStep = 0;
    private Group groupContainer;
    private static final int BAR_WIDTH = 100;
    private static Player player;
    private static Enemy enemy;

    private static CombatManager combatManager;
    private static BattleView view;
    public Player getPlayer() {
        return player;
    }
    public Enemy getEnemy() {
        return enemy;
    }
    private static class EffectAnimation extends AnimationTimer {

        private long lastUpdate;
        private int currentFrame;
        private String currentFramePath;
        private Effect effect;
        private ImageView hitBox;

        public EffectAnimation(Effect effect, ImageView hitBox) {
            this.effect = effect;
            this.hitBox = hitBox;
            this.lastUpdate = 0;
            this.currentFrame = 1;
            this.start();
        }

        @Override
        public void handle(long now) {
            if (now - lastUpdate >= 40_000_000) {
                currentFramePath = Paths.get(effect.getEffectImagePath(), currentFrame + ".png").toString();
                hitBox.setImage(new Image(currentFramePath));
                lastUpdate = now;
                currentFrame++;
                if (currentFrame > effect.getNumEffectFrame()) {
                    this.stop();
                    turnDecide();
                }
            }
        }
    }

    public BattleMap(SimpleRPG master, BattleView battleView, String imagePath, Enemy enemyFighter) {
        super(master, imagePath);
        player = master.getPlayer();
        enemy = enemyFighter;

        this.playerHealthBar = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4 - 100, BAR_WIDTH, 30);
        this.playerManaBar = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4 - 50, BAR_WIDTH, 30);
        this.playerHealthBarContainer = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4 - 100, BAR_WIDTH, 30);
        this.playerManaBarContainer = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4 - 50, BAR_WIDTH, 30);

        this.enemyHealthBar = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 - 100, BAR_WIDTH, 30);
        this.enemyManaBar = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 - 50, BAR_WIDTH, 30);
        this.enemyHealthBarContainer = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 - 100, BAR_WIDTH, 30);
        this.enemyManaBarContainer = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 - 50, BAR_WIDTH, 30);

        this.playerFrame = new ImageView(this.player.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");

        //Flip the sprite
        this.playerFrame.setScaleX(-1);

        this.enemyFrame = new ImageView(this.enemy.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");
        this.playerFrame.setX(100);
        this.playerFrame.setY(SimpleRPG.SCREEN_HEIGHT/4);

        this.playerFrame.setFitWidth(80);
        this.playerFrame.setFitHeight(150);

        this.enemyFrame.setX(SimpleRPG.SCREEN_WIDTH - 200);
        this.enemyFrame.setY(SimpleRPG.SCREEN_HEIGHT/4);

        this.enemyFrame.setFitWidth(80);
        this.enemyFrame.setFitHeight(150);

        playerHitBox = new ImageView();

        //Flip the sprite
        playerHitBox.setScaleX(-1);

        enemyHitBox = new ImageView();
        playerHitBox.setX(0);
        playerHitBox.setY(SimpleRPG.SCREEN_HEIGHT/6);
        playerHitBox.setFitWidth(300);
        playerHitBox.setFitHeight(300);

        enemyHitBox.setX(SimpleRPG.SCREEN_WIDTH - 300);
        enemyHitBox.setY(SimpleRPG.SCREEN_HEIGHT/6);
        enemyHitBox.setFitWidth(300);
        enemyHitBox.setFitHeight(300);

        this.groupContainer = new Group();
        combatManager = new CombatManager(new Player[]{this.player}, new Enemy[]{this.enemy});
        view = battleView;
        start();
    }

    public void start() {
        this.getMaster().hideHUD();
        this.getGraphicsContext().setFill(Color.BLACK);
        this.getGraphicsContext().fillRect(0, 0, this.getMaster().canvasBattle.getWidth(), this.getMaster().canvasBattle.getHeight());
        this.getGraphicsContext().drawImage(this.getBg(), 0, 0, SimpleRPG.SCREEN_WIDTH, SimpleRPG.SCREEN_HEIGHT);

        this.groupContainer.getChildren().add(this.playerHealthBarContainer);
        this.groupContainer.getChildren().add(this.enemyHealthBarContainer);
        this.groupContainer.getChildren().add(this.playerManaBarContainer);
        this.groupContainer.getChildren().add(this.enemyManaBarContainer);

        this.groupContainer.getChildren().add(this.playerHealthBar);
        this.groupContainer.getChildren().add(this.playerManaBar);
        this.groupContainer.getChildren().add(this.enemyHealthBar);
        this.groupContainer.getChildren().add(this.enemyManaBar);
        this.groupContainer.getChildren().add(this.playerFrame);
        this.groupContainer.getChildren().add(this.enemyFrame);

        this.groupContainer.getChildren().add(playerHitBox);
        this.groupContainer.getChildren().add(enemyHitBox);

//        playerHitBox.setVisible(false);
//        enemyHitBox.setVisible(false);

        this.getMaster().mainPane.getChildren().add(this.groupContainer);
        turnDecide();
    }

    public static void turnDecide() {
        Character currentTurnChar = combatManager.getCurrentTurnCharacter();
        if (currentTurnChar instanceof Player) {
            view.cleanUpScene();
            view.showSubScene(SubSceneList.openBattleOption);
            System.out.println("Player turn");
        } else if (currentTurnChar instanceof Enemy) {
            view.cleanUpScene();
            System.out.println("Enemy turn");
            (new NormalAttack()).activate(currentTurnChar, player);
        }
    }

    public static void showSkillEffect(Character defender, Effect effect) {
        if (defender instanceof Player) {
            new EffectAnimation(effect, playerHitBox);
        } else if (defender instanceof Enemy) {
            new EffectAnimation(effect, enemyHitBox);
        }
    }

    @Override
    public void tick() {
        this.playerHealthBar.setWidth(BAR_WIDTH * this.player.getHealthPoint() / 100);
        this.playerManaBar.setWidth(BAR_WIDTH * this.player.getManaPoint() / 100);
        this.enemyHealthBar.setWidth(BAR_WIDTH * this.enemy.getHealthPoint() / 100);
        this.enemyManaBar.setWidth(BAR_WIDTH * this.enemy.getManaPoint() / 100);

        this.playerHealthBar.setFill(Color.CRIMSON);
        this.playerManaBar.setFill(Color.CORNFLOWERBLUE);
        this.enemyHealthBar.setFill(Color.CRIMSON);
        this.enemyManaBar.setFill(Color.CORNFLOWERBLUE);

        this.playerHealthBarContainer.setFill(Color.DIMGRAY);
        this.playerManaBarContainer.setFill(Color.DIMGRAY);
        this.enemyHealthBarContainer.setFill(Color.DIMGRAY);
        this.enemyManaBarContainer.setFill(Color.DIMGRAY);
    }

    public void changeFrame() {
        this.lastFrameStep += Player.MOVEMENT_SPEED;
        if (this.lastFrameStep > 6 * Player.MOVEMENT_SPEED) {
            this.lastFrameStep = 0;
            this.currentFrame++;
            if (this.currentFrame > Character.NUM_IMAGE_FRAME) {
                this.currentFrame = 1;
            }
            this.playerFrame.setImage(new Image(this.player.getImagePath() + Character.BATTLE_IMAGE_PATH + this.currentFrame + ".png"));
            this.enemyFrame.setImage(new Image(this.enemy.getImagePath() + Character.BATTLE_IMAGE_PATH + this.currentFrame + ".png"));
        }
    }
    @Override
    public void render() {
        this.tick();
        this.changeFrame();
    }
}
