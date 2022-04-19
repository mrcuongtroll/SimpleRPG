package entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.SimpleRPG;

public abstract class Character {
    private SimpleRPG master;
    private int x;
    private int y;
    private String imagePath;
    private int currentFrame;
    private Image image;
    private GraphicsContext gc;

    public Character(SimpleRPG master, int x, int y, String imagePath, int width, int height) {
        this.master = master;
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        this.currentFrame = 1;
        this.image = new Image(imagePath + "-0001.png");
        this.gc = this.master.canvas.getGraphicsContext2D();
    }

    public void render() {
        this.gc.drawImage(this.image, this.x, this.y);
    }

    public void changeFrame() {
        this.currentFrame += 1;
        this.currentFrame %= 6;
        this.image = new Image(this.imagePath + "-000" + Integer.toString(currentFrame) + ".png");
    }
}
