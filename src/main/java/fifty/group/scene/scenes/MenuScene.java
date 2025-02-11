package fifty.group.scene.scenes;

import fifty.group.entity.entities.SheepEntity;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import java.awt.*;
import java.util.Random;

import static fifty.group.entity.entities.SheepEntity.*;

public class MenuScene extends Scene {
    SheepEntity myEntity;

    public MenuScene() {
        setID(SceneID.MENU);
        int randX = new Random().nextInt(640);
        int randY = new Random().nextInt(640);
        myEntity = new SheepEntity(randX, randY, getEntityHandler(), Type.LIGHT_BROWN_BLACK);
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
