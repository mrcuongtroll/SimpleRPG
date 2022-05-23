package entity;

import main.SimpleRPG;
import world.World;

public class Ally extends NPC{
    public Ally(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int attackSpeed, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        super(worldMaster, master, x, y, name, imagePath, level, attackSpeed, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, true);
    }

    @Override
    public int getAttackPoint() {
        return 0;
    }

    @Override
    public int getDefensePoint() {
        return 0;
    }

    @Override
    public void render() {

    }
    @Override
    public void triggerScene() {
        //trigger dialog
    }
}
