package entity;

import main.SimpleRPG;

public class Enemy extends NPC{
    public static final double MOVEMENT_SPEED = 0.5;
    public static final int START_CHASE_DISTANCE = 200;
    public static final int STOP_CHASE_DISTANCE = 400;
    public static final int START_COMBAT_DISTANCE = 0;
    private int attack;
    private int defense;
    private double distanceFromPlayer;
    private boolean isChasing = false;
    public Enemy(SimpleRPG master, int x, int y, String imagePath, int level, int healthPoint, int manaPoint, int attack, int defense) {
        super(master, x, y, imagePath, level, healthPoint, manaPoint);
        this.attack = attack;
        this.defense = defense;
    }
    public void chasePlayer() {
        this.distanceFromPlayer = Math.abs(this.getX() - Player.X) + Math.abs(this.getY() - Player.Y);
        // start chasing
        if (this.distanceFromPlayer <= START_CHASE_DISTANCE){
            this.isChasing = true;
            move();
        }
        //continue chasing
        if (this.distanceFromPlayer > START_CHASE_DISTANCE && this.distanceFromPlayer <= STOP_CHASE_DISTANCE && isChasing) {
            move();
        }
        //stop chasing
        if (this.distanceFromPlayer > STOP_CHASE_DISTANCE && isChasing) {
            this.isChasing = false;
        }
        //start combat
        if (this.distanceFromPlayer < START_COMBAT_DISTANCE && isChasing) {
            triggerScene();
        }
        this.render();
    }
    public void move(){
        if (this.getX() < Player.X && this.getY() < Player.Y) {
            this.setX(this.getX() + MOVEMENT_SPEED);
            this.setY(this.getY() + MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        } else if (this.getX() < Player.X && this.getY() > Player.Y) {
            this.setX(this.getX() + MOVEMENT_SPEED);
            this.setY(this.getY() - MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        } else if (this.getX() > Player.X && this.getY() > Player.Y) {
            this.setX(this.getX() - MOVEMENT_SPEED);
            this.setY(this.getY() - MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        } else if (this.getX() > Player.X && this.getY() < Player.Y) {
            this.setX(this.getX() - MOVEMENT_SPEED);
            this.setY(this.getY() + MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        } else if (this.getX() < Player.X && this.getY() == Player.Y) {
            this.setX(this.getX() + MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        } else if (this.getX() == Player.X && this.getY() > Player.Y) {
            this.setY(this.getY() - MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        } else if (this.getX() > Player.X && this.getY() == Player.Y) {
            this.setX(this.getX() - MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        } else if (this.getX() == Player.X && this.getY() < Player.Y) {
            this.setY(this.getY() + MOVEMENT_SPEED);
            this.changeFrame(Character.DOWN_IMAGE_PATH);
        }
    }

    @Override
    public void triggerScene() {
        //trigger combat
    }
}
