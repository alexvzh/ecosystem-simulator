package fifty.group.scene.scenes;

import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;
import fifty.group.scene.SceneManager;

import java.awt.*;

public class MenuScene extends Scene {

    public MenuScene() {
        setID(SceneID.MENU);
        addButtons();
    }

    @Override
    public void update() {
        getEntityHandler().update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        getEntityHandler().draw(g2d);
    }

    private void addButtons() {

        int windowWidth = 1400;
        int windowHeight = 800;

        int buttonWidth = 250;
        int buttonHeight = 100;

        int x = windowWidth/2 - buttonWidth/2;
        int y = windowHeight/2 - buttonHeight/2;

        addButton(x, (int) (y - buttonHeight * 1.25), buttonWidth, buttonHeight, "New Random Simulation", e -> SceneManager.getInstance().setScene(SceneID.SIMULATION, true));
        addButton(x, y, buttonWidth, buttonHeight, "Load Simulation", e -> SceneManager.getInstance().setScene(SceneID.SIMULATION_SELECT, true));
        addButton(x, (int) (y + buttonHeight * 1.25), buttonWidth, buttonHeight, "Exit", e -> System.exit(0));

    }

}
