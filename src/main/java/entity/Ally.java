package entity;

import main.SimpleRPG;

public class Ally extends NPC{
    public Ally(SimpleRPG master, int x, int y, String name, String imagePath, int level, int healthPoint, int manaPoint) {
        super(master, x, y, name, imagePath, level, healthPoint, manaPoint);
    }

    @Override
    public void triggerScene() {
        //trigger dialog
    }
}
