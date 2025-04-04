package fifty.group.scene.scenes;

import fifty.group.entity.entities.*;
import fifty.group.scene.MouseListener;
import fifty.group.terrain.Terrain;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;
import fifty.group.time.TimeManager;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SimulationScene extends Scene {

    private final Random random;
    private final MouseListener mouseListener;
    private String savedJson;

    private TimeManager timeManager;

    public SimulationScene() {
        setID(SceneID.SIMULATION);
        this.mouseListener = new MouseListener(getEntityHandler());
        this.addMouseMotionListener(mouseListener);
        this.random = new Random();
        this.timeManager = new TimeManager();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    savedJson = dataManager.serialize();
                    System.out.println(savedJson);
                }

                if (e.getKeyCode() == KeyEvent.VK_L && savedJson != null) {
                    dataManager.deserialize(savedJson);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

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
