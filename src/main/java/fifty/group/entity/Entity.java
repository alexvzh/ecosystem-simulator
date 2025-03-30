package fifty.group.entity;

import fifty.group.entity.behaviour.Drawable;

import java.awt.*;

public abstract class Entity implements Drawable {

    protected double x;
    protected double y;
    protected EntityHandler entityHandler;

    protected Entity(double x, double y, EntityHandler entityHandler) {
        this.x = x;
        this.y = y;
        this.entityHandler = entityHandler;
        entityHandler.addEntity(this);
    }

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

    public Rectangle getBoundingBox() {
        return new Rectangle((int) x, (int) y, 48, 48);
    }
}
