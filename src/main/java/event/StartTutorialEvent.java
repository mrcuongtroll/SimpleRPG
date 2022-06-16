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
        Dialogue d1 = new Dialogue("Hello there! Welcome to SimpleRPG! Press Z on your keyboard to get to the next dialogue.",
                this.getGameInstance().getPlayer());
        Dialogue d2 = new Dialogue("You can use arrow keys on your keyboard to move the character around",
                this.getGameInstance().getPlayer());
        d1.setNext(d2);
        Choice choice1 = new Choice("Understood");
        choice1.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            public void trigger() {
                System.out.println("I understood");
            }
        });
        Choice choice2 = new Choice("Bruh");
        choice2.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            public void trigger() {
                System.out.println("Bruh");
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
        d2.setNext(choices);
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
