package fifty.group.data;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SavedTerrain {
    @Expose
    public List<SavedTile> tileList;

    public SavedTerrain(List<SavedTile> tileList) {
        this.tileList = tileList;
    }
}
