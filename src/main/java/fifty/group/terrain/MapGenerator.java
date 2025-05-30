package fifty.group.terrain;

public class MapGenerator {
    private final OpenSimplexNoise noise;

    public MapGenerator(long seed) {
        this.noise = new OpenSimplexNoise(10);
    }

    public int[][] generateMap(int width, int height, double scale, double threshold) {
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
