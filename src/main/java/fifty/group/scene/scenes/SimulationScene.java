package fifty.group.scene.scenes;

import fifty.group.entity.entities.SheepEntity;
import fifty.group.scene.Scene;
import fifty.group.scene.SceneID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class SimulationScene extends Scene {

    private final BufferedImage TILE_MAP;
    private HashMap<java.util.List<TileType>, BufferedImage> sprites;
    private int[][] map;

    public enum TileType {
        GRASS,
        DIRT
    }

    public SimulationScene() {
        setID(SceneID.SIMULATION);

        try {
            TILE_MAP = ImageIO.read(Objects.requireNonNull(SimulationScene.class.getResourceAsStream("/TilemapDemo.png")));
        } catch (IOException e) {throw new RuntimeException(e);}

        map = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}
        };


        initSpriteMap();

        new SheepEntity(20, 250, getEntityHandler(), SheepEntity.Type.GRAY_BLACK);

    }

    @Override
    public void update() {
        getEntityHandler().update();

    }

    @Override
    public void draw(Graphics2D g2d) {

        int x = 0;
        int y = 0;

        for (int i = 0; i < map.length-1; i++) {
            for (int j = 0; j < map[i].length-1; j++) {
                g2d.drawImage(sprites.get(getTileNeighbours(i, j)), x, y, null);
                x += 32;
            }
            y += 32;
            x = 0;
        }

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

    private void initSpriteMap() {

        sprites = new HashMap<>();

        sprites.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.GRASS,TileType.GRASS), getResizedSprite(2,1));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.GRASS), getResizedSprite(1,3));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.GRASS,TileType.DIRT), getResizedSprite(0,0));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.DIRT,TileType.DIRT), getResizedSprite(0,2));
        sprites.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.DIRT,TileType.DIRT), getResizedSprite(3,3));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.DIRT,TileType.GRASS), getResizedSprite(1,0));
        sprites.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.GRASS,TileType.DIRT), getResizedSprite(3,2));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.GRASS,TileType.GRASS), getResizedSprite(3,0));
        sprites.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.DIRT,TileType.DIRT), getResizedSprite(1,2));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.GRASS,TileType.GRASS), getResizedSprite(1,1));
        sprites.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.GRASS,TileType.GRASS), getResizedSprite(2,0));
        sprites.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.DIRT,TileType.GRASS), getResizedSprite(2,2));
        sprites.put(Arrays.asList(TileType.GRASS,TileType.GRASS,TileType.GRASS,TileType.DIRT), getResizedSprite(3,1));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.GRASS,TileType.GRASS,TileType.DIRT), getResizedSprite(2,3));
        sprites.put(Arrays.asList(TileType.GRASS,TileType.DIRT,TileType.DIRT,TileType.GRASS), getResizedSprite(0,1));
        sprites.put(Arrays.asList(TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT), getResizedSprite(0,3));

    }

    private java.util.List<TileType> getTileNeighbours(int y, int x) {
        java.util.List<TileType> neighbours = new ArrayList<>();
        neighbours.add(map[y][x] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(map[y][x+1] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(map[y+1][x] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(map[y+1][x+1] == 1 ? TileType.GRASS : TileType.DIRT);

        return neighbours;
    }
}
