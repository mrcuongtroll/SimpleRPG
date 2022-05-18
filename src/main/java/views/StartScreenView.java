package views;

import main.SimpleRPG;
import sceneElement.GameButton;

import java.io.File;

import static sceneElement.SubSceneList.openCredit;
import static sceneElement.SubSceneList.openSetting;

public class StartScreenView extends View{

    private final static int MENU_BUTTON_START_X = 150;
    private final static int MENU_BUTTON_START_Y = 150;
    private final static int MENU_BUTTON_START_HEIGHT = 60;
    private final static int MENU_BUTTON_START_WIDTH = 150;

    private SimpleRPG simpleRPG;

    public StartScreenView(SimpleRPG simpleRPG){
        super(simpleRPG);
        this.simpleRPG = simpleRPG;
        CreateScreenElements();
        createBackground((new File("./assets/test/old map.png")).getAbsolutePath(), false);
    }

    private void CreateScreenElements(){
        addSubSceneToPane(openCredit, openSetting);
        GameButton startGameButton = createBlankButton("Start Game",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y);
        startGameButton.setOnAction(event -> new GameView(simpleRPG));
        GameButton startBattleButton = createBlankButton("Start Battle",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 100);
        startBattleButton.setOnAction(event -> new BattleView(simpleRPG));
        createSubSceneButton("Setting", openSetting,MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 200);
        createSubSceneButton("Credit", openCredit,MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 300);
        GameButton exitButton = createBlankButton("Exit",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 400);
        exitButton.setOnAction(event -> simpleRPG.mainStage.close());
    }
}
