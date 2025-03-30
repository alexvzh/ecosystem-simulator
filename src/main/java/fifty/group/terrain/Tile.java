package fifty.group.terrain;

import fifty.group.entity.entities.Grass;
import fifty.group.scene.SceneManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tile {

    private final int x;
    private final int y;
    private final BufferedImage image;
    private final Random random;
    private Grass currentGrass;
    private TileType type;

    public Tile(int x, int y, TileType type, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.image = image;
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
