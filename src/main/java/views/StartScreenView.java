package views;

import entity.Enemy;
import main.SimpleRPG;
import saveload.SaveLoad;
import sceneElement.GameButton;
import world.World;

import java.io.File;

import static sceneElement.SubSceneList.*;

public class StartScreenView extends View{

    private final static int MENU_BUTTON_START_X = 150;
    private final static int MENU_BUTTON_START_Y = 150;
    private final static int MENU_BUTTON_START_HEIGHT = 60;
    private final static int MENU_BUTTON_START_WIDTH = 150;

    private final SimpleRPG simpleRPG;

    public StartScreenView(SimpleRPG simpleRPG){
        super(simpleRPG);
        this.simpleRPG = simpleRPG;
        CreateScreenElements();
        createBackground(String.valueOf(getClass().getResource("/assets/test/titleScreen.png")), false);
    }

    private void CreateScreenElements(){

//        createLogo((new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath(),700, 300, 400, 250 );
//        createText("Simple\nRPG", WHITE, 90, 700, 300, 400, 250);

        addSubSceneToPane(openCredit, openSetting);

        GameButton startGameButton = createBlankButton("New Game",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y);
        startGameButton.setOnAction(event -> {
            cleanUpScene();
            new GameView(simpleRPG, false, true);
        });
        GameButton startBattleButton = createBlankButton("Start Battle",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 100);
        startBattleButton.setOnAction(event -> {
            view.cleanUpScene();
            new BattleView(simpleRPG, new Enemy((World) simpleRPG.getWorld(), simpleRPG, SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Boss 2",
                    "/assets/test/boss2",
                    1, 5, 20, 0, 100, 100, 100, 100));
        });

        GameButton loadGameButton = createBlankButton("Load Game",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 200);
        loadGameButton.setOnAction(event -> {
            cleanUpScene();
            new GameView(simpleRPG, false, false);
        });
        if (!(SaveLoad.isWorldSaveFileExist() && SaveLoad.isPlayerSaveFileExist())) {
            loadGameButton.disableButton();
        }
        createSubSceneButton("Credit", openCredit,MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 300);
        GameButton exitButton = createBlankButton("Exit",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 400);
        exitButton.setOnAction(event -> simpleRPG.mainStage.close());
    }
}
