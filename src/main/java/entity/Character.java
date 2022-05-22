package entity;

import combat.action.Action;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.SimpleRPG;
import world.Tile;
import world.World;

import java.awt.*;

public abstract class Character {
    public static final String DEFAULT_IMAGE_PATH = "/default/";
    public static final String DOWN_IMAGE_PATH = "/move_down/";
    public static final String UP_IMAGE_PATH = "/move_up/";
    public static final String LEFT_IMAGE_PATH = "/move_left/";
    public static final String RIGHT_IMAGE_PATH = "/move_right/";
    public static final String BATTLE_IMAGE_PATH = "/battle/";
    public static final int NUM_IMAGE_FRAME = 4;

    public static final String DOWN = "DOWN";
    public static final String UP = "UP";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final int MINIMUM_SPEED = 0;

//     public static final String DOWN = "/move_down/";
//     public static final String UP = "/move_up/";
//     public static final String LEFT = "/move_left/";
//     public static final String RIGHT = "/move_right/";

    private SimpleRPG master;
    private String name;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private Rectangle rect;
    private boolean isSolid;
    private Tile tile;
    private double movementSpeed;
    private int lastMove;
    private int level;
    private int attackSpeed;
    private Action[] combatActionList;
    private int turnProgress = 0;
    private int manaPoint;
    private int healthPoint;
    private int maxHealthPoint;
    private int maxManaPoint;
    private String imagePath;
    private int currentFrame;
    private String lastDirection;
    private double lastRelativeX;
    private double lastRelativeY;
    private Image image;
    private GraphicsContext gc;
    public GraphicsContext getGraphicContext() {
        return this.gc;
    }
    public SimpleRPG getMaster() {
        return this.master;
    }
    public Image getImage() {
        return this.image;
    }
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
    public double getRelativeX() {
        return this.x - ((World) this.master.getWorld()).getX();
    }
    public double getRelativeY() {
        return this.y - ((World) this.master.getWorld()).getY();
    }
    public double getMovementSpeed() {
        return this.movementSpeed;
    }
    public Rectangle getRect() {
        return this.rect;
    }
    public Tile getTile() {return this.tile;}
    public double getLastX() {
        return this.lastRelativeX;
    }
    public double getLastY() {
        return this.lastRelativeY;
    }
    public void setLastX(double x) {
        this.lastRelativeX = x;
    }
    public void setLastY(double y) {
        this.lastRelativeY = y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
    public void setDy(double dy) {
        this.dy = dy;
    }
    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
    public int getHealthPoint(){
        return this.healthPoint;
    }
    public int getManaPoint(){
        return this.manaPoint;
    }
    public int getAttackSpeed(){
        return this.attackSpeed;
    }
    public abstract int getAttackPoint();
    public abstract int getDefensePoint();
    public void advanceTurn(){
        this.turnProgress += this.attackSpeed;
    }
    public int getTurnProgress() {
        return turnProgress;
    }
    public void setTurnProgress(int turnProgress) {
        this.turnProgress = turnProgress;
    }
    public Action[] getActionList() {
        return this.combatActionList;
    }
    public void setActionList(Action[] actionList) {
        this.combatActionList = actionList;
    }
    public void increaseHealthPoint(int amount){
        healthPoint += amount;
        if (healthPoint >= maxHealthPoint) {
            healthPoint = maxHealthPoint;
        }
    }
    public void increaseManaPoint(int amount){
        manaPoint += amount;
        if (manaPoint >= maxManaPoint) {
            manaPoint = maxManaPoint;
        }
    }
    public void setHealthPoint(int healthPoint){
        this.healthPoint = healthPoint;
    }
    public void setManaPoint(int manaPoint){
        this.manaPoint = manaPoint;
    }
    public String getName() {
        return this.name;
    }
    public int getCurrentFrame() {
        return this.currentFrame;
    }
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }
    public String getLastDirection() {
        return this.lastDirection;
    }
    public void setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
    }
    public String getImagePath() {
        return this.imagePath;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public Character(SimpleRPG master, int x, int y, String name, String imagePath,

                     int width, int height, int level, int attackSpeed, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, boolean isSolid) {

        this.master = master;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.lastRelativeX = x;
        this.lastRelativeY = y;
        this.name = name;
        this.imagePath = imagePath;
        this.currentFrame = 1;
        this.lastDirection = DEFAULT_IMAGE_PATH;
        this.image = new Image(imagePath + DEFAULT_IMAGE_PATH + "1.png");
        this.gc = this.master.canvasMiddle.getGraphicsContext2D();
        this.level = level;
        this.attackSpeed = attackSpeed;
        this.healthPoint = healthPoint;
        this.manaPoint = manaPoint;

        this.maxHealthPoint = maxHealthPoint;
        this.maxManaPoint = maxManaPoint;

        this.isSolid = isSolid;
        this.rect = new Rectangle((int)this.x, (int)(y+this.image.getHeight()-Tile.TILE_SIZE), 2*Tile.TILE_SIZE, Tile.TILE_SIZE);
        this.tile = new Tile(this.rect, isSolid);
        if (this.getMaster().getWorld() instanceof World) {
            ((World)master.getWorld()).getTileList().add(this.tile);
        }
    }
    public Character(SimpleRPG master, int x, int y, String name, String imagePath,
                     int width, int height, int level, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        this(master, x, y, name, imagePath, width, height, level, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, true);

    }

    public void render() {
        this.tick();
        this.gc.drawImage(this.image, this.x, this.y);
    }
    protected void tick() {
        boolean canMoveH = true;
        boolean canMoveV = true;
        World world = (World) this.master.getWorld();
        for (Tile tile: world.getTileList()) {
            if (!(this.tile == tile)) {
                if (this.tile.isSolid()) {
                    if (tile.isSolid()) {
                        if (tile.getRect().intersects(this.x + dx, y + this.image.getHeight() - Tile.TILE_SIZE, this.rect.getWidth(), this.rect.getHeight())) {
                            canMoveH = false;
                        }
                        if (tile.getRect().intersects(this.x, y + this.image.getHeight() - Tile.TILE_SIZE + dy, this.rect.getWidth(), this.rect.getHeight())) {
                            canMoveV = false;
                        }
                    }
                } else {
                    if (!(tile == this.getMaster().getPlayer().getTile())) {
                        if (tile.isSolid()) {
                            if (tile.getRect().intersects(this.x + dx, y + this.image.getHeight() - Tile.TILE_SIZE, this.rect.getWidth(), this.rect.getHeight())) {
                                canMoveH = false;
                            }
                            if (tile.getRect().intersects(this.x, y + this.image.getHeight() - Tile.TILE_SIZE + dy, this.rect.getWidth(), this.rect.getHeight())) {
                                canMoveV = false;
                            }
                        }
                    }
                }
            }
        }
        if (canMoveH) {
            this.x += this.dx;
        }
        if (canMoveV) {
            this.y += this.dy;
        }
        this.rect.setBounds((int)this.x, (int)(y+this.image.getHeight()-Tile.TILE_SIZE), (int)this.rect.getWidth(), (int)this.rect.getHeight());
        // TODO: Refine relative position for checking collision and stuff
        // Handle frame changing
        if (this.getDy() == 0) {
            if (this.getDx() > 0) {
                this.changeFrame(Character.RIGHT_IMAGE_PATH);
            } else if (this.getDx() < 0) {
                this.changeFrame(Character.LEFT_IMAGE_PATH);
            }
        } else if (this.getDx() == 0) {
            if (this.getDy() > 0) {
                this.changeFrame(Character.DOWN_IMAGE_PATH);
            } else if (this.getDy() < 0) {
                this.changeFrame(Character.UP_IMAGE_PATH);
            }
        } else if (this.getDx() != 0 && this.getDy() != 0) {
            if (this.getDx() > 0) {
                this.changeFrame(Character.RIGHT_IMAGE_PATH);
            } else if (this.getDx() < 0) {
                this.changeFrame(Character.LEFT_IMAGE_PATH);
            }
        }
    }

    public void changeFrame(String direction) {
        this.lastMove += this.getMovementSpeed()*this.getMovementSpeed();
        if (this.lastMove > 12 * this.getMovementSpeed()) {
            this.lastMove = 0;
            if (direction.equals(DEFAULT_IMAGE_PATH)) {
                this.setCurrentFrame(1);
            } else if (direction.equals(this.getLastDirection())){
                this.currentFrame += 1;
                if (this.currentFrame > 4) {
                    this.currentFrame = 1;
                }
            } else {
                this.setLastDirection(direction);
                this.setCurrentFrame(1);
            }
            this.image = new Image(this.imagePath + direction + this.currentFrame + ".png");
        }
    }

    public void defaultFrame(String direction) {
        this.currentFrame = NUM_IMAGE_FRAME;
        this.image = new Image(this.imagePath + direction + currentFrame + ".png");
        this.lastMove = (int) (12 * this.getMovementSpeed()) + 1;
    }

    public void move(String direction) {
        switch (direction) {
            case Character.DOWN -> {
                this.dy = this.movementSpeed;
            }
            case Character.UP -> {
                this.dy = -this.movementSpeed;
            }
            case Character.LEFT -> {
                this.dx = -this.movementSpeed;
            }
            case Character.RIGHT -> {
                this.dx = this.movementSpeed;
            }
            default -> {}
        }
    }
    public void stopMoving(String direction) {
        switch (direction) {
            case Character.DOWN -> {
                this.dy = 0;
                this.defaultFrame(Character.DOWN_IMAGE_PATH);
            }
            case Character.UP -> {
                this.dy = 0;
                this.defaultFrame(Character.UP_IMAGE_PATH);
            }
            case Character.LEFT -> {
                this.dx = 0;
                this.defaultFrame(Character.LEFT_IMAGE_PATH);
            }
            case Character.RIGHT -> {
                this.dx = 0;
                this.defaultFrame(Character.RIGHT_IMAGE_PATH);
            }
            default -> {}
        }
    }
    
}
