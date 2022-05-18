package sceneElement;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import static main.SimpleRPG.SCREEN_HEIGHT;
import static main.SimpleRPG.SCREEN_WIDTH;

public class GameSubScene extends AnchorPane {

    public boolean isHidden;
    private AnchorPane pane;
    private int width;
    private int height;
    private int x;
    private int y;
    private String transitionStyle;

    public GameSubScene(int width, int height, int x, int y, String transitionStyle, String background) {
        super(new AnchorPane());
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.transitionStyle = transitionStyle;

        this.setMinSize(width, height);
        prefWidth(width);
        prefHeight(height);

        BackgroundImage image = new BackgroundImage(new Image(background, width, height, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        pane = this;
        pane.setVisible(false);
        pane.setBackground(new Background(image));
        isHidden = true ;

        switch (transitionStyle) {
            case "Horizontal" -> {
                setLayoutX(SCREEN_WIDTH);
                setLayoutY(y);
            }
            case "Vertical" -> {
                setLayoutX(x);
                setLayoutY(SCREEN_HEIGHT);
            }
            case "None" -> {
                setLayoutX(x);
                setLayoutY(y);
            }
        }
    }

    public void addButton(Button button, int x, int y){
        button.setLayoutX(x);
        button.setLayoutY(y);
        pane.getChildren().add(button);
    }

    public void addGrid(GridPane grid, int x, int y){
        grid.setLayoutX(x);
        grid.setLayoutY(y);
        pane.getChildren().add(grid);
    }

    public void addText(String text, int x, int y){
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        pane.getChildren().add(label);
    }


    public void moveSubScene() {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (transitionStyle.equals("Horizontal")) {
            if (isHidden) {
                this.setVisible(true);
                transition.setToX(x - SCREEN_WIDTH);
                isHidden = false;
            } else {
                transition.setToX(0);
                isHidden = true ;
            }
        } else if (transitionStyle.equals("Vertical")) {
            if (isHidden) {
                this.setVisible(true);
                transition.setToY(y - SCREEN_HEIGHT);
                isHidden = false;
            } else {
                transition.setToY(0);
                isHidden = true ;
            }
        }

        transition.play();
        transition.setOnFinished(event -> {
            if (isHidden) {
                this.setVisible(false);
            }
        });

    }

}
