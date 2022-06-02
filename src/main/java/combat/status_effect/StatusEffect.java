package combat.status_effect;

import entity.Character;
public abstract class StatusEffect {
    private int numTurn;
    private String iconPath;
    public void checkEffect(Character character, StatusEffect newStatusEffect){
        for (StatusEffect statusEffect:character.getStatusEffects()){
            if (statusEffect.getClass()==this.getClass()){
                statusEffect.setNumTurn(statusEffect.getNumTurn()+newStatusEffect.getNumTurn());
            }
        }
    }
    public abstract void applyEffect(Character character);
    public abstract void unApplyEffect(Character character);
    public int getNumTurn() {
        return numTurn;
    }

    public void setNumTurn(int numTurn) {
        this.numTurn = numTurn;
    }

    public String getIconPath() {
        return iconPath;
    }
}
