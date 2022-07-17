package entity.item.consumable;

import combat.effect.Effect;
import entity.Character;
import entity.Player;
import sceneElement.SubSceneList;
import world.BattleMap;

import java.io.File;

public class ManaPotion extends Consumable{
    public static final String NAME = "Health Potion";
    public static final String DESC = "+15 HP";
    public static final int VALUE = 100;
    public static final String ICONPATH = "/assets/test/item/glass04purple.png";
    static Effect effect = new combat.effect.Heal();
    public ManaPotion(int quantity){
        this.setQuantity(quantity);
    }
    @Override
    public boolean isActivated() {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESC;
    }

    @Override
    public int getValue() {
        return VALUE;
    }

    @Override
    public String getIconPath() {
        return String.valueOf(getClass().getResource(ICONPATH));
    }

    public void activate(Player player){
        super.activate(player);
        player.setManaPoint(player.getHealthPoint()+15);
    }

    @Override
    public void activateInBattle(Character character, SubSceneList subSceneList) {
        super.activateInBattle(character,subSceneList);
        character.setManaPoint(character.getManaPoint()+15);
        BattleMap.showSkillEffect(character, character, effect, character.getName() + " heals " + 15 + " mana points ");
    }

}
