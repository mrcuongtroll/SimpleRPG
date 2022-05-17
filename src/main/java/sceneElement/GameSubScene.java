package sceneElement;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class GameSubScene extends SubScene {

    private final static String BACKGROUND_IMAGE = "D:/Programming stuff/Intellij/Intro 2 SE project/SimpleRPG/assets/test/button/blue_button.png";
    public boolean isHidden;
    public AnchorPane pane;

    public GameSubScene() {
        super(new AnchorPane(), 600, 300);
        prefWidth(600);
        prefHeight(300);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 300, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        pane = (AnchorPane) this.getRoot();
        pane.setBackground(new Background(image));

        isHidden = true ;

        setLayoutX(1280);
        setLayoutY(180);

    }

    public void addButton(Button button, int x, int y){
        button.setLayoutX(x);
        button.setLayoutY(y);
        pane.getChildren().add(button);
    }

    public void addText(String text, int x, int y){
//        pane.getChildren().add(text);
    }


    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {

            transition.setToX(-700);
            isHidden = false;

        } else {

            transition.setToX(0);
            isHidden = true ;

        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
