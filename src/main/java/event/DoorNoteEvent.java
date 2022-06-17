package event;

import dialogue.Choice;
import dialogue.ChoicesList;
import dialogue.Dialogue;
import entity.Character;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import main.SimpleRPG;
import world.Tile;
import world.World;
import world.WorldHome;
import world.WorldOutside;

public class DoorNoteEvent extends Event {

    public DoorNoteEvent(World world) {
        super(world, Event.TRIGGER_TYPE_INTERACT);
    }

    public DoorNoteEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_INTERACT, x, y);
    }

    public DoorNoteEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_INTERACT, character);
    }

    public DoorNoteEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_INTERACT, tile);
    }

    @Override
    public void trigger() {
        // Set up dialogue
        if (WorldHome.keyObtained) {
            // If you have the key
            Dialogue d7 = new Dialogue("Use the key to open the door?");
            Choice c3 = new Choice("Yes");
            Choice c4 = new Choice("No");
            ChoicesList choices2 = new ChoicesList();
            choices2.addChoice(c3);
            choices2.addChoice(c4);
            Dialogue choiceNop = new Dialogue("...");
            Dialogue d8 = new Dialogue("Ah! The door is opened",
                    this.getGameInstance().getPlayer());
            // Set events
            d8.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
                @Override
                public void trigger() {
                    WorldHome.doorUnlocked = true;
                    // Get game instance
                    SimpleRPG gameInstance = this.getGameInstance();
                    // Stop the player character
                    gameInstance.getPlayer().stopMoving(Character.LEFT);
                    gameInstance.getPlayer().stopMoving(Character.DOWN);

                    // Fade out
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), gameInstance.mainPane);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();

                    // Initialize outside map
                    gameInstance.setWorld(new WorldOutside(gameInstance));

                    // Fade back in
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.play();
                }
            });
            // Set dialogue sequence
            d7.setNext(choices2);
            c3.setNext(choiceNop);
            choiceNop.setNext(d8);
            this.getGameInstance().getDialogueRender().setDialogue(d7);
        } else {
            // A note
            Dialogue d1 = new Dialogue("There is a note on the door.");
            Dialogue d2 = new Dialogue("Read the note?");
            // Choose to read the note or not
            Choice c1 = new Choice("Yes");
            Choice c2 = new Choice("No");
            ChoicesList choices1 = new ChoicesList();
            choices1.addChoice(c1);
            choices1.addChoice(c2);
            // Content of the note
            Dialogue choiceNop = new Dialogue("...");
            Dialogue d3 = new Dialogue("There is a riddle written on the note.");
            Dialogue d4 = new Dialogue("\"The great book sits and waits for you, its breath held, still as stone,\n" +
                    "So wicked the spells and thieving hands both leave it well alone.\"");
            Dialogue d5 = new Dialogue("\"It's hidden somewhere safe and sound, if somewhat unexpected.\n" +
                    "While other books fear such a place, our spellbook's unaffected!\"");
            Dialogue d6 = new Dialogue("So this clue leads to the key for this door huh? I wonder where it is.",
                    this.getGameInstance().getPlayer());
            // Set events for dialogues
            d6.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
                @Override
                public void trigger() {
                    this.getWorld().getEventList().add(new FireplaceKeyEvent(this.getWorld(), 567, 192));
                }
            });
            // Set dialogue sequence
            d1.setNext(d2);
            d2.setNext(choices1);
            c1.setNext(choiceNop);
            choiceNop.setNext(d3);
            d3.setNext(d4);
            d4.setNext(d5);
            d5.setNext(d6);
            this.getGameInstance().getDialogueRender().setDialogue(d1);
        }
    }
}
