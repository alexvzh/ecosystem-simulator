package fifty.group.entity.entities;

import fifty.group.entity.*;
import fifty.group.scene.SceneManager;
import fifty.group.terrain.*;

public class Raccoon extends LivingEntity {

    public Raccoon(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        retrieveSprites(1, 0, "/Raccoon.png");
        setHostility(EntityHostility.NEUTRAL);
        setSize(EntitySize.SMALL);
        setStats(new EntityStats(this, 1000, 1000, 10, 20));
    }

    @Override
    public void reproduce() {}

    @Override
    public void updateBehaviour() {
        Terrain terrain = SceneManager.getInstance().getTerrain();
        Tile tile = terrain.getTile((int) x + 48, (int) y + 48);
        if (tile != null) {
            tile.growGrass();
        }
    }
}
