package database;
import java.sql.*;

public class DataBaseHelper {
    private static final String URL = "jdbc:mysql://tianshansu.com:3306/u867104763_PuzzleGameData";
    private static final String USER = "u867104763_Gtz";
    private static final String PASSWORD = "Gtz120734";

    //create database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //register user
    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO PuzzleGameUsers (userId, userPw) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    //user login check
    public static boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM PuzzleGameUsers WHERE userId = ? AND userPw = ?";//use username and password to search for account
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); //if there is a result, return true
        } catch (SQLException e) {
            return false; //if caught exception, return false
        }
    }

    public static boolean checkUserIdExists(String username){
        String sql = "SELECT * FROM PuzzleGameUsers WHERE userId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setString(1, username);
             ResultSet rs = stmt.executeQuery();
             return rs.next(); //if there is a result, return true
        } catch (SQLException e) {
             return false; //if caught exception, return false
        }
    }
}
