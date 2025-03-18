package fifty.group.scene.scenes;

import fifty.group.entity.entities.SheepEntity;
import fifty.group.terrain.Terrain;
import fifty.group.terrain.TileType;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import java.awt.*;

public class SimulationScene extends Scene {

    Terrain terrain;

    public SimulationScene() {
        setID(SceneID.SIMULATION);

        terrain = new Terrain();
        new SheepEntity(50, 50, getEntityHandler(), SheepEntity.Type.GRAY_BLACK);

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
