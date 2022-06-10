package views;

import handler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import loop.GameLoopManager;
import main.HUD;
import main.SimpleRPG;
import popup.PopupChoice;
import popup.PopupRender;
import world.Map;

import static sceneElement.SubSceneList.openSetting;

public class GameView extends View{

    private Scene theScene;
    private AnchorPane mainPane;
    private Canvas canvasBackground;
    private Canvas canvasMiddle;
    private Canvas canvasOverlay;
    private Canvas canvasShading;
    private Canvas canvasBattle;
    private GameLoopManager gameLoopManager;
    private KeyHandler keyHandler;
    private AnchorPane popupPane = new AnchorPane();

    private HUD mainHUD;
    private Map world;

    public GameView(SimpleRPG simpleRPG){
        super(simpleRPG);
        clearPane();

        this.theScene = simpleRPG.theScene;
        this.mainPane = simpleRPG.mainPane;
        this.canvasBackground = simpleRPG.canvasBackground;
        this.canvasMiddle = simpleRPG.canvasMiddle;
        this.canvasOverlay = simpleRPG.canvasOverlay;
        this.canvasShading = simpleRPG.canvasShading;
        this.canvasBattle = simpleRPG.canvasBattle;
        this.popupPane = simpleRPG.popupPane;
        this.mainHUD = simpleRPG.mainHUD;
        mainHUD.showHUD();
        this.world = simpleRPG.getWorld();
        this.keyHandler = simpleRPG.keyHandler;


        theScene.setOnKeyPressed(keyHandler);
        theScene.setOnKeyReleased(keyHandler);
        mainPane.getChildren().add(canvasBackground);
        mainPane.getChildren().add(canvasMiddle);
        mainPane.getChildren().add(canvasOverlay);
        mainPane.getChildren().add(canvasShading);
        mainPane.getChildren().add(canvasBattle);
        mainPane.getChildren().add(mainHUD.getHUD());
        mainPane.getChildren().add(popupPane);

        simpleRPG.getGameLoopManager().stop();
//        simpleRPG.setArchiveWorld(world);
        simpleRPG.setWorld(simpleRPG.archiveWorld);
        simpleRPG.setGameLoopManager(new GameLoopManager(simpleRPG));
        this.gameLoopManager = simpleRPG.getGameLoopManager();
        CreateScreenElements();
        canvasMiddle.requestFocus();
        this.gameLoopManager.start();

//        String[] choices = {"Yes", "No"};
//        PopupChoice popup = new PopupChoice("Choose an option", choices);
//        PopupRender popupRender = new PopupRender(simpleRPG,popup);
//        popupRender.render();

    }

    private void CreateScreenElements(){
        addSubSceneToPane(openSetting);
        createSubSceneButton("Pause", openSetting,150, 60, 1100, 20);
    }

}
