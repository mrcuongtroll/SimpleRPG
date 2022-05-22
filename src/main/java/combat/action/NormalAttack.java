package combat.action;

import combat.CombatManager;
import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class NormalAttack  extends Action{
    public static final String NAME = "Normal Attack";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.NormalAttack();

    public void activate(Character attacker, Character defender){
        BattleMap.showSkillEffect(defender, effect);
        int damage = attacker.getAttackPoint() - defender.getDefensePoint();
        if (damage > 0) {
            defender.setHealthPoint(defender.getHealthPoint() - damage);
        }
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
