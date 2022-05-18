package combat.entity;

import combat.action.Action;

// This class is just for implementing/testing the combat manager
// To-be replaced with the appropriate Character class
public abstract class TestChar {
    private String name;
    private int hp;
    /*actionList doesn't have any function for now
     it will indicate the skill available to the player and enemy
     (choose randomly for the enemy?)*/
    private  Action[] actionList;

    private int speed;
    private int turnProgress = 0;

    public TestChar(String name, int hp, int speed, Action[] actionList) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
        this.actionList = actionList;
    }

    public abstract String getCharacterType();

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void advanceTurn(){
        this.turnProgress += this.speed;
    }

    public int getTurnProgress() {
        return turnProgress;
    }
    public void setTurnProgress(int turnProgress) {
        this.turnProgress = turnProgress;
    }

    public Action[] getActionList() {
        return actionList;
    }

    public void setActionList(Action[] actionList) {
        this.actionList = actionList;
    }
}
