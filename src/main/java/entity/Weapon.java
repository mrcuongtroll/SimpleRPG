package entity;

public class Weapon {
    private int attack;
    private int defense;
    private String imageIconPath;

    public Weapon(int attack, int defense, String imageIconPath) {
        this.attack = attack;
        this.defense = defense;
        this.imageIconPath = imageIconPath;
    }
}
