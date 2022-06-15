package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Spark extends Action{
    public static final String NAME = "Spark";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.Spark();
    public static int COST = 15;
    public int getCost(){
        return COST;
    }
    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-COST);
        int damage = (int) (attacker.getAttackPoint() - defender.getDefensePoint())*3/2;
        if (damage > 0) {
            defender.setHealthPoint((int) Math.round(defender.getHealthPoint() - damage));
        }
        BattleMap.showSkillEffect(defender, effect, attacker.getName() + " deals " + (int) Math.round(defender.getHealthPoint() - damage*1.5) + " damage to " + defender.getName());
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
