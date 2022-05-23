package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Heal extends Action{
    public static final String NAME = "Self Healing";
    public static final int HEAL_POINTS = 10;

    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.Heal();

    public void activate(Character attacker, Character defender){
        attacker.increaseHealthPoint(HEAL_POINTS);
        BattleMap.showSkillEffect(defender, effect, attacker.getName() + " heals " + HEAL_POINTS + " health points ");
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
