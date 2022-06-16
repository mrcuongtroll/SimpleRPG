package world;

import event.HomeToOutsideEvent;
import event.StartTutorialEvent;
import main.SimpleRPG;

import java.io.File;

public class WorldHome extends World {
    public WorldHome(SimpleRPG master, double playerX, double playerY) {
        super(master, playerX, playerY,
                (new File("./assets/Map/Home/home_bottom.png")).getAbsolutePath(),
                (new File("./assets/Map/Home/home_overlay.png")).getAbsolutePath(),
                (new File("./assets/Map/Home/home_shading.png")).getAbsolutePath(),
                (new File("./assets/Map/Home/home_mask.png")).getAbsolutePath());

        // Add event to get outta the house
        for (int i = 264; i <= 360; i+=Tile.TILE_SIZE*2) {
            this.getEventList().add(new HomeToOutsideEvent(this, 200, i));
        }
        this.getEventList().add(new StartTutorialEvent(this, 0, 0));
    }

    public WorldHome(SimpleRPG master) {
        this(master, 240, 300);
    }
}
