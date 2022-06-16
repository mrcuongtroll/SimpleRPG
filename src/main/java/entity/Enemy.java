package entity;

import combat.action.*;
import main.SimpleRPG;
import world.World;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Enemy extends NPC{
    public static final int START_CHASE_DISTANCE = 200;
    public static final int STOP_CHASE_DISTANCE = 400;
    public static final int START_COMBAT_DISTANCE = 30;
    private int award;
    private double distanceFromPlayer;
    private boolean isChasing = false;
    private List<Action> actionList = Arrays.asList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark(),new Rest(), new NormalAttack()});

    public Enemy(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        super(worldMaster, master, x, y, name, imagePath, level, attackSpeed, attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, false, NPC.MODE_WANDER);
        this.setActionList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark(),new Rest(), new NormalAttack()});
        this.award = 20*level;
    }
    public Enemy(World worldMaster, SimpleRPG master, int x, int y, int xDisplay, int yDisplay, String name, String imagePath,
                 int level, int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        super(worldMaster, master, x, y, xDisplay, yDisplay, name, imagePath, level, attackSpeed, attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, false, NPC.MODE_WANDER);
        this.setActionList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark()});
        this.award = 20*level;
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

    public void randomAttack(Player player){
        List<Action> possibleActions = actionList.stream().filter(action -> action.getCost() < this.getManaPoint()).collect(Collectors.toList()); ;
        int randomNum = ThreadLocalRandom.current().nextInt(0, possibleActions.size());
        possibleActions.get(randomNum).activate(this, player);
    }

    public int getAward(){
        return award;
    }
}
