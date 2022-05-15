package main;

import entity.Armor;
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
import world.World;

import java.io.File;
import java.io.IOException;

public class SimpleRPG extends Application {

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    public Canvas canvasBackground = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    public Canvas canvasMiddle = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    public World world = new World(this, (new File("./assets/test/old map.png")).getAbsolutePath(),
            (new File("./assets/test/old map_mask.png")).getAbsolutePath());
    public Player player = new Player(this, Player.X, Player.Y, "Player",
            (new File("./assets/test/player")).getAbsolutePath(), 1, 80, 100,
            new Weapon(10, 0, "example_armor.png"),
            new Armor(0, 20, "example_armor.png"));
    public KeyHandler keyHandler = new KeyHandler(this);
    public Group root = new Group();
    public Scene theScene = new Scene(root);
    public HUD mainHUD = new HUD(this, player);
    public GameLoopManager gameLoopManager = new GameLoopManager(this, mainHUD, world, player);
    public World getWorld() {
        return this.world;
    }
    public Player getPlayer() {
        return this.player;
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
        mainHUD.start();
        gameLoopManager.start();
        primaryStage.show();
    }
}
