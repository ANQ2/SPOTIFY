package olen.olen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "E.Akylbai7");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
