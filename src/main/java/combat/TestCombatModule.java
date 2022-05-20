package combat;

import combat.action.Action;
import combat.action.TestAction;
import entity.Armor;
import entity.Enemy;
import entity.Player;
import main.SimpleRPG;

public class TestCombatModule {
    public static void main(String[] args) {
        Player[] players = new Player[] {

        };
        Enemy[] enemies = new Enemy[] {

        };
        CombatManager combatInstance = new CombatManager(players, enemies);
    }
}
