package entity;

import main.SimpleRPG;

public class Player extends Character {

    public Player(SimpleRPG master, int x, int y, String imagePath) {
        super(master, x, y, imagePath, 32, 80);
    }
}
