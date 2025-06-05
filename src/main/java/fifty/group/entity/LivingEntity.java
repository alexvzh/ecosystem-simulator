package fifty.group.entity;

import com.google.gson.annotations.*;
import fifty.group.entity.behaviour.Hoverable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class LivingEntity extends Entity implements Hoverable {

    private static final int SPRITE_SIZE = 48;

    @Expose private double velX;
    @Expose private double velY;

    @Expose private EntitySize size;
    @Expose private EntityState state;
    @Expose private EntityStats stats;
    @Expose private EntityHostility hostility;

    @Expose private int tickCounter;
    @Expose private int frameCounter;
    @Expose private int nextChangeTime;

    private boolean isHovered;

    private int spriteOffset;
    private BufferedImage spritesToDraw;
    private ArrayList<BufferedImage> sprites;

    private Random random;

    protected Entity target;

    protected LivingEntity(int x, int y, EntityHandler entityHandler) {
        super(x, y, entityHandler);
        this.random = new Random();
        this.state = EntityState.SEARCHING;
        this.tickCounter = 0;
        this.frameCounter = 0;
        this.sprites = new ArrayList<>();
        this.stats = new EntityStats(this, 100, 200, 4, 5);
    }

    public abstract void reproduce();

    public void draw(Graphics2D g2d) {
        g2d.drawImage(spritesToDraw, (int) x, (int) y, null);
        if (isHovered) {
            drawHealthBar(g2d);
            drawHungerBar(g2d);
        }
    }

    private void drawHealthBar(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect((int) x, (int) y - 12, 50, 5);
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int) x, (int) y - 12, stats.getHealth() / (stats.getMaxHealth() / 50), 5);
    }

    private void drawHungerBar(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) x, (int) y - 5, 50, 5);
        g2d.setColor(Color.YELLOW);
        g2d.fillRect((int) x, (int) y - 5, stats.getHunger() / (stats.getMaxHunger() / 50), 5);
    }

    public void onHover() {
        isHovered = true;
    }

    public void onUnhover() {
        isHovered = false;
    }

    public void updateEntity() {
        updateBehaviour();
        updateGenericBehaviour();
        updateMovement();
        updateHunger();
        updateImage();
        updateFrameCounter();
        restrictPosition();

        x += velX;
        y += velY;

        tickCounter++;
    }

    protected void updateBehaviour() {
    }

    private void updateMovement() {
        if (state.equals(EntityState.SEARCHING)) {
            updateDirection();
        } else if (state.equals(EntityState.MOVING)) {
            moveTo(target.getX(), target.getY());
        }

    }

    private void updateGenericBehaviour() {
        if (stats.getHunger() > stats.getMaxHunger()/4*3) return;
        Entity targetEntity = hostility.equals(EntityHostility.HOSTILE) ? entityHandler.getVisiblePray(this) : entityHandler.getVisibleGrass(this);

        if (targetEntity == null) {
            state = EntityState.SEARCHING;
            stats.setSpeed(stats.getMaxSpeed() / 2);
            this.target = null;
        } else {
            state = EntityState.MOVING;
            stats.setSpeed(stats.getMaxSpeed());
            this.target = targetEntity;
        }

        if (target == null) return;

        if (hostility.equals(EntityHostility.HOSTILE)) {
                damagePray();
        } else if (hostility.equals(EntityHostility.PASSIVE)) {
                eatGrass();
        }
    }

    private void damagePray() {
        if (tickCounter % 10 != 0) return;
        if (this.getBoundingBox().intersects(target.getBoundingBox())) {
            ((LivingEntity)target).getStats().damage(this.getStats().getDamage());
            if (((LivingEntity)target).getStats().getHealth() < 0) {
                consumeEntity();
            }
        }
    }

    private void eatGrass() {
        if (this.getBoundingBox().intersects(target.getBoundingBox())) {
            consumeEntity();
        }
    }

    private void consumeEntity() {
        this.getStats().applyEatBonus();
        entityHandler.removeEntity(target);
        target = null;
        state = EntityState.SEARCHING;
        stats.setSpeed(stats.getMaxSpeed()/2);
    }

    private void updateFrameCounter() {
        if (tickCounter % (40 / stats.getSpeed()) == 0) {
            frameCounter++;
            frameCounter = frameCounter % 3;
        }
    }

    private void restrictPosition() {
        if (x <= 0 || x  + SPRITE_SIZE >= 1400) {
            velX = -velX;
        }

        if (y <= 0 || y  + SPRITE_SIZE >= 800) {
            velY = -velY;
        }

        calculateOffset();
    }

    private void updateHunger() {
        if (tickCounter % 100 == 0) {
            stats.decreaseHunger();
        }
    }

    private void updateImage() {
        int imageIndex = frameCounter + spriteOffset;

        spritesToDraw = sprites.get(imageIndex);
    }

    private void calculateOffset() {
        if (Math.abs(velX) > Math.abs(velY)) {
            spriteOffset = (velX > 0) ? 2 : 1;
        } else {
            spriteOffset = (velY > 0) ? 0 : 3;
        }
        spriteOffset *= 3;
    }

    private void updateDirection() {
        if (!state.equals(EntityState.SEARCHING)) return;
        if (random.nextInt(50 * 12) < 10) {
            nextChangeTime--;
        }

        if (nextChangeTime <= 0) {
            nextChangeTime = random.nextInt(10) + 5;

            changeDirection();
            calculateOffset();
        }
    }

    private void changeDirection() {
        velX = random.nextInt(100) - (double) 50;
        velY = random.nextInt(100) - (double) 50;

        double magnitude = Math.sqrt(velX * velX + velY * velY) / stats.getSpeed() * 12;

        if (magnitude != 0) {
            velX /= magnitude;
            velY /= magnitude;
        }
    }

    private void moveTo(double targetX, double targetY) {
        double dx = targetX - x;
        double dy = targetY - y;

        double magnitude = Math.sqrt(dx * dx + dy * dy) / stats.getSpeed() * 12;

        if (magnitude != 0) {
            velX = (dx / magnitude);
            velY = (dy / magnitude);
        }
    }

    public Rectangle getFOV() {
        double rectangleSize = 200 * size.getNumerical();
        return new Rectangle((int) (x - rectangleSize / 2 + 24), (int) (y - rectangleSize / 2 + 32), (int) rectangleSize, (int) rectangleSize);
    }

    public double getSize() {
        return this.size.getNumerical();
    }

    public EntityHostility getHostility() {
        return hostility;
    }

    public EntityStats getStats() {
        return stats;
    }

    public void retrieveSprites(int xIndex, int yIndex, String path) {
        int col = xIndex * 3;
        int row = yIndex * 4;
        BufferedImage spriteSheet;
        try {
            spriteSheet = ImageIO.read(Objects.requireNonNull(LivingEntity.class.getResourceAsStream(path)));
        } catch (Exception ignored) {
            return;
        }

        for (int y = row * SPRITE_SIZE; y < (row + 4) * SPRITE_SIZE; y += SPRITE_SIZE) {
            for (int x = col * SPRITE_SIZE; x < (col + 3) * SPRITE_SIZE; x += SPRITE_SIZE) {
                sprites.add(spriteSheet.getSubimage(x, y, SPRITE_SIZE, SPRITE_SIZE));
            }
        }
    }

    protected void setSize(EntitySize size) {
        this.size = size;
    }

    protected void setHostility(EntityHostility hostility) {
        this.hostility = hostility;
    }

    protected void setStats(EntityStats stats) {
        this.stats = stats;
    }

    public void setSprites(ArrayList<BufferedImage> sprites) {
        this.sprites = sprites;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
