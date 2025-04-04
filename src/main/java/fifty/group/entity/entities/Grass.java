package fifty.group.entity.entities;

import fifty.group.entity.Entity;
import fifty.group.entity.EntityHandler;
import fifty.group.terrain.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Grass extends Entity {

    private static final BufferedImage sprite;

    static {
        try {
            sprite = ImageIO.read(Objects.requireNonNull(Grass.class.getResourceAsStream("/GrassRecolored.png")));
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public Grass(Tile tile, EntityHandler entityHandler) {
        super(tile.getBounds().getX() - 16, tile.getBounds().getY() - 16, entityHandler);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(sprite, (int)x, (int)y, null);
    }
}
