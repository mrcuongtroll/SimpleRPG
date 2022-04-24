package entity;

import main.SimpleRPG;
import world.World;

public abstract class NPC extends Character {
    public NPC(SimpleRPG master, int x, int y, String imagePath, int level, int healthPoint, int manaPoint) {
        super(master, x, y, imagePath, 32, 80, level, healthPoint, manaPoint);
    }

    public abstract void triggerScene(); //trigger combat or dialog
}
