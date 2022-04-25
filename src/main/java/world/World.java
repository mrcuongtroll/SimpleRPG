package world;

import entity.Enemy;
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

    private int dy = 0;
    private int dx = 0;
    private SimpleRPG master;
    private int x;
    private int y;
    private GraphicsContext gc;
    private Image bg;
    public Enemy testNPC;
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public World(SimpleRPG master, String bgImagePath) {
        this.master = master;
        this.gc = this.master.canvasBackground.getGraphicsContext2D();
        this.bg = new Image(bgImagePath);
        this.testNPC = new Enemy(master, 1280/5-16, 720/2-40,
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 100, 100, 10, 10);
    }

    public void render() {
        this.y += this.dy;
        this.x += this.dx;
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, this.master.canvasBackground.getWidth(), this.master.canvasBackground.getHeight());
        this.gc.drawImage(this.bg, this.x, this.y);
        this.testNPC.chasePlayer();
    }
    public void renderNPC() {
        this.testNPC.render();
    }

    public void move(String direction) {
        switch (direction) {
            case World.DOWN -> {
                this.dy = -Player.MOVEMENT_SPEED;
                this.testNPC.setDy(-Player.MOVEMENT_SPEED);
            }
            case World.UP -> {
                this.dy = Player.MOVEMENT_SPEED;
                this.testNPC.setDy(Player.MOVEMENT_SPEED);
            }
            case World.LEFT -> {
                this.dx = Player.MOVEMENT_SPEED;
                this.testNPC.setDx(Player.MOVEMENT_SPEED);
            }
            case World.RIGHT -> {
                this.dx = -Player.MOVEMENT_SPEED;
                this.testNPC.setDx(-Player.MOVEMENT_SPEED);
            }
            default -> {
            }
        }
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
}
