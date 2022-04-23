package world;

import entity.NPC;
import entity.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.SimpleRPG;

import java.io.File;

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
    public NPC testNPC;
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public World(SimpleRPG master, String bgImagePath) {
        this.master = master;
        this.gc = this.master.canvas.getGraphicsContext2D();
        this.bg = new Image(bgImagePath);
        this.testNPC = new NPC(master, 1280/5-16, 720/2-40,
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 100, 100, 10, 10);
    }

    public void render() {
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, this.master.canvas.getWidth(), this.master.canvas.getHeight());
        this.gc.drawImage(this.bg, this.x, this.y);
        this.testNPC.render();
        this.testNPC.chasePlayer();
    }

    public void move(String direction) {
        switch (direction) {
            case World.DOWN -> {
                this.y -= Player.MOVEMENT_SPEED;
                this.testNPC.setY(this.testNPC.getY() - Player.MOVEMENT_SPEED);
            }
            case World.UP -> {
                this.y += Player.MOVEMENT_SPEED;
                this.testNPC.setY(this.testNPC.getY() + Player.MOVEMENT_SPEED);
            }
            case World.LEFT -> {
                this.x += Player.MOVEMENT_SPEED;
                this.testNPC.setX(this.testNPC.getX() + Player.MOVEMENT_SPEED);
            }
            case World.RIGHT -> {
                this.x -= Player.MOVEMENT_SPEED;
                this.testNPC.setX(this.testNPC.getX() - Player.MOVEMENT_SPEED);
            }
            default -> {
            }
        }
    }
}
