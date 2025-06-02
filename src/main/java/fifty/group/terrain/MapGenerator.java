package fifty.group.terrain;

public class MapGenerator {

    public int[][] generateMap(int width, int height, double scale, double threshold, long seed) {
        OpenSimplexNoise noise = new OpenSimplexNoise(seed);
        int[][] map = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double noiseValue = noise.eval(x * scale, y * scale, 2);
                map[y][x] = (noiseValue > threshold) ? 1 : 0;
            }
        }

        return map;
    }
}
