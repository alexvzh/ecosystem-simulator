package fifty.group.scene.scenes;

import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import java.awt.*;

public class MenuScene extends Scene {

    public MenuScene() {
        setID(SceneID.MENU);
    }

    @Override
    public void update() {
        getEntityHandler().update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        getEntityHandler().draw(g2d);
    }

}
