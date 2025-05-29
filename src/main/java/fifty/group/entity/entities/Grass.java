package fifty.group.entity.entities;

import com.google.gson.annotations.Expose;
import fifty.group.entity.Entity;
import fifty.group.entity.EntityHandler;
import fifty.group.terrain.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Grass extends Entity {

//    private static final BufferedImage SPRITE;
//
//    static {
//        try {
//            SPRITE = ImageIO.read(Objects.requireNonNull(Grass.class.getResourceAsStream("Grass.png")));
//        } catch (IOException e) {throw new RuntimeException(e);}
//    }

    @Expose
    private Tile tile;

    public Grass(Tile tile, EntityHandler entityHandler) {
        super(tile.getBounds().getX() - 16, tile.getBounds().getY() - 16, entityHandler);
        this.tile = tile;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawOval((int) x, (int) y, 32, 32);
    }
}
