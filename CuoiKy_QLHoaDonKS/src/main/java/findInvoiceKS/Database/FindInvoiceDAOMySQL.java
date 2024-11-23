package findInvoiceKS.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import findInvoiceKS.UseCase.FindInvoiceDatabaseBoundary;
import findInvoiceKS.UseCase.FindInvoiceInputDTO;
import findInvoiceKS.UseCase.FindInvoiceOutPutDTO;

public class FindInvoiceDAOMySQL implements FindInvoiceDatabaseBoundary {

    private ConnectionDBFindInvoice connectionDB;

    public FindInvoiceDAOMySQL(ConnectionDBFindInvoice connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public boolean maHoaDonExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query = "SELECT * FROM HoaDon WHERE MaHoaDon LIKE ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<FindInvoiceOutPutDTO> getMaHoaDon(FindInvoiceInputDTO findInvoiceInputDTO) {
        String query = "SELECT * FROM HoaDon WHERE MaHoaDon LIKE ?";
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>();

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FindInvoiceOutPutDTO invoice = new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));
                
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    @Override
    public boolean ngayHoaDonExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query;

        query = "SELECT * FROM HoaDon WHERE NgayHoaDon LIKE ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            String inputDate = findInvoiceInputDTO.getGiaTri().trim();
            String formattedDate = inputDate; 

            if (inputDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = inputFormat.parse(inputDate);
                    formattedDate = outputFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return false; 
                }
            }

            preparedStatement.setString(1, "%" + formattedDate + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<FindInvoiceOutPutDTO> getNgayHoaDon(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException {
        String query;
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>();

        query = "SELECT * FROM HoaDon WHERE NgayHoaDon LIKE ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            String inputDate = findInvoiceInputDTO.getGiaTri().trim();
            String formattedDate = inputDate; 

            if (inputDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = inputFormat.parse(inputDate);
                    formattedDate = outputFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return invoiceList;
                }
            }

            preparedStatement.setString(1, "%" + formattedDate + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FindInvoiceOutPutDTO invoice = new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));

                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    @Override
    public boolean tenKhachHangExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query = "SELECT * FROM HoaDon WHERE TenKhachHang LIKE ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;

    }

    @Override
    public List<FindInvoiceOutPutDTO> getTenKhachHang(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException {
        String query = "SELECT * FROM HoaDon WHERE TenKhachHang LIKE ?";
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>();

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FindInvoiceOutPutDTO invoice = new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));

                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    @Override
    public boolean maPhongExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query = "SELECT * FROM HoaDon WHERE MaPhong LIKE ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<FindInvoiceOutPutDTO> getMaPhong(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException {
        String query = "SELECT * FROM HoaDon WHERE MaPhong LIKE ?";
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>();

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FindInvoiceOutPutDTO invoice = new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));

                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    @Override
    public boolean donGiaExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query = "SELECT * FROM HoaDon WHERE DonGia LIKE ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<FindInvoiceOutPutDTO> getDonGia(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException {
        String query = "SELECT * FROM HoaDon WHERE DonGia LIKE ?";
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>();

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FindInvoiceOutPutDTO invoice = new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));

                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    @Override
    public boolean loaiHoaDonExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query = "SELECT * FROM HoaDon WHERE LoaiHoaDon LIKE ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<FindInvoiceOutPutDTO> getLoaiHoaDon(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException {
        String query = "SELECT * FROM HoaDon WHERE LoaiHoaDon LIKE ?";
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>(); 

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                invoiceList.add(new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList; 
    }

    @Override
    public boolean soGioThueExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query = "SELECT * FROM HoaDon WHERE SoGioThue = ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, findInvoiceInputDTO.getGiaTri().trim());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<FindInvoiceOutPutDTO> getSoGioThue(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException {
        String query = "SELECT * FROM HoaDon WHERE SoGioThue LIKE ?";
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>();

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FindInvoiceOutPutDTO invoice = new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));

                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    @Override
    public boolean soNgayThueExists(FindInvoiceInputDTO findInvoiceInputDTO) {
        boolean exists = false;
        String query = "SELECT * FROM HoaDon WHERE SoNgayThue = ?";

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, findInvoiceInputDTO.getGiaTri().trim());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<FindInvoiceOutPutDTO> getSoNgayThue(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException {
        String query = "SELECT * FROM HoaDon WHERE SoNgayThue LIKE ?";
        List<FindInvoiceOutPutDTO> invoiceList = new ArrayList<>();

        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + findInvoiceInputDTO.getGiaTri().trim() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FindInvoiceOutPutDTO invoice = new FindInvoiceOutPutDTO(
                        resultSet.getString("MaHoaDon"),
                        resultSet.getDate("NgayHoaDon"),
                        resultSet.getString("TenKhachHang"),
                        resultSet.getString("MaPhong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getString("LoaiHoaDon"),
                        resultSet.getInt("SoGioThue"),
                        resultSet.getInt("SoNgayThue"));

                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

}
