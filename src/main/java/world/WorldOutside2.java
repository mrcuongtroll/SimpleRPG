package world;

import entity.Ally;
import entity.Enemy;
import entity.NPC;
import event.*;
import main.SimpleRPG;

import java.io.File;

public class WorldOutside2 extends World {

    public static boolean boss1Defeated = false;
    public static boolean boss2Defeated = false;

    public WorldOutside2(SimpleRPG master, double playerX, double playerY) {
        super(master, playerX, playerY,
                "/assets/Map/Outside2/outside2_bottom.png",
                "/assets/Map/Outside2/outside2_overlay.png",
                "/assets/Map/Outside2/outside2_shading.png",
                "/assets/Map/Outside2/outside2_mask.png");

        for (int i = 216; i <= 504; i += Tile.TILE_SIZE) {
            this.getEventList().add(new Outside2ToOutsideEvent(this, i, -48));
        }

        initiateNPCList();
    }
    public WorldOutside2(SimpleRPG master) {
        this(master, 375, 60);
    }

    @Override
    public void initiateNPCList() {
        if (this.getNpcList().size() == 0) {
            if (!boss1Defeated) {
                this.getNpcList().add(new Enemy(this, this.getMaster(), 285, 1005, 285 + (int) this.getX(), 1005 + (int) this.getY(), "Boss 1",
                        "/assets/test/boss1",
                        7, 5, 50, 20, 1000, 200, 1000, 200));
            }
            if (!boss2Defeated) {
                this.getNpcList().add(new Enemy(this, this.getMaster(), 1420, 130, 1420 + (int) this.getX(), 130 + (int) this.getY(), "Boss 2",
                        "/assets/test/boss2",
                        7, 5, 50, 20, 1000, 200, 1000, 200));
            }
        }
        for (NPC npc: this.getNpcList()) {
            if (npc instanceof Enemy) {
                Event enemyEvent = new BossBattleEvent(this, Event.TRIGGER_TYPE_TOUCH, npc);
                npc.setEvent(enemyEvent);
                this.getEventList().add(enemyEvent);
                if (npc instanceof Enemy) {
                    ((Enemy) npc).initEnemyAllies();
                }
            }
        }
    }
}
