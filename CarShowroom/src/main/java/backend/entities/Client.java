package backend.entities;

public class Client {

    private long id;
    private String name;
    private boolean isMan;
    private int age;
    private Car car;

    public Client(long id, String name, boolean isMan, int age, Car car) {
        this.id = id;
        this.name = name;
        this.isMan = isMan;
        this.age = age;
        this.car = car;
    }

    @Override
    public String toString() {
        return name + " (" + (isMan ? "man" : "woman") + ") " + age
                + " --> " + car.getModel() + " " + car.getName() + ": " + car.getType().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
