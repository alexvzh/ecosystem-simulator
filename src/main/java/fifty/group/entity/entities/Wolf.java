package fifty.group.entity.entities;

import fifty.group.entity.EntityHandler;
import fifty.group.entity.LiveEntity;

import java.awt.*;

public class Wolf extends LiveEntity {

    public Wolf(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        setSpriteSheet("/Wolf.png");
    }

    @Override
    public void update() {
        x += 1;
    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
