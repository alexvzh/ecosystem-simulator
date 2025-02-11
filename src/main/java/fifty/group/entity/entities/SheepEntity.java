package fifty.group.entity.entities;

import fifty.group.entity.Entity;
import fifty.group.entity.EntityHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class SheepEntity extends Entity {

    public enum Type {
        WHITE_BLACK(0, 0),
        WHITE_GRAY(3, 0),
        BLACK_GRAY(6, 0),
        DARK_BROWN_GRAY(9, 0),
        BLACK_BLACK(0, 4),
        WHITE_WHITE(3, 4),
        GRAY_BLACK(6, 4),
        LIGHT_BROWN_BLACK(9, 4);

        private final int x;
        private final int y;

        Type(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public enum Direction {
        DOWN,
        LEFT,
        RIGHT,
        UP,
    }

    private final static int TILE_SIZE = 48;
    private final static BufferedImage IMAGE;
    private int spriteSheetTileX = 0;
    private int spriteSheetTileY = 0;
    private Direction direction;
    private int frame = 0;
    private boolean isIdle = false;
    private Direction directionArr[] = {Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
    private int randInt = new Random().nextInt(4);

    static {
        try {
            IMAGE = ImageIO.read(Objects.requireNonNull(SheepEntity.class.getResourceAsStream("/Sheep.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public SheepEntity(int x, int y, EntityHandler entityHandler, Type type) {
        super(x, y, entityHandler);

        this.direction = directionArr[randInt];
        this.spriteSheetTileX = type.getX();
        this.spriteSheetTileY = type.getY();

    }

    public BufferedImage getFrameImage(int walkingFrameIndex) {
        int localYOffset = 0;

        switch (direction) {
            case LEFT:
                localYOffset = 1;
                break;
            case RIGHT:
                localYOffset = 2;
                break;
            case UP:
                localYOffset = 3;
                break;
        }

        return IMAGE.getSubimage(
                (spriteSheetTileX + walkingFrameIndex) * TILE_SIZE,
                (spriteSheetTileY + localYOffset) * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE
        );
    }

    @Override
    public void update() {
        if (!isIdle) {
            switch (direction) {
                case DOWN:
                    setY(getY() + 5);
                    break;
                case LEFT:
                    setX(getX() - 5);
                    break;
                case RIGHT:
                    setX(getX() + 5);
                    break;
                case UP:
                    setY(getY() - 5);
                    break;
            }
        }

        frame++;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int frameIndex = isIdle ? 1 : frame % 3;
        g2d.drawImage(getFrameImage(frameIndex), getX(), getY(), null);
    }

}
