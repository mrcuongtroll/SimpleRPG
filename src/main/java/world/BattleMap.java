package world;

import combat.CombatManager;
import combat.action.NormalAttack;
import combat.effect.Effect;
import combat.effect.EffectAnimationTimer;
import entity.Character;
import entity.Enemy;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
    private static ImageView playerFrame;
    private static ImageView enemyFrame;
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

    public static ImageView getEnemyFrame() {
        return enemyFrame;
    }

    public static ImageView getPlayerFrame() {
        return playerFrame;
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

        playerFrame = new ImageView(this.player.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");

        //Flip the sprite
        playerFrame.setScaleX(-1);

        enemyFrame = new ImageView(this.enemy.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");
        playerFrame.setX(100);
        playerFrame.setY(SimpleRPG.SCREEN_HEIGHT/4);

        playerFrame.setFitWidth(80);
        playerFrame.setFitHeight(150);

        enemyFrame.setX(SimpleRPG.SCREEN_WIDTH - 200);
        enemyFrame.setY(SimpleRPG.SCREEN_HEIGHT/4);

        enemyFrame.setFitWidth(80);
        enemyFrame.setFitHeight(150);

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
        combatManager = new CombatManager(new Player[]{player}, new Enemy[]{enemy});
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

        this.groupContainer.getChildren().add(playerFrame);
        this.groupContainer.getChildren().add(enemyFrame);
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

    public static void showSkillEffect(Character attacker, Character defender, Effect effect) {
        if (defender instanceof Player) {
            new EffectAnimationTimer(effect, playerHitBox, attacker, defender);
        } else if (defender instanceof Enemy) {
            new EffectAnimationTimer(effect, enemyHitBox, attacker, defender);
        }
    }

    @Override
    public void tick() {
        this.playerHealthBar.setWidth(BAR_WIDTH * player.getHealthPoint() / 100);
        this.playerManaBar.setWidth(BAR_WIDTH * player.getManaPoint() / 100);
        this.enemyHealthBar.setWidth(BAR_WIDTH * enemy.getHealthPoint() / 100);
        this.enemyManaBar.setWidth(BAR_WIDTH * enemy.getManaPoint() / 100);

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
            playerFrame.setImage(new Image(player.getImagePath() + Character.BATTLE_IMAGE_PATH + this.currentFrame + ".png"));
            enemyFrame.setImage(new Image(enemy.getImagePath() + Character.BATTLE_IMAGE_PATH + this.currentFrame + ".png"));
        }
    }
    @Override
    public void render() {
        this.tick();
        this.changeFrame();
    }
}