package fifty.group.scene.scenes;

import fifty.group.entity.entities.SheepEntity;
import fifty.group.map.TileType;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SimulationScene extends Scene {

    private final BufferedImage TILE_MAP;
    private HashMap<java.util.List<TileType>, BufferedImage> sprites;
    private int[][] map;

    public SimulationScene() {
        setID(SceneID.SIMULATION);

        try {
            TILE_MAP = ImageIO.read(Objects.requireNonNull(SimulationScene.class.getResourceAsStream("/TilemapDemo.png")));
        } catch (IOException e) {throw new RuntimeException(e);}

        new SheepEntity(20, 250, getEntityHandler(), SheepEntity.Type.GRAY_BLACK);

    }

    @Override
    public void update() {
        getEntityHandler().update();

    }

    @Override
    public void draw(Graphics2D g2d) {

        getEntityHandler().draw(g2d);

    }


    private BufferedImage getResizedSprite(int x, int y) {
        BufferedImage original = TILE_MAP.getSubimage(x*16, y*16, 16, 16);
        int size = 32;

        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(original.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();

        return image;
    }

}
