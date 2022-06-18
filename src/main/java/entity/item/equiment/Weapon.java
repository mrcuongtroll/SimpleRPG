package entity.item.equiment;

import entity.Player;

import java.io.File;

public class Weapon extends Equipment{
    public static final String TYPE = "Weapon";
    public String description;
    public int value;
    public String iconPath;

    public Weapon(String name, int attackSpeed, int attackPoint, int defensePoint,
                  int maxHealthPoint, int maxManaPoint, String imageIconPath){
        super( name,  attackSpeed,  attackPoint,  defensePoint, maxHealthPoint,  maxManaPoint,  imageIconPath);
    }
    public Weapon(String name, int attackSpeed, int attackPoint, int defensePoint, int maxHealthPoint, int maxManaPoint,
                  String imageIconPath, String description, int value){
        super( name,  attackSpeed,  attackPoint,  defensePoint, maxHealthPoint,  maxManaPoint,  imageIconPath);
        this.description = description;
        this.value = value;
    }
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getIconPath() {
        return (new File(iconPath).getAbsolutePath());
    }
    @Override
    public void activate(Player player){
        super.activate(player);
        player.equip(this);
    }
    public String getType(){
        return TYPE;
    }

}
