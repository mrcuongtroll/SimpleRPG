package sceneElement;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class GameButton extends Button {

    private int width;
    private int height;

    private void setBackground(String background){
        BackgroundImage image = new BackgroundImage(new Image(background, width, height, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(image));
    }



    public GameButton(String text, int width, int height) {
        this.width = width;
        this.height = height;

        setText(text);
        setButtonFont(10);
        setPrefWidth(width);
        setPrefHeight(height);
        setStyle("-fx-wrap-text: true; -fx-text-alignment: CENTER; -fx-alignment: CENTER;");
        setBackground((new File("./assets/test/button/smallFree.png").getAbsolutePath()));
        initializeButtonListeners();

        //Comment this to navigate buttons with arrow keys
        setFocusTraversable(false);

    }

    private void setButtonFont(int size) {

        setFont(Font.loadFont("file:src/main/resources/arcade.ttf", size));
        setTextFill(Color.BROWN);
    }

    private void setButtonPressedStyle() {
        setBackground((new File("./assets/test/button/smallPressed.png").getAbsolutePath()));
        setPrefHeight(height);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setBackground((new File("./assets/test/button/smallFree.png").getAbsolutePath()));
        setPrefHeight(height);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {

        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }

        });

        setOnMouseReleased(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));
    }
}