package fifty.group.scene;

import fifty.group.entity.EntityHandler;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {

    EntityHandler entityHandler;
    SceneID sceneID;

    public Scene() {

        this.setPreferredSize(new Dimension(640, 640));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(null);

        this.entityHandler = new EntityHandler();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2d);

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    public SceneID getID() {
        return sceneID;
    }

    public void setID(SceneID sceneID) {
        this.sceneID = sceneID;
    }
}
