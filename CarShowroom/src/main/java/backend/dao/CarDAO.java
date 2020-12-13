package backend.dao;

import backend.entities.Car;
import backend.enums.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements DAO<Car, Long> {

    private static final String getSql = "SELECT c.id, t.name AS type, c.model, c.name, c.is_sold FROM car c JOIN car_type t ON c.type_id=t.id WHERE c.id=?";
    private static final String getAllSql = "SELECT c.id, t.name AS type, c.model, c.name, c.is_sold FROM car c JOIN car_type t ON c.type_id=t.id";
    private static final String insertSql = "INSERT INTO car (id, type_id, model, name, is_sold) VALUES (DEFAULT, (SELECT id FROM car_type WHERE name=?), ?, ?, ?) RETURNING id";
    private static final String updateSql = "UPDATE car SET type_id=(SELECT id FROM car_type WHERE name=?), model=?, name=?, is_sold=? WHERE id=?";
    private static final String deleteSql = "DELETE FROM car WHERE id=?";

    private final Connection connection;

    public CarDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long insert(Car model) {
        long id = -1L;
        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, model.getType().toString());
            statement.setString(2, model.getModel());
            statement.setString(3, model.getName());
            statement.setBoolean(4, model.isSold());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Car get(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(getSql)) {
            statement.setLong(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Car(rs.getLong("id"),
                        rs.getString("model"),
                        rs.getString("name"),
                        CarType.getCarTypeByName(rs.getString("type")),
                        rs.getBoolean("is_sold"));
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }


    @Override
    public boolean update(Car model) {
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, model.getType().toString());
            statement.setString(2, model.getModel());
            statement.setString(3, model.getName());
            statement.setBoolean(4, model.isSold());
            statement.setLong(5, model.getId());
            return statement.executeQuery().next();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setLong(1, id);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(getAllSql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cars.add(new Car(rs.getLong("id"),
                        rs.getString("model"),
                        rs.getString("name"),
                        CarType.getCarTypeByName(rs.getString("type")),
                        rs.getBoolean("is_sold")));
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return cars;
    }
}
