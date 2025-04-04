package fifty.group.scene;

import fifty.group.entity.EntityHandler;
import fifty.group.entity.behaviour.Hoverable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements MouseMotionListener {

    EntityHandler entityHandler;
    Hoverable currentEntity;

    public MouseListener(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //Not needed
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Hoverable entity = entityHandler.getEntity(e.getPoint());
        if (currentEntity == entity) return;

        if (entity == null) {
            currentEntity.onUnhover();
        } else {
            entity.onHover();
        }

        currentEntity = entity;
    }

    public void setEntityHandler(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;
    }
}
