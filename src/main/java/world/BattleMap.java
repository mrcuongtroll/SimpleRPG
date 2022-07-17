package world;

import combat.CombatManager;
import combat.effect.Effect;
import combat.effect.EffectAnimationTimer;
import combat.status_effect.OvertimeStatusEffect;
import dialogue.Dialogue;
import entity.Character;
import entity.Enemy;
import entity.Player;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.SimpleRPG;
import sceneElement.GameSubScene;
import sceneElement.SubSceneList;
import views.BattleView;
import views.GameView;

import java.io.File;
import java.util.ArrayList;

public class BattleMap extends Map {

    private int currentFrame = 2;
    private int lastFrameStep = 0;
    private Group groupContainer;
    private static final int BAR_WIDTH = 100;
    private static final int BAR_HEIGHT = 20;
    private static final int FRAME_WIDTH = 80;
    private static final int FRAME_HEIGHT = 120;
    private static final int HIT_BOX_SIZE = 200;
    private static final int BAR_POINT_FONT_SIZE = 8;
    private static int totalExp = 0;

    public static Player player;
    public static Enemy enemy;
    public static Character currentTurnChar;
    public static ArrayList<Character> playerTeam;
    public static ArrayList<Character> enemyTeam;

    public static Integer[] xPlayerTeam = {300, 150, 450};
    public static Integer[] xEnemyTeam = {SimpleRPG.SCREEN_WIDTH - 350, SimpleRPG.SCREEN_WIDTH - 500, SimpleRPG.SCREEN_WIDTH - 200};
    public static Integer[] yManaBar = {SimpleRPG.SCREEN_HEIGHT/4 + 100, SimpleRPG.SCREEN_HEIGHT/4 - 50, SimpleRPG.SCREEN_HEIGHT/4 - 50};
    public static Integer[] yHealthBar = {SimpleRPG.SCREEN_HEIGHT/4 + 80, SimpleRPG.SCREEN_HEIGHT/4 - 70, SimpleRPG.SCREEN_HEIGHT/4 - 70};
    public static Integer[] yFrame = {SimpleRPG.SCREEN_HEIGHT/4 + 150, SimpleRPG.SCREEN_HEIGHT/4, SimpleRPG.SCREEN_HEIGHT/4};
    public static Integer[] yHitBox = {SimpleRPG.SCREEN_HEIGHT/6 + 150, SimpleRPG.SCREEN_HEIGHT/6, SimpleRPG.SCREEN_HEIGHT/6};
    public static Integer[] yManaPoint = {SimpleRPG.SCREEN_HEIGHT/4 + 115, SimpleRPG.SCREEN_HEIGHT/4 - 40, SimpleRPG.SCREEN_HEIGHT/4 - 40};
    public static Integer[] yHealthPoint = {SimpleRPG.SCREEN_HEIGHT/4 + 95, SimpleRPG.SCREEN_HEIGHT/4 - 60, SimpleRPG.SCREEN_HEIGHT/4 - 60};
    public static Integer[] yName = {SimpleRPG.SCREEN_HEIGHT/4 + 135, SimpleRPG.SCREEN_HEIGHT/4 - 15, SimpleRPG.SCREEN_HEIGHT/4 - 15};
    public static Integer[] xHitBoxPlayerTeam = {245, 95, 395};
    public static Integer[] xHitBoxEnemyTeam = {SimpleRPG.SCREEN_WIDTH - 425, SimpleRPG.SCREEN_WIDTH - 575, SimpleRPG.SCREEN_WIDTH - 275};

    private static CombatManager combatManager;
    private static BattleView view;
    public Player getPlayer() {
        return player;
    }
    public Enemy getEnemy() {
        return enemy;
    }

    public static ImageView getBattleFrame(Character character) {
        return character.getBattleFrame();
    }

    public static ImageView getBattleHitBox(Character character) {
        return character.getBattleHitBox();
    }

    public static BattleView getView() {
        return view;
    }

