package backend.enums;

import java.util.Arrays;

public enum CarType {

    SEDAN(1),
    COUPE(2),
    SPORTS(3),
    CROSSOVER(4),
    WAGON(5);

    private int id;

    CarType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CarType getCarTypeByName(String name) {
        return Arrays.stream(CarType.values())
                .filter(type -> type.toString().toUpperCase().equals(name.toUpperCase()))
                .findAny()
                .get();
    }

    public static CarType getCarTypeById(int id) {
        return Arrays.stream(CarType.values())
                .filter(type -> type.getId() == id)
                .findAny()
                .get();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
