package combat.status_effect;

import entity.Character;
public abstract class StatusEffect {
    private int numTurn;
    private String iconPath;
    public abstract void applyEffect(Character character);
    public abstract void unApplyEffect(Character character);

}
