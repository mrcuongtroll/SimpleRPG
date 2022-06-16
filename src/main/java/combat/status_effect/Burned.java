package combat.status_effect;

import combat.effect.Effect;
import entity.Character;
import world.BattleMap;

public class Burned extends OvertimeStatusEffect{
    private Effect  effect = new combat.effect.Burned();
    public Burned(Character character) {
        super(character);
    }

    @Override
    public void applyEffect(Character character) {
        int damage = Math.round(character.getHealthPoint()/10);
        BattleMap.showSkillEffect(character, effect, false,character.getName() + " burns for "+ damage +" HP.");
        character.setHealthPoint(character.getHealthPoint()-damage);

    }

    public Effect getEffect() {
        return effect;
    }
}
