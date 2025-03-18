package fifty.group.terrain;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private final int x;
    private final int y;
    private final BufferedImage image;

    public Tile(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }
}
