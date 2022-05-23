package views;

import handler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import loop.GameLoopManager;
import main.HUD;
import main.SimpleRPG;
import world.Map;

import static sceneElement.SubSceneList.openSetting;

public class GameView extends View{

    private Scene theScene;
    private AnchorPane mainPane;
    private Canvas canvasBackground;
    private Canvas canvasMiddle;
    private Canvas canvasBattle;
    private GameLoopManager gameLoopManager;
    private KeyHandler keyHandler;
    private HUD mainHUD;
    private Map world;

    public GameView(SimpleRPG simpleRPG){
        super(simpleRPG);
        clearPane();

        this.theScene = simpleRPG.theScene;
        this.mainPane = simpleRPG.mainPane;
        this.canvasBackground = simpleRPG.canvasBackground;
        this.canvasMiddle = simpleRPG.canvasMiddle;
        this.canvasBattle = simpleRPG.canvasBattle;
        this.mainHUD = simpleRPG.mainHUD;
        mainHUD.showHUD();
        this.world = simpleRPG.getWorld();
        this.keyHandler = simpleRPG.keyHandler;


        theScene.setOnKeyPressed(keyHandler);
        theScene.setOnKeyReleased(keyHandler);
        mainPane.getChildren().add(canvasBackground);
        mainPane.getChildren().add(canvasMiddle);
        mainPane.getChildren().add(canvasBattle);
        mainPane.getChildren().add(mainHUD.getHUD());

        simpleRPG.getGameLoopManager().stop();
//        simpleRPG.setArchiveWorld(world);
        simpleRPG.setWorld(simpleRPG.archiveWorld);
        simpleRPG.setGameLoopManager(new GameLoopManager(simpleRPG));
        this.gameLoopManager = simpleRPG.getGameLoopManager();
        CreateScreenElements();
        this.gameLoopManager.start();
    }

    private void CreateScreenElements(){
        addSubSceneToPane(openSetting);
        createSubSceneButton("Pause", openSetting,150, 60, 1100, 20);
    }

}
