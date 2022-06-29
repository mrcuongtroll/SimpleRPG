package event;

import dialogue.Choice;
import dialogue.ChoicesList;
import dialogue.Dialogue;
import entity.Character;
import world.Tile;
import world.World;
import world.WorldHome;

public class FireplaceKeyEvent extends Event {

    public FireplaceKeyEvent(World world) {
        super(world, Event.TRIGGER_TYPE_INTERACT);
    }

    public FireplaceKeyEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_INTERACT, x, y);
    }

    public FireplaceKeyEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_INTERACT, character);
    }

    public FireplaceKeyEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_INTERACT, tile);
    }

    @Override
    public void trigger() {
        this.getGameInstance().getPlayer().stopMoving(this.getGameInstance().getPlayer().getLastDirection());
        // Create dialogue
        Dialogue d1 = new Dialogue("You found an old book covered in ashes. Open it?");
        Choice c1 = new Choice("Yes");
        Choice c2 = new Choice("No");
        ChoicesList choices = new ChoicesList();
        choices.addChoice(c1);
        choices.addChoice(c2);
        Dialogue d2 = new Dialogue("Ah! There is a key hidden inside the book.",
                this.getGameInstance().getPlayer());
        Dialogue choiceNop = new Dialogue("...");
        Dialogue d3 = new Dialogue("Obtained Home key");
        // Set up events
        d3.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            @Override
            public void trigger() {
                WorldHome.keyObtained = true;
            }
        });
        // Set up dialogue sequence
        d1.setNext(choices);
        c1.setNext(choiceNop);
        choiceNop.setNext(d2);
        d2.setNext(d3);
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
