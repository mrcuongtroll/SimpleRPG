package main;

import entity.Player;
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
    private Text healthPoint;
    private Text manaPoint;
    private Rectangle container;
    private SimpleRPG master;

    private Player player;
    public HUD(SimpleRPG master, Player player) {
        this.master = master;

        this.healthBar = new Rectangle(50, 50, BAR_WIDTH, 30);
        this.manaBar = new Rectangle(50, 100, BAR_WIDTH, 30);
        this.staminaBar = new Rectangle(50, 150, BAR_WIDTH, 30);

        this.healthBarContainer = new Rectangle(50, 50, BAR_WIDTH, 30);
        this.manaBarContainer = new Rectangle(50, 100, BAR_WIDTH, 30);
        this.staminaBarContainer = new Rectangle(50, 150, BAR_WIDTH, 30);

        this.healthPoint = new Text(100, 75, "");
        this.manaPoint = new Text(100, 125, "");

        this.container = new Rectangle(25, 37.5, 350, 150);

        this.player = player;
    }

    public void start() {
        this.master.root.getChildren().add(this.container);

        this.master.root.getChildren().add(this.healthBarContainer);
        this.master.root.getChildren().add(this.manaBarContainer);
        this.master.root.getChildren().add(this.staminaBarContainer);

        this.master.root.getChildren().add(this.healthBar);
        this.master.root.getChildren().add(this.manaBar);
        this.master.root.getChildren().add(this.staminaBar);

        this.master.root.getChildren().add(this.healthPoint);
        this.master.root.getChildren().add(this.manaPoint);

        this.healthBarContainer.setFill(Color.DIMGRAY);
        this.manaBarContainer.setFill(Color.DIMGRAY);
        this.staminaBarContainer.setFill(Color.DIMGRAY);
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
    public void render() {
        setHealthPoint(this.player.getHealthPoint());
        setManaPoint(this.player.getManaPoint());
        setStaminaPoint(this.player.getStamina());

        this.healthBar.setFill(Color.CRIMSON);
        this.manaBar.setFill(Color.CORNFLOWERBLUE);
        this.staminaBar.setFill(Color.LIGHTGREEN);
    }
}
