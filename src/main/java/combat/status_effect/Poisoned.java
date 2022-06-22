package combat.status_effect;

import entity.Character;

import java.io.File;

public class Poisoned extends OneTimeStatusEffect{
    public static final String ICON_PATH = (new File("./assets/test/status-effects/poisoned.png")).getAbsolutePath();
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
        super.unApplyEffect(character);
        character.setAttackPoint(oldValue);
    }
}
