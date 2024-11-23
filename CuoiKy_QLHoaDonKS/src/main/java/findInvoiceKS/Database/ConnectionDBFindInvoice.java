package findInvoiceKS.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBFindInvoice {
    private static ConnectionDBFindInvoice connectionDB;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/dbquanlyinvoiceks";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789@Vy";

    public static ConnectionDBFindInvoice getInstance() throws SQLException, ClassNotFoundException {
        if (connectionDB == null) {
            connectionDB = new ConnectionDBFindInvoice();
        }
        return connectionDB;
    }

    private ConnectionDBFindInvoice() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
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
