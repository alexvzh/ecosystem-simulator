package fifty.group.scene.scenes;

import fifty.group.entity.entities.*;
import fifty.group.scene.MouseListener;
import fifty.group.terrain.Terrain;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import java.awt.*;
import java.util.Random;

public class SimulationScene extends Scene {

    private Terrain terrain;
    private Random random;

    public SimulationScene() {
        setID(SceneID.SIMULATION);

        this.addMouseMotionListener(new MouseListener(getEntityHandler()));
        this.random = new Random();

        terrain = new Terrain();
        spawnRandomEntities(random.nextInt(100));


    }

    @Override
    public void update() {
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
