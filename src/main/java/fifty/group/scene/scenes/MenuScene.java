package fifty.group.scene.scenes;

import fifty.group.entity.entities.ExampleEntity;
import fifty.group.scene.Scene;

import java.awt.*;

public class MenuScene extends Scene {
    ExampleEntity myEntity;

    public MenuScene() {
        myEntity = new ExampleEntity(0, 100, getEntityHandler());
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
