package entity;

public class Weapon {
    private int attackPoint;
    private int defensePoint;
    private String imageIconPath;

    public Weapon(int attackPoint, int defensePoint, String imageIconPath) {
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.imageIconPath = imageIconPath;
    }
}
