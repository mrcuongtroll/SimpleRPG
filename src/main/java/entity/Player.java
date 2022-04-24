package entity;

import main.SimpleRPG;

public class Player extends Character {

    public static final int MOVEMENT_SPEED = 3;
    public static final int X = 1280/2-16;
    public static final int Y = 720/2-40;
    private Weapon weapon;
    private Armor armor;

    public Player(SimpleRPG master, int x, int y, String imagePath, int level, int healthPoint, int manaPoint, Weapon weapon, Armor armor) {
        super(master, x, y, imagePath, 32, 80, level, healthPoint, manaPoint);
        this.weapon = weapon;
        this.armor = armor;
    }
}
