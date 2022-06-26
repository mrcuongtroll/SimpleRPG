package entity;

import combat.action.Action;
import combat.status_effect.OvertimeStatusEffect;
import combat.status_effect.StatusEffect;
import entity.item.equiment.Armor;
import entity.item.equiment.Weapon;
import event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import main.SimpleRPG;
import world.Tile;
import world.World;

import java.awt.*;
import java.util.ArrayList;

public abstract class Character {
    public static final String DEFAULT_IMAGE_PATH = "/default/";
    public static final String BATTLE_IMAGE_PATH = "/battle/";
    public static final int NUM_IMAGE_FRAME = 4;

    public static final String DOWN = "/move_down/";
    public static final String UP = "/move_up/";
    public static final String LEFT = "/move_left/";
    public static final String RIGHT = "/move_right/";
    public static final int MINIMUM_SPEED = 0;
    public static final int[] EXP_THRESHOLD = {50, 100, 200, 350};

    private ImageView battleFrame;
    private ImageView battleHitBox;
    private Rectangle battleHealthBar;
    private Rectangle battleManaBar;
    private Rectangle battleHealthBarContainer;
    private Rectangle battleManaBarContainer;

    private Text battleHealthPoint;
    private Text battleManaPoint;
    private Text battleName;

    private String battleSide;
    private SimpleRPG master;
    private String name;
    private double x;
    private double y;
    private double xDisplay;
    private double yDisplay;
    private int dx;
    private int dy;
    private java.awt.Rectangle rect;
    private boolean isSolid;
    private Tile tile;
    private int movementSpeed;
    private int lastMove;
    private int level = 1;
    private int exp = 0;
    private int attackSpeed;
    private int attackPoint;
    private int defensePoint;
    private Action[] combatActionList;
    private int turnProgress = 0;
    private int manaPoint;
    private int healthPoint;
    private int maxHealthPoint;
    private int maxManaPoint;
    private ArrayList<StatusEffect> statusEffects = new ArrayList<StatusEffect>();
    private String imagePath;
    private String battleImagePath;
    private int currentFrame;
    private String lastDirection;
    private double lastRelativeX;
    private double lastRelativeY;
    private Image image;
    private Event event;
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

