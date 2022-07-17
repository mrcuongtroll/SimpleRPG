package entity;

import combat.action.*;
import main.SimpleRPG;
import world.World;

import java.io.File;
import java.util.ArrayList;
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
        super(worldMaster, master, x, y, name, imagePath, level, attackSpeed, attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, false, NPC.MODE_WANDER, "right");
        this.setActionList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark(),new Rest(), new NormalAttack()});
        this.award = 20*level;
    }
    public Enemy(World worldMaster, SimpleRPG master, int x, int y, int xDisplay, int yDisplay, String name, String imagePath,
                 int level, int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        super(worldMaster, master, x, y, xDisplay, yDisplay, name, imagePath, level, attackSpeed, attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, false, NPC.MODE_WANDER, "right");
        this.setActionList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark()});
        this.award = 20*level;
    }

    public void initEnemyAllies() {
        this.getAllyList().clear();
        int randomNumEnemyAllies = ThreadLocalRandom.current().nextInt(1, 3);
        for (int i = 1; i <= randomNumEnemyAllies; i++) {
            this.getAllyList().add(new Enemy(this.getWorldMaster(), this.getMaster(), SimpleRPG.SCREEN_WIDTH/5-16, SimpleRPG.SCREEN_HEIGHT/2-40, "Bandit " + i,
                    "/assets/test/bandit" + i,
                    1, 5, 15, 0, 100, 100, 100, 100));
        }
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

    public void randomAttack(ArrayList<Character> playerTeam, ArrayList<Character> enemyTeam){
        List<Action> possibleActions = actionList.stream().filter(action -> action.getCost() < this.getManaPoint()).collect(Collectors.toList()); ;
        int randomNum = ThreadLocalRandom.current().nextInt(0, possibleActions.size());
        Action action = possibleActions.get(randomNum);
        if (action.isTargetEnemy()){
            action.activate(this, playerTeam.get((int)(Math.random() * playerTeam.size())));
        }
        else{
            action.activate(this, enemyTeam.get((int)(Math.random() * enemyTeam.size())));
        }
    }

    public int getAward(){
        return award;
    }
}
