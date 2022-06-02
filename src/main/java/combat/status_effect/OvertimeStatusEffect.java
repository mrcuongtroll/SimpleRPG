package combat.status_effect;

import entity.Character;

public abstract class OvertimeStatusEffect extends StatusEffect{
    @Override
    public abstract void applyEffect(Character character);

    @Override
    public void unApplyEffect(Character character) {}
}
