package views;

import entity.Enemy;
import handler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import loop.GameLoopManager;
import main.HUD;
import main.SimpleRPG;
import world.BattleMap;
import world.Map;

import java.io.File;

import static sceneElement.SubSceneList.*;

public class BattleView extends View{

    private Scene theScene;
    private AnchorPane mainPane;
    private Canvas canvasBackground;
    private Canvas canvasMiddle;
    private Canvas canvasBattle;
    private GameLoopManager gameLoopManager;
    private KeyHandler keyHandler;
    private HUD mainHUD;
    private Map world;

    public BattleView(SimpleRPG simpleRPG, Enemy enemy){
        super(simpleRPG);
        clearPane();

        this.theScene = simpleRPG.theScene;
        this.mainPane = simpleRPG.mainPane;
        this.canvasBackground = simpleRPG.canvasBackground;
        this.canvasMiddle = simpleRPG.canvasMiddle;
        this.canvasBattle = simpleRPG.canvasBattle;
        this.mainHUD = simpleRPG.mainHUD;
        this.world = simpleRPG.getWorld();
        this.keyHandler = simpleRPG.keyHandler;


        theScene.setOnKeyPressed(keyHandler);
        theScene.setOnKeyReleased(keyHandler);
        mainPane.getChildren().add(canvasBackground);
        mainPane.getChildren().add(canvasMiddle);
        mainPane.getChildren().add(canvasBattle);
        mainPane.getChildren().add(mainHUD.getHUD());
//        mainPane.getChildren().add(simpleRPG.popupPane);

        BattleMap battleMap = new BattleMap(simpleRPG, this, (new File("./assets/test/battle_map.png")).getAbsolutePath(), enemy);

        simpleRPG.getGameLoopManager().stop();
        simpleRPG.setBattleMap(battleMap);
        simpleRPG.setWorld(battleMap);
        simpleRPG.setGameLoopManager(new GameLoopManager(simpleRPG));

        createScreenElements();
        this.gameLoopManager = simpleRPG.getGameLoopManager();
        this.gameLoopManager.start();
    }

    public void createScreenElements(){
        addSubSceneToPane(openDialog);
        addSubSceneToPane(openBattleOption);
        addSubSceneToPane(openInventory);
        addSubSceneToPane(openSkill);
        addSubSceneToPane(openGameOver);
//        showSubScene(openBattleOption);
    }

}
