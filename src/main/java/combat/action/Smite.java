package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Smite  extends Action{
    public static final String NAME = "Smite";
    public static final String DESC = "Summon a righteous ray of light that Deals 5*Effective Dmg.";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.Smite();
    public static int COST = 70;
    public int getCost(){
        return COST;
    }
    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-COST);
        int damage = (attacker.getAttackPoint() - defender.getDefensePoint())*5;
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