    public static ArrayList<EffectAnimationTimer> effectAnimationList = new ArrayList<>();
    public BattleMap(SimpleRPG master, BattleView battleView, String imagePath, Enemy enemyFighter) {
        super(master, 0, 0, imagePath);
        player = master.getPlayer();
        enemy = enemyFighter;
        playerTeam = new ArrayList<>();
        enemyTeam = new ArrayList<>();
        playerTeam.add(player);
        for (Character character: player.getAllyList()) {
            playerTeam.add(character);
        }
        enemyTeam.add(enemy);
        for (Character character: enemy.getAllyList()) {
            enemyTeam.add(character);
        }
        setCharactersPosition();

        this.groupContainer = new Group();
        combatManager = new CombatManager(playerTeam, enemyTeam);

        SubSceneList.updateSkillScene();
        SubSceneList.checkManaRequirement();

        view = battleView;
        start();
    }

    public void setCharactersPosition() {
        for (int i = 0; i < playerTeam.size(); i++) {
            playerTeam.get(i).setBattleHealthBar(new Rectangle(Double.valueOf(xPlayerTeam[i]), Double.valueOf(yHealthBar[i]), BAR_WIDTH, BAR_HEIGHT));
            playerTeam.get(i).setBattleManaBar(new Rectangle(Double.valueOf(xPlayerTeam[i]), Double.valueOf(yManaBar[i]), BAR_WIDTH, BAR_HEIGHT));
            playerTeam.get(i).setBattleHealthBarContainer(new Rectangle(Double.valueOf(xPlayerTeam[i]), Double.valueOf(yHealthBar[i]), BAR_WIDTH, BAR_HEIGHT));
            playerTeam.get(i).setBattleManaBarContainer(new Rectangle(Double.valueOf(xPlayerTeam[i]), Double.valueOf(yManaBar[i]), BAR_WIDTH, BAR_HEIGHT));
            playerTeam.get(i).setBattleHealthPoint(new Text(Double.valueOf(xPlayerTeam[i]), Double.valueOf(yHealthPoint[i]), ""));
            playerTeam.get(i).setBattleManaPoint(new Text(Double.valueOf(xPlayerTeam[i]), Double.valueOf(yManaPoint[i]), ""));
            playerTeam.get(i).getBattleHealthPoint().setFont(new Font("Arcade Normal", BAR_POINT_FONT_SIZE));
            playerTeam.get(i).getBattleManaPoint().setFont(new Font("Arcade Normal", BAR_POINT_FONT_SIZE));
            playerTeam.get(i).setBattleName(new Text(Double.valueOf(xPlayerTeam[i]), Double.valueOf(yName[i]), playerTeam.get(i).getName()));
            playerTeam.get(i).getBattleName().setFont(new Font("Arcade Normal", BAR_POINT_FONT_SIZE));
            playerTeam.get(i).getBattleName().setFill(Color.WHITE);

            playerTeam.get(i).setBattleFrame(new ImageView(playerTeam.get(i).getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png"));
            playerTeam.get(i).getBattleFrame().setX(xPlayerTeam[i]);
            playerTeam.get(i).getBattleFrame().setY(yFrame[i]);
            playerTeam.get(i).getBattleFrame().setFitWidth(FRAME_WIDTH);
            playerTeam.get(i).getBattleFrame().setFitHeight(FRAME_HEIGHT);
            playerTeam.get(i).getBattleFrame().setScaleX(-1);

            playerTeam.get(i).setBattleHitBox(new ImageView());
            playerTeam.get(i).getBattleHitBox().setX(xHitBoxPlayerTeam[i]);
            playerTeam.get(i).getBattleHitBox().setY(yHitBox[i]);
            playerTeam.get(i).getBattleHitBox().setFitWidth(HIT_BOX_SIZE);
            playerTeam.get(i).getBattleHitBox().setFitHeight(HIT_BOX_SIZE);
        }
        for (int i = 0; i < enemyTeam.size(); i++) {
            enemyTeam.get(i).setBattleHealthBar(new Rectangle(Double.valueOf(xEnemyTeam[i]), Double.valueOf(yHealthBar[i]), BAR_WIDTH, BAR_HEIGHT));
            enemyTeam.get(i).setBattleManaBar(new Rectangle(Double.valueOf(xEnemyTeam[i]), Double.valueOf(yManaBar[i]), BAR_WIDTH, BAR_HEIGHT));
            enemyTeam.get(i).setBattleHealthBarContainer(new Rectangle(Double.valueOf(xEnemyTeam[i]), Double.valueOf(yHealthBar[i]), BAR_WIDTH, BAR_HEIGHT));
            enemyTeam.get(i).setBattleManaBarContainer(new Rectangle(Double.valueOf(xEnemyTeam[i]), Double.valueOf(yManaBar[i]), BAR_WIDTH, BAR_HEIGHT));
            enemyTeam.get(i).setBattleHealthPoint(new Text(Double.valueOf(xEnemyTeam[i]), Double.valueOf(yHealthPoint[i]), ""));
            enemyTeam.get(i).setBattleManaPoint(new Text(Double.valueOf(xEnemyTeam[i]), Double.valueOf(yManaPoint[i]), ""));
            enemyTeam.get(i).getBattleHealthPoint().setFont(new Font("Arcade Normal", BAR_POINT_FONT_SIZE));
            enemyTeam.get(i).getBattleManaPoint().setFont(new Font("Arcade Normal", BAR_POINT_FONT_SIZE));
            enemyTeam.get(i).setBattleName(new Text(Double.valueOf(xEnemyTeam[i]), Double.valueOf(yName[i]), enemyTeam.get(i).getName()));
            enemyTeam.get(i).getBattleName().setFont(new Font("Arcade Normal", BAR_POINT_FONT_SIZE));
            enemyTeam.get(i).getBattleName().setFill(Color.WHITE);

            enemyTeam.get(i).setBattleFrame(new ImageView(enemyTeam.get(i).getImagePath() + Character.BATTLE_IMAGE_PATH + "2.png"));
            enemyTeam.get(i).getBattleFrame().setX(xEnemyTeam[i]);
            enemyTeam.get(i).getBattleFrame().setY(yFrame[i]);
            enemyTeam.get(i).getBattleFrame().setFitWidth(FRAME_WIDTH);
            enemyTeam.get(i).getBattleFrame().setFitHeight(FRAME_HEIGHT);

            enemyTeam.get(i).setBattleHitBox(new ImageView());
            enemyTeam.get(i).getBattleHitBox().setX(xHitBoxEnemyTeam[i]);
            enemyTeam.get(i).getBattleHitBox().setY(yHitBox[i]);
            enemyTeam.get(i).getBattleHitBox().setFitWidth(HIT_BOX_SIZE);
            enemyTeam.get(i).getBattleHitBox().setFitHeight(HIT_BOX_SIZE);
        }
    }

    public void start() {
        getMaster().hideHUD();
        this.getGraphicsContext().setFill(Color.BLACK);
        this.getGraphicsContext().fillRect(0, 0, getMaster().canvasBattle.getWidth(), getMaster().canvasBattle.getHeight());
        this.getGraphicsContext().drawImage(this.getBg(), 0, 0, SimpleRPG.SCREEN_WIDTH, SimpleRPG.SCREEN_HEIGHT);

        for (int i = 0; i < playerTeam.size(); i++) {
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleHealthBarContainer());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleManaBarContainer());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleHealthBar());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleManaBar());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleHealthPoint());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleManaPoint());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleFrame());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleHitBox());
            this.groupContainer.getChildren().add(playerTeam.get(i).getBattleName());
        }
        for (int i = 0; i < enemyTeam.size(); i++) {
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleHealthBarContainer());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleManaBarContainer());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleHealthBar());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleManaBar());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleHealthPoint());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleManaPoint());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleFrame());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleHitBox());
            this.groupContainer.getChildren().add(enemyTeam.get(i).getBattleName());
        }

