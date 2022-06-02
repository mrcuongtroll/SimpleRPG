package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Spark extends Action{
    public static final String NAME = "Normal Attack";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.NormalAttack();
    public static int Cost = 15;
    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-Cost);
        int damage = attacker.getAttackPoint() - defender.getDefensePoint();
        if (damage > 0) {
            defender.setHealthPoint((int) Math.round(defender.getHealthPoint() - damage*1.5));
        }
        BattleMap.showSkillEffect(defender, effect, attacker.getName() + " deals " + (int) Math.round(defender.getHealthPoint() - damage*1.5) + " damage to " + defender.getName());
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
