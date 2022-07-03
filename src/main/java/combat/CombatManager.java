package combat;

import combat.effect.Effect;
import combat.entity.TestChar;
import entity.Character;
import entity.Enemy;
import entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CombatManager {
    private static final String[] combatChoices = new String[] {"Actions", "Items", "Skip", "Run"};
    private static final int MAX_TURN_PROGRESS = 50;
    private ArrayList<Character> playerTeam;
    private ArrayList<Character> enemyTeam;
    private ArrayList<Character> turnQueue = new ArrayList<>();
    private boolean endCombat = false;

    private final Comparator<Character> turnProgressComparator = Comparator.comparingInt(Character::getTurnProgress);

    /* TODO:
    1. integrate with GUI
        a. Implement the combat narrator dialog which takes in a String and show the dialog on GUI
           showActionText
           showEffectText
    2. there might be not only "character" effects
    => implements other effect child classes and use method overloading in this class
    */
    public CombatManager(ArrayList<Character> playerTeam, ArrayList<Character> enemyTeam) {
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;

//        while (!this.endCombat) {
////            TestChar currentTurnChar = turnDecider(this.players, this.enemies);
//            Character currentTurnChar = turnDeciderTest(this.players, this.enemies);
////            switch (currentTurnChar.getCharacterType()){
////                case "Enemy":
//////                    int randomAction = ThreadLocalRandom.current().nextInt(0, 5);
//////                    currentTurnChar.getActionList()[randomAction].randomActivate(currentTurnChar,players,enemies);
////                    break;
////                case "Players":
////
////                    break;
////            }
//            if (currentTurnChar instanceof Enemy) {
//
//            } else if (currentTurnChar instanceof Player) {
//
//            }
//        }

    }

    public Character getCurrentTurnCharacter() {
        return turnDecider(this.playerTeam, this.enemyTeam);
    }
    public void removePlayerMember(Character character) {
        this.playerTeam.remove(character);
        while(turnQueue.remove(character)) {}
        System.out.println(character.getName() + " is dead");
    }
    public void removeEnemyMember(Character character) {
        this.enemyTeam.remove(character);
        while(turnQueue.remove(character)) {}
        System.out.println(character.getName() + " is dead");
    }
    public ArrayList<Character> getPlayerTeam() {
        return this.playerTeam;
    }
    public ArrayList<Character> getEnemyTeam() {
        return this.enemyTeam;
    }
    private Character turnDecider(ArrayList<Character> playerTeam, ArrayList<Character> enemyTeam) {
        while (turnQueue.isEmpty()){
            for (Character character : playerTeam){
                character.advanceTurn();
                if (character.getTurnProgress() >= MAX_TURN_PROGRESS){
                    turnQueue.add(character);
                }
            }
            for (Character character : enemyTeam){
                character.advanceTurn();
                if (character.getTurnProgress() >= MAX_TURN_PROGRESS){
                    turnQueue.add(character);
                }
            }
        }
        Collections.shuffle(turnQueue);
        turnQueue.sort(turnProgressComparator);
        Character toPop = turnQueue.get(0);
        toPop.setTurnProgress(toPop.getTurnProgress() - MAX_TURN_PROGRESS);
        turnQueue.remove(0);
        return toPop;
    }

    //This function will need to show the effect and the GUI needs to show before the next effect happens
    public static void showEffectTest(Effect effect, TestChar character){
        // This function is only for testing and will print all the effect's image path
//        for (int i=0; i<effect.getEffectImagePath().length; i++){
//            System.out.println(effect.getEffectImagePath()[i]);
//        }
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
    private Character turnDeciderTest(ArrayList<Player> players, ArrayList<Enemy> enemies) {
        while (turnQueue.isEmpty()){
            for (Player player : players){
                player.advanceTurn();
                if (player.getTurnProgress()>=100){
                    turnQueue.add(player);
                }
                System.out.println(player.getName()+" Progress: "+player.getTurnProgress());
            }
            for (Enemy enemy : enemies){
                enemy.advanceTurn();
                if (enemy.getTurnProgress()>=100){
                    turnQueue.add(enemy);
                }
                System.out.println(enemy.getName()+" Progress: "+enemy.getTurnProgress());
            }
        }
        Collections.shuffle(turnQueue);
        turnQueue.sort(turnProgressComparator);
        for (Character character : turnQueue){
            System.out.print(character.getName()+" Progress: "+character.getTurnProgress());
        }
        System.out.println();
        Character toPop = turnQueue.get(0);
        toPop.setTurnProgress(toPop.getTurnProgress()-100);
        turnQueue.remove(0);
        return toPop;

    }

    private void gameResult(){

    }
}
