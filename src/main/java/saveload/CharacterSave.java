package saveload;

import java.io.Serializable;
import java.util.ArrayList;

public class CharacterSave implements Serializable {

    private String name;

    private String type;
    private int x;
    private int y;
    private int level;
    private int exp;
    private int attackSpeed;
    private int attackPoint;
    private int defensePoint;
    private int manaPoint;
    private int healthPoint;
    private int maxHealthPoint;
    private int maxManaPoint;
    private String imagePath;

    private ArrayList<CharacterSave> allyList;

    public CharacterSave(String name, String type, int x, int y, int level, int exp,
                         int attackSpeed, int attackPoint, int defensePoint, int manaPoint,
                         int healthPoint, int maxHealthPoint, int maxManaPoint, String imagePath, ArrayList<CharacterSave> allyList) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
        this.level = level;
        this.exp = exp;
        this.attackSpeed = attackSpeed;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.manaPoint = manaPoint;
        this.healthPoint = healthPoint;
        this.maxHealthPoint = maxHealthPoint;
        this.maxManaPoint = maxManaPoint;
        this.imagePath = imagePath;
        this.allyList = allyList;
    }

    public String getName() {
        return name;
    }
    public ArrayList<CharacterSave> getAllyList() {
        return allyList;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getType() {
        return type;
    }
}
