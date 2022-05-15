package world;

import java.awt.Rectangle;

public class Tile {
    public static final int TILE_SIZE = 24;
    private final Rectangle rect;
    private final boolean isSolid;

    public Tile(int x, int y, boolean isSolid) {
        this.rect = new Rectangle(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
        this.isSolid = isSolid;
    }

    public boolean isSolid() {
        return this.isSolid;
    }

    public Rectangle getRect() {
        return this.rect;
    }
}
