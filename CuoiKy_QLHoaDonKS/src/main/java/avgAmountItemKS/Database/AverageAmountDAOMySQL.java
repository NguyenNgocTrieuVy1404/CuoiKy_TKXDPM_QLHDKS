package avgAmountItemKS.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import avgAmountItemKS.Entity.Invoice;
import avgAmountItemKS.Entity.InvoiceDay;
import avgAmountItemKS.Entity.InvoiceHour;
import avgAmountItemKS.UseCase.AverageAmountDatabaseBoundary;
import avgAmountItemKS.UseCase.AverageAmountInputDTO;

public class AverageAmountDAOMySQL implements AverageAmountDatabaseBoundary {
    private ConnectionDBAmount connectionDB;

    public AverageAmountDAOMySQL(ConnectionDBAmount connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public List<Invoice> getAverageAmountInvoices(AverageAmountInputDTO averageAmountInputDTO) {
        List<Invoice> invoices = new ArrayList<>();
        String query = "SELECT * FROM HoaDon WHERE MONTH(NgayHoaDon) = ?";
        
        Connection connection = connectionDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, averageAmountInputDTO.getThangHD());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Invoice invoice;
                if ("Theo Ngày".equals(resultSet.getString("LoaiHoaDon"))) {
                    invoice = new InvoiceDay(
                            resultSet.getString("MaHoaDon"),
                            resultSet.getDate("NgayHoaDon"),
                            resultSet.getString("TenKhachHang"),
                            resultSet.getString("MaPhong"),
                            resultSet.getDouble("DonGia"),
                            resultSet.getInt("SoNgayThue"));
                } else {
                    invoice = new InvoiceHour(
                            resultSet.getString("MaHoaDon"),
                            resultSet.getDate("NgayHoaDon"),
                            resultSet.getString("TenKhachHang"),
                            resultSet.getString("MaPhong"),
                            resultSet.getDouble("DonGia"),
                            resultSet.getInt("SoGioThue"));
                }
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
}
