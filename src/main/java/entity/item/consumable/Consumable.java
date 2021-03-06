package entity.item.consumable;

import entity.Character;
import entity.Inventory;
import entity.Player;
import entity.item.Item;
import sceneElement.SubSceneList;

public abstract class Consumable extends Item {
    public static final String TYPE = "Consumables";
    public  void activate(Player player){
        this.setQuantity(this.getQuantity()-1);
        if (this.getQuantity()==0){
            Inventory.items.remove(this);
        }
    }

    public String getType(){
        return TYPE;
    }
    public void activateInBattle(Character player, SubSceneList subSceneList){
        this.setQuantity(this.getQuantity()-1);
        if (this.getQuantity()==0){
            Inventory.items.remove(this);
        }
        subSceneList.updateInventory();
    }
}
