package combat;

import combat.action.Action;

// This class is just for implementing/testing the combat manager
// To-be replaced with the appropriate Character class
public class TestChar {
    private String name;
    private int hp;
    /*actionList doesn't have any function for now
     it will indicate the skill available to the player and enemy
     (choose randomly for the enemy?)*/
    private final Action[] actionList;

    public TestChar(String name, int hp, Action[] actionList) {
        this.name = name;
        this.hp = hp;
        this.actionList = actionList;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName(){
        return this.name;
    }
    public void sethp(int newhp) {
        this.hp = newhp;
    }

    public int gethp(){
        return this.hp;
    }
}
