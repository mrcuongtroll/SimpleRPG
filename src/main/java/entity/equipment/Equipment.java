package entity.equipment;

public abstract class Equipment {
    private int attackSpeed;
    private int attackPoint;
    private int defensePoint;
    private int maxHealthPoint;
    private int maxManaPoint;
    private String name;
    private String imageIconPath;

    public Equipment(String name, int attackSpeed, int attackPoint, int defensePoint,
                     int maxHealthPoint, int maxManaPoint, String imageIconPath) {
        this.name = name;
        this.attackSpeed = attackSpeed;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.maxManaPoint = maxManaPoint;
        this.maxHealthPoint = maxHealthPoint;
        this.imageIconPath = imageIconPath;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int healthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    public void setMaxManaPoint(int manaPoint) {
        this.maxManaPoint = manaPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageIconPath() {
        return imageIconPath;
    }

    public void setImageIconPath(String imageIconPath) {
        this.imageIconPath = imageIconPath;
    }
}
