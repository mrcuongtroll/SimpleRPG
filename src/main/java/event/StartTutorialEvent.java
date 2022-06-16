package event;

import dialogue.Choice;
import dialogue.ChoicesList;
import dialogue.Dialogue;
import entity.Character;
import world.Tile;
import world.World;

public class StartTutorialEvent extends Event {
    public StartTutorialEvent(World world) {
        super(world, Event.TRIGGER_TYPE_AUTO_SINGLE);
    }

    public StartTutorialEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_AUTO_SINGLE, x, y);
    }

    public StartTutorialEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_AUTO_SINGLE, character);
    }

    public StartTutorialEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_AUTO_SINGLE, tile);
    }

    @Override
    public void trigger() {
        // Test
        Dialogue d1 = new Dialogue("Hello there! Welcome to SimpleRPG! Press X on your keyboard to get to the next dialogue.");
        Dialogue d2 = new Dialogue("You can use arrow keys on your keyboard to move the character around. To select a choice when prompted, press Z on the keyboard.");
        d1.setNext(d2);
        Dialogue d3 = new Dialogue("You can also interact with some of the stuff around you by pressing Z on your keyboard.");
        d2.setNext(d3);
        Dialogue d4 = new Dialogue("That concludes our tutorial.");
        d3.setNext(d4);
        Choice choice1 = new Choice("Understood");
        choice1.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            public void trigger() {
                System.out.println("I understood");
            }
        });
        Choice choice2 = new Choice("Let me review the tutorial");
        choice2.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            public void trigger() {
                StartTutorialEvent.this.trigger();
            }
        });
        Choice choice3 = new Choice("Let's goooooooooooooooooooooooooooooooooooooooooo");
        choice3.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            public void trigger() {
                System.out.println("Let's go... I guess?");
            }
        });
        ChoicesList choices = new ChoicesList();
        choices.addChoice(choice1);
        choices.addChoice(choice2);
        choices.addChoice(choice3);
        d4.setNext(choices);
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
