package entity;

import main.SimpleRPG;
import world.World;
import java.util.Random;

public abstract class NPC extends Character {

    public static final String MODE_IDLE = "IDLE";
    public static final String MODE_WANDER = "WANDER";
    public static final String MODE_CHASE = "CHASE";
    public static final double MOVEMENT_SPEED = 1.5;
    public static final double SPRINT_SPEED = 5;

    private String mode;
    private double xDistanceFromPlayer;
    private double yDistanceFromPlayer;

    private World worldMaster;

    private int frameCounter;

    public World getWorldMaster(){
        return this.worldMaster;
    }
    public String getMode() {
        return this.mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public NPC(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath,
               int level, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, boolean isSolid, String mode) {
        super(master, x, y, name, imagePath, 32, 80, level, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, isSolid);
        this.worldMaster = worldMaster;
        this.mode = mode;
        this.frameCounter = new Random().nextInt(100);
        this.setMovementSpeed(NPC.MOVEMENT_SPEED);
    }
    public NPC(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath,
               int level, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, boolean isSolid) {
        this(worldMaster, master, x, y, name, imagePath, level, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, isSolid, NPC.MODE_IDLE);
    }

    @Override
    protected void tick() {
        if (this.mode.equals(NPC.MODE_WANDER)) {
            if (this.frameCounter == 0) {
                this.stopMoving(this.getLastDirection());
            } else if (this.frameCounter == 70) {
                Random randomGenerator = new Random();
                int choice = randomGenerator.nextInt(5);
                if (choice == 0) {
                    this.move(Character.DOWN);
                } else if (choice == 1) {
                    this.move(Character.UP);
                } else if (choice == 2) {
                    this.move(Character.LEFT);
                } else if (choice == 3) {
                    this.move(Character.RIGHT);
                } else {
                    this.stopMoving(this.getLastDirection());
                }
            }
            this.frameCounter = (this.frameCounter+1) % 100;
        } else if (this.mode.equals(NPC.MODE_CHASE)) {
            Player currentPlayer = this.getMaster().getPlayer();
            this.xDistanceFromPlayer = this.getRelativeX() - currentPlayer.getRelativeX();
            this.yDistanceFromPlayer = this.getRelativeY() - currentPlayer.getRelativeY();
            // Chase in the x-axis
            if (this.xDistanceFromPlayer < 0) {
                this.move(Character.RIGHT);
            } else if (this.xDistanceFromPlayer >= 0) {
                this.move(Character.LEFT);
            }
            if (Math.abs(this.xDistanceFromPlayer) <= 5) {
                this.setDx(0);
            }
            // Chase in the y-axis
            if (this.yDistanceFromPlayer < 0) {
                this.move(Character.DOWN);
            } else if (this.yDistanceFromPlayer >= 0) {
                this.move(Character.UP);
            }
            if (Math.abs(this.yDistanceFromPlayer) <= 5) {
                this.setDy(0);
            }
        }
        super.tick();
    }

    public abstract void triggerScene(); //trigger combat or dialog
}
