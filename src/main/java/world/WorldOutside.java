package world;

import entity.Enemy;
import entity.NPC;
import event.BattleEvent;
import event.Event;
import event.HomeToOutsideEvent;
import event.OutsideToHomeEvent;
import main.SimpleRPG;

import java.io.File;

public class WorldOutside extends World {

    public WorldOutside(SimpleRPG master, double playerX, double playerY) {
        super(master, playerX, playerY,
                (new File("./assets/Map/Outside/outside_bottom.png")).getAbsolutePath(),
                (new File("./assets/Map/Outside/outside_overlay.png")).getAbsolutePath(),
                (new File("./assets/Map/Outside/outside_shading.png")).getAbsolutePath(),
                (new File("./assets/Map/Outside/outside_mask.png")).getAbsolutePath());
        // Entrance event to Home
        this.getEventList().add(new OutsideToHomeEvent(this, 624, 770));

        // Create NPCs (this should be move to subclasses)
        this.getNpcList().add(new Enemy(this, master, 1000, 400, 1000+(int)this.getX(), 400+(int)this.getY(), "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 100, 100, 100, 100, 20, 10));
        this.getNpcList().add(new Enemy(this, master, 1500, 400, 1500+(int)this.getX(), 400+(int)this.getY(), "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 100, 100, 100, 100, 20, 10));
        this.getNpcList().add(new Enemy(this, master, 1300, 600, 1300+(int)this.getX(), 600+(int)this.getY(), "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 100, 100, 100, 100, 20, 10));
        for (NPC npc: this.getNpcList()) {
            Event enemyEvent = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, npc);
            npc.setEvent(enemyEvent);
            this.getEventList().add(enemyEvent);
        }
    }

    public WorldOutside(SimpleRPG master) {
        this(master, 620, 820);
    }
}