//        playerHitBox.setVisible(false);
//        enemyHitBox.setVisible(false);

        getMaster().mainPane.getChildren().add(this.groupContainer);
        turnDecide();
    }

    public static void turnDecide() {
        for (int i = 0; i < enemyTeam.size(); i++) {
            if (enemyTeam.get(i).getHealthPoint() <= 0) {
                System.out.println("Adding exp : " + ((Enemy)enemyTeam.get(i)).getAward());
                totalExp += ((Enemy)enemyTeam.get(i)).getAward();
                combatManager.removeEnemyMember(enemyTeam.get(i));
            }
        }
        for (int i = 0; i < playerTeam.size(); i++) {
            if (playerTeam.get(i).getHealthPoint() <= 0) {
                combatManager.removePlayerMember(playerTeam.get(i));
            }
        }
        if (combatManager.getEnemyTeam().size() == 0) {
//            player.increaseExp(enemy.getAward());
            removeNPC(enemy);
            view.cleanUpScene();
            player.getStatusEffects().clear();
            SimpleRPG.canvasBattle.getGraphicsContext2D().clearRect(0, 0, SimpleRPG.canvasBattle.getWidth(), SimpleRPG.canvasBattle.getHeight());
            GameView gv = new GameView(getMaster(), true);
            ((World) getMaster().getWorld()).removeNPC(enemy);

            boolean checkLevelUp = player.increaseExp(totalExp);

            Dialogue currentDialogue = null;
            Dialogue d1 = new Dialogue("You gain: " + totalExp + " exp");
            currentDialogue = d1;

            if (checkLevelUp) {
                Dialogue temp = new Dialogue("You reach level: " + player.getLevel() + "\n"
                        + "HP += 20" + "         "
                        + "ATK += 5" + "\n"
                        + "MP += 20" + "         "
                        + "SPD += 5" + "\n"
                        + "DEF += 5");
                currentDialogue.setNext(temp);
                currentDialogue = temp;

            }

//            ArrayList<Dialogue> DialogueList = new ArrayList<Dialogue>();

            for (int i = 1; i < playerTeam.size(); i++) {
                Dialogue temp1 = new Dialogue(playerTeam.get(i).getName() + " gain: " + totalExp + " exp");
                currentDialogue.setNext(temp1);
                currentDialogue = temp1;
                if(playerTeam.get(i).increaseExp(totalExp)){
                    Dialogue temp2 = new Dialogue(playerTeam.get(i).getName() +  " reach level: " + playerTeam.get(i).getLevel() + "\n"
                            + "HP += 20" + "         "
                            + "ATK += 5" + "\n"
                            + "MP += 20" + "         "
                            + "SPD += 5" + "\n"
                            + "DEF += 5");
                    currentDialogue.setNext(temp2);
                    currentDialogue = temp2;
                }
            }

            getMaster().getDialogueRender().setDialogue(d1);



            // Focus control on the game
            gv.getMainPane().requestFocus();
            return;
        } else if (player.getHealthPoint() <= 0) {
            view.cleanUpScene();
            view.showSubScene(SubSceneList.openGameOver);
        } else {
            Character currentTurnChar = combatManager.getCurrentTurnCharacter();
            BattleMap.currentTurnChar = currentTurnChar;
            for (Character character: combatManager.getPlayerTeam()) {
                character.getBattleHitBox().setImage(null);
            }
            for (Character character: combatManager.getEnemyTeam()) {
                character.getBattleHitBox().setImage(null);
            }
//            currentTurnChar.getBattleHitBox().setImage(new Image((new File("./assets/test/effect/turn/current_turn.png")).getAbsolutePath()));
            boolean anyStatusEffect = false;
            ArrayList<OvertimeStatusEffect> allOvertimeStatusEffect = new ArrayList<>();
            for (Character character: combatManager.getPlayerTeam()) {
                allOvertimeStatusEffect.add(character.getOvertimeStatusEffect());
                if (character.getOvertimeStatusEffect() != null) {
                    anyStatusEffect = true;
                }
            }
            for (Character character: combatManager.getEnemyTeam()) {
                allOvertimeStatusEffect.add(character.getOvertimeStatusEffect());
                if (character.getOvertimeStatusEffect() != null) {
                    anyStatusEffect = true;
                }
            }
            if (!anyStatusEffect){
                if (BattleMap.currentTurnChar.getHealthPoint() > 0) {
                    if (!BattleMap.currentTurnChar.getName().equals("Enemy")) {
                        if (BattleMap.currentTurnChar.getBattleSide().equals("left")) {
                            view.cleanUpScene();
                            view.showSubScene(SubSceneList.openBattleOption);
                        } else if (BattleMap.currentTurnChar.getBattleSide().equals("right")) {
                            view.cleanUpScene();
                            ((Enemy) BattleMap.currentTurnChar).randomAttack(playerTeam,enemyTeam);
                        }
                    }
                }
            }
            else  {
                if (currentTurnChar.getBattleSide().equals("left")) {
                    view.cleanUpScene();
                    GameSubScene dialogScene = SubSceneList.createDialogSceneStatusEffect(true,
                            allOvertimeStatusEffect);
                    view.showSubScene(dialogScene);
                } else if (currentTurnChar.getBattleSide().equals("right")) {
                    view.cleanUpScene();
                    GameSubScene dialogScene = SubSceneList.createDialogSceneStatusEffect(false,
                            allOvertimeStatusEffect);
                    view.showSubScene(dialogScene);
                }
            }
        }
        for (Character character: combatManager.getPlayerTeam()) {
            character.advanceStatusEffect();
        }
        for (Character character: combatManager.getEnemyTeam()) {
            character.advanceStatusEffect();
        }
        SubSceneList.checkManaRequirement();
    }

    public static void showSkillEffect(Character attacker, Character defender, Effect effect, String... dialogTexts) {
        if (view.currentShowingScene != null) {
            view.currentShowingScene.disableButtons();
        }
        effectAnimationList.add(new EffectAnimationTimer(effect, getBattleHitBox(defender), attacker, defender, getMaster(), dialogTexts));
    }
    public static void showSkillEffect(Character attacker, Character defender, Effect effect, boolean showDialog, String... dialogTexts ) {
        effectAnimationList.add(new EffectAnimationTimer(effect, getBattleHitBox(defender), attacker, defender, getMaster(), dialogTexts, showDialog));
    }
    public static void showDialog(String[] texts) {
        view.cleanUpScene();
        GameSubScene dialogScene = SubSceneList.createDialogScene(texts);
//        view.addSubSceneToPane(dialogScene);
        view.showSubScene(dialogScene);
//        view.showSubScene(SubSceneList.openDialog);
    }

    @Override
    public void tick() {
        for (int i = 0; i < playerTeam.size(); i++) {
            playerTeam.get(i).getBattleHealthBar().setWidth(BAR_WIDTH * playerTeam.get(i).getHealthPoint() / 100);
            playerTeam.get(i).getBattleManaBar().setWidth(BAR_WIDTH * playerTeam.get(i).getManaPoint() / 100);
            playerTeam.get(i).getBattleHealthPoint().setText("" + playerTeam.get(i).getHealthPoint() + " / 100");
            playerTeam.get(i).getBattleManaPoint().setText("" + playerTeam.get(i).getManaPoint() + " / 100");
            playerTeam.get(i).getBattleHealthBar().setFill(Color.CRIMSON);
            playerTeam.get(i).getBattleManaBar().setFill(Color.CORNFLOWERBLUE);
            playerTeam.get(i).getBattleHealthBarContainer().setFill(Color.DIMGRAY);
            playerTeam.get(i).getBattleManaBarContainer().setFill(Color.DIMGRAY);
        }
        for (int i = 0; i < enemyTeam.size(); i++) {
            enemyTeam.get(i).getBattleHealthBar().setWidth(BAR_WIDTH * enemyTeam.get(i).getHealthPoint() / 100);
            enemyTeam.get(i).getBattleManaBar().setWidth(BAR_WIDTH * enemyTeam.get(i).getManaPoint() / 100);
            enemyTeam.get(i).getBattleHealthPoint().setText("" + enemyTeam.get(i).getHealthPoint() + " / 100");
            enemyTeam.get(i).getBattleManaPoint().setText("" + enemyTeam.get(i).getManaPoint() + " / 100");
            enemyTeam.get(i).getBattleHealthBar().setFill(Color.CRIMSON);
            enemyTeam.get(i).getBattleManaBar().setFill(Color.CORNFLOWERBLUE);
            enemyTeam.get(i).getBattleHealthBarContainer().setFill(Color.DIMGRAY);
            enemyTeam.get(i).getBattleManaBarContainer().setFill(Color.DIMGRAY);
        }
        currentTurnChar.getBattleHitBox().setImage(new Image((new File("./assets/test/effect/turn/current_turn.png")).getAbsolutePath()));
    }

    public void changeFrame() {
        this.getMaster().canvasBattle.getGraphicsContext2D().drawImage(this.getBg(), 0, 0);
        this.lastFrameStep += Player.MOVEMENT_SPEED;
        if (this.lastFrameStep > 6 * Player.MOVEMENT_SPEED) {
            this.lastFrameStep = 0;
            this.currentFrame++;
            if (this.currentFrame > Character.NUM_IMAGE_FRAME) {
                this.currentFrame = 1;
            }
            for (Character character : playerTeam) {
                if (character.getHealthPoint() > 0) {
                    character.getBattleFrame().setImage(new Image(character.getImagePath() + Character.BATTLE_IMAGE_PATH + this.currentFrame + ".png"));
                } else {
                    character.getBattleFrame().setImage(new Image(character.getImagePath() + Character.DEAD_IMAGE_PATH + this.currentFrame + ".png"));
                }
            }
            for (Character character : enemyTeam) {
                if (character.getHealthPoint() > 0) {
                    character.getBattleFrame().setImage(new Image(character.getImagePath() + Character.BATTLE_IMAGE_PATH + this.currentFrame + ".png"));
                } else {
                    character.getBattleFrame().setImage(new Image(character.getImagePath() + Character.DEAD_IMAGE_PATH + this.currentFrame + ".png"));
                }
            }
        }
    }

    private static void removeNPC(Enemy enemy) {
        if (enemy.getName().equals("Enemy 1")) {
            WorldOutside.enemy1Defeated = true;
        } else if (enemy.getName().equals("Enemy 2")) {
            WorldOutside.enemy2Defeated = true;
        } else if (enemy.getName().equals("Enemy 3")) {
            WorldOutside.enemy3Defeated = true;
        } else if (enemy.getName().equals("Boss 1")) {
            WorldOutside2.boss1Defeated = true;
        } else if (enemy.getName().equals("Boss 2")) {
            WorldOutside2.boss2Defeated = true;
        }
    }

    @Override
    public void render() {
        this.tick();
        this.changeFrame();
    }
    public static void quitBattle(){
        removeNPC(enemy);
        view.cleanUpScene();
        player.getStatusEffects().clear();
        SimpleRPG.canvasBattle.getGraphicsContext2D().clearRect(0, 0, SimpleRPG.canvasBattle.getWidth(), SimpleRPG.canvasBattle.getHeight());
        GameView gv = new GameView(getMaster(), true);
        ((World) getMaster().getWorld()).removeNPC(enemy);
        // Focus control on the game
        gv.getMainPane().requestFocus();
    }
}
