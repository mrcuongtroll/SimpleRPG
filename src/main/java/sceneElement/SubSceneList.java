package sceneElement;

import combat.action.Action;
import combat.action.NormalAttack;
import combat.action.Rest;
import combat.status_effect.OvertimeStatusEffect;
import entity.Character;
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
import saveload.SaveLoad;
import views.BattleView;
import views.StartScreenView;
import views.View;
import world.BattleMap;
import world.World;

import java.io.File;
import java.util.ArrayList;

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
    public static GameSubScene[] openSkill;
    public static GameSubScene openGameOver;
    public static GameSubScene openDialog;
    public static Action[] actionList;
    public static GameButton[][] gameButtonList = new GameButton[3][4];

    public SubSceneList(SimpleRPG simpleRPG) {
        Inventory.items.addAll(new HealthPotion(1),new ManaPotion(1));
        SubSceneList.simpleRPG = simpleRPG;
        openSetting = createSettingScene();
        openBattleOption = createBattleOptionScene();
        openInventory = createInventoryScene();
        openCredit = createCreditScene();
        openGameOver = createGameOverScene();
//        openDialog = createDialogScene("Test dialog");
    }
    public static void updateSkillScene(){
        openSkill = new GameSubScene[BattleMap.playerTeam.size()];
        for (int i = 0; i<BattleMap.playerTeam.size();i++){
            openSkill[i] = createSkillOptionScene(BattleMap.playerTeam.get(i), i);
        }
    }
    public void updateInventory(){
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
                            view.cleanUpScene();
                            currentShowingView.showSubScene(createChoosePlayerScene(current));
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
        openCredit = new GameSubScene(600, 300, 340, 210, "Horizontal", "/assets/test/menuBackground/rectangle.png");
        return openCredit;
    }

    private GameSubScene createGameOverScene() {
        openGameOver = new GameSubScene(600, 300, 340, 210, "Horizontal", "/assets/test/menuBackground/rectangle.png");
        openGameOver.addText("You died", BROWN, 40, 400, 100, 100, 30);
        GameButton btnGoHome = new GameButton("Back to menu", 100, 50);
        GameButton btnRetry = new GameButton("Try again", 100, 50);

        btnGoHome.setOnAction(event ->  {view.cleanUpScene(); simpleRPG.mainPane.getChildren().clear(); openView(new StartScreenView(simpleRPG));});
        btnRetry.setOnAction(event -> {
            view.cleanUpScene();
            simpleRPG.mainPane.getChildren().clear(); openView(new BattleView(simpleRPG, new Enemy((World) simpleRPG.getWorld(), simpleRPG, SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                "/assets/test/enemy",
                1, 5, 15, 0, 100, 100, 100, 100)));
        });
        openGameOver.addButton(btnGoHome, 150, 150);
        openGameOver.addButton(btnRetry, 350, 150);
        return openGameOver;
    }

    private GameSubScene createInventoryScene() {
        GameSubScene openInventory = new GameSubScene(400, 400, 440, 160, "Horizontal", "/assets/test/menuBackground/square.png");
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
                                                      view.cleanUpScene();
                                                      currentShowingView.showSubScene(createChoosePlayerScene(current));
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

        GameSubScene openSetting = new GameSubScene(600, 300, 340, 210, "Horizontal", "/assets/test/menuBackground/rectangle.png");

        openSetting.addText("Options", BROWN, 15, 200, 50, 200, 30);
        GameButton btnGoHome = new GameButton("Back to menu", 100, 50);
        GameButton btnSave = new GameButton("Save", 100, 50);
        GameButton btnLoad = new GameButton("Load", 100, 50);
        btnGoHome.setOnAction(event ->  {view.cleanUpScene(); simpleRPG.mainPane.getChildren().clear(); openView(new StartScreenView(simpleRPG));});
        btnSave.setOnAction(event ->  {
            SaveLoad.saveState(simpleRPG);
            System.out.println("Success save...");
        });
        btnLoad.setOnAction(event ->  {
            simpleRPG.setPlayer(SaveLoad.loadPlayer(simpleRPG));
            simpleRPG.setWorld(SaveLoad.loadWorld(simpleRPG));
            System.out.println("Success load...");
        });
        openSetting.addButton(btnGoHome, 100, 100);
        openSetting.addButton(btnSave, 250, 100);
        openSetting.addButton(btnLoad, 400, 100);

        return openSetting;
    }

    private GameSubScene createBattleOptionScene(){
        GameSubScene openBattleOption = new GameSubScene(1100, 200, 100, 470, "Vertical", "/assets/test/menuBackground/long_square.png");

//        openBattleOption.addText("Choose your action", 200, 50);
        GameButton btnFight= new GameButton("Fight", 100, 50);
        GameButton btnSurrender = new GameButton("Run", 100, 50);
        GameButton btnDoNothing = new GameButton("Rest", 100, 50);
        GameButton btnItems = new GameButton("Inventory", 100, 50);

        btnSurrender.setOnAction(event -> {view.cleanUpScene();
            BattleMap.quitBattle();
        });

        btnFight.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openSkill[BattleMap.playerTeam.indexOf(BattleMap.currentTurnChar)]);
        });

        btnItems.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openInventory);
        });

        btnDoNothing.setOnAction(event -> {
            new Rest().activate(BattleMap.currentTurnChar, BattleMap.currentTurnChar);
        });

        addButtonGrid(openBattleOption, 800, 25, 2, 2, 10, btnFight, btnSurrender, btnDoNothing, btnItems);

        return openBattleOption;
    }

    private static GameSubScene createSkillOptionScene(Character player, int playerIndex){
//        if (BattleMap.currentTurnChar == null) {
//            actionList = simpleRPG.getPlayer().getActionList();
//        } else {
//            actionList =  BattleMap.currentTurnChar.getActionList();
//        }
        Action[] actionList = player.getActionList();

        GameSubScene openSkill = new GameSubScene(400, 200, (SCREEN_WIDTH - 400)/2, 470, "Vertical", "/assets/test/menuBackground/rectangle.png");

        for (int i = 0;i<actionList.length ;i++){
            Action action = actionList[i];
            gameButtonList[playerIndex][i] = new GameButton(action.getName(), 100, 50);
            gameButtonList[playerIndex][i].setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    if (action.isTargetEnemy()){
                        currentShowingView.cleanUpScene();
                        currentShowingView.showSubScene(createChooseEnemyScene(action));
                    }
                    else{
                        currentShowingView.cleanUpScene();
                        currentShowingView.showSubScene(createChoosePlayerScene(action));
                    }
                }
            });
        }

        GameButton btnNormalAttack = new GameButton("Normal Attack", 100, 50);
        GameButton btnBack = new GameButton("Go back", 100, 50);

        btnNormalAttack.setOnAction(event -> {
            currentShowingView.cleanUpScene();
            currentShowingView.showSubScene(createChooseEnemyScene(new NormalAttack()));
        });

        btnBack.setOnAction(event -> {
            view.cleanUpScene();
            currentShowingView.showSubScene(openBattleOption);
        });

        addButtonGrid(openSkill, 25, 25, 2, 3, 10,
                gameButtonList[playerIndex][0] ,gameButtonList[playerIndex][1],
                gameButtonList[playerIndex][2], gameButtonList[playerIndex][3], btnNormalAttack, btnBack);
        return openSkill;
    }
    public static void checkManaRequirement(){
        ArrayList<Character> players = BattleMap.playerTeam;
        for (int i = 0;i<players.size() ;i++) {
            for (int j = 0; j < 4; j++) {
                Character player = players.get(i);
                Action action = player.getActionList()[j];
                if (player.getManaPoint() < action.getCost()) {
                    gameButtonList[i][j].setOpacity(0.3);
                    gameButtonList[i][j].setOnAction(event -> {
                    });
                } else if (action.isTargetEnemy()) {
                    gameButtonList[i][j].setOpacity(1);
                    gameButtonList[i][j].setOnAction(event -> {
                        view.cleanUpScene();
                        currentShowingView.showSubScene(createChooseEnemyScene(action));
                    });
                } else {
                    gameButtonList[i][j].setOpacity(1);
                    gameButtonList[i][j].setOnAction(event -> {
                        view.cleanUpScene();
                        currentShowingView.showSubScene(createChoosePlayerScene(action));
                    });
                }
            }
        }
    }
    public static GameSubScene createDialogScene(String... texts){
        ArrayList<GameSubScene> dialogScenes = new ArrayList<GameSubScene>();
        ArrayList<GameButton> dialogButtons = new ArrayList<GameButton>();

        for (int i = 0; i<texts.length; i++) {
            dialogScenes.add(new GameSubScene(1100, 200, 100, 470, "Vertical", "/assets/test/menuBackground/long_square.png"));
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

    public static GameSubScene createChooseEnemyScene(Action action){
        GameSubScene dialogScene = new GameSubScene(1100, 200, 100, 470, "Vertical", "/assets/test/menuBackground/long_square.png");
        ArrayList<GameButton> dialogButtons = new ArrayList<>();
        for (int i = 0; i < BattleMap.enemyTeam.size() ;i++){
            dialogButtons.add(new GameButton(BattleMap.enemyTeam.get(i).getName(), 100, 50));
            int enemyIndex = i;
            dialogButtons.get(i).setOnAction(event -> {
                action.activate(BattleMap.currentTurnChar, BattleMap.enemyTeam.get(enemyIndex));
            });
        }
        addButtonGrid(dialogScene, 800, 20, 2, 2, 10, dialogButtons);
        dialogScene.addText("Choose character to perform " + action.getName(), BROWN, 16, 800, 300, 0, 0);
        view.addSubSceneToPane(dialogScene);
        return dialogScene;
    }
    public static GameSubScene createChoosePlayerScene(Action action){
        GameSubScene dialogScene = new GameSubScene(1100, 200, 100, 470, "Vertical", (new File("./assets/test/menuBackground/long_square.png")).getAbsolutePath());
        ArrayList<GameButton> dialogButtons = new ArrayList<>();
        for (int i = 0; i < BattleMap.playerTeam.size() ;i++){
            dialogButtons.add(new GameButton(BattleMap.playerTeam.get(i).getName(), 100, 50));
            int playerIndex = i;
            dialogButtons.get(i).setOnAction(event -> {
                action.activate(BattleMap.currentTurnChar, BattleMap.playerTeam.get(playerIndex));
            });
        }
        addButtonGrid(dialogScene, 800, 20, 2, 2, 10, dialogButtons);
        dialogScene.addText("Choose character to perform " + action.getName(), BROWN, 16, 800, 300, 0, 0);
        view.addSubSceneToPane(dialogScene);
        return dialogScene;
    }
    public GameSubScene createChoosePlayerScene(Consumable consumable){
        GameSubScene dialogScene = new GameSubScene(1100, 200, 100, 470, "Vertical", "/assets/test/menuBackground/long_square.png");
        ArrayList<GameButton> dialogButtons = new ArrayList<>();
        for (int i = 0; i < BattleMap.playerTeam.size() ;i++){
            dialogButtons.add(new GameButton(BattleMap.playerTeam.get(i).getName(), 100, 50));
            int playerIndex = i;
            dialogButtons.get(i).setOnAction(event -> {
                consumable.activateInBattle(BattleMap.playerTeam.get(playerIndex),this);
            });
        }
        addButtonGrid(dialogScene, 800, 20, 2, 2, 10, dialogButtons);
        dialogScene.addText("Choose character to perform " + consumable.getName(), BROWN, 16, 800, 300, 0, 0);
        view.addSubSceneToPane(dialogScene);
        return dialogScene;
    }
    public static GameSubScene createDialogSceneStatusEffect(boolean isPlayerTurn, ArrayList<OvertimeStatusEffect> effects){
        ArrayList<GameSubScene> dialogScenes = new ArrayList<GameSubScene>();
        ArrayList<GameButton> dialogButtons = new ArrayList<GameButton>();
        ArrayList<OvertimeStatusEffect> effectsList = effects;
        effectsList.removeIf(x -> x == null);
        for (int i = 0; i<effectsList.size(); i++) {
            dialogScenes.add(new GameSubScene(1100, 200, 100, 470, "Vertical", "/assets/test/menuBackground/long_square.png"));
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
                            currentShowingView.showSubScene(SubSceneList.openBattleOption);
                            System.out.println("Player turn");
                        }
                    });
                }
                else {
                    dialogButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            view.cleanUpScene();
                            System.out.println("Enemy turn");
                            Enemy currentEnemy = (Enemy) BattleMap.currentTurnChar;
                            currentEnemy.randomAttack(BattleMap.playerTeam,BattleMap.enemyTeam);
                        }
                    });
                }
            } else{
                GameSubScene nextDialog = dialogScenes.get(i + 1);
                OvertimeStatusEffect nextEffect = effectsList.get(i+1);
                dialogButtons.get(i).setOnAction(event -> {
                    view.cleanUpScene();
                    nextEffect.applyOvertime(nextEffect.getCharacter());
                    BattleMap.showSkillEffect(nextEffect.getCharacter(), nextEffect.getCharacter(), nextEffect.getEffect(), false,"");
                    currentShowingView.showSubScene(nextDialog);
                });
            }
        }
        effectsList.get(0).applyOvertime(effectsList.get(0).getCharacter());
        BattleMap.showSkillEffect(effectsList.get(0).getCharacter(), effectsList.get(0).getCharacter(), effectsList.get(0).getEffect(), false,"");

        return dialogScenes.get(0);
    }

    private static void addButtonGrid(GameSubScene gameSubScene, int x, int y, int rows, int columns, int padding, GameButton... buttons){
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
    private static void addButtonGrid(GameSubScene gameSubScene, int x, int y, int rows, int columns, int padding, ArrayList<GameButton> buttons){
        GridPane gridPane = new GridPane();

        for (int i = 0; i < columns; i ++){
            for (int j = 0; j < rows; j ++){
                try{
                    Button btnCurrent = buttons.get(j + i* rows);
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
