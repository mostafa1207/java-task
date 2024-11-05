package model;

import data.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCommunication {

    private static final String DB_NAME = "Mostafa";
    private static Connection conn;

    private static void createDB() {
        String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";";
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void useDB() {
        String sql = "use " + DB_NAME + ";";
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user ( "
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "email VARCHAR(100) NOT NULL, "
                + "password INT(20) NOT NULL);";
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setConnection(Connection connection) {
        conn = connection;
        createDB();
        useDB();
        createTable();
    }

    public static void insertData(String name, String email, int password) {
        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, password);
            int pstmtResult = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User selectData(String email, int password) {
        User user = null;
        String sql = "SELECT * FROM user "
                + "WHERE email = '" + email + "';";
        try (Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(Integer.parseInt(rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
