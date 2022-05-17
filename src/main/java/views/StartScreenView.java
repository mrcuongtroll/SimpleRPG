package views;

import main.SimpleRPG;
import sceneElement.GameButton;
import sceneElement.GameSubScene;

import java.io.File;

public class StartScreenView extends View{

    private final static int MENU_BUTTON_START_X = 150;
    private final static int MENU_BUTTON_START_Y = 225;
    public GameSubScene openCredit;
    public GameSubScene openSetting;
    private SimpleRPG simpleRPG;

    public StartScreenView(SimpleRPG simpleRPG){
        super(simpleRPG);
        this.simpleRPG = simpleRPG;
        openCredit = new GameSubScene();
        openSetting = new GameSubScene();
        createSubScenes(openCredit, openSetting);
        CreateScreenElements();
        createBackground((new File("./assets/test/old map.png")).getAbsolutePath(), false);
    }


    private void CreateScreenElements(){
        GameButton startButton = createBlankButton("Start", MENU_BUTTON_START_X, MENU_BUTTON_START_Y);
        startButton.setOnAction(event -> new GameView(simpleRPG));
        createSubSceneButton("Setting", openSetting, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 100);
        createSubSceneButton("Credit", openCredit, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 200);
        GameButton exitButton = createBlankButton("Exit", MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 300);
        exitButton.setOnAction(event -> simpleRPG.mainStage.close());
    }
}
