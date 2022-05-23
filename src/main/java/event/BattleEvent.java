package event;

import entity.Character;
import entity.Enemy;
import views.BattleView;
import world.Tile;
import world.World;

public class BattleEvent extends Event {


    public BattleEvent(World world, String triggerType) {
        super(world, triggerType);
    }

    public BattleEvent(World world, String triggerType, double x, double y) {
        super(world, triggerType, x, y);
    }

    public BattleEvent(World world, String triggerType, Character character) {
        super(world, triggerType, character);
    }

    public BattleEvent(World world, String triggerType, Tile tile) {
        super(world, triggerType, tile);
    }

    @Override
    public void trigger() {
        new BattleView(this.getGameInstance(), (Enemy) this.getCharacter());
    }
}
