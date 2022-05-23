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

    private final Scene theScene;
    private final AnchorPane mainPane;
    private final Canvas canvasBackground;
    private final Canvas canvasMiddle;
    private final Canvas canvasBattle;
    private final GameLoopManager gameLoopManager;
    private final KeyHandler keyHandler;
    private final HUD mainHUD;
    private final Map world;

    public GameView(SimpleRPG simpleRPG){
        super(simpleRPG);
        clearPane();

        this.theScene = simpleRPG.theScene;
        this.mainPane = simpleRPG.mainPane;
        this.canvasBackground = simpleRPG.canvasBackground;
        this.canvasMiddle = simpleRPG.canvasMiddle;
        this.canvasBattle = simpleRPG.canvasBattle;
        this.gameLoopManager = simpleRPG.gameLoopManager;
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

//        simpleRPG.setArchiveWorld(world);
        simpleRPG.setWorld(simpleRPG.archiveWorld);
        CreateScreenElements();
        gameLoopManager.start();
    }

    private void CreateScreenElements(){
        addSubSceneToPane(openSetting);
        createSubSceneButton("Pause", openSetting,150, 60, 1100, 20);

    }

}
