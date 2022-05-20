package entity;

import javafx.scene.image.Image;
import main.SimpleRPG;
import world.World;

public class Player extends Character {
    public static final int MOVEMENT_SPEED = 2;
    public static final int SPRINT_SPEED = 6;
    public static final int X = 1280/2-16;
    public static final int Y = 720/2-40;
    public static final int MAX_STAMINA = 200;
    private Weapon weapon;
    private Armor armor;
    private int lastMove;
    private int stamina;
    private boolean isSprinting;
    public int getStamina() {
        return this.stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public void sprint() {
        this.isSprinting = true;
        this.setDx(this.getDx() / this.getMovementSpeed() * Player.SPRINT_SPEED);
        this.setDy(this.getDy() / this.getMovementSpeed() * Player.SPRINT_SPEED);
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        this.setMovementSpeed(Player.SPRINT_SPEED);
    }
    public void unSprint() {
        this.isSprinting = false;
        this.setDx(this.getDx() * Player.MOVEMENT_SPEED / this.getMovementSpeed());
        this.setDy(this.getDy() * Player.MOVEMENT_SPEED / this.getMovementSpeed());
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        this.setMovementSpeed(Player.MOVEMENT_SPEED);
    }
    public boolean isSprintable() {
        return (this.getStamina() > 0);
    }
    public boolean isSprinting() {
        return this.isSprinting;
    }
    public void sprintRender() {
        if (this.isSprinting && this.isSprintable()) {
            this.setStamina(this.getStamina() - 2);
        } else if (this.getStamina() < MAX_STAMINA) {
            if (this.getStamina() <= 2 && !this.isSprinting()) {
                this.setStamina(this.getStamina() + 1);
            } else if (this.getStamina() > 2) {
                this.setStamina(this.getStamina() + 1);
            }
        }
        if (this.getStamina() <= 0 && this.isSprinting()) {
            this.unSprint();
        }
    }

    public Player(SimpleRPG master, int x, int y, String name, String imagePath, int level, int attackSpeed, int healthPoint, int manaPoint, Weapon weapon, Armor armor) {
        super(master, x, y, name, imagePath, 32, 80, level, attackSpeed, healthPoint, manaPoint);
        this.weapon = weapon;
        this.armor = armor;
        this.lastMove = 0;
        this.stamina = MAX_STAMINA;
        this.setMovementSpeed(Player.MOVEMENT_SPEED);
    }

    @Override
    public void render() {
        this.tick();
        sprintRender();
        int xPos = Player.X;
        int yPos = Player.Y;
        // Now check if the map scrolls or not
        World world = ((World) this.getMaster().getWorld());
        // Check x-axis:
        if (this.getRelativeX() <= SimpleRPG.SCREEN_WIDTH) {
            xPos = (int) this.getRelativeX();
        } else if (this.getRelativeX() >= world.getBg().getWidth() - SimpleRPG.SCREEN_WIDTH) {
            xPos = (int) this.getRelativeX() - (int) (world.getBg().getWidth() - SimpleRPG.SCREEN_WIDTH);
        }
        // Check y-axis:
        if (this.getRelativeY() <= SimpleRPG.SCREEN_HEIGHT) {
            yPos = (int) this.getRelativeY();
        } else if (this.getRelativeY() >= world.getBg().getHeight() - SimpleRPG.SCREEN_HEIGHT) {
            yPos = (int) this.getRelativeY() - (int) (world.getBg().getHeight() - SimpleRPG.SCREEN_HEIGHT);
        }
        this.getGraphicContext().drawImage(this.getImage(), xPos, yPos);
    }
    @Override
    protected void tick() {
        super.tick();
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

    @Override
    public void changeFrame(String direction) {
        this.lastMove += MOVEMENT_SPEED;
        if (this.lastMove > 6 * MOVEMENT_SPEED) {
            this.lastMove = 0;
            if (direction.equals(DEFAULT_IMAGE_PATH)) {
                this.setCurrentFrame(1);
            } else if (direction.equals(this.getLastDirection())){
                this.setCurrentFrame(this.getCurrentFrame() + 1);
                if (this.getCurrentFrame() > NUM_IMAGE_FRAME) {
                    this.setCurrentFrame(1);
                }
            } else {
                this.setLastDirection(direction);
                this.setCurrentFrame(1);
            }
            this.setImage(new Image(this.getImagePath() + direction + this.getCurrentFrame() + ".png"));
        }
    }

    @Override
    public void move(String direction) {
        super.move(direction);
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
    }
    @Override
    public void stopMoving(String direction) {
        super.stopMoving(direction);
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
    }
}
