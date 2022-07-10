package event;

import dialogue.Dialogue;
import entity.Ally;
import entity.Character;
import entity.NPC;
import world.Tile;
import world.World;
import world.WorldOutside;

public class SecondAllyRecruitEvent extends Event {
    public SecondAllyRecruitEvent(World world) {
        super(world, Event.TRIGGER_TYPE_TOUCH);
    }

    public SecondAllyRecruitEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_TOUCH, x, y);
    }

    public SecondAllyRecruitEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_TOUCH, character);
    }

    public SecondAllyRecruitEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_TOUCH, tile);
    }

    @Override
    public void trigger() {
        ((Ally)this.getCharacter()).setMode(NPC.MODE_IDLE);
        this.getCharacter().stopMoving(this.getCharacter().getLastDirection());
        this.getGameInstance().getPlayer().stopMoving(this.getGameInstance().getPlayer().getLastDirection());
        // Create dialogues
        Dialogue d1 = new Dialogue("Thank you for saving me", this.getCharacter());
        Dialogue d2 = new Dialogue("The bosses of these bandits you have just beaten reside in the southern side of this forest.",
                this.getCharacter());
        Dialogue d3 = new Dialogue("I suggest we go there and beat them arses. There are 2 of them.",
                this.getCharacter());
        Dialogue d4 = new Dialogue("I will accompany you on your journey.", this.getCharacter());
        Dialogue d5 = new Dialogue(this.getCharacter().getName() + " has joined the team.");
        Dialogue d6 = new Dialogue("You can now go south and try to defeat the 2 bandit bosses.");
        // Set up events
        d5.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            @Override
            public void trigger() {
                SecondAllyRecruitEvent.this.getGameInstance().getPlayer().getAllyList().add(SecondAllyRecruitEvent.this.getCharacter());
                SecondAllyRecruitEvent.this.getWorld().getNpcList().remove((NPC)SecondAllyRecruitEvent.this.getCharacter());
                SecondAllyRecruitEvent.this.getWorld().getEventList().remove(SecondAllyRecruitEvent.this.getCharacter().getEvent());
                WorldOutside.ally2Recruited = true;
            }
        });
        // Set up dialog sequence
        d1.setNext(d2);
        d2.setNext(d3);
        d3.setNext(d4);
        d4.setNext(d5);
        d5.setNext(d6);
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
