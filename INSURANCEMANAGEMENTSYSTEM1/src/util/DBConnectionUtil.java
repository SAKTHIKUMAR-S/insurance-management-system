package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {
    public static Connection getConnection() throws Exception {
        String connectionString = DBPropertyUtil.getPropertyString("resources/db.properties");
        return DriverManager.getConnection(connectionString);
    }
}
