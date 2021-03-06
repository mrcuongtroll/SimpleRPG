package main;

import dialogue.DialogueRender;
import entity.Player;
import handler.KeyHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import loop.GameLoopManager;
import saveload.SaveLoad;
import sceneElement.SubSceneList;
import views.StartScreenView;
import world.Map;

import java.io.IOException;

import static sceneElement.SubSceneList.openView;

public class SimpleRPG extends Application {

    //Variables for starting screen
    public Stage mainStage;
    public StartScreenView startScreenView;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public Canvas canvasBackground = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    public Canvas canvasMiddle = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    public Canvas canvasOverlay = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    public Canvas canvasShading = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);


    public AnchorPane popupPane = new AnchorPane();
    public static Canvas canvasBattle = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);

//    private Player player = new Player(this, 620, 820, "Player",
//            (new File("./assets/test/player")).getAbsolutePath(), 1, 6,
//            25, 10, 80, 100, 100, 100,
//            new Weapon("Wooden sword",
//                    0, 25,0,0,0,
//                    "example_armor.png"),
//            new Armor("Wooden armor",
//                    0, 0, 5,0,0,
//                    "example_armor.png"));
    private Player player = SaveLoad.loadPlayer(this);
//    private Map world = new World(this, 620, 820,
//            (new File("./assets/Map/Outside/outside_bottom.png")).getAbsolutePath(),
//            (new File("./assets/Map/Outside/outside_overlay.png")).getAbsolutePath(),
//            (new File("./assets/Map/Outside/outside_shading.png")).getAbsolutePath(),
//            (new File("./assets/Map/Outside/outside_mask.png")).getAbsolutePath());
    private Map world = SaveLoad.loadWorld(this);

    //    public Map world = BattleMap(this, (new File("./assets/test/battle_map.png")).getAbsolutePath());
    private DialogueRender dialogueRender = new DialogueRender(this);

    public KeyHandler keyHandler = new KeyHandler(this);
    public AnchorPane mainPane = new AnchorPane();
    public Scene theScene = new Scene(mainPane, SCREEN_WIDTH, SCREEN_HEIGHT);
    public HUD mainHUD = new HUD(this);
    public GameLoopManager gameLoopManager = new GameLoopManager(this);

    public Map battleMap;
    public Map archiveWorld = world;

    public Map getWorld() {
        return this.world;
    }

    public void setWorld(Map world) {
        this.world = world;
    }

    public void setBattleMap(Map world) {
        this.battleMap = world;
    }

    public void setArchiveWorld(Map world) {
        this.world = archiveWorld;
    }

    public Player getPlayer() {
        return this.player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public DialogueRender getDialogueRender() {
        return this.dialogueRender;
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
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        mainStage.setTitle("Simple RPG");
        theScene.getStylesheets().add(String.valueOf(getClass().getResource("/buttonStyle.css")));
        mainStage.setScene(theScene);
        SubSceneList sceneList = new SubSceneList(this);
        openView(new StartScreenView(this));

        mainStage.show();
    }

    public GameLoopManager getGameLoopManager() {
        return this.gameLoopManager;
    }

    public void setGameLoopManager(GameLoopManager gameLoopManager) {
        this.gameLoopManager = gameLoopManager;
    }
}
