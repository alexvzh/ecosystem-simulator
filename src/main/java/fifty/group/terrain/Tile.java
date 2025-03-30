package fifty.group.terrain;

import com.google.gson.annotations.*;
import fifty.group.entity.entities.Grass;
import fifty.group.scene.SceneManager;
import fifty.group.sprite.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Tile {

    @Expose
    private final int x;

    @Expose
    private final int y;

    @Expose
    private TileType type;

    @Expose
    private List<TileType> neighbors;

    private Grass currentGrass;
    private final BufferedImage image;
    private final Random random;

    public Tile(int x, int y, TileType type, List<TileType> neighbors) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.neighbors = neighbors;

        this.image = SpriteLoader.getInstance().getSpriteMap().get(this.neighbors);
        this.random = new Random();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void growGrass() {
        if (!type.equals(TileType.GRASS)) return;
        if (random.nextInt(3000) < 10) {
            currentGrass = new Grass(this, SceneManager.getInstance().getCurrentScene().getEntityHandler());
        }
    }

    public void removeGrass() {
        SceneManager.getInstance().getCurrentScene().getEntityHandler().removeEntity(currentGrass);
    }
}
