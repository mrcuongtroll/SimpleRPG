package event;

import dialogue.Dialogue;
import entity.Character;
import world.Tile;
import world.World;

public class LambSauceEvent extends Event {

    public LambSauceEvent(World world) {
        super(world, Event.TRIGGER_TYPE_INTERACT);
    }

    public LambSauceEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_INTERACT, x, y);
    }

    public LambSauceEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_INTERACT, character);
    }

    public LambSauceEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_INTERACT, tile);
    }

    @Override
    public void trigger() {
        this.getGameInstance().getPlayer().stopMoving(this.getGameInstance().getPlayer().getLastDirection());
        Dialogue d1 = new Dialogue("Inside these bottles is lamb sauce!");
        Dialogue d2 = new Dialogue("(Why is there lamb sauce here?)",
                this.getGameInstance().getPlayer());
        Dialogue d3 = new Dialogue("(And who would have stored lamb sauce in ink bottles in the first place?)",
                this.getGameInstance().getPlayer());
        Dialogue d4 = new Dialogue("(The chef must be so mad right now. I can imagine him yelling \"WHERE IS THE LAMB SAUCE?\".)",
                this.getGameInstance().getPlayer());
        d1.setNext(d2);
        d2.setNext(d3);
        d3.setNext(d4);
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
