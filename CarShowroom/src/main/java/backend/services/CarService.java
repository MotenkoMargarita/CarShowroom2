package backend.services;

import backend.dao.CarDAO;
import backend.entities.Car;

import java.sql.Connection;
import java.util.List;

public class CarService {

    private CarDAO carDAO;


    public CarService(Connection connection) {
        this.carDAO = new CarDAO(connection);
    }

    public List<Car> getCars() {
        return carDAO.getAll();
    }

    public Long addCar(Car car) {
        return carDAO.insert(car);
    }

    public boolean deleteCar(long id) {
        return carDAO.delete(id);
    }

    public boolean editCar(Car car) {
        return carDAO.update(car);
    }

    public Car getCarById(long id) {
        return carDAO.get(id);
    }

}
