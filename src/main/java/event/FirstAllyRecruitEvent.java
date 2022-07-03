package event;

import dialogue.Choice;
import dialogue.ChoicesList;
import dialogue.Dialogue;
import entity.Ally;
import entity.Character;
import entity.NPC;
import world.Tile;
import world.World;
import world.WorldOutside;

public class FirstAllyRecruitEvent extends Event {
    public FirstAllyRecruitEvent(World world) {
        super(world, Event.TRIGGER_TYPE_TOUCH);
    }

    public FirstAllyRecruitEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_TOUCH, x, y);
    }

    public FirstAllyRecruitEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_TOUCH, character);
    }

    public FirstAllyRecruitEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_TOUCH, tile);
    }

    @Override
    public void trigger() {
        ((Ally)this.getCharacter()).setMode(NPC.MODE_IDLE);
        this.getCharacter().stopMoving(this.getCharacter().getLastDirection());
        this.getGameInstance().getPlayer().stopMoving(this.getGameInstance().getPlayer().getLastDirection());
        // Create dialogues
        Dialogue d1 = new Dialogue("PLEASE HELP ME!. MY FRIEND GOT KIDNAPPED BY SOME DOUCHES!!!", this.getCharacter());
        Dialogue d2 = new Dialogue("PLEASE! YOU HAVE TO HELP ME SAVE HIM", this.getCharacter());
        Dialogue d3 = new Dialogue("Help this person?");
        Choice c1 = new Choice("Yes");
        Choice c2 = new Choice("No");
        ChoicesList choices = new ChoicesList();
        choices.addChoice(c1);
        choices.addChoice(c2);
        Dialogue d4 = new Dialogue("You decided to save this person's friend.");
        Dialogue d5 = new Dialogue("PLEASE! IF WE DON'T HURRY HE WILL BE KILLED.", this.getCharacter());
        Dialogue d6 = new Dialogue("Thank you so much!!! I will join you to fight the bandits.", this.getCharacter());
        Dialogue d7 = new Dialogue("I can take care of myself. I promise I won't be a burden to you.", this.getCharacter());
        Dialogue d8 = new Dialogue(this.getCharacter().getName() + " has joined your team.");
        Dialogue choiceNop1 = new Dialogue("...");
        Dialogue choiceNop2 = new Dialogue("...");
        // Set up events
        d8.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            @Override
            public void trigger() {
                FirstAllyRecruitEvent.this.getGameInstance().getPlayer().getAllyList().add(FirstAllyRecruitEvent.this.getCharacter());
                FirstAllyRecruitEvent.this.getWorld().getNpcList().remove((NPC)FirstAllyRecruitEvent.this.getCharacter());
                FirstAllyRecruitEvent.this.getWorld().getEventList().remove(FirstAllyRecruitEvent.this.getCharacter().getEvent());
                WorldOutside.ally1Recruited = true;
            }
        });
        // Set up dialogue sequence
        d1.setNext(d2);
        d2.setNext(d3);
        d3.setNext(choices);
        c1.setNext(choiceNop1);
        choiceNop1.setNext(d4);
        c2.setNext(choiceNop2);
        choiceNop2.setNext(d5);
        d4.setNext(d6);
        d5.setNext(d4);
        d6.setNext(d7);
        d7.setNext(d8);
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
