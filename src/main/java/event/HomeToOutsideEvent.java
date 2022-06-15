package event;

import entity.Character;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import main.SimpleRPG;
import world.Tile;
import world.World;
import world.WorldOutside;

public class HomeToOutsideEvent extends Event {
    public HomeToOutsideEvent(World world) {
        super(world, Event.TRIGGER_TYPE_TOUCH);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, 312, this.getRect().getY()) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    public HomeToOutsideEvent(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_TOUCH, x, y);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, 312, this.getRect().getY()) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    public HomeToOutsideEvent(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_TOUCH, character);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, 312, this.getRect().getY()) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    public HomeToOutsideEvent(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_TOUCH, tile);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, 312, this.getRect().getY()) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    @Override
    public void trigger() {
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
}
