package sceneElement;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;

import static main.SimpleRPG.SCREEN_HEIGHT;
import static main.SimpleRPG.SCREEN_WIDTH;

public class GameSubScene extends AnchorPane {

    public boolean isHidden;
    private int width;
    private int height;
    private int x;
    private int y;
    private String transitionStyle;
    private ArrayList<GameButton> buttons = new ArrayList<GameButton>();

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

        setVisible(false);
        setBackground(new Background(image));
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
        getChildren().add(button);
        buttons.add((GameButton) button);
    }

    public ArrayList<GameButton> getButtons(){
        return this.buttons;
    }

    public void addGrid(GridPane grid, int x, int y){
        grid.setLayoutX(x);
        grid.setLayoutY(y);
        getChildren().add(grid);
    }

    protected void addText(String text, Color color, int size, int width, int height, int x, int y){
        Label label = new Label(text);
        label.setFont(Font.loadFont("file:src/main/resources/arcade.ttf", size));
        label.setPrefHeight(height);
        label.setPrefWidth(width);
        label.setTextFill(color);
        label.setStyle("-fx-text-alignment: CENTER; -fx-alignment: CENTER");
        label.setLayoutX(x);
        label.setLayoutY(y);
        getChildren().add(label);
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
    public void disableButtons(){
        for (GameButton gameButton:this.getButtons()){
            gameButton.disableButton();
        }
    }
    public void enableButtons(){
        for (GameButton gameButton:this.getButtons()){
            gameButton.enableButton();
        }
    }
}
