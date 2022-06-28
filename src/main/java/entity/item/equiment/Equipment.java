package entity.item.equiment;

import entity.Player;
import entity.item.Item;

import java.io.Serializable;

public abstract class Equipment extends Item implements Serializable {
    private int attackSpeed;
    private int attackPoint;
    private int defensePoint;
    private int maxHealthPoint;
    private int maxManaPoint;
    private String name;
    private String imageIconPath;
    private int value;

    private boolean activated;

    public Equipment(String name, int attackSpeed, int attackPoint, int defensePoint,
                     int maxHealthPoint, int maxManaPoint, String imageIconPath) {
        this.name = name;
        this.setQuantity(1);
        this.attackSpeed = attackSpeed;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.maxManaPoint = maxManaPoint;
        this.maxHealthPoint = maxHealthPoint;
        this.imageIconPath = imageIconPath;
    }
    public Equipment(String name, int attackSpeed, int attackPoint, int defensePoint,
                     int maxHealthPoint, int maxManaPoint, String imageIconPath, int value) {
        this(name, attackSpeed, attackPoint, defensePoint, maxHealthPoint, maxManaPoint, imageIconPath);
        this.value = value;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int healthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    public void setMaxManaPoint(int manaPoint) {
        this.maxManaPoint = manaPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageIconPath() {
        return imageIconPath;
    }
    public void activate(Player player){
        this.activated = true;
    }
    public boolean isActivated() {
        return activated;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
