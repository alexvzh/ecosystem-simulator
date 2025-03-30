package fifty.group.data;

import com.google.gson.*;
import com.google.gson.reflect.*;
import fifty.group.terrain.*;

import java.lang.reflect.*;
import java.util.*;

public class TileDeserializer implements JsonDeserializer<Tile> {
    @Override
    public Tile deserialize(JsonElement jsonElement,
                            Type type,
                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject root = jsonElement.getAsJsonObject();
        Gson gson = new Gson();

        return new Tile(
                root.get("x").getAsInt(),
                root.get("y").getAsInt(),
                gson.fromJson(root.get("type"), TileType.class),
                gson.fromJson(root.get("neighbors"), new TypeToken<ArrayList<TileType>>(){}.getType())
        );
    }
}
