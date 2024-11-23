package totalQuantityItemKS.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import totalQuantityItemKS.Entity.Invoice;
import totalQuantityItemKS.Entity.InvoiceDay;
import totalQuantityItemKS.Entity.InvoiceHour;
import totalQuantityItemKS.UseCase.TotalQuantityDatabaseBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityInputDTO;

public class TotalQuantityDAOMySQL implements TotalQuantityDatabaseBoundary {

    private ConnectionDBTotal connectionDB;

    public TotalQuantityDAOMySQL(ConnectionDBTotal connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public List<Invoice> getTotalQuantityInvoices(TotalQuantityInputDTO totalQuantityInputDTO) {
        String query = "SELECT * FROM HoaDon WHERE LoaiHoaDon = ?";
        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, totalQuantityInputDTO.getLoaiHD());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Invoice invoice;
                    String loaiHD = resultSet.getString("LoaiHoaDon");

                    if ("Theo Ngày".equals(loaiHD)) {
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
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return invoices;
    }
}
