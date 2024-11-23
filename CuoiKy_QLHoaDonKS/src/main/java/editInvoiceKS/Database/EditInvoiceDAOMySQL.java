package editInvoiceKS.Database;

import java.sql.*;

import editInvoiceKS.UseCase.EditInvoiceDatabaseBoundary;
import editInvoiceKS.UseCase.EditInvoiceInputDTO;

public class EditInvoiceDAOMySQL implements EditInvoiceDatabaseBoundary {
    private ConnectionDBEdit connectionDB;

    public EditInvoiceDAOMySQL(ConnectionDBEdit connectionDB) {
        this.connectionDB = connectionDB;

    }

    @Override
    public boolean editInvoiceDB(EditInvoiceInputDTO editInvoiceInputDTO) {
        String query = "UPDATE HoaDon SET NgayHoaDon = ?, TenKhachHang = ?, MaPhong = ?, LoaiHoaDon = ?, DonGia = ?, SoGioThue = ?, SoNgayThue = ? WHERE MaHoaDon = ?";
        try{
            Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, new java.sql.Date(editInvoiceInputDTO.getNgayHD().getTime()));
            preparedStatement.setString(2, editInvoiceInputDTO.getTenKH());
            preparedStatement.setString(3, editInvoiceInputDTO.getMaPhong());
            preparedStatement.setString(4, editInvoiceInputDTO.getLoaiHoaDon());
            preparedStatement.setDouble(5, Double.parseDouble(editInvoiceInputDTO.getDonGia()));

            if (editInvoiceInputDTO.getSoGioThue() != null && !editInvoiceInputDTO.getSoGioThue().isEmpty()) {
                preparedStatement.setInt(6, Integer.parseInt(editInvoiceInputDTO.getSoGioThue()));
            } else {
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
            }

            if (editInvoiceInputDTO.getSoNgayThue() != null && !editInvoiceInputDTO.getSoNgayThue().isEmpty()) {
                preparedStatement.setInt(7, Integer.parseInt(editInvoiceInputDTO.getSoNgayThue()));
            } else {
                preparedStatement.setNull(7, java.sql.Types.INTEGER);
            }

            preparedStatement.setString(8, editInvoiceInputDTO.getMaHD());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi sửa hóa đơn: " + e.getMessage());
            return false;
        }
    }
}
