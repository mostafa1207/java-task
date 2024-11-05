package listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.DBCommunication;

public class Listener implements ServletContextListener {

    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection intialized...");
            sce.getServletContext().setAttribute("DBConnection", connection);
            DBCommunication.setConnection(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed...");
            }
            sce.getServletContext().removeAttribute("DBConnection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