    public ImageView getBattleFrame() {
        return battleFrame;
    }
    public ImageView getBattleHitBox() {
        return battleHitBox;
    }
    public String getBattleSide() {
        return battleSide;
    }
    public void setBattleFrame(ImageView battleFrame) {
        this.battleFrame = battleFrame;
    }
    public void setBattleHitBox(ImageView battleHitBox) {
        this.battleHitBox = battleHitBox;
    }
    public Rectangle getBattleHealthBar() {
        return battleHealthBar;
    }
    public Rectangle getBattleManaBar() {
        return battleManaBar;
    }
    public Rectangle getBattleHealthBarContainer() {
        return battleHealthBarContainer;
    }
    public Rectangle getBattleManaBarContainer() {
        return battleManaBarContainer;
    }
    public Text getBattleHealthPoint() {
        return battleHealthPoint;
    }
    public Text getBattleManaPoint() {
        return battleManaPoint;
    }
    public Text getBattleName() {
        return battleName;
    }
    public void setBattleHealthBar(Rectangle battleHealthBar) {
        this.battleHealthBar = battleHealthBar;
    }
    public void setBattleManaBar(Rectangle battleManaBar) {
        this.battleManaBar = battleManaBar;
    }
    public void setBattleHealthBarContainer(Rectangle battleHealthBarContainer) {
        this.battleHealthBarContainer = battleHealthBarContainer;
    }
    public void setBattleManaBarContainer(Rectangle battleManaBarContainer) {
        this.battleManaBarContainer = battleManaBarContainer;
    }
    public void setBattleHealthPoint(Text battleHealthPoint) {
        this.battleHealthPoint = battleHealthPoint;
    }
    public void setBattleManaPoint(Text battleManaPoint) {
        this.battleManaPoint = battleManaPoint;
    }
    public void setBattleName(Text battleName) {
        this.battleName = battleName;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getXDisplay() {
        return this.xDisplay;
    }
    public double getYDisplay() {
        return this.yDisplay;
    }
    public double getDx() {
        return this.dx;
    }
    public double getDy() {
        return this.dy;
    }
    public double getRelativeX() {
//        return this.x - ((World) this.master.getWorld()).getX();
        return this.x;
    }
    public double getRelativeY() {
//        return this.y - ((World) this.master.getWorld()).getY();
        return this.y;
    }
    public double getMovementSpeed() {
        return this.movementSpeed;
    }
    public java.awt.Rectangle getRect() {
        return this.rect;
    }
    public Tile getTile() {return this.tile;}
    public double getLastX() {
        return this.lastRelativeX;
    }
    public Event getEvent() {
        return this.event;
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
    public void setXDisplay(double xDisplay) {
        this.xDisplay = xDisplay;
    }
    public void setYDisplay(double yDisplay) {
        this.yDisplay = yDisplay;
    }
    public void setDx(int dx) {
        this.dx = dx;
    }
    public void setDy(int dy) {
        this.dy = dy;
    }
    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
    public void setEvent(Event e) {
        this.event = e;
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
    public void setAttackSpeed(int attackSpeed) { this.attackSpeed = attackSpeed; }
    public ArrayList<StatusEffect> getStatusEffects() {
        return statusEffects;
    }
    public int getAttackPoint(){
        return attackPoint;
    }
    public void setAttackPoint(int attackPoint){
        this.attackPoint = attackPoint;
    }
    public int getDefensePoint(){
        return defensePoint;
    }
    public void setDefensePoint(int defensePoint){
        this.defensePoint = defensePoint;
    }
    public void advanceTurn(){
        this.turnProgress += this.attackSpeed;
    }
    public void advanceStatusEffect(){
        for (StatusEffect statusEffect:this.getStatusEffects()){
            statusEffect.setNumTurn(statusEffect.getNumTurn()-1);
            statusEffect.applyEffect(this);
            if (statusEffect.getNumTurn()==0){
                statusEffect.unApplyEffect(this);
            }
        }
    }
    public OvertimeStatusEffect getOvertimeStatusEffect(){
        for (StatusEffect statusEffect:this.getStatusEffects()){
            if (statusEffect instanceof OvertimeStatusEffect){
                return (OvertimeStatusEffect) statusEffect;
            }
        }
        return null;
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
        if (healthPoint>maxHealthPoint){
            this.healthPoint = maxHealthPoint;
        } else if (healthPoint<0) {
            this.healthPoint = 0;
        } else {
            this.healthPoint = healthPoint;
        }
    }
    public void setManaPoint(int manaPoint){
        if (manaPoint>maxManaPoint){
            this.manaPoint = maxManaPoint;
        } else if (manaPoint<0) {
            this.manaPoint = 0;
        } else {
            this.manaPoint = manaPoint;
        }
    }
    public void setMaxHealthPoint(int maxHealthPoint){
        this.maxHealthPoint = maxHealthPoint;
    }
    public void setMaxManaPoint(int maxManaPoint){
        this.maxManaPoint = maxManaPoint;
    }
    public int getMaxHealthPoint(){
        return this.maxHealthPoint;
    }
    public int getMaxManaPoint(){
        return this.maxManaPoint;
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
    public String getBattleImagePath() {return this.battleImagePath;}
    public void setImage(Image image) {
        this.image = image;
    }

    public Character(SimpleRPG master, int x, int y, String name, String imagePath,
                     int width, int height, int level,
                     int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, boolean isSolid, String battleSide) {
        this.master = master;
        this.x = x;
        this.y = y;
        this.xDisplay = x;
        this.yDisplay = y;
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
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.healthPoint = healthPoint;
        this.manaPoint = manaPoint;

        this.maxHealthPoint = maxHealthPoint;
        this.maxManaPoint = maxManaPoint;

        this.isSolid = isSolid;
        this.rect = new java.awt.Rectangle((int)this.x, (int)(y+this.image.getHeight()-Tile.TILE_SIZE), 2*Tile.TILE_SIZE, Tile.TILE_SIZE);
        this.tile = new Tile(this.rect, isSolid);
        if (this.getMaster().getWorld() instanceof World) {
            ((World)master.getWorld()).getTileList().add(this.tile);
        }

        this.battleSide = battleSide;
    }
    public Character(SimpleRPG master, int x, int y, String name, String imagePath, int width, int height, int level,
                     int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, Weapon weapon, Armor armor, String battleSide) {
        this(master, x, y, name, imagePath, width, height, level, attackSpeed,  attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, true, battleSide);
        this.attackSpeed += weapon.getAttackSpeed() + armor.getAttackSpeed();
        this.attackPoint += weapon.getAttackPoint() + armor.getAttackPoint();
        this.defensePoint += weapon.getDefensePoint() + armor.getDefensePoint();
        this.maxHealthPoint += weapon.getMaxHealthPoint() + armor.getMaxHealthPoint();
        this.maxManaPoint += weapon.getMaxManaPoint() + armor.getMaxManaPoint();
    }

    public Character(SimpleRPG master, int x, int y, int xDisplay, int yDisplay, String name, String imagePath,
                     int width, int height, int level, int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, boolean isSolid, String battleSide) {
        this(master, x, y, name, imagePath, width, height, level, attackSpeed, attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, true, battleSide);
        this.yDisplay = yDisplay;
        this.xDisplay = xDisplay;
    }

    public void render() {
        this.tick();
        this.gc.drawImage(this.image, this.xDisplay, this.yDisplay);
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
            this.xDisplay += this.dx;
        }
        if (canMoveV) {
            this.y += this.dy;
            this.yDisplay += dy;
        }
        this.rect.setBounds((int)this.x, (int)(y+this.image.getHeight()-Tile.TILE_SIZE), (int)this.rect.getWidth(), (int)this.rect.getHeight());
        // TODO: Refine relative position for checking collision and stuff
        // TODO: Idea: split the coordinates into logical and display coordinates
        // Handle frame changing
        if (this.getDy() == 0) {
            if (this.getDx() > 0) {
                this.changeFrame(Character.RIGHT);
            } else if (this.getDx() < 0) {
                this.changeFrame(Character.LEFT);
            }
        } else if (this.getDx() == 0) {
            if (this.getDy() > 0) {
                this.changeFrame(Character.DOWN);
            } else if (this.getDy() < 0) {
                this.changeFrame(Character.UP);
            }
        } else if (this.getDx() != 0 && this.getDy() != 0) {
            if (this.getDx() > 0) {
                this.changeFrame(Character.RIGHT);
            } else if (this.getDx() < 0) {
                this.changeFrame(Character.LEFT);
            }
        }
    }

    public void changeFrame(String direction) {
        this.lastMove += this.getMovementSpeed()*this.getMovementSpeed();
        if (this.lastMove > 15 * this.getMovementSpeed()) {
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
        this.lastMove = (int) (15 * this.getMovementSpeed()) + 1;
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
                this.defaultFrame(Character.DOWN);
            }
            case Character.UP -> {
                this.dy = 0;
                this.defaultFrame(Character.UP);
            }
            case Character.LEFT -> {
                this.dx = 0;
                this.defaultFrame(Character.LEFT);
            }
            case Character.RIGHT -> {
                this.dx = 0;
                this.defaultFrame(Character.RIGHT);
            }
            default -> {}
        }
    }

    public void trigger() {
        if (this.event != null) {
            if (this.event.getTriggerType().equals(Event.TRIGGER_TYPE_INTERACT)) {
                this.event.trigger();
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void updateLevel(){

        for  (int i = EXP_THRESHOLD.length; true; i--){
            if (exp > EXP_THRESHOLD[i]){
                level = i+2;
                break;
            } else {
                level = 1;
            }
        }
    }

    public int getExp(){
        return exp;
    }

    public void increaseExp(int amount){
        if (exp + amount > 350){
            exp = 350;
        } else {
            exp += amount;
        }
        updateLevel();
    }
}
