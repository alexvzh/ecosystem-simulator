package fifty.group.entity;

public enum Direction {
    DOWN(0),
    LEFT(1),
    RIGHT(2),
    UP(3);

    private final int localYOffset;

    Direction(int localYOffset) {
        this.localYOffset = localYOffset;
    }

    public int getLocalYOffset() {
        return localYOffset;
    }
}
