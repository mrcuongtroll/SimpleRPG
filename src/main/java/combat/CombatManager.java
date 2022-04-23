package combat;

import combat.action.Action;
import combat.effect.Effect;

public class CombatManager {

    /* To-do:
    1. integrate with GUI
    2. there might be not only "character" effects
    => implements other effect child classes and use method overloading in this class
    */

    //This function will need to show the effect and the GUI needs to show before the next effect happens
    public static void showEffectTest(Effect effect, TestChar character){
        // This function is only for testing and will print all the effect's image path
        for (int i=0; i<effect.getEffectImagePaths().length; i++){
            System.out.println(effect.getEffectImagePaths()[i]);
        }
        System.out.println();
    }
    //Announces action
    public static void showActionText(TestChar character, String actionName){
        System.out.println(character.getName() + " uses " + actionName);
        System.out.println();
    }
    public static void showEffectText(String actionText){
        System.out.println(actionText);
        System.out.println();
    }

    public static void showCharacterState(TestChar character){
        System.out.println(character.getName()+": "+ character.gethp() + " HP");
    }
}
