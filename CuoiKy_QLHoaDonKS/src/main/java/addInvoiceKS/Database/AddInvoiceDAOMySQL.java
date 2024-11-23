package addInvoiceKS.Database;

import java.sql.*;

import addInvoiceKS.UseCase.AddInvoiceDatabaseBoundary;
import addInvoiceKS.UseCase.AddInvoiceInputDTO;

public class AddInvoiceDAOMySQL implements AddInvoiceDatabaseBoundary {
    private ConnectionDB connectionDB;

    public AddInvoiceDAOMySQL(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public boolean addInvoiceDB(AddInvoiceInputDTO addInvoiceInputDTO){
        String query = "INSERT INTO HoaDon (MaHoaDon, NgayHoaDon, TenKhachHang, MaPhong, DonGia, LoaiHoaDon, SoGioThue, SoNgayThue) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        connection = connectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, addInvoiceInputDTO.getMaHD());
            preparedStatement.setDate(2, new java.sql.Date(addInvoiceInputDTO.getNgayHD().getTime()));
            preparedStatement.setString(3, addInvoiceInputDTO.getTenKH());
            preparedStatement.setString(4, addInvoiceInputDTO.getMaPhong());
            preparedStatement.setDouble(5, Double.parseDouble(addInvoiceInputDTO.getDonGia()));
            preparedStatement.setString(6, addInvoiceInputDTO.getLoaiHoaDon());

            if (addInvoiceInputDTO.getLoaiHoaDon().equals("Theo Giờ")) {
                Integer soGioThue = addInvoiceInputDTO.getSoGioThue() != null ? Integer.parseInt(addInvoiceInputDTO.getSoGioThue()) : 0;
                preparedStatement.setInt(7, soGioThue);
                preparedStatement.setNull(8, java.sql.Types.INTEGER);
            } else if (addInvoiceInputDTO.getLoaiHoaDon().equals("Theo Ngày")) {
                Integer soNgayThue = addInvoiceInputDTO.getSoNgayThue() != null ? Integer.parseInt(addInvoiceInputDTO.getSoNgayThue()) : 0;
                preparedStatement.setInt(8, soNgayThue);
                preparedStatement.setNull(7, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setNull(7, java.sql.Types.INTEGER);
                preparedStatement.setNull(8, java.sql.Types.INTEGER);
            }

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean invoiceExists(AddInvoiceInputDTO addInvoiceInputDTO) {
        Connection connection = null;
        connection = connectionDB.getConnection();
        String querySQL = "SELECT MaHoaDon FROM HoaDon WHERE MaHoaDon = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setString(1, addInvoiceInputDTO.getMaHD());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra hóa đơn: " + e.getMessage());
            return false;
        }
    }

}
