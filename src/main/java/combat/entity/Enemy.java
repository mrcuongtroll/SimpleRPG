package combat.entity;

import combat.action.Action;

public class Enemy extends TestChar{
    public Enemy(String name, int hp, int speed, Action[] actionList) {
        super(name, hp, speed, actionList);
    }
    public  String getCharacterType(){
        return "Enemy";
    }
}
