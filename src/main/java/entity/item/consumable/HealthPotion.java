package entity.item.consumable;

import combat.effect.Effect;
import entity.Character;
import entity.Player;
import sceneElement.SubSceneList;
import world.BattleMap;

import java.io.File;

public class HealthPotion extends Consumable{
    public static final String NAME = "Health Potion";
    public static final String DESC = "+15 HP";
    public static final int VALUE = 100;
    public static final String ICONPATH = new File("./assets/test/item/glass05red.png").getAbsolutePath();
    static Effect effect = new combat.effect.Heal();
    public HealthPotion(int quantity){
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
        return ICONPATH ;
    }

    public void activate(Player player){
        super.activate(player);
        player.setHealthPoint(player.getHealthPoint()+15);
    }

    @Override
    public void activateInBattle(Character character, SubSceneList subSceneList) {
        super.activateInBattle(character,subSceneList);
        character.setHealthPoint(character.getHealthPoint()+15);
        BattleMap.showSkillEffect(character, character, effect, character.getName() + " heals " + 15 + " health points ");
    }

}
