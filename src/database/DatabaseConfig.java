package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    //load config info
    static {
        //try to load the file
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fis);

            //read info stored in the file
            URL = props.getProperty("DB_URL");
            USER = props.getProperty("DB_USER");
            PASSWORD = props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }

    //getters
    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}

