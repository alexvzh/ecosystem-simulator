package fifty.group.entity;

import fifty.group.entity.entities.Sheep;
import fifty.group.terrain.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

public abstract class LiveEntity extends Entity {
    private int health;
    private int maxHealth;
    private int hunger;
    private int maxHunger;
    private int speed;
    private int maxSpeed;
    private int damage;
    private double velX;
    private double velY;
    private int size;
    private int currentFrame;
    private Tile targetTile;
    private int frame;
    private static BufferedImage spriteSheet;
    private EntityState state;
    private Random random = new Random();
    private int tickCounter;
    private int nextChangeTime;
    private static final int TILE_SIZE = 48;
    private Direction direction = Direction.DOWN;

    public LiveEntity(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        this.state = EntityState.SEARCHING;
        this.tickCounter = 0;
    }

    public void update() {
    }

    @Override
    public void draw(Graphics2D g2d) {
        int frameIndex = state.equals(EntityState.IDLE) ? 1 : frame % 3;
        g2d.drawImage(getFrameImage(frameIndex), (int) getX(), (int) getY(), null);
    }

    public BufferedImage getFrameImage(int walkingFrameIndex) {

        return spriteSheet.getSubimage(
                (walkingFrameIndex) * TILE_SIZE,
                (direction.getLocalYOffset()) * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE
        );
    }


    public void updateEntity() {
        update();
        updateHunger();

        if (state.equals(EntityState.SEARCHING)) {
            updateDirection();
        }

        x += velX;
        y += velY;

        tickCounter++;
    }

    private void updateHunger() {

        if (tickCounter % 100 == 0) {
            int hungerDecrease = maxHunger / 100;
            hunger -= hungerDecrease;
        }

    }

    private void updateDirection() {
        if (random.nextInt(100) > 10) {
            nextChangeTime--;
        }

        System.out.println(nextChangeTime);
        if (nextChangeTime <= 0) {
            nextChangeTime = random.nextInt(6) + 5;

            changeDirection();
        }
    }

    private void changeDirection() {
        do {
            velX = random.nextInt(3) - 1;
            velY = random.nextInt(3) - 1;
        } while (velX == 0 && velY == 0);

        double magnitude = Math.sqrt(velX * velX + velY * velY);
        velX /= magnitude;
        velY /= magnitude;

        System.out.print(velX);
        System.out.println(" " + velY);

    }


    protected void setSpriteSheet(String path) {
        try {
            spriteSheet = ImageIO.read(Objects.requireNonNull(Sheep.class.getResourceAsStream(path)));
        } catch (Exception ignored) {
        }
    }


}
