package enums;

public enum Size {
    LARGE ("Large"),
    MEDIUM ("Medium"),
    SMALL ("Small");

    private final String size;

    Size(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }
}
