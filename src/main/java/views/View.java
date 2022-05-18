package views;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import main.SimpleRPG;
import sceneElement.GameButton;
import sceneElement.GameSubScene;

import java.util.ArrayList;
import java.util.List;

public abstract class View {

    List<GameButton> menuButtons = new ArrayList<>();

    protected AnchorPane mainPane;
    public static GameSubScene currentShowingScene;
    public static View currentShowingView;
    protected View(SimpleRPG simpleRPG){
        mainPane = simpleRPG.mainPane;
        currentShowingView = this;
    }

    protected void createSubSceneButton(String Title, GameSubScene Scene, int height, int width, int x, int y){
        GameButton newButton = new GameButton(Title, height, width);
        AddMenuButtons(newButton, x, y);
        newButton.setOnAction(event -> showSubScene(Scene));
    }

    protected GameButton createBlankButton(String Title, int height, int width, int x, int y){
        GameButton newButton = new GameButton(Title, height, width);
        AddMenuButtons(newButton, x, y);
        return newButton;
    }

    protected void createLogo(String path, int Height, int Width, int x, int y) {
        ImageView logo = new ImageView(path);
        logo.setFitHeight(Height);
        logo.setFitWidth(Width);
        logo.setLayoutX(x);
        logo.setLayoutY(y);
        logo.setOnMouseEntered(event -> logo.setEffect(new DropShadow()));
        logo.setOnMouseExited(event -> logo.setEffect(null));
        mainPane.getChildren().add(logo);

    }

    protected void createBackground(String path, Boolean repeat) {
        Image backgroundImage = new Image(path, 1280, 720, false, false);
        BackgroundImage background;

        if(repeat){
            background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        } else {
            background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        }
        mainPane.setBackground(new Background(background));
    }

    protected void AddMenuButtons(GameButton button, int x, int y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    protected void addSubSceneToPane(GameSubScene... scenes) {
        for(GameSubScene scene: scenes){
//            scene = new GameSubScene();
            mainPane.getChildren().add(scene);
        }
    }

    public void showSubScene(GameSubScene subScene) {
        //Cần ai đó làm đơn giản lại cái if else này
        if (currentShowingScene == null) {
            subScene.moveSubScene();
            currentShowingScene = subScene;
        } else {
            if(currentShowingScene == subScene){
                subScene.moveSubScene();
            } else {
                if(!currentShowingScene.isHidden){
                    currentShowingScene.moveSubScene();
                }
                subScene.moveSubScene();
                currentShowingScene = subScene;
            }
        }
    }

    // Check if any scene is opened before starting a new view
    public void cleanUpScene(){
        if(!currentShowingScene.isHidden){
            currentShowingScene.moveSubScene();
        }
    }

    protected void clearPane(){
        mainPane.getChildren().clear();
    }
}
