package fifty.group.entity.entities;

import fifty.group.entity.Entity;
import fifty.group.entity.EntityHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ExampleEntity extends Entity {

    int health;
    int index = 0;
    ArrayList<BufferedImage> images = new ArrayList<>();
    private final static BufferedImage img;

    static {
        try {
            img = ImageIO.read(Objects.requireNonNull(ExampleEntity.class.getResourceAsStream("/Sheep.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ExampleEntity(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        this.health = health;
        getImages(img);
    }

    public void getImages(BufferedImage img) {
        for (int i = 0; i < 3 * 48; i += 48) {
            images.add(img.getSubimage(i, 2 * 48, 48, 48));
        }
    }

    @Override
    public void update() {
        setX(getX() + 2);
        index = (index + 1) % 3;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawImage(images.get(index), getX(), getY(), null);
    }

}
