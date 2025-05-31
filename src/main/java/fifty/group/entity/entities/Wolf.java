package fifty.group.entity.entities;

import fifty.group.entity.*;

public class Wolf extends LivingEntity {

    public Wolf(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        retrieveSprites(2, 0, "/Wolf.png");
        setSize(EntitySize.LARGE);
        setHostility(EntityHostility.HOSTILE);
    }

}
