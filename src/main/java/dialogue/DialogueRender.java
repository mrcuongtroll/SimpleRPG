package dialogue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.SimpleRPG;
import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class DialogueRender {
    private Dialogue dialogue;
    private ChoicesList choices = null;
    private SimpleRPG master;
    private AnchorPane root;
    private boolean showing = false;
    private double height = 175.0;
//    private Canvas canvasDialogue = new Canvas(1280, 720 - height);
    private static final String DIALOG_NEXT_BUTTON = "file:./assets/test/dialogue/arrow.png";
    private static final String DIALOGUE_BACKGROUND = "file:./assets/test/dialogue/dialogue_background.png";
    private static final String DIALOG_FONT  = "file:./assets/test/font/ARCADE_N.ttf";

    public boolean isShowing() {
        return this.showing;
    }

    private boolean inputCharacter;
    public Dialogue getDialogue() {
        return this.dialogue;
    }
    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }
    public DialogueRender(SimpleRPG master) {
        this.master = master;
        this.root = this.master.popupPane;
    }
    public DialogueRender(SimpleRPG master, Dialogue dialogue) {
        this(master);
        this.dialogue = dialogue;
    }

    public void renderWithCharacter(){
//        Image img = new Image(DIALOG_NEXT_BUTTON);
//        ImageView buttonView = new ImageView(img);
//        buttonView.setFitHeight(50);
//        buttonView.setPreserveRatio(true);
//        Button nextButton = new Button();
//        nextButton.setAlignment(Pos.CENTER);
//        nextButton.setGraphic(buttonView);
//        nextButton.setPickOnBounds(false);
//        nextButton.setStyle("-fx-background-color: transparent");
//
//        nextButton.setTranslateX(1280 - height - 10 - 100);
//        nextButton.setTranslateY(height/2 - 70);

//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                clear();
//            }
//
//        };

        // when button is pressed
//        nextButton.setOnAction(event);

        Font dialogFont = Font.loadFont(DIALOG_FONT, 15.0);
        String dialogueString = this.dialogue.getText();
        Label dialogueContent = new Label(dialogueString);
        dialogueContent.setFont(dialogFont);
        dialogueContent.setStyle("-fx-line-spacing: 0.2em;");

        dialogueContent.setWrapText(true);
        //Setting the alignment to the label
        //Setting the maximum width of the label
        dialogueContent.setMaxWidth(1280 - height - 50);
        dialogueContent.setTextFill(Color.WHITE);
        dialogueContent.setTranslateY(10);

        Canvas imageBox = new Canvas(height, height);
        VBox contentPane = new VBox();
        if (this.dialogue.getCharacter() != null){
            Image portrait = new Image(this.dialogue.getImagePath() , 100, 100, false, false);
            imageBox.setStyle("-fx-background-color: rgba(20, 20, 100, 0.8); -fx-background-radius: 10;");

            imageBox.getGraphicsContext2D().drawImage(portrait, (height - 100) / 2, (height - 100) / 2);
            imageBox.setStyle("-fx-background-color: rgba(200, 200,200, 0.8); -fx-background-radius: 10;");


            Font nameFont = Font.loadFont("file:./assets/font/dialog-font.ttf", 20.0);
            Label characterName = new Label(this.dialogue.getName());
            characterName.setTranslateY(5);
            characterName.setFont(nameFont);
            characterName.setTextFill(Color.TEAL);
            characterName.setStyle("-fx-font-weight: bold");

            Pane namePane = new Pane(characterName);
            namePane.setMinSize(70, 30);
            contentPane.getChildren().add(namePane);
        }

        contentPane.setMinSize(1280 - height - 10, height);
//            contentPane.getChildren().addAll(dialogueContent, nextButton);
        contentPane.getChildren().add(dialogueContent);
        HBox dialogArea = new HBox(10);
        dialogArea.setMaxHeight(height);
        dialogArea.setMaxWidth(300);
        dialogArea.getChildren().addAll(imageBox, contentPane);
        dialogArea.setStyle("-fx-background-color: rgba(200, 200,200, 0.8); -fx-background-radius: 10;");

        VBox popupContent = new VBox(10);
        VBox choiceBox = new VBox(2);
        choiceBox.setAlignment(Pos.CENTER);
        // Add choices if needed
        if (this.choices != null) {
            // TODO: focus on choices
            for (Choice choice: this.choices.getChoices()) {
                Button button = new Button(choice.getText());
                button.setMinSize(100, 30);
                button.setStyle("-fx-background-image: null;" +
                        "-fx-background-color: rgba(200, 200,200, 0.8);");
                button.setOnKeyPressed(e -> {
                    if ((e.getCode() == KeyCode.Z) || (e.getCode() == KeyCode.ENTER)) {
                        this.root.setVisible(false);
                        this.master.mainPane.requestFocus();
                        this.showing = false;
                        this.dialogue = (Dialogue) choice.getNext();
                        this.choices = null;
                        this.showing = false;
                        choice.trigger();
                    }
                });
                button.setOnAction(e -> {
                    this.root.setVisible(false);
                    this.master.mainPane.requestFocus();
                    this.showing = false;
                    this.dialogue = (Dialogue) choice.getNext();
                    this.choices = null;
                    this.showing = false;
                    choice.trigger();
                });
                choiceBox.getChildren().add(button);
                button.requestFocus();
            }
            popupContent.getChildren().add(choiceBox);
            choiceBox.setFocusTraversable(true);
//            choiceBox.requestFocus();
        }

        // Put things inside a VBox
        popupContent.getChildren().add(dialogArea);

        this.root.setBottomAnchor(popupContent, 0.0);
        if (this.choices == null) {
            this.root.setTopAnchor(popupContent, 720.0 - height);
        } else {
            this.root.setTopAnchor(popupContent, 720 - (height + 32*this.choices.getLength() + 10));
        }
        this.root.setLeftAnchor(popupContent, 0.0);
        this.root.setRightAnchor(popupContent, 0.0);

        this.root.getChildren().add(popupContent);
//        this.master.dialoguePane.getChildren().clear();
//        this.root.setFocusTraversable(true);
        if (this.choices == null) {
            this.root.requestFocus();
        } else {
            for (Node button: choiceBox.getChildren()) {
                button.requestFocus();
                break;
            }
        }

        this.root.setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.X) || (e.getCode() == KeyCode.ENTER))  {
//                this.clear();
                this.root.setVisible(false);
                this.master.mainPane.requestFocus();
                this.showing = false;
                if (this.dialogue != null) {
                    this.dialogue.trigger();
                    if (this.dialogue.getNext() instanceof Dialogue) {
                        this.dialogue = (Dialogue) this.dialogue.getNext();
                    } else if (this.dialogue.getNext() instanceof ChoicesList) {
                        this.choices = (ChoicesList) this.dialogue.getNext();
                    } else {
                        this.dialogue = null;
                        this.choices = null;
                    }
                }
            }
        });
    }

    public void renderWithNoCharacter(){
        height = height * 0.75;
//        Image img = new Image(DIALOG_NEXT_BUTTON);
//        ImageView buttonView = new ImageView(img);
//        buttonView.setFitHeight(50);
//        buttonView.setPreserveRatio(true);
//        Button nextButton = new Button();
//        nextButton.setAlignment(Pos.CENTER);
//        nextButton.setGraphic(buttonView);
//        nextButton.setPickOnBounds(false);
//        nextButton.setStyle("-fx-background-color: transparent");
//
//        nextButton.setTranslateX(1280 * 0.925);
//        nextButton.setTranslateY(height/2 - 50);
//
//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                clear();
//            }
//
//        };
//
//        // when button is pressed
//        nextButton.setOnAction(event);

        Font dialogFont = Font.loadFont(DIALOG_FONT, 15.0);
        String dialogueString = this.dialogue.getText();
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
//        contentPane.getChildren().addAll(dialogueContent, nextButton);
        contentPane.getChildren().add(dialogueContent);

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
            if ((e.getCode() == KeyCode.Z) || (e.getCode() == KeyCode.ENTER))  {
//                this.clear();
                this.root.setVisible(false);
                this.master.mainPane.requestFocus();
//                this.showing = false;
//                this.dialogue = this.dialogueQueue.poll();
                this.dialogue = (Dialogue)this.dialogue.getNext();
            }
        });
    }

    public void render() {
        if (!this.showing) {
            if (this.dialogue == null) {
//            this.dialogue = this.dialogueQueue.poll();
            }
            if (this.dialogue != null) {
                this.showing = true;
                this.root.setVisible(true);
                this.root.getChildren().clear();
//                this.root.requestFocus();
//                if (this.dialogue.getCharacter() == null) {
//                    this.renderWithNoCharacter();
//                } else {
//                    this.renderWithCharacter();
//                }
                this.renderWithCharacter();
            }
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


