package combat;

import combat.action.Action;
import combat.action.TestAction;

public class TestCombatModule {
    public static void main(String[] args) {
        TestChar char1 = new TestChar("Player",10, new Action[] {new TestAction()});
        TestChar char2 = new TestChar("Monster", 10, new Action[] {new TestAction()});
        TestAction.activate(char1,char2);
    }
}
