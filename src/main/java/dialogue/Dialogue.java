package dialogue;

import event.Event;
import main.SimpleRPG;
import entity.Character;
import popup.PopupChoice;

import java.util.ArrayList;

public class Dialogue extends Popup {

    private Character character;

    public Character getCharacter() {
        return this.character;
    }
    public String getName() {
        return this.character.getName();
    }
    public String getImagePath() {
        return this.character.getImagePath() + this.character.DEFAULT_IMAGE_PATH + "1.png";
    }

    public Dialogue(String text) {
        super(text);
    }
    public Dialogue(String text, Character character) {
        super(text);
        this.character = character;
    }
    public Dialogue(String text, Event e) {
        super(text, e);
    }
    public Dialogue(String text, Character character, Event e) {
        super(text, e);
        this.character = character;
    }
}



