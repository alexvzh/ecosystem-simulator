package fifty.group.data;

import com.google.gson.annotations.Expose;
import fifty.group.terrain.TileType;

public class SavedTile {
    @Expose
    public final int x;

    @Expose
    public final int y;

    @Expose
    public final TileType type;

    public SavedTile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
