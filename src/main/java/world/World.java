package world;

import entity.Enemy;
import entity.NPC;
import entity.Player;
import event.BattleEvent;
import event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.SimpleRPG;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class World extends Map {
    public static final String DOWN = "DOWN";
    public static final String UP = "UP";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    private double dy;
    private double dx;
    private double x;
    private double y;
    private BufferedImage mask;
    private int[][] maskArray;
    private ArrayList<Tile> tileList = new ArrayList<Tile>();
    public ArrayList<NPC> npcList = new ArrayList<NPC>();
    public ArrayList<Event> eventList = new ArrayList<Event>();

    private boolean isPlayerSprinting = false;
    private GraphicsContext gc;
    private Image bg;
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getDx() {
        return this.dx;
    }
    public double getDy() {
        return this.dy;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setDy(double dy) {
        this.dy = dy;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
    public void updateYNPC(double dy) {
        for (NPC npc : this.npcList) {
            npc.setY(npc.getY() + dy);
        }
    }
    public void updateXNPC(double dx) {
        for (NPC npc : this.npcList) {
            npc.setX(npc.getX() + dx);
        }
    }
    public ArrayList<Tile> getTileList() {
        return this.tileList;
    }

    public World(SimpleRPG master, String bgImagePath, String maskPath) {
        super(master, bgImagePath);
        this.npcList.add(new Enemy(this, master, SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 100, 100, 100, 100, 100, 10));
        this.npcList.add(new Enemy(this, master, SimpleRPG.SCREEN_WIDTH/7-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 100, 100, 100, 100, 100, 10));
        this.npcList.add(new Enemy(this, master, SimpleRPG.SCREEN_WIDTH/9-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 100, 100, 100, 100, 100, 10));
        for (NPC npc: this.npcList) {
            Event enemyEvent = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, npc);
            npc.setEvent(enemyEvent);
            this.eventList.add(enemyEvent);
        }

        // Load collision mask
        try {
            this.mask = ImageIO.read(new File(maskPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Convert mask from image to 2D array of 0's and 1's
        this.maskArray = new int[(int) this.getBg().getWidth()][(int) this.getBg().getHeight()];
        for (int i = 0; i < (int) this.getBg().getWidth(); i++) {
            for (int j = 0; j < (int) this.getBg().getHeight(); j++)
                if (mask.getRGB(i, j) == -16777216) {
                    this.maskArray[i][j] = 0;
                } else if (mask.getRGB(i, j) == -1) {
                    this.maskArray[i][j] = 1;
                }
        }
        // Convert 2D array of 0's and 1's to List of tiles
        // For each column
        for (int i = 0; i < this.getBg().getWidth(); i += Tile.TILE_SIZE) {
            // For each row
            for (int j = 0; j < this.getBg().getHeight(); j += Tile.TILE_SIZE) {
                // Solid if the number of 0's is greater than the number of 1's and vice versa
                int solidCount = 0;
                for (int k = i; k < i + Tile.TILE_SIZE; k++) {
                    for (int l = j; l < j + Tile.TILE_SIZE; l++) {
                        try {
                            if (this.maskArray[k][l] == 1) {
                                solidCount += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {}
                    }
                }
                if (solidCount >= Tile.TILE_SIZE*Tile.TILE_SIZE/2) {
                    this.tileList.add(new Tile(i, j, false));
                } else {
                    this.tileList.add(new Tile(i, j, true));
                }
            }
        }
    }

    @Override
    public void render() {
        this.tick();
        this.getGraphicsContext().setFill(Color.BLACK);
        this.getGraphicsContext().fillRect(0, 0, this.getMaster().canvasBackground.getWidth(), this.getMaster().canvasBackground.getHeight());
        this.getGraphicsContext().drawImage(this.getBg(), this.x, this.y);
    }
    @Override
    protected void tick() {
        for (Event e: this.eventList) {
            e.tick();
        }
        boolean canMoveH = true;
        boolean canMoveV = true;
        Player player = this.getMaster().getPlayer();
        for (Tile tile: this.tileList) {
            if (!(player.getTile() == tile)) {
                if (tile.isSolid()) {
                    if (tile.getRect().intersects(player.getX() - dx, player.getY() + player.getImage().getHeight() - Tile.TILE_SIZE, player.getRect().getWidth(), player.getRect().getHeight())) {
                        canMoveH = false;
                    }
                    if (tile.getRect().intersects(player.getX(), player.getY() + player.getImage().getHeight() - Tile.TILE_SIZE - dy, player.getRect().getWidth(), player.getRect().getHeight())) {
                        canMoveV = false;
                    }
                }
            }
        }
        // Check when not to scroll the map
        // Idea: The map will not scroll if the player is near the side of the map
        // Check the x-axis:
        if (player.getRelativeX() >= SimpleRPG.SCREEN_WIDTH || player.getRelativeX() <= this.getBg().getWidth() - SimpleRPG.SCREEN_WIDTH) {
            if (canMoveH) {
                this.x += this.dx;
                // Also update NPCs' x-value to keep their relative position consistent
                this.updateXNPC(this.dx);
            }
        }
        // Check the y-axis
        if (player.getRelativeY() >= SimpleRPG.SCREEN_HEIGHT || player.getRelativeY() <= this.getBg().getHeight() - SimpleRPG.SCREEN_HEIGHT) {
            if (canMoveV) {
                this.y += this.dy;
                // Also update NPCs' x-value to keep their relative position consistent
                this.updateYNPC(this.dy);
            }
        }
    }

    public void removeNPC(NPC npc) {
        this.eventList.remove(npc.getEvent());
        this.npcList.remove(npc);
    }

    public void renderNPC() {
        for (NPC npc : this.npcList) {
            npc.render();
        }
    }

//    public void moveNPC(String direction) {
//        switch (direction) {
//            case World.DOWN -> {
//                this.setDyNPC(-Player.MOVEMENT_SPEED);
//            }
//            case World.UP -> {
//                this.setDyNPC(Player.MOVEMENT_SPEED);
//            }
//            case World.LEFT -> {
//                this.setDxNPC(Player.MOVEMENT_SPEED);
//            }
//            case World.RIGHT -> {
//                this.setDxNPC(-Player.MOVEMENT_SPEED);
//            }
//            default -> {}
//        }
//    }
//
//    public void move(String direction) {
//        switch (direction) {
//            case World.DOWN -> {
//                this.dy = -Player.MOVEMENT_SPEED;
//            }
//            case World.UP -> {
//                this.dy = Player.MOVEMENT_SPEED;
//            }
//            case World.LEFT -> {
//                this.dx = Player.MOVEMENT_SPEED;
//            }
//            case World.RIGHT -> {
//                this.dx = -Player.MOVEMENT_SPEED;
//            }
//            default -> {}
//        }
//        moveNPC(direction);
//    }

}
