package fifty.group.scene.scenes;

import fifty.group.data.DataManager;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;
import fifty.group.scene.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SimulationSelectScene extends Scene {

    private final DataManager dataManager;

    public SimulationSelectScene(DataManager dataManager) {
        setID(SceneID.SIMULATION_SELECT);
        addButtons();

        this.dataManager = dataManager;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    private File[] fetchSaves() {
        File folder = new File("saves");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

        return files;
    }

    public void addButtons() {

        this.removeAll();
        File[] saves = fetchSaves();

        if (saves == null) {
            this.add(new JLabel("No saved simulations found."));
            return;
        }
        for (File save : saves) {

            JButton button = new JButton(save.getName());
            button.addActionListener(e -> {
                String path = save.getPath();
                this.dataManager.deserialize(path);
                SceneManager.getInstance().setScene(SceneID.SIMULATION, false);
            });

            this.add(button);
        }
    }

    @Override
    protected void update() {

    }

    @Override
    protected void draw(Graphics2D g2d) {

    }
}
