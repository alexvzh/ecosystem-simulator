package fifty.group.scene.scenes;

import fifty.group.data.DataManager;
import fifty.group.entity.entities.*;
import fifty.group.scene.MouseListener;
import fifty.group.terrain.Terrain;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;
import fifty.group.time.TimeManager;

import java.awt.*;
import java.util.Random;

public class SimulationScene extends Scene {

    private final Random random;
    private final DataManager dataManager;
    private final TimeManager timeManager;
    private final MouseListener mouseListener;

    public SimulationScene(DataManager dataManager, TimeManager timeManager) {
        setID(SceneID.SIMULATION);
        this.random = new Random();
        this.dataManager = dataManager;
        this.timeManager = timeManager;
        this.mouseListener = new MouseListener(entityHandler);
        this.addMouseMotionListener(mouseListener);
        this.dataManager.setEntityHandler(this.entityHandler);

        initRandomSimulation();

    }

    @Override
    public void update() {
        getEntityHandler().update();
        timeManager.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        dataManager.getTerrain().drawTileLayer(g2d);
        getEntityHandler().draw(g2d);
        timeManager.draw(g2d);
    }

    public Terrain getTerrain() {
        return dataManager.getTerrain();
    }

    public void initRandomSimulation() {
        spawnRandomEntities(random.nextInt(100));
    }


    public void spawnRandomEntities(int maxEntities) {
        int numEntities = random.nextInt(maxEntities) + 1;

        for (int i = 0; i < numEntities; i++) {
            int x = random.nextInt(1300) + 50;
            int y = random.nextInt(700) + 50;
            int entityType = random.nextInt(6);

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

    public MouseListener getMouseListener() {
        return mouseListener;
    }
}
