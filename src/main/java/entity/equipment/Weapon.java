package entity.equipment;

public class Weapon extends Equipment{

    public Weapon(String name, int attackSpeed, int attackPoint, int defensePoint,
                  int maxHealthPoint, int maxManaPoint, String imageIconPath){
        super( name,  attackSpeed,  attackPoint,  defensePoint, maxHealthPoint,  maxManaPoint,  imageIconPath);
    }
}
