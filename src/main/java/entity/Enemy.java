package entity;

import main.SimpleRPG;
import views.GameView;
import world.BattleMap;
import world.World;

import java.io.File;

public class Enemy extends NPC{
    public static final int START_CHASE_DISTANCE = 200;
    public static final int STOP_CHASE_DISTANCE = 400;
    public static final int START_COMBAT_DISTANCE = 30;
    private int attack;
    private int defense;
    private double distanceFromPlayer;
    private boolean isChasing = false;
    @Override
    public int getAttackPoint() {
        return this.attack;
    }
    @Override
    public int getDefensePoint() {
        return this.defense;
    }

    public Enemy(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, int attack, int defense) {
        super(worldMaster, master, x, y, name, imagePath, level, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, false, NPC.MODE_WANDER);
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    protected void tick() {
        super.tick();
        Player player = this.getMaster().getPlayer();
        if (Math.abs(this.getRelativeX() - player.getRelativeX()) + Math.abs(this.getRelativeY() - player.getRelativeY()) <= START_COMBAT_DISTANCE) {
            this.triggerScene();
        } else if (Math.abs(this.getRelativeX() - player.getRelativeX()) + Math.abs(this.getRelativeY() - player.getRelativeY()) <= START_CHASE_DISTANCE) {
            this.setMode(NPC.MODE_CHASE);
        } else if (Math.abs(this.getRelativeX() - player.getRelativeX()) + Math.abs(this.getRelativeY() - player.getRelativeY()) <= STOP_CHASE_DISTANCE) {
            this.setMode(NPC.MODE_WANDER);
        }
    }

    @Override
    public void triggerScene() {
        //trigger combat
        System.out.println("Encountered");
    }
}
