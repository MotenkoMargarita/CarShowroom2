package frontend.console.views;

import backend.entities.Car;
import backend.entities.Client;
import backend.enums.CarType;
import backend.services.CarService;
import backend.services.ClientService;

import java.util.Arrays;
import java.util.List;

import static frontend.console.App.SCANNER;

public class CarView {
    private CarService carService;
    private ClientService clientService;

    public CarView(CarService carService, ClientService clientService) {
        this.carService = carService;
        this.clientService = clientService;
    }

    public void showCars() {
        carService.getCars().stream().filter(car -> !car.isSold()).forEach(car -> System.out.println(car.toString()));
    }

    public void addCar() {
        System.out.print("Model: ");
        String model = SCANNER.nextLine();

        System.out.print("Name: ");
        String name = SCANNER.nextLine();

        System.out.println("Type:");
        Arrays.asList(CarType.values())
                .forEach(type -> System.out.println(type.getId() + ". " + type.toString()));
        System.out.print("- ");
        CarType type = CarType.getCarTypeById(Integer.valueOf(SCANNER.nextLine()));
        carService.addCar(new Car(-1L, model, name, type, false));
    }

    public void dropCar() {
        showCars();
        System.out.println();
        System.out.print("Id: ");
        carService.deleteCar(Long.valueOf(SCANNER.nextLine()));
    }

    public void buyCar() {
        showCars();
        System.out.print("Id: ");
        long id = Long.valueOf(SCANNER.nextLine());
        Car car = carService.getCarById(id);

        System.out.print("Client name: ");
        String name = SCANNER.nextLine();

        System.out.print("1. Man\n2. Woman\n- ");
        boolean isMan = Integer.valueOf(SCANNER.nextLine()) == 1;

        System.out.print("Age: ");
        int age = Integer.valueOf(SCANNER.nextLine());

        car.setSold(true);
        carService.editCar(car);
        clientService.addClient(new Client(-1L, name, isMan, age, car));
    }
}
