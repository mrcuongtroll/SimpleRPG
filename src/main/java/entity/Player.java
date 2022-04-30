package entity;

import javafx.scene.image.Image;
import main.SimpleRPG;

public class Player extends Character {

    public static final int MOVEMENT_SPEED = 2;
    public static final int SPRINT_SPEED = 4;
    public static final int X = 1280/2-16;
    public static final int Y = 720/2-40;
    private Weapon weapon;
    private Armor armor;
    private int lastMove;
    private double stamina;
    private boolean isSprinting;
    public double getStamina() {
        return this.stamina;
    }
    public void setStamina(double stamina) {
        this.stamina = stamina;
    }
    public void sprint() {
        this.isSprinting = true;
    }
    public void unSprint() {
        this.isSprinting = false;
    }
    public boolean isSprintable() {
        return (this.getStamina() > 0);
    }

    public boolean isSprinting() {
        return this.isSprinting;
    }

    public Player(SimpleRPG master, int x, int y, String name, String imagePath, int level, int healthPoint, int manaPoint, Weapon weapon, Armor armor) {
        super(master, x, y, name, imagePath, 32, 80, level, healthPoint, manaPoint);
        this.weapon = weapon;
        this.armor = armor;
        this.lastMove = 0;
        this.stamina = 100;
    }

    @Override
    public void render() {
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
        if (this.isSprinting && this.isSprintable()) {
            this.setStamina(this.getStamina() - 0.5);
        } else if (this.getStamina() < 100) {
            this.setStamina(this.getStamina() + 0.25);
        }
        this.getGraphicContext().drawImage(this.getImage(), this.getX(), this.getY());
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
}
