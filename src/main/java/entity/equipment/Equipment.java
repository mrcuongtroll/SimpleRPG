package entity.equipment;

import java.awt.*;
import java.io.Serializable;

public abstract class Equipment implements Serializable {
    private int attack;
    private int defense;
    private String name;
    private String imageIconPath;

    public Equipment(String name, int attack, int defense, String imageIconPath) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.imageIconPath = imageIconPath;
    }

    public int getAttackPoint() {
        return this.attack;
    }

    public int getDefensePoint() {
        return this.defense;
    }
}
