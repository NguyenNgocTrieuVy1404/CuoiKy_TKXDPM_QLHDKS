package avgAmountItemKS.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBAmount {
    private static ConnectionDBAmount connectionDBAmount;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/dbquanlyinvoiceks";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789@Vy";

    public static ConnectionDBAmount getInstance() throws SQLException, ClassNotFoundException {
        if (connectionDBAmount == null) {
            connectionDBAmount = new ConnectionDBAmount();
        }
        return connectionDBAmount;
    }

    private ConnectionDBAmount() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}
