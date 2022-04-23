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

    public Canvas canvas = new Canvas(1280, 720);

    public World testWorld = new World(this, (new File("./assets/test/old map.png")).getAbsolutePath());
    public Player testPlayer = new Player(this, Player.X, Player.Y,
            (new File("./assets/test/player")).getAbsolutePath(), 1, 100, 100,
            new Weapon(10, 0, "example_armor.png"),
            new Armor(0, 20, "example_armor.png"));
    public GameLoopManager gameLoopManager = new GameLoopManager(testWorld, testPlayer);

    public KeyHandler keyHandler = new KeyHandler(this);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Simple RPG");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theScene.setOnKeyPressed(this.keyHandler);
        primaryStage.setScene(theScene);
        root.getChildren().add(this.canvas);
        gameLoopManager.start();
        primaryStage.show();
    }
}
