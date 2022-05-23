package combat.action;

import combat.effect.Effect;
import combat.entity.TestChar;
import entity.Character;
import entity.Enemy;
import entity.Player;

public abstract class Action {
    String name;
    Effect[] effectList;
    abstract public String getName();
    /*
    Since we only have 2 "Characters" interact with each other in combat
    char1 is the initiator of the action, char2 is affected by the action
    Class Action will handle the update of attribute and call the CombatManager for effect handling
    Note:
    In case there are other types of action (e.g heal self)
    => create child classes and let GUI handle it

    Design idea:
    self-action
    1 target action
    aoe action
    the player method will take in the correct parameter
    the enemy method takes in both Player array and Enemy array
    */
    abstract public void randomActivate(Character currentTurnChar, Player player, Enemy enemy);
}
