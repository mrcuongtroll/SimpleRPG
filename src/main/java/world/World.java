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
import java.util.ArrayList;

public class World {
    public static final String DOWN = "DOWN";
    public static final String UP = "UP";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    private int dy = 0;
    private int dx = 0;
    private SimpleRPG master;
    private int x = 0;
    private int y = 0;
    private GraphicsContext gc;
    private Image bg;
    public ArrayList<NPC> npcList = new ArrayList<>();
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public SimpleRPG getMaster() {
        return this.master;
    }

    public World(SimpleRPG master, String bgImagePath) {
        this.master = master;
        this.gc = this.master.canvasBackground.getGraphicsContext2D();
        this.bg = new Image(bgImagePath);
        this.npcList.add(new Enemy(this, master, SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 100, 100, 10, 10));
        this.npcList.add(new Enemy(this, master, SimpleRPG.SCREEN_WIDTH/7-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 100, 100, 10, 10));
        this.npcList.add(new Enemy(this, master, SimpleRPG.SCREEN_WIDTH/9-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 100, 100, 10, 10));
    }

    public void render() {
        if (this.master.testPlayer.isSprintable() && this.master.testPlayer.isSprinting()) {
            this.setY(this.getY() + this.dy * Player.SPRINT_SPEED / Player.MOVEMENT_SPEED);
            this.setX(this.getX() + this.dx * Player.SPRINT_SPEED / Player.MOVEMENT_SPEED);
        } else {
            this.setY(this.getY() + this.dy);
            this.setX(this.getX() + this.dx);
        }
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, this.master.canvasBackground.getWidth(), this.master.canvasBackground.getHeight());
        this.gc.drawImage(this.bg, this.x, this.y);
        for (NPC npc : this.npcList) {
            if (npc instanceof Enemy) {
                ((Enemy) npc).chasePlayer();
            }
        }
    }
    public void renderNPC() {
        for (NPC npc : this.npcList) {
            npc.render();
        }
    }

    public void moveNPC(String direction) {
        switch (direction) {
            case World.DOWN -> {
                this.setDyNPC(-Player.MOVEMENT_SPEED);
            }
            case World.UP -> {
                this.setDyNPC(Player.MOVEMENT_SPEED);
            }
            case World.LEFT -> {
                this.setDxNPC(Player.MOVEMENT_SPEED);
            }
            case World.RIGHT -> {
                this.setDxNPC(-Player.MOVEMENT_SPEED);
            }
            default -> {}
        }
    }

    public void move(String direction) {
        switch (direction) {
            case World.DOWN -> {
                this.dy = -Player.MOVEMENT_SPEED;
            }
            case World.UP -> {
                this.dy = Player.MOVEMENT_SPEED;
            }
            case World.LEFT -> {
                this.dx = Player.MOVEMENT_SPEED;
            }
            case World.RIGHT -> {
                this.dx = -Player.MOVEMENT_SPEED;
            }
            default -> {}
        }
        moveNPC(direction);
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDyNPC(int dy) {
        for (NPC npc : this.npcList) {
            npc.setDy(dy);
        }
    }

    public void setDxNPC(int dx) {
        for (NPC npc : this.npcList) {
            npc.setDx(dx);
        }
    }
}
