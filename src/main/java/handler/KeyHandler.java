package handler;

import entity.Player;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.SimpleRPG;
import world.World;

public class KeyHandler implements EventHandler<KeyEvent> {

    public SimpleRPG gameInstance;

    public KeyHandler(SimpleRPG gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code.equals(KeyCode.DOWN)) {
            this.gameInstance.testWorld.move(World.DOWN);
            this.gameInstance.testPlayer.changeFrame(Player.DOWN_IMAGE_PATH);
        } else if (code.equals(KeyCode.UP)) {
            this.gameInstance.testWorld.move(World.UP);
            this.gameInstance.testPlayer.changeFrame(Player.UP_IMAGE_PATH);
        } else if (code.equals(KeyCode.LEFT)) {
            this.gameInstance.testWorld.move(World.LEFT);
            this.gameInstance.testPlayer.changeFrame(Player.LEFT_IMAGE_PATH);
        } else if (code.equals(KeyCode.RIGHT)) {
            this.gameInstance.testWorld.move(World.RIGHT);
            this.gameInstance.testPlayer.changeFrame(Player.RIGHT_IMAGE_PATH);
        }
    }
}
