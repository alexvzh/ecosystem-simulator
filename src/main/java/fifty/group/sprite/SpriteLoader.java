package fifty.group.sprite;

import fifty.group.terrain.TileType;
import fifty.group.scene.scenes.SimulationScene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SpriteLoader {

    private final BufferedImage TILE_MAP;
    private HashMap<List<TileType>, BufferedImage> spriteMap;
    private static SpriteLoader instance;

    public SpriteLoader() {
        try {
            TILE_MAP = ImageIO.read(Objects.requireNonNull(SimulationScene.class.getResourceAsStream("/TilemapDemo.png")));
        } catch (IOException e) {throw new RuntimeException(e);}

        initSpriteMap();
    }

    private BufferedImage getResizedSprite(int x, int y, int newSize) {
        BufferedImage original = TILE_MAP.getSubimage(x*16, y*16, 16, 16);

        BufferedImage image = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(original.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();

        return image;
    }

    private void initSpriteMap() {

        spriteMap = new HashMap<>();
        int size = 32;

        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT), getResizedSprite(0,3, size));
        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.GRASS), getResizedSprite(1,3, size));
        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.GRASS,TileType.DIRT), getResizedSprite(0,0, size));
        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.DIRT,TileType.DIRT), getResizedSprite(0,2, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.DIRT,TileType.DIRT), getResizedSprite(3,3, size));
        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.DIRT,TileType.GRASS), getResizedSprite(1,0, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.GRASS,TileType.DIRT), getResizedSprite(3,2, size));
        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.GRASS,TileType.GRASS), getResizedSprite(3,0, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.DIRT,TileType.DIRT), getResizedSprite(1,2, size));
        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.GRASS,TileType.DIRT), getResizedSprite(2,3, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.DIRT,TileType.GRASS), getResizedSprite(0,1, size));
        spriteMap.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.GRASS,TileType.GRASS), getResizedSprite(1,1, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.GRASS,TileType.GRASS), getResizedSprite(2,0, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.DIRT,TileType.GRASS), getResizedSprite(2,2, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.GRASS,TileType.DIRT), getResizedSprite(3,1, size));
        spriteMap.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.GRASS,TileType.GRASS), getResizedSprite(2,1, size));

    }

    public HashMap<List<TileType>, BufferedImage> getSpriteMap() {
        return spriteMap;
    }

    public static SpriteLoader getInstance() {
        if (instance == null) {
            instance = new SpriteLoader();
        }
        return instance;
    }
}
