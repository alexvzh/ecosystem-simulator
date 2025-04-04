package fifty.group.entity.entities;

import fifty.group.entity.EntityHandler;
import fifty.group.entity.EntityHostility;
import fifty.group.entity.EntitySize;
import fifty.group.entity.LivingEntity;

public class Sheep extends LivingEntity
{
    public Sheep(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        retrieveSprites(2, 0, "/Sheep.png");
        setSize(EntitySize.MEDIUM);
        setHostility(EntityHostility.PASSIVE);
    }

    public void init() {
        retrieveSprites(2, 0, "/Sheep.png");
        setSize(EntitySize.MEDIUM);
        setHostility(EntityHostility.PASSIVE);
    }
}
