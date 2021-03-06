package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Heal extends Action{
    public static final String NAME = "Heal";
    public static final String DESC = "Heals 15% health.";
    public static final int COST = 50;
    public static final boolean targetEnemy = false;


    public String getName(){
        return NAME;
    }
    public int getCost(){
        return COST;
    }
    public boolean isTargetEnemy() {
        return targetEnemy;
    }
    static Effect effect = new combat.effect.Heal();

    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-COST);
        defender.setHealthPoint((int) Math.round(defender.getMaxHealthPoint()*0.15)+defender.getHealthPoint());
        BattleMap.showSkillEffect(attacker, defender, effect, defender.getName() + " heals " + Math.round(defender.getMaxHealthPoint()*0.15) + " health points ");
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
