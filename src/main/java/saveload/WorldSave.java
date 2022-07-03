package saveload;

import entity.Enemy;
import entity.NPC;
import entity.Player;
import event.BattleEvent;
import event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.SimpleRPG;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class WorldSave implements Serializable {
    private double dy;
    private double dx;
    private double x;
    private double y;
    private String worldType;
    private String bgImagePath;
    private String overlayImagePath;
    private String shadingImagePath;
    private String maskPath;
    private boolean tutorialViewed = true;
    private boolean doorUnlocked = false;
    private boolean keyObtained = false;

    private boolean ally1Recruited;
    private boolean enemy1Defeated;
    private boolean enemy2Defeated;
    private boolean enemy3Defeated;

    public WorldSave(double dy, double dx, double x, double y, String worldType, String bgImagePath, String overlayImagePath, String shadingImagePath, String maskPath, boolean tutorialViewed, boolean doorUnlocked, boolean keyObtained, boolean ally1Recruited, boolean enemy1Defeated, boolean enemy2Defeated, boolean enemy3Defeated) {
        this.dy = dy;
        this.dx = dx;
        this.x = x;
        this.y = y;
        this.worldType = worldType;
        this.bgImagePath = bgImagePath;
        this.overlayImagePath = overlayImagePath;
        this.shadingImagePath = shadingImagePath;
        this.maskPath = maskPath;
        this.tutorialViewed = tutorialViewed;
        this.doorUnlocked = doorUnlocked;
        this.keyObtained = keyObtained;
        this.ally1Recruited = ally1Recruited;
        this.enemy1Defeated = enemy1Defeated;
        this.enemy2Defeated = enemy2Defeated;
        this.enemy3Defeated = enemy3Defeated;
    }

    public boolean isTutorialViewed() {
        return tutorialViewed;
    }

    public boolean isDoorUnlocked() {
        return doorUnlocked;
    }

    public boolean isKeyObtained() {
        return keyObtained;
    }

    public boolean isAlly1Recruited() {
        return ally1Recruited;
    }

    public boolean isEnemy1Defeated() {
        return enemy1Defeated;
    }

    public boolean isEnemy2Defeated() {
        return enemy2Defeated;
    }

    public boolean isEnemy3Defeated() {
        return enemy3Defeated;
    }

    public double getDy() {
        return dy;
    }
    public double getDx() {
        return dx;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getWorldType() {
        return worldType;
    }
    public String getBgImagePath() {
        return bgImagePath;
    }
    public String getOverlayImagePath() {
        return overlayImagePath;
    }
    public String getShadingImagePath() {
        return shadingImagePath;
    }
    public String getMaskPath() {
        return maskPath;
    }
}
