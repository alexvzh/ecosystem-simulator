package fifty.group.terrain;

import com.google.gson.annotations.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Terrain {

    @Expose
    private final ArrayList<Tile> tileList;

    public Terrain() {
        this.tileList = new ArrayList<>();
        initTiles();
    }

    public void drawTileLayer(Graphics2D g2d) {
        for (int i = 0; i < tileList.size(); i++) {
            Tile tile = tileList.get(i);
            tile.draw(g2d);
        }
    }

    private void initTiles() {
        int[][] integerList = generateRandomMap(45, 26);

        int x = 0;
        int y = 0;

        for (int i = 0; i < integerList.length-1; i++) {
            for (int j = 0; j < integerList[i].length-1; j++) {
                TileType type = integerList[i][j] == 1 ? TileType.GRASS : TileType.DIRT;
                tileList.add(new Tile(x, y, type, getTileNeighbours(i, j, integerList)));
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
        neighbours.add(integerMap[y][x+1] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(integerMap[y+1][x] == 1 ? TileType.GRASS : TileType.DIRT);
        neighbours.add(integerMap[y+1][x+1] == 1 ? TileType.GRASS : TileType.DIRT);

        return neighbours;
    }

    public Tile getTile(int x, int y) {
        for (Tile tile : tileList) {
            if (tile.getBounds().contains(x, y)) return tile;
        }
        return null;
    }
}
