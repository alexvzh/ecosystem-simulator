package fifty.group.scene.scenes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import fifty.group.data.SavedSimulation;
import fifty.group.entity.Entity;
import fifty.group.entity.entities.*;
import fifty.group.scene.MouseListener;
import fifty.group.terrain.Terrain;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SimulationScene extends Scene {

    @Expose()
    private Terrain terrain;

    private String lastState = null;

    private Random random;
    private boolean paused = false;

    public SimulationScene() {
        setID(SceneID.SIMULATION);

        this.addMouseMotionListener(new MouseListener(getEntityHandler()));
        this.random = new Random();

        terrain = new Terrain();
        spawnRandomEntities(random.nextInt(100));

        SimulationScene thisthis = this;

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    paused = !paused;
                }

                RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                        .of(Entity.class)
                        .registerSubtype(Fox.class, "fox")
                        .registerSubtype(Grass.class, "grass")
                        .registerSubtype(Rabbit.class, "rabbit")
                        .registerSubtype(Raccoon.class, "raccoon")
                        .registerSubtype(Sheep.class, "sheep")
                        .registerSubtype(Wolf.class, "wolf");


                Gson gson = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .setPrettyPrinting()
                        .registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                        .create();

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    lastState = gson.toJson(thisthis);
                    System.out.println(lastState);
                }

                if (e.getKeyCode() == KeyEvent.VK_L && lastState != null) {
                    SavedSimulation newScene = gson.fromJson(lastState, SavedSimulation.class);
                    System.out.println(newScene.terrain);
                    terrain = new Terrain(newScene.terrain.tileList);
//                    entityHandler.entities = newScene.entityHandler.entities;
//                    System.out.println(newScene.entityHandler.entities.size());
//                    System.out.println(newScene.terrain.tileList.size());
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    @Override
    public void update() {
        if (paused) {
            return;
        }

        getEntityHandler().update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        terrain.drawTileLayer(g2d);
        getEntityHandler().draw(g2d);
    }

    public Terrain getTerrain() {
        return terrain;
    }


    public void spawnRandomEntities(int maxEntities) {
        int numEntities = random.nextInt(maxEntities) + 1;

        for (int i = 0; i < numEntities; i++) {
            int x = random.nextInt(1300) + 50;
            int y = random.nextInt(700) + 50;
            int entityType = random.nextInt(5);

            switch (entityType) {
                case 0:
                    new Wolf(x, y, getEntityHandler());
                    break;
                case 1:
                    new Fox(x, y, getEntityHandler());
                    break;
                case 2:
                    new Sheep(x, y, getEntityHandler());
                    break;
                case 3:
                    new Rabbit(x, y, getEntityHandler());
                    break;
                default:
                    new Raccoon(x, y, getEntityHandler());
            }
        }
    }
}
