package combat.status_effect;

import entity.Character;

import java.io.File;

public class Paralyze extends OneTimeStatusEffect{
    private String iconPath = (new File("./assets/test/status-effects/paralyzed.png")).getAbsolutePath();
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
    public String getIconPath() {
        return iconPath;
    }
}
