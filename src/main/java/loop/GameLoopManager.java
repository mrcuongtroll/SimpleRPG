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
    private SimpleRPG gameInstance;
    private HUD hud;

    public GameLoopManager(SimpleRPG gameInstance) {
        this.gameInstance = gameInstance;
        this.hud = gameInstance.getMainHUD();
        this.currentWorld = gameInstance.getWorld();
        this.player = gameInstance.getPlayer();
    }

    @Override
    public void handle(long currentTime) {
        this.currentWorld.render();
        this.lastUpdateTime = currentTime;
        this.gameInstance.canvasMiddle.getGraphicsContext2D().clearRect(0, 0,
                gameInstance.canvasMiddle.getWidth(), gameInstance.canvasMiddle.getHeight());
        this.currentWorld.renderNPC();
        this.player.render();
        this.hud.render();
    }
}
