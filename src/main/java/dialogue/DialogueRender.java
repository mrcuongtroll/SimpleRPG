package dialogue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.SimpleRPG;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.List;

public class DialogueRender {
    private Dialogue dialogue;
    private SimpleRPG master;

    private GraphicsContext gc;

    private AnchorPane root;

    private double height = 175.0;
    private Canvas canvasDialogue = new Canvas(1280, 720 - height);

    private static final String DIALOG_NEXT_BUTTON = "file:./assets/test/dialogue/arrow.png";

    private static final String DIALOGUE_BACKGROUND = "file:./assets/test/dialogue/dialogue_background.png";


    private static final String DIALOG_FONT  = "file:./assets/test/font/ARCADE_N.ttf";

    private boolean inputCharacter;

    public DialogueRender(Dialogue dialogue, SimpleRPG master) {
        this.dialogue = dialogue;
        this.master = master;
        this.root = this.master.popupPane;
    }

    public void renderWithCharacter(){
        Image img = new Image(DIALOG_NEXT_BUTTON);
        ImageView buttonView = new ImageView(img);
        buttonView.setFitHeight(50);
        buttonView.setPreserveRatio(true);
        Button nextButton = new Button();
        nextButton.setAlignment(Pos.CENTER);
        nextButton.setGraphic(buttonView);
        nextButton.setPickOnBounds(false);
        nextButton.setStyle("-fx-background-color: transparent");

        nextButton.setTranslateX(1280 - height - 10 - 100);
        nextButton.setTranslateY(height/2 - 70);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                clear();
            }

        };

        // when button is pressed
        nextButton.setOnAction(event);

        Font dialogFont = Font.loadFont(DIALOG_FONT, 15.0);
        String dialogueString = this.dialogue.getInputDialogue();
        Label dialogueContent = new Label(dialogueString);
        dialogueContent.setFont(dialogFont);
        dialogueContent.setStyle("-fx-line-spacing: 0.2em;");

        dialogueContent.setWrapText(true);
        //Setting the alignment to the label
        //Setting the maximum width of the label
        dialogueContent.setMaxWidth(1280 - height - 50);
        dialogueContent.setTextFill(Color.WHITE);
        dialogueContent.setTranslateY(10);

        if (this.dialogue.getCharacter() != null){

        }
        Canvas imageBox = new Canvas(height, height);
        Image potrait = new Image(this.dialogue.getImagePath() , 100, 100, false, false);
        imageBox.setStyle("-fx-background-color: rgba(20, 20, 100, 0.8); -fx-background-radius: 10;");

        imageBox.getGraphicsContext2D().drawImage(potrait, (height - 100) / 2, (height - 100) / 2);
        imageBox.setStyle("-fx-background-color: rgba(200, 200,200, 0.8); -fx-background-radius: 10;");


        Font nameFont = Font.loadFont("file:./assets/font/dialog-font.ttf", 20.0);
        Label characterName = new Label(this.dialogue.getName());
        characterName.setTranslateY(5);
        characterName.setFont(nameFont);
        characterName.setTextFill(Color.TEAL);
        characterName.setStyle("-fx-font-weight: bold");

        Pane namePane = new Pane(characterName);
        namePane.setMinSize(70, 30);


        VBox contentPane = new VBox();
        contentPane.setMinSize(1280 - height - 10, height);
        contentPane.getChildren().add(namePane);
        contentPane.getChildren().addAll(dialogueContent, nextButton);

        HBox dialogArea = new HBox(10);
        dialogArea.setMaxHeight(height);
        dialogArea.setMaxWidth(300);
        dialogArea.getChildren().addAll(imageBox, contentPane);
        dialogArea.setStyle("-fx-background-color: rgba(200, 200,200, 0.8); -fx-background-radius: 10;");

        this.root.setBottomAnchor(dialogArea, 0.0);
        this.root.setTopAnchor(dialogArea, 720.0 - height);
        this.root.setLeftAnchor(dialogArea, 0.0);
        this.root.setRightAnchor(dialogArea, 0.0);

        this.root.getChildren().addAll(dialogArea);
