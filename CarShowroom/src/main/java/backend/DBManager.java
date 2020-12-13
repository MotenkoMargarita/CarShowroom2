package backend;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/car_showroom";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";
    private Connection connection;

    public DBManager() {
    }

    private Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }
}
