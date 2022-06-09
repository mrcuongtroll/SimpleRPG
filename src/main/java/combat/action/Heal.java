package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class Heal extends Action{
    public static final String NAME = "Heal";
    public static final int COST = 50;


    public String getName(){
        return NAME;
    }
    public int getCost(){
        return COST;
    }
    static Effect effect = new combat.effect.Heal();

    public void activate(Character attacker, Character defender){
        attacker.setManaPoint(attacker.getManaPoint()-COST);
        attacker.setHealthPoint((int) Math.round(attacker.getMaxHealthPoint()*0.15)+attacker.getHealthPoint());
        BattleMap.showSkillEffect(attacker, effect, attacker.getName() + " heals " + Math.round(attacker.getMaxHealthPoint()*0.15) + " health points ");
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {
        activate(currentTurnChar, player);
    }
}
