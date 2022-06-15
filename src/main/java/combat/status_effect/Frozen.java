package combat.status_effect;

import entity.Character;

public class Frozen extends OneTimeStatusEffect{
    private int oldValue;
    public Frozen(Character character) {
        super(character);
    }

    @Override
    public void applyEffectOneTime(Character character) {
        this.oldValue = character.getAttackSpeed();
        character.setAttackSpeed(Math.round(character.getAttackSpeed())/2);
    }
    @Override
    public void unApplyEffect(Character character) {
        character.setAttackSpeed(oldValue);
    }
}
