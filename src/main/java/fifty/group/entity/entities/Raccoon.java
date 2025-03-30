package fifty.group.entity.entities;

import fifty.group.entity.*;
import fifty.group.scene.SceneManager;
import fifty.group.scene.scenes.SimulationScene;
import fifty.group.terrain.Tile;

public class Raccoon extends LivingEntity {

    public Raccoon(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        retrieveSprites(1, 0, "/Raccoon.png");
        setHostility(EntityHostility.NEUTRAL);
        setSize(EntitySize.SMALL);
        setStats(new EntityStats(this, 1000, 1000, 20, 20));
    }


    @Override
    public void updateBehaviour() {
        Tile tile = ((SimulationScene)SceneManager.getInstance().getCurrentScene()).getTerrain().getTile((int) x + 48, (int) y + 48);
        if (tile != null) {
            tile.growGrass();
        }
    }
}
