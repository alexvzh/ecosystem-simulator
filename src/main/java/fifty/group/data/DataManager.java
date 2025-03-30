package fifty.group.data;

import com.google.gson.*;
import com.google.gson.annotations.*;
import fifty.group.terrain.*;

public class DataManager {
    @Expose
    private Terrain terrain;

    public DataManager(Terrain terrain) {
        this.terrain = terrain;
    }

    public String serialize() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        return gson.toJson(this);
    }

    public void deserialize(String json) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(Tile.class, new TileDeserializer())
                .create();

        DataManager data = gson.fromJson(json, DataManager.class);
        terrain = data.terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }
}
