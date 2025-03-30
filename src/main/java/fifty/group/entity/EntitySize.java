package fifty.group.entity;

public enum EntitySize {
    SMALL(1),
    MEDIUM(1.25),
    LARGE(1.5);

    private final double numerical;

    EntitySize(double numerical) {
        this.numerical = numerical;
    }

    public double getNumerical() {
        return numerical;
    }
}
