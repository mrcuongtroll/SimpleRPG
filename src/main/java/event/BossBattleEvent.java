package event;

import dialogue.Dialogue;
import entity.Character;
import entity.Enemy;
import saveload.SaveLoad;
import views.BattleView;
import world.Tile;
import world.World;

public class BossBattleEvent extends Event {
    public BossBattleEvent(World world, String triggerType) {
        super(world, triggerType);
    }

    public BossBattleEvent(World world, String triggerType, double x, double y) {
        super(world, triggerType, x, y);
    }

    public BossBattleEvent(World world, String triggerType, Character character) {
        super(world, triggerType, character);
    }

    public BossBattleEvent(World world, String triggerType, Tile tile) {
        super(world, triggerType, tile);
    }

    @Override
    public void trigger() {
        SaveLoad.saveStateBeforeBattle(this.getGameInstance());
        this.getGameInstance().getPlayer().stopMoving(this.getGameInstance().getPlayer().getLastDirection());
        Dialogue d1 = new Dialogue("I am " + this.getCharacter().getName() + ". Prepare to die, casul!");
        d1.setEvent(new Event(this.getWorld(), Event.TRIGGER_TYPE_INTERACT) {
            @Override
            public void trigger() {
                new BattleView(BossBattleEvent.this.getGameInstance(), (Enemy) BossBattleEvent.this.getCharacter());
            }
        });
        this.getGameInstance().getDialogueRender().setDialogue(d1);
    }
}
