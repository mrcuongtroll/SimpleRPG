package event;

import dialogue.Dialogue;
import entity.Character;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import main.SimpleRPG;
import world.Tile;
import world.World;
import world.WorldOutside;
import world.WorldOutside2;

public class OutsideToOutside2Event extends Event {
    public OutsideToOutside2Event(World world) {
        super(world, Event.TRIGGER_TYPE_TOUCH);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, this.getRect().getX(), 1381) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    public OutsideToOutside2Event(World world, double x, double y) {
        super(world, Event.TRIGGER_TYPE_TOUCH, x, y);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, this.getRect().getX(), 1381) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    public OutsideToOutside2Event(World world, Character character) {
        super(world, Event.TRIGGER_TYPE_TOUCH, character);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, this.getRect().getX(), 1381) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    public OutsideToOutside2Event(World world, Tile tile) {
        super(world, Event.TRIGGER_TYPE_TOUCH, tile);
        this.disable();
        Event self = this;
        this.getWorld().getEventList().add(new Event(world, Event.TRIGGER_TYPE_TOUCH, this.getRect().getX(), 1381) {
            @Override
            public void trigger() {
                self.enable();
            }
        });
    }

    @Override
    public void trigger() {
        this.getGameInstance().getPlayer().stopMoving(this.getGameInstance().getPlayer().getLastDirection());
        if (WorldOutside.ally2Recruited) {
            // Get game instance
            SimpleRPG gameInstance = this.getGameInstance();
            // Stop the player character
            gameInstance.getPlayer().stopMoving(Character.DOWN);

            // Fade out
            FadeTransition ft = new FadeTransition(Duration.millis(1000), gameInstance.mainPane);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();

            // Initialize outside map
            gameInstance.setWorld(new WorldOutside2(gameInstance));

            // Fade back in
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
        } else {
            if (WorldOutside.ally1Recruited) {
                Dialogue d1 = new Dialogue("You have to help Ally 1 save his friend first.");
                this.getGameInstance().getDialogueRender().setDialogue(d1);
            } else {
                Dialogue d1 = new Dialogue("You should try exploring this area before you advance.");
                this.getGameInstance().getDialogueRender().setDialogue(d1);
            }
        }
    }
}
