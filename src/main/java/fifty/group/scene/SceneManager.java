package fifty.group.scene;

import fifty.group.data.DataManager;
import fifty.group.scene.scenes.MenuScene;
import fifty.group.scene.scenes.SimulationScene;
import fifty.group.scene.scenes.SimulationSelectScene;
import fifty.group.terrain.Terrain;
import fifty.group.time.TimeManager;

import javax.swing.*;
import java.util.ArrayList;

public class SceneManager {

    private static SceneManager instance;

    private JFrame window;
    private boolean running;
    private Scene currentScene;
    private final Terrain terrain;
    private final DataManager dataManager;
    private final TimeManager timeManager;
    private final ArrayList<Scene> scenes;

    private SceneManager() {
        this.scenes = new ArrayList<>();
        this.running = true;


        this.terrain = new Terrain();
        this.timeManager = new TimeManager();
        this.dataManager = new DataManager(terrain, timeManager);

        initScenes();

        currentScene = getSceneByID(SceneID.MENU);
    }

    public void start() {
        final int TARGET_FPS = 120; // Frames per second
        final int TARGET_UPS = 120; // Updates per second
        final double TIME_PER_UPDATE = 1_000_000_000.0 / TARGET_UPS; // Nanoseconds per update
        final double TIME_PER_FRAME = 1_000_000_000.0 / TARGET_FPS; // Nanoseconds per frame

        long lastUpdateTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();

        double delta = 0;
        int frames = 0;
        int updates = 0;

        long fpsTimer = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastUpdateTime) / TIME_PER_UPDATE;
            lastUpdateTime = currentTime;

            if (running) {
                if (delta >= 1) {
                    currentScene.update();
                    updates++;
                    delta = 0;
                }

                if (currentTime - lastRenderTime >= TIME_PER_FRAME) {
                    currentScene.repaint();
                    frames++;
                    lastRenderTime = currentTime;
                }
            }

            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                fpsTimer += 1000;
                frames = 0;
                updates = 0;
            }

            // Save CPU...
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setScene(SceneID sceneID, boolean init) {
        Scene scene = this.getSceneByID(sceneID);
        if (sceneID.equals(SceneID.SIMULATION) && init) ((SimulationScene) scene).initRandomSimulation();
        if (sceneID.equals(SceneID.SIMULATION_SELECT) && init) ((SimulationSelectScene) scene).addButtons();
        window.remove(currentScene);
        window.add(scene);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        currentScene = scene;
    }

    private Scene getSceneByID(SceneID sceneID) {
        for (Scene scene : scenes) {
            if (scene.getID().equals(sceneID)) {
                return scene;
            }
        }
        throw new RuntimeException("No scene with ID " + sceneID + " found");
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    private void initScenes() {
        scenes.add(new MenuScene());
        scenes.add(new SimulationScene(this.terrain, this.dataManager, this.timeManager));
        scenes.add(new SimulationSelectScene(this.dataManager));
    }

    public void initialize(JFrame window) {
        this.window = window;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
}
