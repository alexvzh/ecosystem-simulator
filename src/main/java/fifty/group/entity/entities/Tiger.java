package fifty.group.entity.entities;

import fifty.group.entity.EntityHandler;
import fifty.group.entity.EntityHostility;
import fifty.group.entity.EntitySize;
import fifty.group.entity.LivingEntity;

public class Tiger extends LivingEntity {

    public Tiger(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        retrieveSprites(2, 0, "/Tiger.png");
        setHostility(EntityHostility.HOSTILE);
        setSize(EntitySize.LARGE);
    }


}
