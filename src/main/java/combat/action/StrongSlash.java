package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class StrongSlash  extends Action{
    public static final String NAME = "Normal Attack";
    public String getName(){
        return NAME;
    }
    public static int Cost = 25;
    static Effect effect = new combat.effect.NormalAttack();

    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-Cost);
        int damage = attacker.getAttackPoint() - defender.getDefensePoint();
        if (damage > 0) {
            defender.setHealthPoint(defender.getHealthPoint() - damage*2);
        }
        BattleMap.showSkillEffect(defender, effect, attacker.getName() + " deals " + damage + " damage to " + defender.getName());
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
