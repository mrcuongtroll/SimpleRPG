package combat.action;

import combat.effect.Effect;
import combat.status_effect.Burned;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Spark extends Action{
    public static final String NAME = "Spark";
    public static final String DESC = "Create a flame that Deals 1.5*Effective Dmg. and Burns the enemy";
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
        int damage = Math.max((int) (attacker.getAttackPoint() - defender.getDefensePoint())*3/2, 0);
        if (damage > 0) {
            defender.setHealthPoint((int) Math.round(defender.getHealthPoint() - damage));
        }
        new Burned(defender);
        BattleMap.showSkillEffect(attacker, defender, effect, attacker.getName() + " deals " + damage + " damage to " + defender.getName(),
                defender.getName() + " is now Burned ");
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
