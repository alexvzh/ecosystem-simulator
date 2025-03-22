package fifty.group.entity;

import java.awt.*;

public abstract class Entity {

    protected double x, y;

    public Entity(double x, double y, EntityHandler entityHandler) {
        this.x = x;
        this.y = y;

        entityHandler.addEntity(this);
    }

    public abstract void draw(Graphics2D g2d);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
