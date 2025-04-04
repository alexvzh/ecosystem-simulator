package fifty.group.scene;

import fifty.group.data.*;
import fifty.group.entity.EntityHandler;
import fifty.group.terrain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Scene extends JPanel {
    protected final DataManager dataManager;
    private SceneID sceneID;

    protected Scene() {

        this.setPreferredSize(new Dimension(1400, 800));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(null);

        this.dataManager = new DataManager(new Terrain(), new EntityHandler());
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
        return dataManager.getEntityHandler();
    }

    protected SceneID getID() {
        return sceneID;
    }

    protected void setID(SceneID sceneID) {
        this.sceneID = sceneID;
    }

    public void addButton(int x, int y, int width, int height, String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
        this.add(button);
    }
}
