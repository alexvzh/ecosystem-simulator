package fifty.group.map;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

public class Tile {

    private int x;
    private int y;
    private BufferedImage image;

    public Tile(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }
}
