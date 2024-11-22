package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getPropertyString(String propertyFileName) {
        try (FileInputStream fis = new FileInputStream(propertyFileName)) {
            Properties properties = new Properties();
            properties.load(fis);

            String hostname = properties.getProperty("hostname");
            String dbname = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port");

            return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
