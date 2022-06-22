package combat.status_effect;

import entity.Character;

import java.io.File;

public class Frozen extends OneTimeStatusEffect{
    public static final String ICON_PATH = (new File("./assets/test/status-effects/frozen.png")).getAbsolutePath();
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
        super.unApplyEffect(character);
        character.setAttackSpeed(oldValue);
    }
}
