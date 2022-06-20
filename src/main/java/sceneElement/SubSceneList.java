package sceneElement;

import combat.action.Action;
import combat.action.NormalAttack;
import combat.action.Rest;
import combat.status_effect.OvertimeStatusEffect;
import entity.Enemy;
import entity.Inventory;
import entity.item.Item;
import entity.item.consumable.Consumable;
import entity.item.consumable.HealthPotion;
import entity.item.consumable.ManaPotion;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import main.SimpleRPG;
import views.BattleView;
import views.GameView;
import views.StartScreenView;
import views.View;
import world.BattleMap;
import world.World;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
    public static GameSubScene openGameOver;
    public static GameSubScene openDialog;
    public static Action[] actionList;
    public static GameButton[] gameButtonList ;

    public SubSceneList(SimpleRPG simpleRPG) {
        Inventory.items.addAll(new HealthPotion(1),new ManaPotion(1));
        SubSceneList.simpleRPG = simpleRPG;
        openSetting = createSettingScene();
        openBattleOption = createBattleOptionScene();
        openInventory = createInventoryScene();
        openCredit = createCreditScene();
        openSkill = createSkillOptionScene();
        openGameOver = createGameOverScene();

//        openDialog = createDialogScene("Test dialog");
    }
    public static void updateInventory(){
        openInventory.getChildren().clear();
        openInventory.addText("Inventory", BROWN, 15, 200, 50, 100, 30);

        GridPane itemGrid = new GridPane();

        FilteredList<Item> consumables = Inventory.filterConsumables();

        int columnSize = 3;


        for (int i = 0; i < Math.floor(consumables.size()/3)+1; i ++) {
            System.out.println(i);
            if (i == Math.floor(consumables.size() / 3)) {
                columnSize = consumables.size() % 3;
            }
            for (int j = 0; j < columnSize; j++) {
                int index = j + i * 3;
                Consumable current = (Consumable) consumables.get(index);
                Image testImage = new Image(current.getIconPath(), 50, 50, false, true);
                ImageView imageView = new ImageView(testImage);
                imageView.getStyleClass().add("imageView");
                imageView.setFocusTraversable(true);
                imageView.setOnMouseEntered(event -> imageView.requestFocus());
                imageView.setOnMouseExited(event -> imageView.setEffect(null));
                imageView.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent ke) {
                        if (ke.getCode().isWhitespaceKey()) {
                            current.activateInBattle(SubSceneList.simpleRPG.getPlayer(),
                                    ((BattleMap) simpleRPG.getWorld()).getEnemy());
                        }
                    }
                });


                GridPane.setMargin(imageView, new Insets(10, 10, 10, 10));
                itemGrid.add(imageView, j, i);
            }
        }
        GameButton btnBack = new GameButton("Go back", 100, 50);
        openInventory.addButton(btnBack, 260, 180);

        btnBack.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openBattleOption);
        });

        openInventory.addGrid(itemGrid, 30, 90);
    }
    public static void openView(View newView){
        if (view.currentShowingScene!=null){
            view.currentShowingScene.disableButtons();
        }

        view = newView;
    }


    private GameSubScene createCreditScene() {
        openCredit = new GameSubScene(600, 300, 340, 210, "Horizontal", (new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath());
        return openCredit;
    }

    private GameSubScene createGameOverScene() {
        openGameOver = new GameSubScene(600, 300, 340, 210, "Horizontal", (new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath());
        openGameOver.addText("You died", BROWN, 40, 400, 100, 100, 30);
        GameButton btnGoHome = new GameButton("Back to menu", 100, 50);
        GameButton btnRetry = new GameButton("Try again", 100, 50);

        btnGoHome.setOnAction(event ->  {view.cleanUpScene(); simpleRPG.mainPane.getChildren().clear(); openView(new StartScreenView(simpleRPG));});
        btnRetry.setOnAction(event -> {
            view.cleanUpScene();
            simpleRPG.mainPane.getChildren().clear(); openView(new BattleView(simpleRPG, new Enemy((World) simpleRPG.getWorld(), simpleRPG, SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 15, 0, 100, 100, 100, 100)));
        });
        openGameOver.addButton(btnGoHome, 150, 150);
        openGameOver.addButton(btnRetry, 350, 150);
        return openGameOver;
    }

    private static GameSubScene createInventoryScene() {
        GameSubScene openInventory = new GameSubScene(400, 400, 440, 160, "Horizontal", (new File("./assets/test/menuBackground/square.png")).getAbsolutePath());
        openInventory.addText("Inventory", BROWN, 15, 200, 50, 100, 30);

        GridPane itemGrid = new GridPane();

        FilteredList<Item> consumables = Inventory.filterConsumables();

        int columnSize = 3;


        for (int i = 0; i < Math.floor(consumables.size()/3)+1; i ++){
            System.out.println(i);
            if (i==Math.floor(consumables.size()/3)){
                columnSize = consumables.size()%3;
            }
            for (int j = 0; j < columnSize; j ++){
                int index = j + i*3;
                Consumable current = (Consumable) consumables.get(index);
                Image testImage = new Image(current.getIconPath(), 50, 50, false, true);
                ImageView imageView = new ImageView(testImage);
                imageView.getStyleClass().add("imageView");
                imageView.setFocusTraversable(true);
                imageView.setOnMouseEntered(event -> imageView.requestFocus());
                imageView.setOnMouseExited(event -> imageView.setEffect(null));
                imageView.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                              public void handle(KeyEvent ke) {
                                                  if (ke.getCode().isWhitespaceKey()){
                                                      current.activateInBattle(SubSceneList.simpleRPG.getPlayer(),
                                                              ((BattleMap) simpleRPG.getWorld()).getEnemy());
                                                  }
                                              }
                                          });


                GridPane.setMargin(imageView, new Insets(10, 10, 10, 10));
                itemGrid.add(imageView, j, i);
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
        GameButton btnFight= new GameButton("Fight", 100, 50);
        GameButton btnSurrender = new GameButton("Run", 100, 50);
        GameButton btnDoNothing = new GameButton("Rest", 100, 50);
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
            new Rest().activate(simpleRPG.getPlayer(), simpleRPG.getPlayer());

        });

        addButtonGrid(openBattleOption, 800, 25, 2, 2, 10, btnFight, btnSurrender, btnDoNothing, btnItems);

        return openBattleOption;
    }

    private GameSubScene createSkillOptionScene(){
        actionList =  simpleRPG.getPlayer().getActionList();
        gameButtonList = new GameButton[actionList.length];

        GameSubScene openSkill = new GameSubScene(400, 200, (SCREEN_WIDTH - 400)/2, 470, "Vertical", (new File("./assets/test/menuBackground/rectangle.png")).getAbsolutePath());

        for (int i = 0;i<actionList.length ;i++){
            Action action = actionList[i];
            gameButtonList[i] = new GameButton(action.getName(), 100, 50);
            gameButtonList[i].setOnAction(event -> {
                action.activate(simpleRPG.getPlayer(), ((BattleMap) simpleRPG.getWorld()).getEnemy());
            });
        }
        GameButton btnNormalAttack = new GameButton("Normal Attack", 100, 50);
        GameButton btnBack = new GameButton("Go back", 100, 50);

        btnNormalAttack.setOnAction(event -> {
            (new NormalAttack()).activate(simpleRPG.getPlayer(), ((BattleMap) simpleRPG.getWorld()).getEnemy());
        });

        btnBack.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openBattleOption);
        });

        addButtonGrid(openSkill, 25, 25, 2, 3, 10, gameButtonList[0] ,gameButtonList[1], gameButtonList[2], gameButtonList[3], btnNormalAttack, btnBack);
        return openSkill;
    }
    public static void checkManaRequirement(){
        for (int i = 0;i<actionList.length ;i++){
            Action action = actionList[i];
            if (simpleRPG.getPlayer().getManaPoint() < action.getCost()){
                gameButtonList[i].setOpacity(0.3);
                gameButtonList[i].setOnAction(event -> {});
            }
            else{
                gameButtonList[i].setOpacity(1);
                gameButtonList[i].setOnAction(event -> {
                    action.activate(simpleRPG.getPlayer(), ((BattleMap) simpleRPG.getWorld()).getEnemy());
                });
            }
        }
    }
    public static GameSubScene createDialogScene(String... texts){
        ArrayList<GameSubScene> dialogScenes = new ArrayList<GameSubScene>();
        ArrayList<GameButton> dialogButtons = new ArrayList<GameButton>();

        for (int i = 0; i<texts.length; i++) {
            dialogScenes.add(new GameSubScene(1100, 200, 100, 470, "Vertical", (new File("./assets/test/menuBackground/long_square.png")).getAbsolutePath()));
            dialogButtons.add(new GameButton("Continue", 100, 50));
            dialogScenes.get(i).addButton(dialogButtons.get(i), 900, 100);
            dialogScenes.get(i).addText(texts[i], BROWN, 20, 1100, 200, 0, 0);
            view.addSubSceneToPane(dialogScenes.get(dialogScenes.size() - 1));
        }
        for (int i = 0; i<texts.length; i++) {
            if (i == texts.length - 1) {
                dialogButtons.get(i).setOnAction(event -> {
                    BattleMap.turnDecide();
                });
            } else{
                GameSubScene nextDialog = dialogScenes.get(i + 1);
                dialogButtons.get(i).setOnAction(event -> {
                    view.cleanUpScene();
                    currentShowingView.showSubScene(nextDialog);
                });
            }
        }
        return dialogScenes.get(0);
    }
    public static GameSubScene createDialogSceneStatusEffect(boolean isPlayerTurn, OvertimeStatusEffect... effects){
        ArrayList<GameSubScene> dialogScenes = new ArrayList<GameSubScene>();
        ArrayList<GameButton> dialogButtons = new ArrayList<GameButton>();
        ArrayList<OvertimeStatusEffect> effectsList = new ArrayList<OvertimeStatusEffect> (Arrays.stream(effects).toList());
        effectsList.removeIf(x -> x == null);
        for (int i = 0; i<effectsList.size(); i++) {
            dialogScenes.add(new GameSubScene(1100, 200, 100, 470, "Vertical", (new File("./assets/test/menuBackground/long_square.png")).getAbsolutePath()));
            dialogButtons.add(new GameButton("Continue", 100, 50));
            dialogScenes.get(i).addButton(dialogButtons.get(i), 900, 100);
            dialogScenes.get(i).addText(effectsList.get(i).getText(effectsList.get(i).getCharacter()), BROWN, 20, 1100, 200, 0, 0);
            view.addSubSceneToPane(dialogScenes.get(dialogScenes.size() - 1));
        }
        for (int i = 0; i<effectsList.size(); i++) {
            if (i == effectsList.size() - 1) {
                if (isPlayerTurn) {
                    dialogButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            view.cleanUpScene();
                            view.showSubScene(SubSceneList.openBattleOption);
                            System.out.println("Player turn");
                        }
                    });
                }
                else {
                    dialogButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            view.cleanUpScene();
                            System.out.println("Enemy turn");
                            BattleMap.enemy.randomAttack(BattleMap.player);
                        }
                    });
                }
            } else{
                GameSubScene nextDialog = dialogScenes.get(i + 1);
                OvertimeStatusEffect nextEffect = effectsList.get(i+1);
                dialogButtons.get(i).setOnAction(event -> {
                    view.cleanUpScene();
                    nextEffect.applyOvertime(nextEffect.getCharacter());
                    BattleMap.showSkillEffect(nextEffect.getCharacter(), nextEffect.getEffect(), false,"");
                    currentShowingView.showSubScene(nextDialog);
                });
            }
        }
        effectsList.get(0).applyOvertime(effectsList.get(0).getCharacter());
        BattleMap.showSkillEffect(effectsList.get(0).getCharacter(), effectsList.get(0).getEffect(), false,"");

        return dialogScenes.get(0);
    }

    private void addButtonGrid(GameSubScene gameSubScene, int x, int y, int rows, int columns, int padding, GameButton... buttons){
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
        for (GameButton gameButton:buttons){
            gameSubScene.getButtons().add(gameButton);
        }

        gameSubScene.addGrid(gridPane, x, y);
    }


}
