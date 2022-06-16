package combat.status_effect;

import entity.Character;

public class Poisoned extends OneTimeStatusEffect{
    private int oldValue;
    public Poisoned(Character character) {
        super(character);
    }

    @Override
    public void applyEffectOneTime(Character character) {
        this.oldValue = character.getAttackPoint();
        character.setAttackPoint(Math.round(character.getAttackPoint())/2);
    }
    @Override
    public void unApplyEffect(Character character) {
        character.setAttackPoint(oldValue);
    }
}
