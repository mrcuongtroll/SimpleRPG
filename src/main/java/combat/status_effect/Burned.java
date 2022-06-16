package combat.status_effect;

import entity.Character;

public class Burned extends OvertimeStatusEffect{
    private String effect_path;
    public Burned(Character character) {
        super(character);
    }

    @Override
    public void applyEffect(Character character) {
        character.setHealthPoint(Math.round(character.getHealthPoint()/10));

    }
}
