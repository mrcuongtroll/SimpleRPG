package sceneElement;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

public class GameButton extends Button {

    private final String FONT_PATH = "/resources/ARCADE_N.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-image: url('/resources/blue_button.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-image: url('/resources/red_button.png');";

//    String image = this.getClass().getResource((new File("./assets/test/button/red_.png")).getAbsolutePath()).toExternalForm();

    public GameButton(String text) {
        setText(text);
//        System.out.println(image);
//        setButtonFont();
        setPrefWidth(165);
        setPrefHeight(65);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
        setFocusTraversable(false);

    }

    private void setButtonFont() {

        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));

    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(65);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(65);
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
