package fifty.group.entity.entities;

import fifty.group.entity.EntityHandler;
import fifty.group.entity.EntityHostility;
import fifty.group.entity.EntitySize;
import fifty.group.entity.LivingEntity;

public class Fox extends LivingEntity {

    public Fox(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        retrieveSprites(0, 0, "/Fox.png");
        setSize(EntitySize.SMALL);
        setHostility(EntityHostility.HOSTILE);
    }

    @Override
    public void reproduce() {
        new Fox((int) x, (int) y, entityHandler);
        this.getStats().applyReproductionTax();
    }

}
