package combat.status_effect;

import combat.effect.Effect;
import entity.Character;

public abstract class OvertimeStatusEffect extends StatusEffect{
    public OvertimeStatusEffect(Character character) {
        super(character);
    }

    @Override
    public abstract void applyEffect(Character character);

    @Override
    public void unApplyEffect(Character character) {super.unApplyEffect(character);}
    public abstract String getText(Character character);
    public abstract Effect getEffect();
    public abstract void applyOvertime(Character character);
}
