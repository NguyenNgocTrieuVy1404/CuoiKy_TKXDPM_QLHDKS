package deleteInvoiceDisplayKS.Database;

import java.util.ArrayList;
import java.util.List;

import deleteInvoiceDisplayKS.UseCase.DeleteDisplayDatabaseBoundary;

import java.sql.*;

public class DeleteDisplayDAOMySQL implements DeleteDisplayDatabaseBoundary  {
    private ConnectionDBDDisplay connectionDB;

    public DeleteDisplayDAOMySQL(ConnectionDBDDisplay connectionDB) {
        this.connectionDB = connectionDB;
    }

    public List<String> getInvoiceIds() {
        List<String> invoiceIds = new ArrayList<>();
        String query = "SELECT MaHoaDon FROM HoaDon ORDER BY MaHoaDon ASC";
        Connection connection = connectionDB.getConnection();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                invoiceIds.add(resultSet.getString("MaHoaDon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceIds;

    }

}
