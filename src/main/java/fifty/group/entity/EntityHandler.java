package fifty.group.entity;

import fifty.group.entity.behaviour.Hoverable;
import fifty.group.entity.entities.Grass;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityHandler {

    private final List<Entity> entities;

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

    public void addEntity(Entity entity) {
        if (entity instanceof Grass) {
            entities.add(0, entity);
            return;
        }
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public Hoverable getEntity(Point p) {
        for (Entity entity : entities) {
            if (!(entity instanceof Hoverable)) continue;
            if (entity.getBoundingBox().contains(p))
                return (Hoverable)entity;
        }
        return null;
    }

    public Entity getVisiblePray(LivingEntity entity) {
        if (entity.target != null && entity.getBoundingBox().intersects(entity.target.getBoundingBox())) return entity.target;
        for (Entity target : entities) {
            if (!(target instanceof LivingEntity)) continue;
            LivingEntity targetEntity = (LivingEntity) target;
            if (targetEntity.getHostility().equals(EntityHostility.HOSTILE)) continue;
            if (targetEntity.getSize() > entity.getSize()) continue;
            if (entity.getFOV().intersects(target.getBoundingBox())) return target;
        }
        return null;
    }

    public Entity getVisibleGrass(LivingEntity entity) {
        if (entity.target != null && entity.getBoundingBox().intersects(entity.target.getBoundingBox())) return entity.target;
        for (Entity target : entities) {
            if (!(target instanceof Grass)) continue;
            if (entity.getFOV().intersects(target.getBoundingBox())) return target;
        }
        return null;
    }

    public void reproduceEligibleEntities() {
        List<Entity> currentEntities = new ArrayList<>(entities);

        for (int i = 0; i < currentEntities.size(); i++) {
            Entity entity = currentEntities.get(i);
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity livingEntity = (LivingEntity) entity;
            if (livingEntity.getStats().getHunger() < livingEntity.getStats().getMaxHunger() * 0.75) continue;
            livingEntity.reproduce();
        }
    }

    public List<Entity> getEntityList() {
        return entities;
    }
}
