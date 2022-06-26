package combat.action;

import combat.effect.Effect;
import combat.status_effect.Frozen;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Thunderbolt extends Action{
    public static final String NAME = "Spark";
    public static final String DESC = "Bring forth a thunderbolt that Deals 0.5*Effective Dmg. and Paralyze the enemy";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.Blizzard();
    public static int COST = 30;
    public int getCost(){
        return COST;
    }
    public void activate(Character attacker, Character defender){

        attacker.setManaPoint(attacker.getManaPoint()-COST);
        int damage = Math.max((int) (attacker.getAttackPoint() - defender.getDefensePoint())/2, 0);
        if (damage > 0) {
            defender.setHealthPoint((int) Math.round(defender.getHealthPoint() - damage));
        }
        new Frozen(defender);
        BattleMap.showSkillEffect(attacker, defender, effect, attacker.getName() + " deals " + damage + " damage to " + defender.getName(),
                defender.getName() + " is now Frozen ");
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
