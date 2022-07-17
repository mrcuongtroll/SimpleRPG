package main;

import entity.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HUD {
    private Rectangle healthBar;
    private Rectangle manaBar;

    private static final int BAR_WIDTH = 300;
    private Rectangle staminaBar;
    private Rectangle healthBarContainer;
    private Rectangle manaBarContainer;
    private Rectangle staminaBarContainer;
    private Rectangle expBar;
    private Rectangle expBarContainer;

    private Text healthPoint;
    private Text manaPoint;
    private Text expPoint;

    private Rectangle container;
    private Group groupContainer;
    private SimpleRPG master;

    public HUD(SimpleRPG master) {
        this.master = master;

        this.healthBar = new Rectangle(50, 50, BAR_WIDTH, 30);
        this.manaBar = new Rectangle(50, 100, BAR_WIDTH, 30);
        this.staminaBar = new Rectangle(50, 150, BAR_WIDTH, 30);
        this.expBar = new Rectangle(50, 200, BAR_WIDTH, 30);


        this.healthBarContainer = new Rectangle(50, 50, BAR_WIDTH, 30);
        this.manaBarContainer = new Rectangle(50, 100, BAR_WIDTH, 30);
        this.staminaBarContainer = new Rectangle(50, 150, BAR_WIDTH, 30);
        this.expBarContainer = new Rectangle(50, 200, BAR_WIDTH, 30);

        this.healthPoint = new Text(100, 75, "");
        this.manaPoint = new Text(100, 125, "");
        this.expPoint = new Text(100, 225, "");

        this.container = new Rectangle(25, 37.5, 350, 200);
        this.groupContainer = new Group();

        this.start();
    }

    public void start() {
        this.groupContainer.getChildren().add(this.container);

        this.groupContainer.getChildren().add(this.healthBarContainer);
        this.groupContainer.getChildren().add(this.manaBarContainer);
        this.groupContainer.getChildren().add(this.staminaBarContainer);
        this.groupContainer.getChildren().add(this.expBarContainer);

        this.groupContainer.getChildren().add(this.healthBar);
        this.groupContainer.getChildren().add(this.manaBar);
        this.groupContainer.getChildren().add(this.staminaBar);
        this.groupContainer.getChildren().add(this.expBar);

        this.groupContainer.getChildren().add(this.healthPoint);
        this.groupContainer.getChildren().add(this.manaPoint);
        this.groupContainer.getChildren().add(this.expPoint);

        this.healthBarContainer.setFill(Color.DIMGRAY);
        this.manaBarContainer.setFill(Color.DIMGRAY);
        this.staminaBarContainer.setFill(Color.DIMGRAY);
        this.expBarContainer.setFill(Color.DIMGRAY);

        this.container.setFill(Color.GRAY);
    }
    public void setHealthPoint(int healthPoint) {
        this.healthBar.setWidth(BAR_WIDTH * healthPoint / 100);
        this.healthPoint.setText("" + healthPoint + " / 100");
    }
    public void setManaPoint(int manaPoint) {
        this.manaBar.setWidth(BAR_WIDTH * manaPoint / 100);
        this.manaPoint.setText("" + manaPoint + " / 100");
    }
    public void setStaminaPoint(int staminaPoint) {
        this.staminaBar.setWidth(BAR_WIDTH * staminaPoint / Player.MAX_STAMINA);
    }
    public void setExpPoint(int expPoint){
        this.expBar.setWidth(BAR_WIDTH * expPoint / 350);
        this.expPoint.setText("" + expPoint + " / 350");

    }
    public void render() {
        setHealthPoint(this.master.getPlayer().getHealthPoint());
        setManaPoint(this.master.getPlayer().getManaPoint());
        setStaminaPoint(this.master.getPlayer().getStamina());
        setExpPoint(this.master.getPlayer().getExp());


        this.healthBar.setFill(Color.CRIMSON);
        this.manaBar.setFill(Color.CORNFLOWERBLUE);
        this.staminaBar.setFill(Color.LIGHTGREEN);
        this.expBar.setFill(Color.YELLOW);

    }

    public Group getHUD() {
        return this.groupContainer;
    }

    public void hideHUD() {
        this.groupContainer.setVisible(false);
    }

    public void showHUD() {
        this.groupContainer.setVisible(true);
    }
}
