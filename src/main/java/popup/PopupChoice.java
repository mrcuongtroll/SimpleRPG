package popup;

import java.util.ArrayList;

public class PopupChoice {

    private String[] choices;
    private String message;

    public String[] getChoices() {
        return choices;
    }

    public String getMessage() {
        return message;
    }

    public PopupChoice(String message, String[] choices) {
        this.choices = choices;
        this.message = message;
    }

}
