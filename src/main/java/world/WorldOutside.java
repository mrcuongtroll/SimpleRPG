package world;

import entity.Ally;
import entity.Enemy;
import entity.NPC;
import event.BattleEvent;
import event.Event;
import event.FirstAllyRecruitEvent;
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

        // ***Events***
        // Entrance event to Home
        this.getEventList().add(new OutsideToHomeEvent(this, 620, 800));

        // Create NPCs
        Enemy enemy1 = new Enemy(this, master, 1000, 400, 1000+(int)this.getX(), 400+(int)this.getY(), "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 20, 10, 100, 100, 100, 100);
        enemy1.initEnemyAllies();
        Event enemyEvent1 = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, enemy1);
        enemy1.setEvent(enemyEvent1);
        this.getNpcList().add(enemy1);
        this.getEventList().add(enemyEvent1);
        Enemy enemy2 = new Enemy(this, master, 1500, 400, 1500+(int)this.getX(), 400+(int)this.getY(), "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 20, 10, 100, 100, 100, 100);
        enemy2.initEnemyAllies();
        Event enemyEvent2 = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, enemy2);
        enemy2.setEvent(enemyEvent2);
        this.getNpcList().add(enemy2);
        this.getEventList().add(enemyEvent2);
        Enemy enemy3 = new Enemy(this, master, 1300, 600, 1300+(int)this.getX(), 600+(int)this.getY(), "Enemy",
                (new File("./assets/test/enemy")).getAbsolutePath(),
                1, 5, 20, 10, 100, 100, 100, 100);
        enemy3.initEnemyAllies();
        Event enemyEvent3 = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, enemy3);
        enemy3.setEvent(enemyEvent3);
        this.getNpcList().add(enemy3);
        this.getEventList().add(enemyEvent3);
//        for (NPC npc: this.getNpcList()) {
//            Event enemyEvent = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, npc);
//            npc.setEvent(enemyEvent);
//            this.getEventList().add(enemyEvent);
//        }

        // Add NPC allies
        Ally ally1 = new Ally(this, master, 353, 406, 353+(int)this.getX(), 406+(int)this.getY(), "Ally 1",
                (new File("./assets/test/ally1")).getAbsolutePath(),
                1, 5, 15, 0, 100, 100, 100, 100, NPC.MODE_IDLE);
        Event allyEvent1 = new FirstAllyRecruitEvent(this, ally1);
        ally1.setEvent(allyEvent1);
        this.getNpcList().add(ally1);
        this.getEventList().add(allyEvent1);
    }

    public WorldOutside(SimpleRPG master) {
        this(master, 620, 820);
    }
}
