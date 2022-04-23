package entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.SimpleRPG;

public abstract class Character {

    public static final String DEFAULT_IMAGE_PATH = "/default/";
    public static final String DOWN_IMAGE_PATH = "/move_down/";
    public static final String UP_IMAGE_PATH = "/move_up/";
    public static final String LEFT_IMAGE_PATH = "/move_left/";
    public static final String RIGHT_IMAGE_PATH = "/move_right/";
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
        this.image = new Image(imagePath + DEFAULT_IMAGE_PATH + "1.png");
        this.gc = this.master.canvas.getGraphicsContext2D();
    }

    public void render() {
        this.gc.drawImage(this.image, this.x, this.y);
    }

    public void changeFrame(String direction) {
        if (direction.equals(DEFAULT_IMAGE_PATH)) {
            this.currentFrame = 1;
        } else {
            this.currentFrame += 1;
            this.currentFrame %= 9;
        }
        this.image = new Image(this.imagePath + direction + currentFrame + ".png");
    }
}
