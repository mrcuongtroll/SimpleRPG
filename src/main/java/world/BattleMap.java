package world;

import combat.CombatManager;
import entity.Character;
import entity.Enemy;
import entity.Player;
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
    private int currentFrame = 2;
    private int lastFrameStep = 0;

    private Button btnFight;
    private Button btnSurrender;
    private Button btnDoNothing;
    private Button btnItems;

    private Button btnSkill1;
    private Button btnSkill2;
    private Button btnSkill3;
    private Button btnSkill4;

    private HBox container;
    private Rectangle narrator;
    private GridPane mainOptionContainer;
    private GridPane skillOptionContainer;
    
    private Group groupContainer;

    private static final int BAR_WIDTH = 100;

    private Player player;
    private Enemy enemy;

    public BattleMap(SimpleRPG master, String imagePath, Enemy enemy) {
        super(master, imagePath);
        this.player = master.getPlayer();
        this.enemy = enemy;

        this.playerHealthBar = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/2 - 100, BAR_WIDTH, 30);
        this.playerManaBar = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/2 - 50, BAR_WIDTH, 30);
        this.playerHealthBarContainer = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/2 - 100, BAR_WIDTH, 30);
        this.playerManaBarContainer = new Rectangle(100, SimpleRPG.SCREEN_HEIGHT/2 - 50, BAR_WIDTH, 30);

        this.enemyHealthBar = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/2 - 100, BAR_WIDTH, 30);
        this.enemyManaBar = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/2 - 50, BAR_WIDTH, 30);
        this.enemyHealthBarContainer = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/2 - 100, BAR_WIDTH, 30);
        this.enemyManaBarContainer = new Rectangle(SimpleRPG.SCREEN_WIDTH - 200, SimpleRPG.SCREEN_HEIGHT/2 - 50, BAR_WIDTH, 30);

        this.playerFrame = new ImageView(this.player.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");

        //Flip the sprite
        this.playerFrame.setScaleX(-1);

        this.enemyFrame = new ImageView(this.enemy.getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png");
        this.playerFrame.setX(100);
        this.playerFrame.setY(SimpleRPG.SCREEN_HEIGHT/2);

        this.enemyFrame.setX(SimpleRPG.SCREEN_WIDTH - 200);
        this.enemyFrame.setY(SimpleRPG.SCREEN_HEIGHT/2);

        this.groupContainer = new Group();
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

        this.getMaster().mainPane.getChildren().add(this.groupContainer);

        CombatManager combatInstance = new CombatManager(new Player[]{this.player}, new Enemy[]{this.enemy});
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
