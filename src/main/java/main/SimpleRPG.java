package main;

import entity.Armor;
import entity.Enemy;
import entity.Player;
import entity.Weapon;
import handler.KeyHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import loop.GameLoopManager;
import world.BattleMap;
import world.Map;
import world.World;

import java.io.File;
import java.io.IOException;

public class SimpleRPG extends Application {

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public Canvas canvasBackground = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    public Canvas canvasMiddle = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);

    public Canvas canvasBattle = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);

    public Map world = new World(this, (new File("./assets/test/old map.png")).getAbsolutePath(),
            (new File("./assets/test/old map_mask.png")).getAbsolutePath());
//    public Map world = BattleMap(this, (new File("./assets/test/battle_map.png")).getAbsolutePath());
    public Player player = new Player(this, Player.X, Player.Y, "Player",
            (new File("./assets/test/player")).getAbsolutePath(), 1, 80, 100,
            new Weapon(10, 0, "example_armor.png"),
            new Armor(0, 20, "example_armor.png"));
    public KeyHandler keyHandler = new KeyHandler(this);
    public Group root = new Group();
    public Scene theScene = new Scene(root);
    public HUD mainHUD = new HUD(this, player);
    public GameLoopManager gameLoopManager = new GameLoopManager(this);
    public Map getWorld() {
        return this.world;
    }
    public void setWorld(Map world) {
        this.world = world;
    }
    public Player getPlayer() {
        return this.player;
    }
    public HUD getMainHUD() {
        return this.mainHUD;
    }
    public void hideHUD() {
        this.mainHUD.hideHUD();
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Simple RPG");
        theScene.setOnKeyPressed(this.keyHandler);
        theScene.setOnKeyReleased(this.keyHandler);
        primaryStage.setScene(theScene);
        root.getChildren().add(this.canvasBackground);
        root.getChildren().add(this.canvasMiddle);
        root.getChildren().add(this.canvasBattle);
        root.getChildren().add(mainHUD.getHUD());
//        this.setWorld(world);
        this.setWorld(new BattleMap(this, (new File("./assets/test/battle_map.png")).getAbsolutePath(),
                new Enemy((World) world, this, SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                        (new File("./assets/test/enemy")).getAbsolutePath(),
                        1, 100, 100, 10, 10)));
        gameLoopManager.start();
        primaryStage.show();
    }
}
