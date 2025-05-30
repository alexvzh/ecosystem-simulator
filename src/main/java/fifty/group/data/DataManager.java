package fifty.group.data;

import com.google.gson.*;
import com.google.gson.typeadapters.*;
import fifty.group.entity.*;
import fifty.group.entity.entities.*;
import fifty.group.scene.SceneManager;
import fifty.group.terrain.*;
import fifty.group.time.TimeManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataManager {

    private Terrain terrain;
    private EntityHandler entityHandler;
    private TimeManager timeManager;

    public DataManager(Terrain terrain, TimeManager timeManager) {
        this.terrain = terrain;
        this.timeManager = timeManager;
    }

    public void serialize(SimulationState state) {
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);

        String filename = "saves/simulation_" + timestamp + ".json";

        saveJsonToFile(gson.toJson(state), filename);
    }

    public void deserialize(String path) {

        String json = loadJsonFromFile(path);
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

        SimulationState state = gson.fromJson(json, SimulationState.class);
        entityHandler.getEntityList().clear();
        terrain.setTileList(state.getTileList());
        timeManager.setTime(state.getTime());

        for (Entity entity : state.getEntityList()) {
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

            entityHandler.addEntity(entity);
        }
    }

    public void saveJsonToFile(String json, String filePath) {
        try {
            // Create the parent directories if they don't exist
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                boolean dirsCreated = parent.mkdirs();
                if (!dirsCreated) {
                    System.out.println("Failed to create directories for path: " + parent.getAbsolutePath());
                    return;
                }
            }

            // Write the JSON to the file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json);
                System.out.println("JSON saved to: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("I/O error while saving file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String loadJsonFromFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found: " + file.getAbsolutePath());
                return null;
            }

            String json = Files.readString(Paths.get(filePath));
            System.out.println("JSON loaded from: " + file.getAbsolutePath());
            return json;

        } catch (IOException e) {
            System.out.println("I/O error while reading file: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    public void setEntityHandler(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;
    }
}
