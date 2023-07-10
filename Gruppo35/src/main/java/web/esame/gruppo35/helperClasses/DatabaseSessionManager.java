package web.esame.gruppo35.helperClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

public class DatabaseSessionManager {
    private static final String DB_URL = "jdbc:derby://localhost:1527/Tum4WorldDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "admin";

    public static Connection getConnection(HttpSession session) throws SQLException, ClassNotFoundException{
        Connection connection = (Connection) session.getAttribute("dbConnection");
        if (connection == null || connection.isClosed()) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            session.setAttribute("dbConnection", connection);
        }
        return connection;
    }

    public static void closeConnection(HttpSession session) throws SQLException {
        Connection connection = (Connection) session.getAttribute("dbConnection");
        if (connection != null) {
            connection.close();
            session.removeAttribute("dbConnection");
        }
    }
}