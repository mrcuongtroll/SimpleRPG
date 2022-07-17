package popup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.SimpleRPG;

import java.util.ArrayList;

public class PopupRender {
    private SimpleRPG master;
    private PopupChoice popup;
    private AnchorPane root;
    private static final String DIALOG_FONT  = "/arcade.ttf";

    public PopupRender(SimpleRPG master, PopupChoice popup) {
        this.master = master;
        this.popup = popup;
        this.root = this.master.popupPane;
    }

    public String render() {
        this.root.getChildren().clear();
        this.root.setVisible(true);

        VBox popup = new VBox(20);
        popup.setAlignment(Pos.CENTER_LEFT);
//        popup.setMinSize(100);

//        popup.setTranslateX(1280);

        Font dialogFont = Font.loadFont(getClass().getResourceAsStream(DIALOG_FONT), 15.0);
        if (this.popup.getMessage() != null) {
            String message = this.popup.getMessage();
            Label messageContent = new Label(message);
            messageContent.setFont(dialogFont);
            messageContent.setStyle("-fx-line-spacing: 0.2em;");

            messageContent.setWrapText(true);
            //Setting the alignment to the label
            //Setting the maximum width of the label
            messageContent.setTextFill(Color.WHITE);
//        messageContent.setTranslateX(40);
            messageContent.setTranslateY(15);

            messageContent.setMaxSize(600, 400);
            popup.getChildren().add(messageContent);
        }

        String [] choices = this.popup.getChoices();
//        ScrollPane choicesPane = new ScrollPane();
//        choicesPane.setPannable(true);
        VBox choicesContainer = new VBox(2);
        choicesContainer.setAlignment(Pos.CENTER);
        for (String choice: choices) {
            Button button = new Button(choice);
            choicesContainer.getChildren().add(button);
        }
//        choicesContainer.getChildren().addAll(buttonList);
        choicesContainer.setStyle("-fx-background-color: transparent;");
//        choicesPane.setContent(choicesContainer);
//        choicesPane.setPannable(true);
//        choicesPane.setStyle("-fx-background-color: transparent;");
//        popup.getChildren().add(choicesPane);
        popup.getChildren().add(choicesContainer);
//        popup.setPrefSize(messageContent.getWidth() * 1.2,messageContent.getHeight() * 1.2);
//        popup.setStyle("-fx-background-color: rgba(20, 20, 100, 0.8); -fx-background-radius: 10;");
        popup.setStyle("-fx-background-color: transparent;");
        this.root.getChildren().add(popup);

        this.root.setBottomAnchor(popup, 0.0);
        this.root.setTopAnchor(popup, 720.0 - 375);
        this.root.setLeftAnchor(popup, 30.0);
        this.root.setRightAnchor(popup, 0.0);

        return "";
    }
}
