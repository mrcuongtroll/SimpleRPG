package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Cyclone  extends Action{
    public static final String NAME = "Cyclone";
    public static final String DESC = "Summon a cyclone that Deals 3*Effective Dmg. to the enemy";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.Cyclone();
    public static int COST = 35;
    public int getCost(){
        return COST;
    }
    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-COST);
        int damage = Math.max((attacker.getAttackPoint() - defender.getDefensePoint())*3,0);
        if (damage > 0) {
            defender.setHealthPoint(defender.getHealthPoint() - damage);
        }
        BattleMap.showSkillEffect(defender, effect, attacker.getName() + " deals " + damage + " damage to " + defender.getName());
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
