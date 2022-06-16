package combat.action;

import combat.effect.Effect;
import combat.status_effect.Poisoned;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class PoisonCloud extends Action{
    public static final String NAME = "Poison Cloud";
    public static final String DESC = "Create a poisonous cloud that Deals 0.5*Effective Dmg. and Poison the enemy";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.PoisonCloud();
    public static int COST = 30;
    public int getCost(){
        return COST;
    }
    public void activate(Character attacker, Character defender){

        attacker.setManaPoint(attacker.getManaPoint()-COST);
        int damage = (int) (attacker.getAttackPoint() - defender.getDefensePoint())/2;
        if (damage > 0) {
            defender.setHealthPoint((int) Math.round(defender.getHealthPoint() - damage));
        }
        new Poisoned(defender);
        BattleMap.showSkillEffect(defender, effect, attacker.getName() + " deals " + damage + " damage to " + defender.getName(),
                defender.getName() + " is now Poisoned ");
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
