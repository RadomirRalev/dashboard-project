package enums;

public enum Size {
    LARGE(3, "Large"),
    MEDIUM(2, "Medium"),
    SMALL(1, "Small");

    private final int weight;
    private final String size;

    Size(int weight, String size) {
        this.weight = weight;
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
       return size;
    }

}
