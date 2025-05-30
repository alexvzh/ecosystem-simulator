package fifty.group.data;

import com.google.gson.annotations.Expose;
import fifty.group.entity.Entity;
import fifty.group.terrain.Tile;

import java.util.ArrayList;
import java.util.List;

public class SimulationState {
    @Expose private ArrayList<Tile> tileList;
    @Expose private List<Entity> entityList;
    @Expose private int time;

    public SimulationState(ArrayList<Tile> tileList, List<Entity> entityList, int time) {
        this.tileList = tileList;
        this.entityList = entityList;
        this.time = time;
    }

    public ArrayList<Tile> getTileList() {
        return tileList;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public int getTime() {
        return time;
    }
}
