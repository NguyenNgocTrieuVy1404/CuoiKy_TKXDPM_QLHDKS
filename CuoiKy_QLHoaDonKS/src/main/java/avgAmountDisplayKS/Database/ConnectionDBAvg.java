package avgAmountDisplayKS.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBAvg {
    private static ConnectionDBAvg connectionDB;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/dbquanlyinvoiceks";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789@Vy";

    public static ConnectionDBAvg getInstance() throws SQLException, ClassNotFoundException {
        if (connectionDB == null) {
            connectionDB = new ConnectionDBAvg();
        }
        return connectionDB;
    }

    private ConnectionDBAvg() throws SQLException, ClassNotFoundException {
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
