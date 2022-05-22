package entity;

import main.SimpleRPG;
import world.World;

public abstract class NPC extends Character {

    private World worldMaster;
    public World getWorldMaster(){
        return this.worldMaster;
    }
    public NPC(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int attackSpeed, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        super(master, x, y, name, imagePath, 32, 80, level, attackSpeed, healthPoint, manaPoint, maxHealthPoint, maxManaPoint);
        this.worldMaster = worldMaster;
    }

    public abstract void triggerScene(); //trigger combat or dialog
}
