package world;

import entity.Enemy;
import entity.NPC;
import event.BattleEvent;
import event.Event;
import event.OutsideToHomeEvent;
import main.SimpleRPG;

import java.io.File;
import java.util.ArrayList;

public class WorldOutside extends World {

    public WorldOutside(SimpleRPG master, double playerX, double playerY) {
        super(master, playerX, playerY,
                (new File("./assets/Map/Outside/outside_bottom.png")).getAbsolutePath(),
                (new File("./assets/Map/Outside/outside_overlay.png")).getAbsolutePath(),
                (new File("./assets/Map/Outside/outside_shading.png")).getAbsolutePath(),
                (new File("./assets/Map/Outside/outside_mask.png")).getAbsolutePath());

        // ***Events***
        // Entrance event to Home
        this.getEventList().add(new OutsideToHomeEvent(this, 620, 800));

        //Initialize NPC if cant load save file
        initiateNPCList();
    }

    public void initiateNPCList(ArrayList<NPC> npcList) {
        if (npcList.size() == 0) {
            this.getNpcList().add(new Enemy(this, this.getMaster(), 1000, 400, 1000+(int)this.getX(), 400+(int)this.getY(), "Enemy 1",
                    (new File("./assets/test/enemy1")).getAbsolutePath(),
                    1, 5, 20, 10, 100, 100, 100, 100));
            this.getNpcList().add(new Enemy(this, this.getMaster(), 1500, 400, 1500+(int)this.getX(), 400+(int)this.getY(), "Enemy 2",
                    (new File("./assets/test/enemy2")).getAbsolutePath(),
                    1, 5, 20, 10, 100, 100, 100, 100));
            this.getNpcList().add(new Enemy(this, this.getMaster(), 1300, 600, 1300+(int)this.getX(), 600+(int)this.getY(), "Enemy 3",
                    (new File("./assets/test/enemy3")).getAbsolutePath(),
                    1, 5, 20, 10, 100, 100, 100, 100));
        }
        for (NPC npc: this.getNpcList()) {
            Event enemyEvent = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, npc);
            npc.setEvent(enemyEvent);
            this.getEventList().add(enemyEvent);
            if (npc instanceof Enemy) {
                ((Enemy) npc).initEnemyAllies();
            }
        }
    }

    public void initiateNPCList() {
        if (this.getNpcList().size() == 0) {
            this.getNpcList().add(new Enemy(this, this.getMaster(), 1000, 400, 1000+(int)this.getX(), 400+(int)this.getY(), "Enemy 1",
                    (new File("./assets/test/enemy1")).getAbsolutePath(),
                    1, 5, 20, 10, 100, 100, 100, 100));
            this.getNpcList().add(new Enemy(this, this.getMaster(), 1500, 400, 1500+(int)this.getX(), 400+(int)this.getY(), "Enemy 2",
                    (new File("./assets/test/enemy2")).getAbsolutePath(),
                    1, 5, 20, 10, 100, 100, 100, 100));
            this.getNpcList().add(new Enemy(this, this.getMaster(), 1300, 600, 1300+(int)this.getX(), 600+(int)this.getY(), "Enemy 3",
                    (new File("./assets/test/enemy3")).getAbsolutePath(),
                    1, 5, 20, 10, 100, 100, 100, 100));
        }
        for (NPC npc: this.getNpcList()) {
            Event enemyEvent = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, npc);
            npc.setEvent(enemyEvent);
            this.getEventList().add(enemyEvent);
            if (npc instanceof Enemy) {
                ((Enemy) npc).initEnemyAllies();
            }
        }
    }

    public WorldOutside(SimpleRPG master) {
        this(master, 620, 820);
    }
}
