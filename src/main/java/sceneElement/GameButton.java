package sceneElement;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

public class GameButton extends Button {

    private final String FONT_PATH = "/resources/ARCADE_N.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-image: url('/resources/blue_button.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-image: url('/resources/red_button.png');";
    private int width;
    private int height;

    public GameButton(String text, int width, int height) {
        this.width = width;
        this.height = height;

        setText(text);
//        setButtonFont();
        setPrefWidth(width);
        setPrefHeight(height);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
        setFocusTraversable(false);

    }

    private void setButtonFont() {

        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));

    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(height);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
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
