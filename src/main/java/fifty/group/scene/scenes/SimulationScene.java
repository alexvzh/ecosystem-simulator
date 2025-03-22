package fifty.group.scene.scenes;

import fifty.group.entity.entities.Sheep;
import fifty.group.entity.entities.TempSheep;
import fifty.group.terrain.Terrain;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import java.awt.*;

public class SimulationScene extends Scene {

    Terrain terrain;

    public SimulationScene() {
        setID(SceneID.SIMULATION);

        terrain = new Terrain();
//        new Sheep(50, 50, getEntityHandler(), Sheep.Type.GRAY_BLACK);
        new TempSheep(320, 240, getEntityHandler());

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

}
