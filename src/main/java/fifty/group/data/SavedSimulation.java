package fifty.group.data;

import com.google.gson.annotations.Expose;

public class SavedSimulation {
    @Expose
    public SavedTerrain terrain;

    public SavedSimulation(SavedTerrain terrain) {
        this.terrain = terrain;
    }
}
