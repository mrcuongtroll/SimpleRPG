package sceneElement;

import combat.action.NormalAttack;
import combat.effect.Heal;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.SimpleRPG;
import views.GameView;
import views.StartScreenView;
import views.View;
import world.BattleMap;

import java.io.File;

import static javafx.scene.paint.Color.BROWN;
import static main.SimpleRPG.SCREEN_WIDTH;
import static views.View.currentShowingView;

public class SubSceneList {

    private static SimpleRPG simpleRPG;
    public static View view;
    public static GameSubScene openSetting;
    public static GameSubScene openBattleOption;
    public static GameSubScene openCredit;
    public static GameSubScene openInventory;
    public static GameSubScene openSkill;

    public SubSceneList(SimpleRPG simpleRPG) {
        SubSceneList.simpleRPG = simpleRPG;
        openSetting = createSettingScene();
        openBattleOption = createBattleOptionScene();
        openInventory = createInventoryScene();
        openCredit = createCreditScene();
        openSkill = createSkillOptionScene();
    }

    public static void openView(View newView){
        view = newView;
    }

    private GameSubScene createCreditScene() {
        openCredit = new GameSubScene(600, 300, 340, 210, "Horizontal", (new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath());
        return openCredit;
    }

    private GameSubScene createInventoryScene() {
        GameSubScene openInventory = new GameSubScene(400, 400, 440, 160, "Horizontal", (new File("./assets/test/menuBackground/square.png")).getAbsolutePath());
        openInventory.addText("Inventory", BROWN, 15, 200, 50, 100, 30);

        GridPane itemGrid = new GridPane();

        File dir = new File("./assets/test/item");
        File[] listOfFile = dir.listFiles();

        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j ++){
                int index = j + i*3;
                Image testImage = new Image(listOfFile != null ? listOfFile[index].getAbsolutePath() : null, 50, 50, false, true);
                ImageView imageView = new ImageView(testImage);
                imageView.setFocusTraversable(true);
                imageView.setOnMouseEntered(event -> imageView.setEffect(new DropShadow()));
                imageView.setOnMouseExited(event -> imageView.setEffect(null));
                imageView.setOnMouseClicked(event -> System.out.println("Item " + index));
                GridPane.setMargin(imageView, new Insets(10, 10, 10, 10));
                itemGrid.add(imageView, i, j);
            }
        }

        GameButton btnBack = new GameButton("Go back", 100, 50);
        openInventory.addButton(btnBack, 260, 180);

        btnBack.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openBattleOption);
        });

        openInventory.addGrid(itemGrid, 30, 90);
        return openInventory;
    }


    private GameSubScene createSettingScene(){

        GameSubScene openSetting = new GameSubScene(600, 300, 340, 210, "Horizontal", (new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath());

        openSetting.addText("Paused", BROWN, 15, 200, 50, 200, 30);
        GameButton btnGoHome = new GameButton("Back to menu", 100, 50);
        GameButton btnTest1 = new GameButton("Button 2", 100, 50);
        GameButton btnTest2 = new GameButton("Button 3", 100, 50);
        btnGoHome.setOnAction(event ->  {view.cleanUpScene(); simpleRPG.mainPane.getChildren().clear(); openView(new StartScreenView(simpleRPG));});

        openSetting.addButton(btnGoHome, 100, 100);
        openSetting.addButton(btnTest1, 250, 100);
        openSetting.addButton(btnTest2, 400, 100);

        return openSetting;
    }

    private GameSubScene createBattleOptionScene(){

        GameSubScene openBattleOption = new GameSubScene(1100, 200, 100, 470, "Vertical", (new File("./assets/test/menuBackground/long_square.png")).getAbsolutePath());

//        openBattleOption.addText("Choose your action", 200, 50);
        GameButton btnFight= new GameButton("Lets fuck", 100, 50);
        GameButton btnSurrender = new GameButton("Why are you running?", 100, 50);
        GameButton btnDoNothing = new GameButton("Do nothing", 100, 50);
        GameButton btnItems = new GameButton("Inventory", 100, 50);

        btnSurrender.setOnAction(event -> {view.cleanUpScene();
            new GameView(simpleRPG);
        });

        btnFight.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openSkill);
        });

        btnItems.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openInventory);
        });

        btnDoNothing.setOnAction(event -> {

            simpleRPG.player.increaseHealthPoint(10);
            simpleRPG.player.increaseManaPoint(10);
            BattleMap.showSkillEffect(simpleRPG.getPlayer(), simpleRPG.getPlayer(), new Heal());

        });

        addButtonGrid(openBattleOption, 800, 25, 2, 2, 10, btnFight, btnSurrender, btnDoNothing, btnItems);

        return openBattleOption;
    }

    private GameSubScene createSkillOptionScene(){
        GameSubScene openSkill = new GameSubScene(400, 200, (SCREEN_WIDTH - 400)/2, 470, "Vertical", (new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath());

        GameButton btnSkill1= new GameButton("Skill 1", 100, 50);
        GameButton btnSkill2 = new GameButton("Skill 2", 100, 50);
        GameButton btnSkill3 = new GameButton("Skill 3", 100, 50);
        GameButton btnSkill4 = new GameButton("Skill 4", 100, 50);
        GameButton btnNormalAttack = new GameButton("Normal Attack", 100, 50);
        GameButton btnBack = new GameButton("Go back", 100, 50);

        btnNormalAttack.setOnAction(event -> {
            (new NormalAttack()).activate(simpleRPG.getPlayer(), ((BattleMap) simpleRPG.getWorld()).getEnemy());
        });

        btnBack.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openBattleOption);
        });

        addButtonGrid(openSkill, 25, 25, 2, 3, 10, btnSkill1, btnSkill2, btnSkill3, btnSkill4, btnNormalAttack, btnBack);
        return openSkill;
    }

    private void addButtonGrid(GameSubScene gameSubScene, int x, int y, int rows, int columns, int padding, Button... buttons){
        GridPane gridPane = new GridPane();

        for (int i = 0; i < columns; i ++){
            for (int j = 0; j < rows; j ++){
                try{
                    Button btnCurrent = buttons[j + i* rows];
                    GridPane.setMargin(btnCurrent, new Insets(padding, padding, padding, padding));
                    gridPane.add(btnCurrent, i, j);
                } catch (Exception e) {
                    break;
                }
            }
        }
        gameSubScene.addGrid(gridPane, x, y);
    }


}
