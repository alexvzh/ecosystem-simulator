package fifty.group.terrain;

import com.google.gson.annotations.Expose;
import fifty.group.data.SavedTile;
import fifty.group.sprite.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Terrain {

    private static final int DEFAULT_WIDTH = 45;
    private static final int DEFAULT_HEIGHT = 26;

    @Expose
    public ArrayList<Tile> tileList; // todo: add final

    public Terrain() {
        this.tileList = new ArrayList<>();
        initTiles(generateRandomMap(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    public Terrain(List<SavedTile> savedTiles) {
        this.tileList = new ArrayList<>();
        int[][] map = new int[DEFAULT_HEIGHT][DEFAULT_WIDTH];

        for (SavedTile savedTile : savedTiles) {
            System.out.println(savedTile);
            map[savedTile.y / 32][savedTile.x / 32] = savedTile.type == TileType.GRASS ? 1 : 0;
        }

        initTiles(map);
    }

    public void drawTileLayer(Graphics2D g2d) {
        for (int i = 0; i < tileList.size(); i++) {
            Tile tile = tileList.get(i);
            tile.draw(g2d);
        }
    }

    private void initTiles(int[][] integerList) {
        SpriteLoader spriteLoader = new SpriteLoader();
        HashMap<List<TileType>, BufferedImage> spriteMap = spriteLoader.getSpriteMap();
        int x = 0;
        int y = 0;

        for (int i = 0; i < integerList.length - 1; i++) {
            for (int j = 0; j < integerList[i].length - 1; j++) {
                TileType type = integerList[i][j] == 1 ? TileType.GRASS : TileType.DIRT;
                tileList.add(new Tile(x, y, type, spriteMap.get(getTileNeighbours(i, j, integerList))));
                x += 32;
            }
            y += 32;
            x = 0;
        }

    }

    public int[][] generateRandomMap(int width, int height) {
        int[][] map = new int[height][width];
        Random random = new Random();

        int probability = 30 + random.nextInt(41);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = (random.nextInt(100) < probability) ? 1 : 0;
            }
        }

        return map;
    }

    private java.util.List<TileType> getTileNeighbours(int y, int x, int[][] integerMap) {
        java.util.List<TileType> neighbours = new ArrayList<>();
        neighbours.add(integerMap[y][x] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(integerMap[y][x + 1] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(integerMap[y + 1][x] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(integerMap[y + 1][x + 1] == 1 ? TileType.GRASS : TileType.DIRT);

        return neighbours;
    }

    public Tile getTile(int x, int y) {
        for (Tile tile : tileList) {
            if (tile.getBounds().contains(x, y)) return tile;
        }
        return null;
    }
}
