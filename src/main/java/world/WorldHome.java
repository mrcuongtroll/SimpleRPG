package world;

import event.*;
import main.SimpleRPG;

import java.io.File;

public class WorldHome extends World {

    public static boolean tutorialViewed = true;
    public static boolean doorUnlocked = false;
    public static boolean keyObtained = false;

    public WorldHome(SimpleRPG master, double playerX, double playerY) {
        super(master, playerX, playerY,
                (new File("./assets/Map/Home/home_bottom.png")).getAbsolutePath(),
                (new File("./assets/Map/Home/home_overlay.png")).getAbsolutePath(),
                (new File("./assets/Map/Home/home_shading.png")).getAbsolutePath(),
                (new File("./assets/Map/Home/home_mask.png")).getAbsolutePath());

        // ***Events***
        // Add event to get outta the house
        for (int i = 264; i <= 360; i += Tile.TILE_SIZE) {
            if (doorUnlocked) {
                this.getEventList().add(new HomeToOutsideEvent(this, 228, i));
            } else {
                this.getEventList().add(new DoorNoteEvent(this, 228, i));
            }
        }
        // Add starting tutorial event
        if (tutorialViewed) {
            this.getEventList().add(new StartTutorialEvent(this, 0, 0));
        }
        // Lamb sauce event
        this.getEventList().add(new LambSauceEvent(this, 570, 386));
        // Note event
        this.getEventList().add(new HomeNoteEvent(this, 513, 386));
    }

    public WorldHome(SimpleRPG master) {
        this(master, 240, 300);
    }
}
