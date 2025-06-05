package fifty.group.scene.scenes;

import fifty.group.data.DataManager;
import fifty.group.data.SimulationState;
import fifty.group.entity.entities.*;
import fifty.group.scene.MouseListener;
import fifty.group.scene.SceneManager;
import fifty.group.terrain.Terrain;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;
import fifty.group.time.TimeManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimulationScene extends Scene {

    private final Random random;
    private final Terrain terrain;
    private final TimeManager timeManager;

    public SimulationScene(Terrain terrain, TimeManager timeManager) {
        setID(SceneID.SIMULATION);
        this.random = new Random();
        this.terrain = terrain;
        this.timeManager = timeManager;
        MouseListener mouseListener = new MouseListener(entityHandler);
        this.addMouseMotionListener(mouseListener);
        addButtons();

        this.timeManager.onDayStart(() -> {
            this.entityHandler.reproduceEligibleEntities();
        });
    }

    @Override
    public void update() {
        entityHandler.update();
        timeManager.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        terrain.drawTileLayer(g2d);
        entityHandler.draw(g2d);
        timeManager.draw(g2d);
    }

    public void initRandomSimulation() {
        entityHandler.getEntityList().clear();
        timeManager.init();
        terrain.init();
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


    private void addButtons() {

        int windowHeight = 800;

        int buttonWidth = 100;
        int buttonHeight = 50;

        addButton(15, (int) (windowHeight - buttonHeight * 1.5), buttonWidth, buttonHeight, "Pause", e-> {
            if (SceneManager.getInstance().isRunning()) {
                ((JButton)e.getSource()).setText("Resume");
                SceneManager.getInstance().setRunning(false);
            } else {
                ((JButton)e.getSource()).setText("Pause");
                SceneManager.getInstance().setRunning(true);
            }
        });


        addButton(15 + buttonWidth + 15, (int) (windowHeight - buttonHeight * 1.5), buttonWidth, buttonHeight, "Save", e-> {
            DataManager.getInstance().serialize(new SimulationState(terrain.getTileList(), entityHandler.getEntityList(), timeManager.getTime(), timeManager.getDay()));
        });

        addButton(15 + (buttonWidth + 15) * 2, (int) (windowHeight - buttonHeight * 1.5), buttonWidth, buttonHeight, "Exit", e-> {
            SceneManager.getInstance().setScene(SceneID.MENU, false);
        });

    }

}
