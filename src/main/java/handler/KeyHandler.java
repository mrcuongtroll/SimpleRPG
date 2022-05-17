package handler;

import entity.Character;
import entity.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.SimpleRPG;
import world.BattleMap;
import world.World;

public class KeyHandler implements EventHandler<KeyEvent> {

    public SimpleRPG gameInstance;

    public KeyHandler(SimpleRPG simpleRPG) {
        this.gameInstance = simpleRPG;
    }
    @Override
    public void handle(KeyEvent event) {
        if (this.gameInstance.getWorld() instanceof World) {
            if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                // Handle things that happen when keys on the keyboard are pressed
                KeyCode code = event.getCode();
                if (code.equals(KeyCode.DOWN)) {
                    this.gameInstance.getPlayer().move(Character.DOWN);
                } else if (code.equals(KeyCode.UP)) {
                    this.gameInstance.getPlayer().move(Character.UP);
                } else if (code.equals(KeyCode.LEFT)) {
                    this.gameInstance.getPlayer().move(Character.LEFT);
                } else if (code.equals(KeyCode.RIGHT)) {
                    this.gameInstance.getPlayer().move(Character.RIGHT);
                }
                if (code.equals(KeyCode.SHIFT)) {
                    this.gameInstance.getPlayer().sprint();
                }
            } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                // Handle things that happen when keys on the keyboard are released
                KeyCode code = event.getCode();
                if (code.equals(KeyCode.DOWN)) {
                    this.gameInstance.getPlayer().stopMoving(Character.DOWN);
                } else if (code.equals(KeyCode.UP)) {
                    this.gameInstance.getPlayer().stopMoving(Character.UP);
                } else if (code.equals(KeyCode.LEFT)) {
                    this.gameInstance.getPlayer().stopMoving(Character.LEFT);
                } else if (code.equals(KeyCode.RIGHT)) {
                    this.gameInstance.getPlayer().stopMoving(Character.RIGHT);
                }
                if (code.equals(KeyCode.SHIFT)) {
                    this.gameInstance.getPlayer().unSprint();
                }
            }
        } else if (this.gameInstance.getWorld() instanceof BattleMap) {

        }

    }
}
