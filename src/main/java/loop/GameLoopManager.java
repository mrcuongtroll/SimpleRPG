package loop;


import entity.Player;
import javafx.animation.AnimationTimer;
import world.World;

public class GameLoopManager extends AnimationTimer {

    private World currentWorld;
    private Player player;

    public GameLoopManager(World initWorld, Player player) {
        this.currentWorld = initWorld;
        this.player = player;
    }

    @Override
    public void handle(long currentTime) {
        this.currentWorld.render();
        this.player.render();
    }
}
