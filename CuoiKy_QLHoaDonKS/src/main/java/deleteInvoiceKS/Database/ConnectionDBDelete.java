package deleteInvoiceKS.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBDelete {
    private static ConnectionDBDelete connectionDB;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/dbquanlyinvoiceks";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789@Vy";

    public static ConnectionDBDelete getInstance() throws SQLException, ClassNotFoundException {
        if (connectionDB == null) {
            connectionDB = new ConnectionDBDelete();
        }
        return connectionDB;
    }

    private ConnectionDBDelete() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
