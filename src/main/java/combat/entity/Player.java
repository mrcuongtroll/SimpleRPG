package combat.entity;

import combat.action.Action;

import java.util.ArrayList;

public class Player extends TestChar{
    public Player(String name, int hp, int speed, Action[] actionList) {
        super(name, hp, speed, actionList);
    }
    public String getCharacterType(){
        return "Player";
    }
    // Implement GUI for the 3 other choices in here
    protected void run(){

    }
    protected void items(){

    }
    protected void skip(ArrayList<TestChar> turnQueue){
        turnQueue.add(0, this);
        this.setTurnProgress(this.getTurnProgress()+this.getSpeed()+100);
    }
}
