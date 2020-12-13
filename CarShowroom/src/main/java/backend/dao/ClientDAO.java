package backend.dao;

import backend.entities.Car;
import backend.entities.Client;
import backend.enums.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client, Long> {

    private static final String getSql = "SELECT cl.id, cl.name, cl.age, cl.is_man, c.id AS carId, t.name AS carType, c.model AS carModel, c.name AS carName, c.is_sold FROM client cl JOIN car c ON cl.car_id=c.id JOIN car_type t ON c.type_id=t.id WHERE cl.id=?";
    private static final String getAllSql = "SELECT cl.id, cl.name, cl.age, cl.is_man, c.id AS carId, t.name AS carType, c.model AS carModel, c.name AS carName, c.is_sold FROM client cl JOIN car c ON cl.car_id=c.id JOIN car_type t ON c.type_id=t.id";
    private static final String insertSql = "INSERT INTO client (id, car_id, name, age, is_man) VALUES (DEFAULT, ?, ?, ?, ?) RETURNING id";
    private static final String updateSql = "UPDATE client SET car_id=?, name=?, age=?, is_man=? WHERE id=?";
    private static final String deleteSql = "DELETE FROM client WHERE id=?;";

    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long insert(Client model) {
        long id = -1L;
        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setLong(1, model.getCar().getId());
            statement.setString(2, model.getName());
            statement.setInt(3, model.getAge());
            statement.setBoolean(4, model.isMan());
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
    public Client get(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(getSql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Car car = new Car(rs.getLong("carId"),
                        rs.getString("carModel"),
                        rs.getString("carName"),
                        CarType.getCarTypeByName(rs.getString("carType")),
                        rs.getBoolean("is_sold"));
                return new Client(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("is_man"),
                        rs.getInt("age"),
                        car);
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(Client model) {
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setLong(1, model.getCar().getId());
            statement.setString(2, model.getName());
            statement.setInt(3, model.getAge());
            statement.setBoolean(4, model.isMan());
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
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(getAllSql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Car car = new Car(rs.getLong("carId"),
                        rs.getString("carModel"),
                        rs.getString("carName"),
                        CarType.getCarTypeByName(rs.getString("carType")),
                        rs.getBoolean("is_sold"));
                clients.add(new Client(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("is_man"),
                        rs.getInt("age"),
                        car));
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return clients;
    }
}
