package backend.entities;

import backend.enums.CarType;

public class Car {

    private long id;
    private String model;
    private String name;
    private CarType type;
    private boolean isSold;

    public Car(long id, String model, String name, CarType type, boolean isSold) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.type = type;
        this.isSold = isSold;
    }

    @Override
    public String toString() {
        return id + ") " + model + " " + name + ": " + type.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }
}
