package fifty.group.scene.scenes;

import fifty.group.entity.entities.SheepEntity;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import java.awt.*;

import static fifty.group.entity.entities.SheepEntity.*;

public class MenuScene extends Scene {
    SheepEntity myEntity;

    public MenuScene() {
        setID(SceneID.MENU);
        myEntity = new SheepEntity(0, 100, getEntityHandler(), Type.LIGHT_BROWN_BLACK);
    }

    @Override
    public void update() {
        getEntityHandler().update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        this.myEntity.draw(g2d);
    }

}