//        this.master.dialoguePane.getChildren().clear();
        this.root.setFocusTraversable(true);

        this.root.setOnKeyPressed(e -> {
            if ( (e.getCode() == KeyCode.Z) || (e.getCode() == KeyCode.ENTER))  {
                this.clear();
            }
        });

    }

    public void renderWithNoCharacter(){
        height = height * 0.75;
        Image img = new Image(DIALOG_NEXT_BUTTON);
        ImageView buttonView = new ImageView(img);
        buttonView.setFitHeight(50);
        buttonView.setPreserveRatio(true);
        Button nextButton = new Button();
        nextButton.setAlignment(Pos.CENTER);
        nextButton.setGraphic(buttonView);
        nextButton.setPickOnBounds(false);
        nextButton.setStyle("-fx-background-color: transparent");

        nextButton.setTranslateX(1280 * 0.925);
        nextButton.setTranslateY(height/2 - 50);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                clear();
            }

        };

        // when button is pressed
        nextButton.setOnAction(event);

        Font dialogFont = Font.loadFont(DIALOG_FONT, 15.0);
        String dialogueString = this.dialogue.getInputDialogue();
        Label dialogueContent = new Label(dialogueString);
        dialogueContent.setFont(dialogFont);
        dialogueContent.setStyle("-fx-line-spacing: 0.2em;");

        dialogueContent.setWrapText(true);
        //Setting the alignment to the label
        //Setting the maximum width of the label
        dialogueContent.setMaxWidth(1280  - 70);
        dialogueContent.setTextFill(Color.WHITE);
        dialogueContent.setTranslateX(40);

        dialogueContent.setTranslateY(30);

        VBox contentPane = new VBox();
        contentPane.setMinSize(1280 - 10, height);
//        contentPane.getChildren().add(namePane);
        contentPane.getChildren().addAll(dialogueContent, nextButton);

        HBox dialogArea = new HBox(10);
        dialogArea.setMaxHeight(height);
//        dialogArea.setMaxWidth(300);
        dialogArea.getChildren().addAll(contentPane);
        System.out.println(contentPane.getWidth());
        System.out.println( contentPane.getHeight());
        Image backgroundImg = new Image(DIALOGUE_BACKGROUND, 1280 - 10, height * 1.5, false, false);

        BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        dialogArea.setBackground((new Background(background)));
//        dialogArea.setStyle("-fx-background-color: rgba(200, 200,200, 0.8); -fx-background-radius: 10;");

        this.root.setBottomAnchor(dialogArea, 0.0);
        this.root.setTopAnchor(dialogArea, 720.0 - height);
        this.root.setLeftAnchor(dialogArea, 0.0);
        this.root.setRightAnchor(dialogArea, 0.0);

        this.root.getChildren().addAll(dialogArea);
//        this.master.dialoguePane.getChildren().clear();
        this.root.setFocusTraversable(true);

        this.root.setOnKeyPressed(e -> {
            if ( (e.getCode() == KeyCode.Z) || (e.getCode() == KeyCode.ENTER))  {
                this.clear();
            }
        });

    }

    public void render(){
        this.root.getChildren().clear();

        if (this.dialogue.getCharacter() == null){
            this.renderWithNoCharacter();
        } else {
            this.renderWithCharacter();
        }
    }
    public void clear(){
        this.root.getChildren().clear();
        this.root.setVisible(false);
    }

//    public void render() throws InterruptedException {
//        this.root.setOnKeyPressed(e -> {
//            if ( (e.getCode() == KeyCode.Z) || (e.getCode() == KeyCode.RIGHT))  {
//                System.out.println();
//                this.root.getChildren().clear();
//                this.root.setVisible(false);
//            }
//        });
//
//        String fullDialog = this.dialogue.getInputDialogue();
//        List<String> tokenizedList = tokenizeDialogue(fullDialog);
//        for (String dialogSlice: tokenizedList){
//            this.gradualRender(dialogSlice);
//        }
//
//    }
    public static List<String> tokenizeDialogue(String dialogue) {
        String[] tokenizedDialogue = dialogue.split(" ");
        List<String> slicesList = new ArrayList<String>();
        for (int i = 0; i < tokenizedDialogue.length; i++) {
            String[] arraySlice = Arrays.copyOfRange(tokenizedDialogue, 0, i + 1);          // returns {10, 20}
            String wordSlice = "";
            for (String word : arraySlice) {
                wordSlice += word + " ";
            }
            slicesList.add(wordSlice);
        }
        return slicesList;
    }

    }


