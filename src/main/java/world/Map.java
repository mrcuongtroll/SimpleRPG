package world;

import javafx.scene.canvas.GraphicsContext;
import main.SimpleRPG;
import javafx.scene.image.Image;

public abstract class Map {

    private static SimpleRPG master;
    private GraphicsContext gc;
    private Image bg;

    public static SimpleRPG getMaster() {
        return master;
    }
    public GraphicsContext getGraphicsContext() {
        return this.gc;
    }
    public Image getBg() {
        return this.bg;
    }
    protected abstract void tick();

    public abstract void render();

    public Map(SimpleRPG gameInstance, String imagePath) {
        master = gameInstance;
        this.gc = master.canvasBackground.getGraphicsContext2D();
        this.bg = new Image(imagePath);
    }
}
