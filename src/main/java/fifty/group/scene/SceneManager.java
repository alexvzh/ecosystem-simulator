package fifty.group.scene;

import fifty.group.scene.scenes.MenuScene;

import javax.swing.*;
import java.util.ArrayList;

public class SceneManager {

    private static SceneManager instance;

    private final ArrayList<Scene> scenes;
    private final JFrame window;
    private Scene currentScene;

    public SceneManager(JFrame window) {
        this.window = window;
        this.scenes = new ArrayList<>();

        initScenes();

        currentScene = getSceneByID(SceneID.MENU);

        instance = this;
    }

    public void setScene(SceneID sceneID) {
        Scene scene = this.getSceneByID(sceneID);
        window.remove(currentScene);
        window.add(scene);
        window.pack();
        currentScene = scene;
    }

    public Scene getSceneByID(SceneID sceneID) {
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
    }

    public static SceneManager getInstance() {
        return instance;
    }


}
