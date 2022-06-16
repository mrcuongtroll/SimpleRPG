package combat.status_effect;

import entity.Character;

public class Paralyze extends OneTimeStatusEffect{
    private int oldValue;
    public Paralyze(Character character) {
        super(character);
    }

    @Override
    public void applyEffectOneTime(Character character) {
        this.oldValue = character.getDefensePoint();
        character.setDefensePoint(Math.round(character.getDefensePoint())/2);
    }
    @Override
    public void unApplyEffect(Character character) {
        character.setDefensePoint(oldValue);
    }
}
