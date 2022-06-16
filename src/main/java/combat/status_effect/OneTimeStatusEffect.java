package combat.status_effect;

import entity.Character;

public abstract class OneTimeStatusEffect extends StatusEffect{
    private boolean applied = false;

    public OneTimeStatusEffect(Character character) {
        super(character);
    }

    @Override
    public void applyEffect(Character character){
        if (!this.applied){
            applyEffectOneTime(character);
        }
    }
    public abstract void applyEffectOneTime(Character character);
    @Override
    public abstract void unApplyEffect(Character character);

}
