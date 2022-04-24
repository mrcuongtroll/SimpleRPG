package main;

import entity.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HUD {
    private Rectangle healthBar;
    private Rectangle manaBar;
    private Rectangle healthBarContainer;
    private Rectangle manaBarContainer;
    private Text healthPoint;
    private Text manaPoint;
    private Rectangle container;
    private SimpleRPG master;

    public HUD(SimpleRPG master, Player player) {
        this.master = master;

        this.healthBar = new Rectangle(50, 50, 300, 30);
        this.manaBar = new Rectangle(50, 100, 300, 30);
        this.healthBarContainer = new Rectangle(50, 50, 300, 30);
        this.manaBarContainer = new Rectangle(50, 100, 300, 30);
        this.healthPoint = new Text(100, 75, "");
        this.manaPoint = new Text(100, 125, "");

        this.container = new Rectangle(25, 37.5, 350, 100);

        this.healthBar.setFill(Color.CRIMSON);
        this.manaBar.setFill(Color.CORNFLOWERBLUE);
        this.healthBarContainer.setFill(Color.DIMGRAY);
        this.manaBarContainer.setFill(Color.DIMGRAY);
        this.container.setFill(Color.GRAY);

        setHealthPoint(player.getHealthPoint());
        setManaPoint(player.getManaPoint());
    }
    public void setHealthPoint(int healthPoint) {
        this.healthBar.setWidth(this.healthBar.getWidth() * healthPoint / 100);
        this.healthPoint.setText("" + healthPoint + " / 100");
    }
    public void setManaPoint(int manaPoint) {
        this.manaBar.setWidth(this.manaBar.getWidth() * manaPoint / 100);
        this.manaPoint.setText("" + manaPoint + " / 100");
    }
    public void render() {
        this.master.root.getChildren().add(this.container);
        this.master.root.getChildren().add(this.healthBarContainer);
        this.master.root.getChildren().add(this.manaBarContainer);
        this.master.root.getChildren().add(this.healthBar);
        this.master.root.getChildren().add(this.manaBar);
        this.master.root.getChildren().add(this.healthPoint);
        this.master.root.getChildren().add(this.manaPoint);
    }
}
