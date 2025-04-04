package fifty.group.data;

import com.google.gson.*;
import com.google.gson.annotations.*;
import com.google.gson.typeadapters.*;
import fifty.group.entity.*;
import fifty.group.entity.entities.*;
import fifty.group.scene.SceneManager;
import fifty.group.scene.scenes.SimulationScene;
import fifty.group.terrain.*;

import java.util.*;

public class DataManager {

    @Expose private Terrain terrain;
    @Expose private EntityHandler entityHandler;

    public DataManager(Terrain terrain, EntityHandler entityHandler) {
        this.terrain = terrain;
        this.entityHandler = entityHandler;
    }

    public String serialize() {
        RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Entity.class)
                .registerSubtype(Fox.class, "FOX")
                .registerSubtype(Grass.class, "GRASS")
                .registerSubtype(Rabbit.class, "RABBIT")
                .registerSubtype(Raccoon.class, "RACCOON")
                .registerSubtype(Sheep.class, "SHEEP")
                .registerSubtype(Wolf.class, "WOLF");

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                .setPrettyPrinting()
                .create();

        SceneManager.getInstance().setRunning(!SceneManager.getInstance().isRunning());

        return gson.toJson(this);
    }

    public void deserialize(String json) {
        RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Entity.class)
                .registerSubtype(Fox.class, "FOX")
                .registerSubtype(Grass.class, "GRASS")
                .registerSubtype(Rabbit.class, "RABBIT")
                .registerSubtype(Raccoon.class, "RACCOON")
                .registerSubtype(Sheep.class, "SHEEP")
                .registerSubtype(Wolf.class, "WOLF");

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(Tile.class, new TileDeserializer())
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                .create();

        DataManager data = gson.fromJson(json, DataManager.class);
        terrain = data.terrain;
        entityHandler = data.entityHandler;
        ((SimulationScene)SceneManager.getInstance().getCurrentScene()).getMouseListener().setEntityHandler(data.entityHandler);

        for (Entity entity : entityHandler.entities) {
            entity.setEntityHandler(entityHandler);
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.setRandom(new Random());
                livingEntity.setImages(new ArrayList<>());

                if (livingEntity instanceof Fox) {
                    livingEntity.retrieveSprites(0, 0, "/Fox.png");
                } else if (livingEntity instanceof Rabbit) {
                    livingEntity.retrieveSprites(0, 1, "/Rabbit.png");
                } else if (livingEntity instanceof Raccoon) {
                    livingEntity.retrieveSprites( 1, 0, "/Raccoon.png");
                } else if (livingEntity instanceof Sheep) {
                    livingEntity.retrieveSprites(2, 0, "/Sheep.png");
                } else if (livingEntity instanceof Wolf) {
                    livingEntity.retrieveSprites(2, 0, "/Wolf.png");
                }
            }
        }
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }
}
