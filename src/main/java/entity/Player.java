package entity;

import javafx.scene.image.Image;
import main.SimpleRPG;

public class Player extends Character {

    public static final int MOVEMENT_SPEED = 3;
    public static final int X = 1280/2-16;
    public static final int Y = 720/2-40;
    private Weapon weapon;
    private Armor armor;
    private int lastMove;

    public Player(SimpleRPG master, int x, int y, String name, String imagePath, int level, int healthPoint, int manaPoint, Weapon weapon, Armor armor) {
        super(master, x, y, name, imagePath, 32, 80, level, healthPoint, manaPoint);
        this.weapon = weapon;
        this.armor = armor;
        this.lastMove = 0;
    }

    @Override
    public void changeFrame(String direction) {
        this.lastMove += MOVEMENT_SPEED;
        if (this.lastMove > 3 * MOVEMENT_SPEED) {
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
