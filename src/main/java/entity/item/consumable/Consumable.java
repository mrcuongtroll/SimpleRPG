package entity.item.consumable;

import entity.Enemy;
import entity.Inventory;
import entity.Player;
import entity.item.Item;

public abstract class Consumable extends Item {
    public static final String TYPE = "Consumables";
    public void activate(Player player){
        this.setQuantity(this.getQuantity()-1);
        if (this.getQuantity()==0){
            Inventory.items.remove(this);
        }
    }
    public String getType(){
        return TYPE;
    }
    abstract public void activateInBattle(Player player, Enemy enemy);
}
