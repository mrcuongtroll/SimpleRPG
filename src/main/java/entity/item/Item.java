package entity.item;

import entity.Player;

public abstract class Item {
    private int quantity;
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    abstract public boolean isActivated();
    abstract public String getName();
    abstract public String getDescription(); //Description just lists out the effect
    abstract public int getValue();
    abstract public String getIconPath();
    abstract public String getType();
    abstract public void activate(Player player);
}
