package fifty.group.entity.entities;

import fifty.group.entity.Direction;
import fifty.group.entity.Entity;
import fifty.group.entity.EntityHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Sheep extends Entity {

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

//    public enum Direction {
//        DOWN,
//        LEFT,
//        RIGHT,
//        UP,
//    }

    private static final int TILE_SIZE = 48;
    private static final BufferedImage IMAGE;
    private int spriteSheetTileX = 0;
    private int spriteSheetTileY = 0;
    private Direction direction;
    private int frame = 0;
    private Direction directionArr[] = {Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
    private int randInt = new Random().nextInt(4);
    private boolean isIdle = randInt > 2;


    static {
        try {
            IMAGE = ImageIO.read(Objects.requireNonNull(Sheep.class.getResourceAsStream("/Sheep.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Sheep(int x, int y, EntityHandler entityHandler, Type type) {
        super(x, y, entityHandler);

        this.direction = directionArr[randInt];
        this.spriteSheetTileX = type.getX();
        this.spriteSheetTileY = type.getY();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randInt = new Random().nextInt(4);
                isIdle = randInt > 2;
                direction = directionArr[randInt];
            }
        });
        timer.setRepeats(true);
        timer.start();

    }

    public BufferedImage getFrameImage(int walkingFrameIndex) {

        return IMAGE.getSubimage(
                (spriteSheetTileX + walkingFrameIndex) * TILE_SIZE,
                (spriteSheetTileY + direction.getLocalYOffset()) * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE
        );
    }


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
        g2d.drawImage(getFrameImage(frameIndex), (int) Math.round(getX()), (int) Math.round(getY()), null);
    }

}
