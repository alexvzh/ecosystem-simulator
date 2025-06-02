package fifty.group.entity.entities;

import fifty.group.entity.EntityHandler;
import fifty.group.entity.EntityHostility;
import fifty.group.entity.EntitySize;
import fifty.group.entity.LivingEntity;

public class Rabbit extends LivingEntity {

    public Rabbit(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        retrieveSprites(0, 1, "/Rabbit.png");
        setSize(EntitySize.SMALL);
        setHostility(EntityHostility.PASSIVE);
    }

    @Override
    public void reproduce() {
        new Rabbit((int) x, (int) y, entityHandler);
        this.getStats().applyReproductionTax();
    }

}
