package getAllInvoiceList.DataBase;

import java.util.ArrayList;
import java.util.List;

import getAllInvoiceList.UseCase.GetInvoiceListDatabaseBoundary;
import getAllInvoiceList.UseCase.GetInvoiceOuputDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetInvoiceListDAOMySQL implements GetInvoiceListDatabaseBoundary {
    private ConnectionDBGet connectionDB;

    public GetInvoiceListDAOMySQL(ConnectionDBGet connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public List<GetInvoiceOuputDTO> getAllInvoiceList() {
        List<GetInvoiceOuputDTO> invoiceDTOs = new ArrayList<>();
        String query = "SELECT * FROM HoaDon";
        Connection connection = null;
        connection = connectionDB.getConnection();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                GetInvoiceOuputDTO invoice = new GetInvoiceOuputDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));
                invoiceDTOs.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceDTOs;
    }
}
