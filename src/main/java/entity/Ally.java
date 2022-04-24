package entity;

import main.SimpleRPG;

public class Ally extends NPC{
    public Ally(SimpleRPG master, int x, int y, String imagePath, int level, int healthPoint, int manaPoint) {
        super(master, x, y, imagePath, level, healthPoint, manaPoint);
    }

    @Override
    public void triggerScene() {
        //trigger dialog
    }
}
