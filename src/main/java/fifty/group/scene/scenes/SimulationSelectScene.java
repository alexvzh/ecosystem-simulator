package fifty.group.scene.scenes;

import fifty.group.data.DataManager;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;
import fifty.group.scene.SceneManager;
import fifty.group.terrain.Terrain;
import fifty.group.time.TimeManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SimulationSelectScene extends Scene {

    private final DataManager dataManager;
    private final TimeManager timeManager;
    private final Terrain terrain;
    private final File[] saves;

    public SimulationSelectScene(Terrain terrain, DataManager dataManager, TimeManager timeManager) {
        setID(SceneID.SIMULATION_SELECT);

        this.dataManager = dataManager;
        this.timeManager = timeManager;
        this.terrain = terrain;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.saves = fetchSaves();

        addButtons();

//        JScrollPane scrollPane = new JScrollPane(this);
//        frame.getContentPane().add(scrollPane);

    }

    private File[] fetchSaves() {
        File folder = new File("output");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

        return files;
    }

    private void addButtons() {

        if (saves == null) {
            this.add(new JLabel("No saved simulations found."));
            return;
        }
        for (File save : saves) {

            JButton button = new JButton(save.getName());
            button.addActionListener(e -> {
                String path = save.getPath();
                this.dataManager.deserialize(path);
                SceneManager.getInstance().setScene(SceneID.SIMULATION);
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
