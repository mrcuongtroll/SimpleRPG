package world;

import javafx.scene.canvas.GraphicsContext;
import main.SimpleRPG;
import javafx.scene.image.Image;

public abstract class Map {

    private SimpleRPG master;
    private GraphicsContext gc;
    private Image bg;

    public SimpleRPG getMaster() {
        return this.master;
    }
    public GraphicsContext getGraphicsContext() {
        return this.gc;
    }
    public Image getBg() {
        return this.bg;
    }
    protected abstract void tick();

    public abstract void render();

    public Map(SimpleRPG master, String imagePath) {
        this.master = master;
        this.gc = this.master.canvasBackground.getGraphicsContext2D();
        this.bg = new Image(imagePath);
    }
}
