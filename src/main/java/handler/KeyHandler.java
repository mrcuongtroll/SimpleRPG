package handler;

import entity.Character;
import entity.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.SimpleRPG;
import world.World;

public class KeyHandler implements EventHandler<KeyEvent> {

    private BooleanProperty upPressed = new SimpleBooleanProperty();
    private BooleanProperty downPressed = new SimpleBooleanProperty();
    private BooleanProperty rightPressed = new SimpleBooleanProperty();
    private BooleanProperty leftPressed = new SimpleBooleanProperty();

    private BooleanProperty shiftPressed = new SimpleBooleanProperty();

    public SimpleRPG gameInstance;

    public KeyHandler(SimpleRPG simpleRPG) {
        this.gameInstance = simpleRPG;
    }
    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            // Handle things that happen when keys on the keyboard are pressed
            KeyCode code = event.getCode();
            if (code.equals(KeyCode.DOWN)) {
                downPressed.set(true);
                this.gameInstance.testWorld.move(World.DOWN);
                this.gameInstance.testPlayer.setDy(Player.MOVEMENT_SPEED);
            } else if (code.equals(KeyCode.UP)) {
                upPressed.set(true);
                this.gameInstance.testWorld.move(World.UP);
                this.gameInstance.testPlayer.setDy(-Player.MOVEMENT_SPEED);
            } else if (code.equals(KeyCode.LEFT)) {
                leftPressed.set(true);
                this.gameInstance.testWorld.move(World.LEFT);
                this.gameInstance.testPlayer.setDx(-Player.MOVEMENT_SPEED);
            } else if (code.equals(KeyCode.RIGHT)) {
                rightPressed.set(true);
                this.gameInstance.testWorld.move(World.RIGHT);
                this.gameInstance.testPlayer.setDx(Player.MOVEMENT_SPEED);
            } else if (event.isShiftDown() || code.equals(KeyCode.SHIFT)) {
                shiftPressed.set(true);
                this.gameInstance.testWorld.setPlayerSprinting();
            }
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            // Handle things that happen when keys on the keyboard are released
            KeyCode code = event.getCode();

            if (code.equals(KeyCode.DOWN)) {
                this.gameInstance.testWorld.setDy(0);
                this.gameInstance.testWorld.testNPC.setDy(0);
                this.gameInstance.testPlayer.setDy(0);
                this.gameInstance.testPlayer.defaultFrame(Character.DOWN_IMAGE_PATH);
            } else if (code.equals(KeyCode.UP)) {
                this.gameInstance.testWorld.setDy(0);
                this.gameInstance.testWorld.testNPC.setDy(0);
                this.gameInstance.testPlayer.setDy(0);
                this.gameInstance.testPlayer.setCurrentFrame(Character.NUM_IMAGE_FRAME);
                this.gameInstance.testPlayer.defaultFrame(Character.UP_IMAGE_PATH);
            } else if (code.equals(KeyCode.LEFT)) {
                this.gameInstance.testWorld.setDx(0);
                this.gameInstance.testWorld.testNPC.setDx(0);
                this.gameInstance.testPlayer.setDx(0);
                this.gameInstance.testPlayer.defaultFrame(Character.LEFT_IMAGE_PATH);
            } else if (code.equals(KeyCode.RIGHT)) {
                this.gameInstance.testWorld.setDx(0);
                this.gameInstance.testWorld.testNPC.setDx(0);
                this.gameInstance.testPlayer.setDx(0);
                this.gameInstance.testPlayer.defaultFrame(Character.RIGHT_IMAGE_PATH);
            } else if (code.equals(KeyCode.SHIFT)) {
                this.gameInstance.testWorld.unSetPlayerSprinting();
            }
        }
    }
}
