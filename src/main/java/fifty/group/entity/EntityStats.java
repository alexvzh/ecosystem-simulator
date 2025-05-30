package fifty.group.entity;

import com.google.gson.annotations.*;
import fifty.group.scene.SceneManager;

public class EntityStats {
    @Expose private int health;
    @Expose private int maxHealth;
    @Expose private int hunger;
    @Expose private int maxHunger;
    @Expose private int speed;
    @Expose private int maxSpeed;
    @Expose private int damage;

    private final LivingEntity entity;

    public EntityStats(LivingEntity entity, int maxHealth, int maxHunger, int maxSpeed, int damage) {
        this.entity = entity;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.hunger = maxHunger;
        this.maxHunger = maxHunger;
        this.speed = maxSpeed/2;
        this.maxSpeed = maxSpeed;
        this.damage = damage;
    }

    public void decreaseHunger() {
        if (hunger > 0) {
            this.hunger -= 1;
        } else {
            this.health -= 5;
        }

        if (this.health <= 0) {
            SceneManager.getInstance().getCurrentScene().getEntityHandler().removeEntity(entity);
        }
    }

    public void damage(int damage) {
        this.health -= damage;
    }

    public void applyReproductionTax() {
        this.hunger -= maxHunger / 4;
    }

    public void applyEatBonus() {
        this.hunger += maxHunger / 4;
        if (hunger > maxHunger) hunger = maxHunger;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHunger() {
        return hunger;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
