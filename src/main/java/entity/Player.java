package entity;

import main.SimpleRPG;

public class Player extends Character {
    private int level;
    private int manaPoint;
    private int healthPoint;
    private Weapon weapon;
    private Armor armor;

    public Player(SimpleRPG master, int x, int y, String imagePath, int level, Weapon weapon, Armor armor) {
        super(master, x, y, imagePath, 32, 80);
        this.level = level;
        this.manaPoint = 100;
        this.healthPoint = 100;
        this.weapon = weapon;
        this.armor = armor;
    }
}
