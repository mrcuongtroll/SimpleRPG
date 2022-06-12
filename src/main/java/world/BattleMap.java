package world;

import combat.CombatManager;
import combat.effect.Effect;
import combat.effect.EffectAnimationTimer;
import entity.Character;
import entity.Enemy;
import entity.Player;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.SimpleRPG;
import sceneElement.GameSubScene;
import sceneElement.SubSceneList;
import views.BattleView;
import views.GameView;

import java.util.ArrayList;

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
    private static final int BAR_WIDTH = 125;
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

    public static BattleView getView() {
        return view;
    }

    public static ArrayList<EffectAnimationTimer> effectAnimationList = new ArrayList<>();
    public BattleMap(SimpleRPG master, BattleView battleView, String imagePath, Enemy enemyFighter) {
        super(master, 0, 0, imagePath);
        player = master.getPlayer();
        enemy = enemyFighter;

        this.playerHealthBar = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4, BAR_WIDTH, 30);
        this.playerManaBar = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4 + 50, BAR_WIDTH, 30);
        this.playerHealthBarContainer = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4, BAR_WIDTH, 30);
        this.playerManaBarContainer = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/4 + 50, BAR_WIDTH, 30);
        this.playerHealthPoint = new Text(100, SimpleRPG.SCREEN_HEIGHT/4 + 25, "");
        this.playerManaPoint = new Text(100, SimpleRPG.SCREEN_HEIGHT/4 + 75, "");

        this.enemyHealthBar = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4, BAR_WIDTH, 30);
        this.enemyManaBar = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 + 50, BAR_WIDTH, 30);
        this.enemyHealthBarContainer = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4, BAR_WIDTH, 30);
        this.enemyManaBarContainer = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 + 50, BAR_WIDTH, 30);
        this.enemyHealthPoint = new Text(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 + 25, "100");
        this.enemyManaPoint = new Text(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/4 + 75, "100");

        playerFrame = new ImageView(this.player.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");

        //Flip the sprite
        playerFrame.setScaleX(-1);

        enemyFrame = new ImageView(this.enemy.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");
        playerFrame.setX(100);
        playerFrame.setY(SimpleRPG.SCREEN_HEIGHT/4 + 100);

        playerFrame.setFitWidth(80);
        playerFrame.setFitHeight(150);

        enemyFrame.setX(SimpleRPG.SCREEN_WIDTH - 200);
        enemyFrame.setY(SimpleRPG.SCREEN_HEIGHT/4 + 100);

        enemyFrame.setFitWidth(80);
        enemyFrame.setFitHeight(150);

        playerHitBox = new ImageView();

        //Flip the sprite
        playerHitBox.setScaleX(-1);

        enemyHitBox = new ImageView();
        playerHitBox.setX(0);
        playerHitBox.setY(SimpleRPG.SCREEN_HEIGHT/6 + 100);
        playerHitBox.setFitWidth(300);
        playerHitBox.setFitHeight(300);

        enemyHitBox.setX(SimpleRPG.SCREEN_WIDTH - 300);
        enemyHitBox.setY(SimpleRPG.SCREEN_HEIGHT/6 + 100);
        enemyHitBox.setFitWidth(300);
        enemyHitBox.setFitHeight(300);

        this.groupContainer = new Group();
        combatManager = new CombatManager(new Player[]{player}, new Enemy[]{enemy});
        view = battleView;
        start();
    }

    public void start() {
        getMaster().hideHUD();
        this.getGraphicsContext().setFill(Color.BLACK);
        this.getGraphicsContext().fillRect(0, 0, getMaster().canvasBattle.getWidth(), getMaster().canvasBattle.getHeight());
        this.getGraphicsContext().drawImage(this.getBg(), 0, 0, SimpleRPG.SCREEN_WIDTH, SimpleRPG.SCREEN_HEIGHT);

        this.groupContainer.getChildren().add(this.playerHealthBarContainer);
        this.groupContainer.getChildren().add(this.enemyHealthBarContainer);
        this.groupContainer.getChildren().add(this.playerManaBarContainer);
        this.groupContainer.getChildren().add(this.enemyManaBarContainer);

        this.groupContainer.getChildren().add(this.playerHealthBar);
        this.groupContainer.getChildren().add(this.playerManaBar);
        this.groupContainer.getChildren().add(this.playerHealthPoint);
        this.groupContainer.getChildren().add(this.playerManaPoint);
        this.groupContainer.getChildren().add(this.enemyHealthBar);
        this.groupContainer.getChildren().add(this.enemyManaBar);
        this.groupContainer.getChildren().add(this.enemyHealthPoint);
        this.groupContainer.getChildren().add(this.enemyManaPoint);

        this.groupContainer.getChildren().add(playerFrame);
        this.groupContainer.getChildren().add(enemyFrame);
        this.groupContainer.getChildren().add(playerHitBox);
        this.groupContainer.getChildren().add(enemyHitBox);

//        playerHitBox.setVisible(false);
//        enemyHitBox.setVisible(false);

        getMaster().mainPane.getChildren().add(this.groupContainer);
        turnDecide();
    }

    public static void turnDecide() {
        if (enemy.getHealthPoint() <= 0) {
            view.cleanUpScene();
            new GameView(getMaster());
            ((World) getMaster().getWorld()).removeNPC(enemy);
        } else if (player.getHealthPoint() <= 0) {
            view.cleanUpScene();
            view.showSubScene(SubSceneList.openGameOver);
        } else {
            Character currentTurnChar = combatManager.getCurrentTurnCharacter();
            if (currentTurnChar instanceof Player) {
                view.cleanUpScene();
                view.showSubScene(SubSceneList.openBattleOption);
                System.out.println("Player turn");
            } else if (currentTurnChar instanceof Enemy) {
                view.cleanUpScene();
                System.out.println("Enemy turn");
                enemy.randomAttack(player);
            }
        }
        SubSceneList.checkManaRequirement();
    }

    public static void showSkillEffect(Character character, Effect effect, String... dialogTexts) {
        if (character instanceof Player) {
            effectAnimationList.add(new EffectAnimationTimer(effect, playerHitBox, character, getMaster(), dialogTexts));
        } else if (character instanceof Enemy) {
            effectAnimationList.add(new EffectAnimationTimer(effect, enemyHitBox, character, getMaster(), dialogTexts));
        }
    }

    public static void showDialog(String[] texts) {
        view.cleanUpScene();
        GameSubScene dialogScene = SubSceneList.createDialogScene(texts);
//        view.addSubSceneToPane(dialogScene);
        view.showSubScene(dialogScene);
//        view.showSubScene(SubSceneList.openDialog);
    }

    @Override
    public void tick() {
        this.playerHealthBar.setWidth(BAR_WIDTH * player.getHealthPoint() / 100);
        this.playerManaBar.setWidth(BAR_WIDTH * player.getManaPoint() / 100);
        this.playerHealthPoint.setText("" + player.getHealthPoint() + " / 100");
        this.playerManaPoint.setText("" + player.getManaPoint() + " / 100");
        this.enemyHealthBar.setWidth(BAR_WIDTH * enemy.getHealthPoint() / 100);
        this.enemyManaBar.setWidth(BAR_WIDTH * enemy.getManaPoint() / 100);
        this.enemyHealthPoint.setText("" + enemy.getHealthPoint() + " / 100");
        this.enemyManaPoint.setText("" + enemy.getManaPoint() + " / 100");

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
        this.getMaster().canvasBattle.getGraphicsContext2D().drawImage(this.getBg(), 0, 0);
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
