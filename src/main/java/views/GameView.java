package views;

import javafx.scene.control.Button;
import main.SimpleRPG;
import sceneElement.GameSubScene;

public class GameView extends View{

    private static final int HEIGHT = 720;
    private static final int WIDTH = 1280;
    private GameSubScene openSetting;
    private SimpleRPG simpleRPG;

//    public Canvas canvasBackground;
//    public Canvas canvasMiddle;
//    public World testWorld;
//    public Player testPlayer;
//    public GameLoopManager gameLoopManager;
//    public KeyHandler keyHandler;
//    public HUD mainHUD;

    //Game variables
//    public Canvas canvasBackground = new Canvas(WIDTH, HEIGHT);
//    public Canvas canvasMiddle = new Canvas(WIDTH, HEIGHT);
//    public World testWorld = new World(this, (new File("./assets/test/old map.png")).getAbsolutePath());;
//    public Player testPlayer = new Player(this, Player.X, Player.Y, "Player",
//            (new File("./assets/test/player")).getAbsolutePath(), 1, 80, 100,
//            new Weapon(10, 0, "example_armor.png"),
//            new Armor(0, 20, "example_armor.png"));
//    public GameLoopManager gameLoopManager = new GameLoopManager(simpleRPG, testWorld, testPlayer);
//    public KeyHandler keyHandler = new KeyHandler(simpleRPG);
//    public HUD mainHUD = new HUD(simpleRPG, testPlayer);

    public GameView(SimpleRPG simpleRPG){
        super(simpleRPG);
        this.simpleRPG = simpleRPG;
        clearPane();

//        canvasBackground = new Canvas(WIDTH, HEIGHT);
//        canvasMiddle = new Canvas(WIDTH, HEIGHT);
//        testWorld = new World(this, (new File("./assets/test/old map.png")).getAbsolutePath());;
//        testPlayer = new Player(this, Player.X, Player.Y, "Player",
//                (new File("./assets/test/player")).getAbsolutePath(), 1, 80, 100,
//                new Weapon(10, 0, "example_armor.png"),
//                new Armor(0, 20, "example_armor.png"));
//        gameLoopManager = new GameLoopManager(this, testWorld, testPlayer);
//        keyHandler = new KeyHandler(this);
//        mainHUD = new HUD(simpleRPG, testPlayer);

        openSetting = new GameSubScene();
        Button testButton1 = new Button("Back to menu");
        Button testButton2 = new Button("Button 2");
        Button testButton3 = new Button("Button 3");
        openSetting.addButton(testButton1, 100, 100);
        openSetting.addButton(testButton2, 200, 100);
        openSetting.addButton(testButton3, 300, 100);

        testButton1.setOnAction(event ->  {mainPane.getChildren().clear(); new StartScreenView(simpleRPG);});

        simpleRPG.theScene.setOnKeyPressed(simpleRPG.keyHandler);
        simpleRPG.theScene.setOnKeyReleased(simpleRPG.keyHandler);
        simpleRPG.mainPane.getChildren().add(simpleRPG.canvasBackground);
        simpleRPG.mainPane.getChildren().add(simpleRPG.canvasMiddle);

        simpleRPG.mainHUD.render();
        createSubScenes(openSetting);
        CreateScreenElements();
        simpleRPG.gameLoopManager.start();
    }

    private void CreateScreenElements(){
        createSubSceneButton("Pause", openSetting, 1100, 20);
    }

}
