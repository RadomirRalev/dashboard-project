package enums;

import java.util.HashMap;
import java.util.Map;

public enum Size {
    LARGE(3),
    MEDIUM(2),
    SMALL(1);

    private final int weight;

    Size(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        switch (this) {
            case LARGE:
                return "Large";
            case MEDIUM:
                return "Medium";
            case SMALL:
                return "Small";
            default:
                throw new IllegalArgumentException();
        }
    }

}
