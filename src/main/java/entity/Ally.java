package entity;

import main.SimpleRPG;
import world.World;

public class Ally extends NPC{
    public Ally(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int healthPoint, int manaPoint) {
        super(worldMaster, master, x, y, name, imagePath, level, healthPoint, manaPoint);
    }

    @Override
    public void triggerScene() {
        //trigger dialog
    }
}
