package world;

import entity.*;
import entity.Character;
import event.BattleEvent;
import event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.SimpleRPG;
import saveload.CharacterSave;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class World extends Map {
    public static final String DOWN = "DOWN";
    public static final String UP = "UP";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    private double dy;
    private double dx;
    private double x;
    private double y;
    private String bgImagePath;
    private String overlayImagePath;
    private String shadingImagePath;
    private String maskPath;
    private GraphicsContext gcOverlay;
    private GraphicsContext gcShading;
    private Image overlayImage;
    private Image shadingImage;
    private BufferedImage mask;
    private int[][] maskArray;
    private ArrayList<Tile> tileList = new ArrayList<Tile>();
    private ArrayList<NPC> npcList = new ArrayList<NPC>();
    private ArrayList<Event> eventList = new ArrayList<Event>();

    private boolean isPlayerSprinting = false;
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
    public GraphicsContext getGraphicsContextOverlay() { return this.gcOverlay; }
    public GraphicsContext getGraphicsContextShading() { return this.gcShading; }
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
//            npc.setY(npc.getY() + dy);
            npc.setYDisplay(npc.getYDisplay() + dy);
        }
    }
    public void updateXNPC(double dx) {
        for (NPC npc : this.npcList) {
//            npc.setX(npc.getX() + dx);
            npc.setXDisplay(npc.getXDisplay() + dx);
        }
    }
    public ArrayList<Tile> getTileList() {
        return this.tileList;
    }
    public ArrayList<NPC> getNpcList() {
        return this.npcList;
    }
    public void setNpcList(ArrayList<NPC> npcList) {
        this.npcList = npcList;
    }
    public ArrayList<Event> getEventList() {
        return this.eventList;
    }
    public String getBgImagePath() {
        return bgImagePath;
    }
    public String getOverlayImagePath() {
        return overlayImagePath;
    }
    public String getShadingImagePath() {
        return shadingImagePath;
    }
    public String getMaskPath() {
        return maskPath;
    }

    public World(SimpleRPG master, double playerX, double playerY, String bgImagePath, String overlayImagePath, String shadingImagePath, String maskPath) {
        super(master, playerX, playerY, bgImagePath);
        this.bgImagePath = bgImagePath;
        this.overlayImagePath = overlayImagePath;
        this.shadingImagePath = shadingImagePath;
        this.maskPath = maskPath;
        // Configure world coordinates
        if (master.getPlayer().getX() <= (double)SimpleRPG.SCREEN_WIDTH/2 - (double) Player.SPRITE_WIDTH/2) {
            this.x = 0;
        } else if (master.getPlayer().getX() >= this.getBg().getWidth() - (double)SimpleRPG.SCREEN_WIDTH/2 - (double)Player.SPRITE_WIDTH/2) {
            this.x = -(this.getBg().getWidth() - (double)SimpleRPG.SCREEN_WIDTH);
        } else {
            this.x = -(master.getPlayer().getX() - (double)SimpleRPG.SCREEN_WIDTH/2 + (double) Player.SPRITE_WIDTH/2);
        }
        if (master.getPlayer().getY() <= (double)SimpleRPG.SCREEN_HEIGHT/2 - (double) Player.SPRITE_HEIGHT/2) {
            this.y = 0;
        } else if (master.getPlayer().getY() >= this.getBg().getHeight() - (double)SimpleRPG.SCREEN_HEIGHT/2 - (double)Player.SPRITE_HEIGHT/2) {
            this.y = -(this.getBg().getHeight() - (double)SimpleRPG.SCREEN_HEIGHT);
        } else {
            this.y = -(master.getPlayer().getY() - (double)SimpleRPG.SCREEN_HEIGHT/2 + (double) Player.SPRITE_HEIGHT/2);
        }

        // Grab canvases for rendering
        this.gcOverlay = this.getMaster().canvasOverlay.getGraphicsContext2D();
        this.gcShading = this.getMaster().canvasShading.getGraphicsContext2D();
        this.overlayImage = new Image(String.valueOf(getClass().getResource(overlayImagePath)));
        this.shadingImage = new Image(String.valueOf(getClass().getResource(shadingImagePath)));

        // Load collision mask
        try {
            this.mask = ImageIO.read(getClass().getResource(maskPath));
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
        this.getGraphicsContext().clearRect(0, 0, SimpleRPG.SCREEN_WIDTH, SimpleRPG.SCREEN_HEIGHT);
        this.gcOverlay.clearRect(0, 0, SimpleRPG.SCREEN_WIDTH, SimpleRPG.SCREEN_HEIGHT);
        this.gcShading.clearRect(0, 0, SimpleRPG.SCREEN_WIDTH, SimpleRPG.SCREEN_HEIGHT);
        this.tick();
        this.getGraphicsContext().setFill(Color.BLACK);
        this.getGraphicsContext().fillRect(0, 0, this.getMaster().canvasBackground.getWidth(), this.getMaster().canvasBackground.getHeight());
        this.getGraphicsContext().drawImage(this.getBg(), this.x, this.y);
        this.gcOverlay.drawImage(this.overlayImage, this.x, this.y);
        this.gcShading.drawImage(this.shadingImage, this.x, this.y);
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
        if (player.getRelativeX() >= (double)SimpleRPG.SCREEN_WIDTH/2 - (double)Player.SPRITE_WIDTH/2 && player.getRelativeX() <= this.getBg().getWidth() - (double)SimpleRPG.SCREEN_WIDTH/2 - (double)Player.SPRITE_WIDTH/2) {
            if (canMoveH) {
                this.x += this.dx;
                // Also update NPCs' x-value to keep their relative position consistent
                this.updateXNPC(this.dx);
            }
        }
        // Check the y-axis
        if (player.getRelativeY() >= (double)SimpleRPG.SCREEN_HEIGHT/2 - (double)Player.SPRITE_HEIGHT/2 && player.getRelativeY() <= this.getBg().getHeight() - (double)SimpleRPG.SCREEN_HEIGHT/2 - (double)Player.SPRITE_HEIGHT/2) {
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

    public abstract void initiateNPCList();

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
