package handler;

import entity.Character;
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
        if (this.gameInstance.mainPane.isFocused()) {
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
                    if (code.equals(KeyCode.X)) {
                        if (!this.gameInstance.getDialogueRender().isShowing()) {
                            this.gameInstance.getPlayer().interact();
                        }
                    }
                } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                    // Handle things that happen when keys on the keyboard are released
                    KeyCode code = event.getCode();
                    if (code.equals(KeyCode.DOWN)) {
                        if (this.gameInstance.getPlayer().getDy() > 0) {
                            this.gameInstance.getPlayer().stopMoving(Character.DOWN);
                        }
                    } else if (code.equals(KeyCode.UP)) {
                        if (this.gameInstance.getPlayer().getDy() < 0) {
                            this.gameInstance.getPlayer().stopMoving(Character.UP);
                        }
                    } else if (code.equals(KeyCode.LEFT)) {
                        if (this.gameInstance.getPlayer().getDx() < 0) {
                            this.gameInstance.getPlayer().stopMoving(Character.LEFT);
                        }
                    } else if (code.equals(KeyCode.RIGHT)) {
                        if (this.gameInstance.getPlayer().getDx() > 0) {
                            this.gameInstance.getPlayer().stopMoving(Character.RIGHT);
                        }
                    }
                    if (code.equals(KeyCode.SHIFT)) {
                        this.gameInstance.getPlayer().unSprint();
                    }
                }
            } else if (this.gameInstance.getWorld() instanceof BattleMap) {

            }
        }
    }
}
