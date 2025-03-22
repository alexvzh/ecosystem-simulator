package fifty.group.entity.entities;

import fifty.group.entity.EntityHandler;
import fifty.group.entity.LiveEntity;

import java.awt.*;

public class TempSheep extends LiveEntity {

    public TempSheep(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        setSpriteSheet("/Sheep.png");
    }

//    @Override
//    public void update() {
//
//
//    }
//
//    @Override
//    public void draw(Graphics2D g2d) {
//
//    }
}
