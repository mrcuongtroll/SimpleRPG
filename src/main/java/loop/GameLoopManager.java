package loop;


import entity.Player;
import javafx.animation.AnimationTimer;
import main.HUD;
import main.SimpleRPG;
import world.World;

public class GameLoopManager extends AnimationTimer {

    private World currentWorld;
    private Player player;
    private long lastUpdateTime = 0;
    private SimpleRPG master;

    private HUD hud;
    public GameLoopManager(SimpleRPG master, HUD hud, World initWorld, Player player) {
        this.master = master;
        this.hud = hud;
        this.currentWorld = initWorld;
        this.player = player;
    }

    @Override
    public void handle(long currentTime) {
        this.currentWorld.render();
        this.lastUpdateTime = currentTime;
        this.master.canvasMiddle.getGraphicsContext2D().clearRect(0, 0,
                master.canvasMiddle.getWidth(), master.canvasMiddle.getHeight());
        this.currentWorld.renderNPC();
        this.player.render();
        this.hud.render();
    }
}
