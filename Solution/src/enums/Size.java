package enums;

import java.util.HashMap;
import java.util.Map;

public enum Size {
    LARGE(3),
    MEDIUM(2),
    SMALL(1);

    private final int weight;

    private static Map map = new HashMap<>();

    static {
        for (Size size : Size.values()) {
            map.put(size.weight, size);
        }
    }

    public static Size valueOf(int sizeTypeWeight){
        return (Size)map.get(sizeTypeWeight);
    }

    Size(int weight) {
        this.weight = weight;
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
