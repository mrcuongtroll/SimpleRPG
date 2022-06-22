package combat.status_effect;

import entity.Character;

import java.io.File;

public class Paralyze extends OneTimeStatusEffect{
    public static final String ICON_PATH = (new File("./assets/test/status-effects/paralyzed.png")).getAbsolutePath();
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
        super.unApplyEffect(character);
        character.setDefensePoint(oldValue);
    }
}
