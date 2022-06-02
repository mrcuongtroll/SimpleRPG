package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Cyclone  extends Action{
    public static final String NAME = "Normal Attack";
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.NormalAttack();
    public static int Cost = 25;
    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-Cost);
        int damage = attacker.getAttackPoint() - defender.getDefensePoint();
        if (damage > 0) {
            defender.setHealthPoint(defender.getHealthPoint() - damage*3);
        }
        BattleMap.showSkillEffect(defender, effect, attacker.getName() + " deals " + damage + " damage to " + defender.getName());
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
