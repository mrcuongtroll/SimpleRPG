package entity.item.consumable;

import combat.effect.Effect;
import entity.Enemy;
import entity.Player;
import world.BattleMap;

import java.io.File;

public class ManaPotion extends Consumable{
    public static final String NAME = "Health Potion";
    public static final String DESC = "+15 HP";
    public static final int VALUE = 100;
    public static final String ICONPATH = new File("./assets/test/item/glass04purple.png").getAbsolutePath();
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
        return ICONPATH;
    }

    public void activate(Player player){
        super.activate(player);
        player.setManaPoint(player.getHealthPoint()+15);
    }

    @Override
    public void activateInBattle(Player player, Enemy enemy) {
        this.activate(player);
        BattleMap.showSkillEffect(player, player, effect, player.getName() + " heals " + 15 + " mana points ");
    }

}
