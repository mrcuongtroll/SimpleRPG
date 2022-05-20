package entity;

import main.SimpleRPG;
import world.World;

public class Ally extends NPC{
    public Ally(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int healthPoint, int manaPoint, boolean isSolid) {
        super(worldMaster, master, x, y, name, imagePath, level, healthPoint, manaPoint, isSolid);
    }

    @Override
    public void render() {

    }

    @Override
    public void triggerScene() {
        //trigger dialog
    }
}
