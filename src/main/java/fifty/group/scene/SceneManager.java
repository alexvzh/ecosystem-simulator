package fifty.group.scene;

import fifty.group.scene.scenes.MenuScene;
import fifty.group.scene.scenes.SimulationScene;

import javax.swing.*;
import java.util.ArrayList;

public class SceneManager {

    private static SceneManager instance;

    private final ArrayList<Scene> scenes;
    private JFrame window;
    private Scene currentScene;
    private boolean running = true;

    private SceneManager() {
        this.scenes = new ArrayList<>();

        initScenes();

        currentScene = getSceneByID(SceneID.MENU);
    }

    public void start() {
        while (running) {
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

            while (running) {
                long currentTime = System.nanoTime();
                delta += (currentTime - lastUpdateTime) / TIME_PER_UPDATE;
                lastUpdateTime = currentTime;

                while (delta >= 1) {
                    currentScene.update();
                    updates++;
                    delta--;
                }

                if (currentTime - lastRenderTime >= TIME_PER_FRAME) {
                    currentScene.repaint();
                    frames++;
                    lastRenderTime = currentTime;
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
    }

    public void setScene(SceneID sceneID) {
        Scene scene = this.getSceneByID(sceneID);
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
        scenes.add(new SimulationScene());
    }

    public void initialize(JFrame window) {
        this.window = window;
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
}
