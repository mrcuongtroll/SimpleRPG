package combat.action;

import combat.CombatManager;
import combat.effect.Effect;
import combat.entity.TestChar;
import entity.Character;
import entity.Enemy;
import entity.Player;

public class TestAction extends Action{
    public static final String name = "Lifesteal";
    public String getName(){
        return TestAction.name;
    }

    static Effect[] effectList = {
//            new TestEffect(new String[]{"**This is the image path of the 1st effect of the action**",}, "Effect 1"),
//            new TestEffect(new String[]{"**This is the image path of the 2nd effect of the action**",}, "Effect 2")
    };

    public static void activate(TestChar char1, TestChar[] chars2){
        TestChar char2 = chars2[0];
        CombatManager.showActionText(char1, TestAction.name); //maybe move this to the activate method of Action class

        CombatManager.showEffectTest(effectList[0],char2);
        char2.sethp(char2.gethp()-5);
        CombatManager.showEffectText(char1.getName() +" deals 5 damages to " + char2.getName());
        CombatManager.showCharacterState(char1);
        CombatManager.showCharacterState(char2);

        CombatManager.showEffectTest(effectList[1],char2);
        char1.sethp(char1.gethp()+5);
        CombatManager.showEffectText(char1.getName() +" heals for 5" );
        CombatManager.showCharacterState(char1);
        CombatManager.showCharacterState(char2);
    }
    @Override
    public void randomActivate(Character currentTurnChar, Player player, Enemy enemy) {

    }
}
/* Basically, each "action" class must
1. Call the Combat Manager to announce the action
2. Call the Combat Manager to show the effect on the GUI
3. Execute the effect on characters (e.g. lifesteal phase 1 will deal damage)
4. Go back to 2 with the next effect

 */
