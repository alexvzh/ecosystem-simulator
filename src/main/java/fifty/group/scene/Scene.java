package fifty.group.scene;

import com.google.gson.annotations.Expose;
import fifty.group.entity.EntityHandler;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {

    @Expose
    public EntityHandler entityHandler; //todo change
    private SceneID sceneID;

    protected Scene() {

        this.setPreferredSize(new Dimension(1400, 800));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(null);

        this.entityHandler = new EntityHandler();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    protected abstract void update();

    protected abstract void draw(Graphics2D g2d);

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    protected SceneID getID() {
        return sceneID;
    }

    protected void setID(SceneID sceneID) {
        this.sceneID = sceneID;
    }
}
