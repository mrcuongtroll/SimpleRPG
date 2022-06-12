package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Rest extends Action{
    public static final String NAME = "Rest";
    public static final int HEAL_POINTS = 10;
    public static int COST = 0;
    public int getCost(){
        return COST;
    }
    public String getName(){
        return NAME;
    }
    static Effect effect = new combat.effect.Heal();

    public void activate(Character attacker, Character defender){
        attacker.increaseHealthPoint(HEAL_POINTS);
        attacker.increaseManaPoint(HEAL_POINTS);

        BattleMap.showSkillEffect(defender, effect,
                attacker.getName() + " heals " + HEAL_POINTS + " health points ",
                attacker.getName() + " recovers " + HEAL_POINTS + " mana points ");
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
