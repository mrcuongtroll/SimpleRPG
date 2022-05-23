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

<<<<<<< Updated upstream
    private Scene theScene;
    private AnchorPane mainPane;
    private Canvas canvasBackground;
    private Canvas canvasMiddle;
    private Canvas canvasBattle;
    private GameLoopManager gameLoopManager;
    private KeyHandler keyHandler;
    private HUD mainHUD;
    private Map world;

    public BattleView(SimpleRPG simpleRPG){
        super(simpleRPG);
        clearPane();

        this.theScene = simpleRPG.theScene;
        this.mainPane = simpleRPG.mainPane;
        this.canvasBackground = simpleRPG.canvasBackground;
        this.canvasMiddle = simpleRPG.canvasMiddle;
        this.canvasBattle = simpleRPG.canvasBattle;
        this.gameLoopManager = simpleRPG.gameLoopManager;
        this.mainHUD = simpleRPG.mainHUD;
        this.world = simpleRPG.getWorld();
        this.keyHandler = simpleRPG.keyHandler;
=======
    public BattleView(SimpleRPG simpleRPG, Enemy enemy){
        super(simpleRPG);
        clearPane();

        Scene theScene = simpleRPG.theScene;
        AnchorPane mainPane = simpleRPG.mainPane;
        Canvas canvasBackground = simpleRPG.canvasBackground;
        Canvas canvasMiddle = simpleRPG.canvasMiddle;
        Canvas canvasBattle = simpleRPG.canvasBattle;
        HUD mainHUD = simpleRPG.mainHUD;
        Map world = simpleRPG.getWorld();
        KeyHandler keyHandler = simpleRPG.keyHandler;
>>>>>>> Stashed changes


        theScene.setOnKeyPressed(keyHandler);
        theScene.setOnKeyReleased(keyHandler);
        mainPane.getChildren().add(canvasBackground);
        mainPane.getChildren().add(canvasMiddle);
        mainPane.getChildren().add(canvasBattle);
        mainPane.getChildren().add(mainHUD.getHUD());

        BattleMap battleMap = new BattleMap(simpleRPG, this, (new File("./assets/test/battle_map.png")).getAbsolutePath(),
                new Enemy((World) simpleRPG.getWorld(), simpleRPG, SCREEN_WIDTH/5-16, SCREEN_HEIGHT/2-40, "Enemy",
                        (new File("./assets/test/enemy")).getAbsolutePath(),
                        1, 5, 100, 100, 100, 100, 15, 0));

        simpleRPG.setBattleMap(battleMap);
        simpleRPG.setWorld(battleMap);

        createScreenElements();
<<<<<<< Updated upstream
=======
        GameLoopManager gameLoopManager = simpleRPG.getGameLoopManager();
>>>>>>> Stashed changes
        gameLoopManager.start();
    }

    public void createScreenElements(){
        addSubSceneToPane(openBattleOption);
        addSubSceneToPane(openInventory);
        addSubSceneToPane(openSkill);
        addSubSceneToPane(openGameOver);

//        showSubScene(openBattleOption);
    }

}
