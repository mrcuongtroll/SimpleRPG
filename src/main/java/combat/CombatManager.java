package combat;

import combat.effect.Effect;
import combat.entity.Enemy;
import combat.entity.Player;
import combat.entity.TestChar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CombatManager {
    private final static String[] combatChoices = new String[] {"Actions", "Items", "Skip", "Run"};
    private ArrayList<Player> players;
    private ArrayList<Enemy> enemies;
    private ArrayList<TestChar> turnQueue = new ArrayList<>();
    private boolean endCombat = false;

    private final Comparator<TestChar> turnProgressComparator = Comparator.comparingInt(TestChar::getTurnProgress);

    /* TODO:
    1. integrate with GUI
        a. Implement the combat narrator dialog which takes in a String and show the dialog on GUI
           showActionText
           showEffectText
    2. there might be not only "character" effects
    => implements other effect child classes and use method overloading in this class


    */

    public CombatManager(Player[] players, Enemy[] enemies) {
        this.players = new ArrayList<>(Arrays.asList(players));
        this.enemies = new ArrayList<>(Arrays.asList(enemies));

        while (!this.endCombat) {
//            TestChar currentTurnChar = turnDecider(this.players, this.enemies);
            TestChar currentTurnChar = turnDeciderTest(this.players, this.enemies);
            switch (currentTurnChar.getCharacterType()){
                case "Enemy":
//                    int randomAction = ThreadLocalRandom.current().nextInt(0, 5);
//                    currentTurnChar.getActionList()[randomAction].randomActivate(currentTurnChar,players,enemies);
                    break;
                case "Players":

                    break;
            }

        }

    }
    private TestChar turnDecider(ArrayList<Player> players, ArrayList<Enemy> enemies) {

        while (turnQueue.isEmpty()){
            for (TestChar player : players){
                player.advanceTurn();
            }
            for (TestChar enemy : enemies){
                enemy.advanceTurn();
            }
            for (TestChar player : players){
                if (player.getTurnProgress()>=100){
                    turnQueue.add(player);
                }
            }
            for (TestChar enemy : enemies){
                if (enemy.getTurnProgress()>=100){
                    turnQueue.add(enemy);
                }
            }
        }

        Collections.shuffle(turnQueue);
        turnQueue.sort(turnProgressComparator);
        System.out.println();
        TestChar toPop = turnQueue.get(0);
        toPop.setTurnProgress(toPop.getTurnProgress()-100);
        turnQueue.remove(0);
        return toPop;

    }

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
    private TestChar turnDeciderTest(ArrayList<Player> players, ArrayList<Enemy> enemies) {

        while (turnQueue.isEmpty()){
            for (TestChar player : players){
                player.advanceTurn();
            }
            for (TestChar enemy : enemies){
                enemy.advanceTurn();
            }
            for (TestChar player : players){
                if (player.getTurnProgress()>=100){
                    turnQueue.add(player);
                }
            }
            for (TestChar enemy : enemies){
                if (enemy.getTurnProgress()>=100){
                    turnQueue.add(enemy);
                }
            }
            for (TestChar player : players){
                System.out.println(player.getName()+" Progress: "+player.getTurnProgress());
            }
            for (TestChar enemy : enemies){
                System.out.println(enemy.getName()+" Progress: "+enemy.getTurnProgress());
            }

        }

        Collections.shuffle(turnQueue);
        turnQueue.sort(turnProgressComparator);
        for (TestChar character : turnQueue){
            System.out.print(character.getName()+" Progress: "+character.getTurnProgress());
        }
        System.out.println();
        TestChar toPop = turnQueue.get(0);
        toPop.setTurnProgress(toPop.getTurnProgress()-100);
        turnQueue.remove(0);
        return toPop;

    }
}
