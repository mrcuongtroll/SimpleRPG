package combat.status_effect;

import entity.Character;
public abstract class StatusEffect {
    private int numTurn;
    private String iconPath;

    public StatusEffect(Character character){
        boolean duplicate = false;
        for (StatusEffect statusEffect:character.getStatusEffects()){
            if (statusEffect.getClass()==this.getClass()){
                duplicate = true;
                statusEffect.setNumTurn(Math.max(statusEffect.getNumTurn(), this.getNumTurn()));
                break;
            }
        }
        if (!duplicate){
            character.getStatusEffects().add(this);
        }
    };
    public void checkEffect(Character character, StatusEffect newStatusEffect){

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
