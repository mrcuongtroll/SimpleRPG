package main;

import entity.Armor;
import entity.Player;
import entity.Weapon;
import handler.KeyHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import loop.GameLoopManager;
import views.GameView;
import views.StartScreenView;
import world.World;

import java.io.File;
import java.io.IOException;

public class SimpleRPG extends Application {

    //Variables for starting screen
    private static final int HEIGHT = 720;
    private static final int WIDTH = 1280;
    public Stage mainStage;
    public GameView gameView;
    public StartScreenView startScreenView;


    //Current opening subscene
//    private GameSubScene currentShowingScene;
//    private final static int MENU_BUTTON_START_X = 150;
//    private final static int MENU_BUTTON_START_Y = 225;

    //Starting buttons and popup windows
//    List<GameButton> menuButtons;
//    private GameSubScene openCredit;
//    private GameSubScene openSetting;

    //Game variables
    public Canvas canvasBackground = new Canvas(1280, 720);
    public Canvas canvasMiddle = new Canvas(1280, 720);
    public World testWorld = new World(this, (new File("./assets/test/old map.png")).getAbsolutePath());
    public Player testPlayer = new Player(this, Player.X, Player.Y, "Player",
            (new File("./assets/test/player")).getAbsolutePath(), 1, 80, 100,
            new Weapon(10, 0, "example_armor.png"),
            new Armor(0, 20, "example_armor.png"));
    public GameLoopManager gameLoopManager = new GameLoopManager(this, testWorld, testPlayer);
    public KeyHandler keyHandler = new KeyHandler(this);

    public AnchorPane mainPane = new AnchorPane();
    public Scene theScene = new Scene(mainPane, WIDTH, HEIGHT);
    public HUD mainHUD = new HUD(this, testPlayer);

    public World getWorld() {
        return this.testWorld;
    }
    
//    public static void main(String[] args) {
//        launch(args);
//    }

//    private void createBackground() {
//        Image backgroundImage = new Image("D:/Programming stuff/Intellij/Intro 2 SE project/SimpleRPG/assets/test/old map.png", 1280, 720, false, false);
//        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
//        mainPane.setBackground(new Background(background));
//    }

//    private void CreateButtons() {
//        createStartButton();
//        createSettingButton();
//        createCreditButton();
//        createExitButton();
//    }

//    //Create setting button (Start screen)
//    private void createStartButton() {
//        GameButton newButton = new GameButton("Duma lets go!");
//        AddMenuButtons(newButton);
//        newButton.setOnAction(event -> startGame());
//    }
//
//    //Create exit button (Start screen)
//    private void createExitButton() {
//        GameButton newButton = new GameButton("I quit, this game sucks");
//        AddMenuButtons(newButton);
//        newButton.setOnAction(event -> mainStage.close());
//    }
//
//    //Create setting button (Start screen)
//    private void createSettingButton() {
//        GameButton newButton = new GameButton("Settings");
//        AddMenuButtons(newButton);
//        newButton.setOnAction(event -> showSubScene(openSetting));
//    }
//
//    //As if somebody want to know who make this game
//    private void createCreditButton() {
//        GameButton newButton = new GameButton("Credits");
//        AddMenuButtons(newButton);
//        newButton.setOnAction(event -> showSubScene(openCredit));
//    }
//
//    //Create pause button (During game)
//    private void createPauseButton() {
//        GameButton newButton = new GameButton("Pause");
//        newButton.setLayoutX(1100);
//        newButton.setLayoutY(20);
//        newButton.setOnAction(event -> showSubScene(openSetting));
//        mainPane.getChildren().add(newButton);
//    }
//
//    //Return to start screen
//    private void createHomeButton(AnchorPane pane) {
//        Button newButton = new GameButton("Go home");
//        newButton.setLayoutX(100);
//        newButton.setLayoutY(100);
//        newButton.setOnAction(event -> openHomeScreen());
//        pane.getChildren().add(newButton);
//    }

//    private void openHomeScreen() {
//        mainPane.getChildren().clear();
//        menuButtons.clear();
//        createLogo();
//        CreateButtons();
//        createSubScenes();
//        createBackground();
//    }

//    //Create columns of button and create a bit of space between them
//    private void AddMenuButtons(GameButton button) {
//        button.setLayoutX(MENU_BUTTON_START_X);
//        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
//        menuButtons.add(button);
//        mainPane.getChildren().add(button);
//    }
//
//    //Show the big ass panel when you press a button (And make sure that it disappear when pressed again)
//    private void showSubScene(GameSubScene subScene) {
//
//        //Cần ai đó làm đơn giản lại cái ìf else này
//        if (currentShowingScene == null) {
//            subScene.moveSubScene();
//            currentShowingScene = subScene;
//        } else {
//            if(currentShowingScene == subScene){
//                subScene.moveSubScene();
//            } else {
//                if(!currentShowingScene.isHidden){
//                    currentShowingScene.moveSubScene();
//                }
//                subScene.moveSubScene();
//                currentShowingScene = subScene;
//            }
//        }
//    }
//
//    //Add the panel to our game Anchor Pane
//    private void createSubScenes() {
//        openCredit = new GameSubScene();
//        mainPane.getChildren().add(openCredit);
//        openSetting = new GameSubScene();
//        createHomeButton(openSetting.getPane());
//
//        mainPane.getChildren().add(openSetting);
//    }

//    private void createLogo() {
//        ImageView logo = new ImageView("D:/Programming stuff/Intellij/Intro 2 SE project/SimpleRPG/src/main/resources/title.png");
//        logo.setFitHeight(400);
//        logo.setFitWidth(650);
//        logo.setLayoutX(380);
//        logo.setLayoutY(200);
//        logo.setOnMouseEntered(event -> logo.setEffect(new DropShadow()));
//        logo.setOnMouseExited(event -> logo.setEffect(null));
//        mainPane.getChildren().add(logo);
//
//    }

//    public void startGame(){
//
//        if (currentShowingScene != null && !currentShowingScene.isHidden){
//            currentShowingScene.moveSubScene();
//        }
//        mainPane.getChildren().add(canvasBackground);
//        mainPane.getChildren().add(canvasMiddle);
////        createPauseButton();
//        startScreen.openSetting.toFront();
//        mainHUD.render();
//        theScene.setOnKeyPressed(keyHandler);
//        theScene.setOnKeyReleased(keyHandler);
//        gameLoopManager.start();
//
//    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        //Start screen elements
//        menuButtons = new ArrayList<>();
        mainStage = primaryStage;
        mainStage.setTitle("Simple RPG");
        mainStage.setScene(theScene);
        startScreenView = new StartScreenView(this);

//        createLogo();
//        CreateButtons();
//        createSubScenes();
//        createBackground();
        mainStage.show();
    }
}
