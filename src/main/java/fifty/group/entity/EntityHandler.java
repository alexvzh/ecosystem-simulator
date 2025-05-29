package fifty.group.entity;

import com.google.gson.annotations.Expose;
import fifty.group.entity.behaviour.Hoverable;
import fifty.group.entity.entities.Grass;

import java.awt.*;
import java.util.ArrayList;

public class EntityHandler {

    @Expose
    public ArrayList<Entity> entities; // todo:

    public EntityHandler() {
        entities = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            if (!(entities.get(i) instanceof LivingEntity)) continue;
            LivingEntity entity = (LivingEntity) entities.get(i);
            entity.updateEntity();
        }
    }

    public void draw(Graphics2D g2d) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(g2d);
        }
    }

    public Hoverable getEntity(Point p) {
        for (Entity entity : entities) {
            if (!(entity instanceof Hoverable)) continue;
            if (entity.getBoundingBox().contains(p))
                return (Hoverable) entity;
        }
        return null;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public Entity getVisiblePray(LivingEntity entity) {
        if (entity.target != null && entity.getBoundingBox().intersects(entity.target.getBoundingBox()))
            return entity.target;
        for (Entity target : entities) {
            if (!(target instanceof LivingEntity)) continue;
            LivingEntity targetEntity = (LivingEntity) target;
            if (targetEntity.getHostility().equals(EntityHostility.HOSTILE)) continue;
            if (targetEntity.getSize() > entity.getSize()) continue;
            if (entity.getFOV().intersects(target.getBoundingBox())) return target;
        }
        return null;
    }

    public Entity getVisibleEntity(LivingEntity entity) {
        for (Entity target : entities) {
            if (!(target instanceof Grass)) continue;
            if (entity.getFOV().intersects(target.getBoundingBox())) return target;
        }
        return null;
    }

}
