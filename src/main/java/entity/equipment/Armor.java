package entity.equipment;

public class Armor extends Equipment{
    public Armor(String name, int attackSpeed, int attackPoint, int defensePoint,
                 int maxHealthPoint, int maxManaPoint, String imageIconPath) {
        super( name,  attackSpeed,  attackPoint,  defensePoint, maxHealthPoint,  maxManaPoint,  imageIconPath);
    }
}
