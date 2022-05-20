package combat;

import combat.action.Action;
import combat.action.TestAction;
import combat.entity.Enemy;
import combat.entity.Player;

public class TestCombatModule {
    public static void main(String[] args) {
        Player[] players = new Player[] {
                new Player("Player 1",10, 5, new Action[] {new TestAction()}),
                new Player("Player 2",10, 10, new Action[] {new TestAction()}),
                new Player("Player 3",10, 15, new Action[] {new TestAction()}),
        };
        Enemy[] enemies = new Enemy[] {
                new Enemy("Enemy 1",10, 20, new Action[] {new TestAction()}),
                new Enemy("Enemy 2",10, 30, new Action[] {new TestAction()}),
                new Enemy("Enemy 3",10, 40, new Action[] {new TestAction()}),
        };
        CombatManager combatInstance = new CombatManager(players, enemies);
    }
}
