package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class DoubleSlash extends Action{
    public static final String NAME = "Double Slash";
    public String getName(){
        return NAME;
    }
    public static int COST = 25;
    public int getCost(){
        return COST;
    }
    static Effect effect = new combat.effect.DoubleSlash();

    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-COST);
        int damage = (attacker.getAttackPoint() - defender.getDefensePoint())*2;
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
