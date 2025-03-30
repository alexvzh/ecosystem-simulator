package fifty.group.entity.entities;

import fifty.group.entity.Entity;
import fifty.group.entity.EntityHandler;
import fifty.group.terrain.Tile;

import java.awt.*;

public class Grass extends Entity {
    public Grass(Tile tile, EntityHandler entityHandler) {
        super(tile.getBounds().getX() - 16, tile.getBounds().getY() - 16, entityHandler);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawOval((int) x, (int) y, 32, 32);
    }
}
