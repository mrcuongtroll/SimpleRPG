package dialogue;

import event.Event;

public class Choice extends Popup {

    public Choice(String text) {
        super(text);
    }
    public Choice(String text, Event e) {
        super(text, e);
    }
}
