package fifty.group.entity;

import java.awt.*;
import java.util.ArrayList;

public class EntityHandler {

    private final ArrayList<Entity> entities;

    public EntityHandler() {
        entities = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            if (!(entities.get(i) instanceof LiveEntity)) continue;
            LiveEntity entity = (LiveEntity) entities.get(i);
            entity.updateEntity();

        }
    }

    public void draw(Graphics2D g2d) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(g2d);
        }
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

}
