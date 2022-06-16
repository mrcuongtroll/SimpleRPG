package dialogue;

import event.Event;

public abstract class Popup {

    private final String text;
    private Popup next = null;
    private DialogueRender renderer;
    private Event event;

    public String getText() {
        return this.text;
    }
    public Popup getNext() {
        return this.next;
    }
    public DialogueRender getRenderer() {
        return this.renderer;
    }
    public void trigger() {
        if (this.event != null) {
            this.event.trigger();
        }
    }

    public void setNext(Popup next) {
        this.next = next;
    }
    public void setRenderer(DialogueRender renderer) {
        this.renderer = renderer;
    }
    public void setEvent(Event e) {
        this.event = e;
    }

    public Popup(String text) {
        this.text = text;
    }
    public Popup(String text, Event e) {
        this(text);
        this.event = e;
    }
}
