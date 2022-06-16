package dialogue;

import java.util.ArrayList;

public class ChoicesList extends Popup {

    private ArrayList<Choice> choices = new ArrayList<Choice>();

    public int getLength() {
        return this.choices.toArray().length;
    }

    public ArrayList<Choice> getChoices() {
        return this.choices;
    }

    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }

    public ChoicesList() {
        super(null);
    }
    public ChoicesList(ArrayList<Choice> choices) {
        this();
        this.choices = choices;
    }
}
