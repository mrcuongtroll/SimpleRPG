package combat.action;

import combat.effect.Effect;
import entity.Character;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

public class NormalAttack  extends Action{
    public static final String NAME = "Normal Attack";
    public static final String DESC = "Slash at the enemy, Deals 1*Effective Dmg.";
    public String getName(){
        return NAME;
    }
    public static int COST=0;
    public int getCost(){
        return COST;
    }
    static Effect effect = new combat.effect.NormalAttack();

    public void activate(Character attacker, Character defender){
        int damage = Math.max((attacker.getAttackPoint() - defender.getDefensePoint()),0);
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
