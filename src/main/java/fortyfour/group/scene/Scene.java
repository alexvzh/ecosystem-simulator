package fortyfour.group.scene;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {

    public Scene() {

        this.setPreferredSize(new Dimension(640, 640));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(null);

    }

    public abstract void update();
    public abstract void draw(Graphics2D g2d);

}
