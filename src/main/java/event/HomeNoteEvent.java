package event;

import dialogue.Dialogue;
import entity.Character;
import world.Tile;
import world.World;

public class HomeNoteEvent extends Event {

    public HomeNoteEvent(World world) {
        super(world, Event.TRIGGER_TYPE_INTERACT);
    }

    public HomeNoteEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_INTERACT, x, y);
    }

    public HomeNoteEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_INTERACT, character);
    }

    public HomeNoteEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_INTERACT, tile);
    }

    @Override
    public void trigger() {
        Dialogue d1 = new Dialogue("The note says: \"You can go outside through the door in the left side of this room\".");
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
