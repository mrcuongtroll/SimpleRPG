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
import world.World;

import java.io.File;

import static main.SimpleRPG.SCREEN_HEIGHT;
import static main.SimpleRPG.SCREEN_WIDTH;
import static sceneElement.SubSceneList.*;

public class BattleView  extends View{

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
        this.world = simpleRPG.world;
        this.keyHandler = simpleRPG.keyHandler;


        theScene.setOnKeyPressed(keyHandler);
        theScene.setOnKeyReleased(keyHandler);
        mainPane.getChildren().add(canvasBackground);
        mainPane.getChildren().add(canvasMiddle);
        mainPane.getChildren().add(canvasBattle);
        mainPane.getChildren().add(mainHUD.getHUD());

        BattleMap battleMap = new BattleMap(simpleRPG, (new File("./assets/test/battle_map.png")).getAbsolutePath(),
                new Enemy((World) simpleRPG.getWorld(), simpleRPG, SCREEN_WIDTH/5-16, SCREEN_HEIGHT/2-40, "Enemy",
                        (new File("./assets/test/enemy")).getAbsolutePath(),
                        1, 100, 100, 10, 10));

        simpleRPG.setBattleMap(battleMap);
        simpleRPG.setWorld(battleMap);

        CreateScreenElements();
        gameLoopManager.start();
    }

    public void CreateScreenElements(){

        addSubSceneToPane(openBattleOption);
        addSubSceneToPane(openInventory);
        addSubSceneToPane(openSkill);
        showSubScene(openBattleOption);

    }

}
