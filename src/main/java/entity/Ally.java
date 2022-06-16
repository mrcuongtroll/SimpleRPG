package entity;

import main.SimpleRPG;
import world.World;

public class Ally extends NPC{
    public Ally(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        super(worldMaster, master, x, y, name, imagePath, level, attackSpeed, 0, 0, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, true);
    }

    @Override
    public void render() {

    }
    @Override
    public void triggerScene() {
        //trigger dialog
    }
}
