package combat.status_effect;

import combat.effect.Effect;
import entity.Character;

import java.io.File;

public class Burned extends OvertimeStatusEffect{
    public static final String ICON_PATH = (new File("./assets/test/status-effects/burned.png")).getAbsolutePath();
    public static Effect  effect = new combat.effect.Burned();
    public Burned(Character character) {
        super(character);
    }

    @Override
    public void applyEffect(Character character) {

    }

    @Override
    public String getText(Character character) {
        int damage = Math.round(character.getHealthPoint()/10);
        return (character.getName() + " burns for " + damage + " damage.");
    }

    @Override
    public void applyOvertime(Character character) {
        int damage = Math.round(character.getHealthPoint()/10);
        character.setHealthPoint(character.getHealthPoint()-damage);
    }

    public Effect getEffect() {
        return effect;
    }
}
