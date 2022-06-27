package entity;

import combat.action.*;
import main.SimpleRPG;
import world.World;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Ally extends NPC{

    private List<Action> actionList = Arrays.asList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark(),new Rest(), new NormalAttack()});


    public Ally(World worldMaster, SimpleRPG master, int x, int y, String name, String imagePath, int level, int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint) {
        super(worldMaster, master, x, y, name, imagePath, level, attackSpeed, attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, true, "left");
        this.setActionList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark()});
    }

    public void randomAttack(Player player){
        List<Action> possibleActions = actionList.stream().filter(action -> action.getCost() < this.getManaPoint()).collect(Collectors.toList()); ;
        int randomNum = ThreadLocalRandom.current().nextInt(0, possibleActions.size());
        possibleActions.get(randomNum).activate(this, player);
    }

    @Override
    public void render() {

    }
    @Override
    public void triggerScene() {
        //trigger dialog
    }
}
