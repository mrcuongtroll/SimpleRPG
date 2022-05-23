package views;

import entity.Enemy;
import main.SimpleRPG;
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
        createBackground((new File("./assets/test/old map.png")).getAbsolutePath(), false);
    }

    private void CreateScreenElements(){

//        createLogo((new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath(),700, 300, 400, 250 );
//        createText("Simple\nRPG", WHITE, 90, 700, 300, 400, 250);

        addSubSceneToPane(openCredit, openSetting);

        GameButton startGameButton = createBlankButton("Start Game",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y);
        startGameButton.setOnAction(event -> {
            cleanUpScene();
            new GameView(simpleRPG);
        });
        GameButton startBattleButton = createBlankButton("Start Battle",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 100);
        startBattleButton.setOnAction(event -> {
            view.cleanUpScene();
            new BattleView(simpleRPG, new Enemy((World) simpleRPG.getWorld(), simpleRPG, SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                    (new File("./assets/test/enemy")).getAbsolutePath(),
                    1, 5, 100, 100, 100, 100, 20, 0));
        });
        createSubSceneButton("Setting", openSetting,MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 200);
        createSubSceneButton("Credit", openCredit,MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 300);
        GameButton exitButton = createBlankButton("Exit",MENU_BUTTON_START_WIDTH, MENU_BUTTON_START_HEIGHT, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + 400);
        exitButton.setOnAction(event -> simpleRPG.mainStage.close());
    }
}
