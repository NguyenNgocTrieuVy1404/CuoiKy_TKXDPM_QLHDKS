package deleteInvoiceKS.Database;

import java.sql.*;

import deleteInvoiceKS.UseCase.DeleteInvoiceDatabaseBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceInputDTO;

public class DeleteInvoiceDAOMySQL implements DeleteInvoiceDatabaseBoundary {
    private ConnectionDBDelete connectionDB;

    public DeleteInvoiceDAOMySQL(ConnectionDBDelete connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public boolean deleteInvoice(DeleteInvoiceInputDTO deleteInvoiceInputDTO) {

        String query = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
        Connection connection = null;
        connection = connectionDB.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, deleteInvoiceInputDTO.getMaHD());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa hóa đơn: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean invoiceExists(DeleteInvoiceInputDTO deleteInvoiceInputDTO) {
        Connection connection = null;
        connection = connectionDB.getConnection();
        String querySQL = "SELECT MaHoaDon FROM HoaDon WHERE MaHoaDon = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {
            preparedStatement.setString(1, deleteInvoiceInputDTO.getMaHD());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra hóa đơn: " + e.getMessage());
            return false;
        }
    }

}
