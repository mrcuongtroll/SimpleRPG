package event;

import entity.Character;
import main.SimpleRPG;
import world.Tile;
import world.World;

import java.awt.*;

public abstract class Event {
    public static final String TRIGGER_TYPE_AUTO_SINGLE = "AUTO_SINGLE";
    public static final String TRIGGER_TYPE_AUTO_MULTI = "AUTO_MULTI";
    public static final String TRIGGER_TYPE_TOUCH = "TOUCH";
    public static final String TRIGGER_TYPE_INTERACT = "INTERACT";

    private final World world;
    private final SimpleRPG gameInstance;
    private final String triggerType;
    private Character character;
    private Rectangle rect;
    private boolean triggered = false;

    public Rectangle getRect() {
        return this.rect;
    }

    public Event(World world, String triggerType) {
        this.world = world;
        this.gameInstance = world.getMaster();
        this.triggerType = triggerType;
    }
    public Event(World world, String triggerType, double x, double y) {
        this(world, triggerType);
        this.rect = new Rectangle((int)x, (int)y, Tile.TILE_SIZE*2, Tile.TILE_SIZE*2);
    }
    public Event(World world, String triggerType, Character character) {
        this(world, triggerType);
        this.character = character;
        this.rect = character.getRect();
    }
    public Event(World world, String triggerType, Tile tile) {
        this(world, triggerType);
        this.rect = tile.getRect();
    }

    public void tick() {
        switch (this.triggerType) {
            case Event.TRIGGER_TYPE_TOUCH:
                // This should trigger only once each time the player steps on the event's rectangle
                if (this.rect.intersects(this.gameInstance.getPlayer().getRect())) {
                    if (!this.triggered) {
                        this.trigger();
                        this.triggered = true;
                    }
                } else {
                    this.triggered = false;
                }
                break;
            case Event.TRIGGER_TYPE_AUTO_SINGLE:
                // This can only be triggered once
                if (!this.triggered) {
                    this.trigger();
                    this.triggered = true;
                }
                break;
            case Event.TRIGGER_TYPE_AUTO_MULTI:
                // This can trigger multiple times, but make sure to implement auto-multi trigger events to trigger
                // only once at a time window.
                this.trigger();
                break;
        }
        // Interactive trigger type needs to be triggered manually
    }

    public abstract void trigger();
}