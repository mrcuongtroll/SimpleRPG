package world;

import entity.Ally;
import entity.Enemy;
import entity.NPC;
import event.*;
import main.SimpleRPG;

import java.io.File;
import java.util.ArrayList;

public class WorldOutside extends World {

    public static boolean ally1Recruited = false;
    public static boolean ally2Recruited = false;
    public static boolean enemy1Defeated = false;
    public static boolean enemy2Defeated = false;
    public static boolean enemy3Defeated = false;

    public WorldOutside(SimpleRPG master, double playerX, double playerY) {
        super(master, playerX, playerY,
                "/assets/Map/Outside/outside_bottom.png",
                "/assets/Map/Outside/outside_overlay.png",
                "/assets/Map/Outside/outside_shading.png",
                "/assets/Map/Outside/outside_mask.png");

        // ***Events***
        // Entrance event to Home
        this.getEventList().add(new OutsideToHomeEvent(this, 620, 800));
        for (int i = 314; i <= 494; i += Tile.TILE_SIZE) {
            this.getEventList().add(new OutsideToOutside2Event(this, i, 1426));
        }
        //Initialize NPC if cant load save file
        initiateNPCList();
    }

    public void initiateNPCList() {
        if (this.getNpcList().size() == 0) {
            if (!enemy1Defeated) {
                this.getNpcList().add(new Enemy(this, this.getMaster(), 1000, 400, 1000 + (int) this.getX(), 400 + (int) this.getY(), "Enemy 1",
                        "/assets/test/enemy1",
                        1, 5, 20, 10, 100, 100, 100, 100));
            }
            if (!enemy2Defeated) {
                this.getNpcList().add(new Enemy(this, this.getMaster(), 1500, 400, 1500 + (int) this.getX(), 400 + (int) this.getY(), "Enemy 2",
                        "/assets/test/enemy2",
                        1, 5, 20, 10, 100, 100, 100, 100));
            }
            if (!enemy3Defeated) {
                this.getNpcList().add(new Enemy(this, this.getMaster(), 1300, 600, 1300 + (int) this.getX(), 600 + (int) this.getY(), "Enemy 3",
                        "/assets/test/enemy3",
                        1, 5, 20, 10, 100, 100, 100, 100));
            }
            if (!ally1Recruited) {
                Ally ally1 = new Ally(this, this.getMaster(), 353, 406, 353 + (int) this.getX(), 406 + (int) this.getY(), "Ally 1",
                        "/assets/test/ally1",
                        1, 5, 35, 15, 100, 100, 100, 100, NPC.MODE_IDLE);
                Event allyEvent1 = new FirstAllyRecruitEvent(this, ally1);
                ally1.setEvent(allyEvent1);
                this.getNpcList().add(ally1);
                this.getEventList().add(allyEvent1);
            }
            if (!ally2Recruited && enemy1Defeated && enemy2Defeated && enemy3Defeated && ally1Recruited) {
                Ally ally2 = new Ally(this, this.getMaster(), (int)(this.getMaster().getPlayer().getX() + 50),
                        (int)(this.getMaster().getPlayer().getY() - 50),
                        (int)(this.getMaster().getPlayer().getX() + 50) + (int) this.getX(),
                        (int)(this.getMaster().getPlayer().getY() - 50) + (int) this.getY(), "Ally 2",
                        "/assets/test/ally2",
                        1, 5, 40, 20, 100, 100, 100, 100, NPC.MODE_CHASE);
                Event allyEvent2 = new SecondAllyRecruitEvent(this, ally2);
                ally2.setEvent(allyEvent2);
                this.getNpcList().add(ally2);
                this.getEventList().add(allyEvent2);
            }
        }
        for (NPC npc: this.getNpcList()) {
            if (npc instanceof Enemy) {
                Event enemyEvent = new BattleEvent(this, Event.TRIGGER_TYPE_TOUCH, npc);
                npc.setEvent(enemyEvent);
                this.getEventList().add(enemyEvent);
                if (npc instanceof Enemy) {
                    ((Enemy) npc).initEnemyAllies();
                }
            }
        }
    }

    public WorldOutside(SimpleRPG master) {
        this(master, 620, 820);
    }
}
