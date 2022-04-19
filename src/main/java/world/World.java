package world;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.SimpleRPG;

public class World {
    public static final String DOWN = "DOWN";
    public static final String UP = "UP";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";

    private SimpleRPG master;
    private int x;
    private int y;

    private GraphicsContext gc;
    private Image bg;

    public World(SimpleRPG master, String bgImagePath) {
        this.master = master;
        this.gc = this.master.canvas.getGraphicsContext2D();
        this.bg = new Image(bgImagePath);
    }

    public void render() {
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, this.master.canvas.getWidth(), this.master.canvas.getHeight());
        this.gc.drawImage(this.bg, this.x, this.y);
    }

    public void move(String direction) {
        switch (direction) {
            case World.DOWN -> this.y -= 2;
            case World.UP -> this.y += 2;
            case World.LEFT -> this.x += 2;
            case World.RIGHT -> this.x -= 2;
            default -> {
            }
        }
    }
}
