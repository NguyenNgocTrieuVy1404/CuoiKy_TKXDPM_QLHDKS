package com.addinvoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import findInvoiceDisplayKS.UI.FindDisplayInvoicePresenter;
import findInvoiceDisplayKS.UI.FindViewModel;
import findInvoiceKS.Database.ConnectionDBFindInvoice;
import findInvoiceKS.Database.FindInvoiceDAOMySQL;
import findInvoiceKS.UseCase.DataMessageFind;
import findInvoiceKS.UseCase.ResponseDataFind;
import findInvoiceKS.UseCase.FindInvoiceDatabaseBoundary;
import findInvoiceKS.UseCase.FindInvoiceInputBoundary;
import findInvoiceKS.UseCase.FindInvoiceInputDTO;
import findInvoiceKS.UseCase.FindInvoiceOutPutDTO;
import findInvoiceKS.UseCase.FindInvoiceOutputBoundary;
import findInvoiceKS.UseCase.FindInvoiceUseCase;

public class FindInvoiceTest {
    FindViewModel viewModel;
    ConnectionDBFindInvoice connectionDB;
    ResponseDataFind dataMessage;
    FindInvoiceDatabaseBoundary findInvoiceDatabaseBoundary;
    FindInvoiceOutputBoundary findInvoiceOutputBoundary;
    FindInvoiceInputBoundary findInvoiceInputBoundary;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        viewModel = new FindViewModel();
        connectionDB = ConnectionDBFindInvoice.getInstance();
        dataMessage = new DataMessageFind();
        findInvoiceDatabaseBoundary = new FindInvoiceDAOMySQL(connectionDB);
        findInvoiceOutputBoundary = new FindDisplayInvoicePresenter(viewModel);
        findInvoiceInputBoundary = new FindInvoiceUseCase(findInvoiceDatabaseBoundary,
                findInvoiceOutputBoundary, dataMessage);
    }

    // Mã hóa đơn trống
    @Test
    public void FindTestMaHDEmpty() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Mã hoá đơn", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập mã hóa đơn.",
        viewModel.getErrorMessage());
    }

    // Mã hóa đơn không tồn tại
    @Test
    public void FindTestMaHDExists() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Mã hoá đơn", "1000");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Mã hóa đơn không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm theo mã hóa đơn
    @Test
    public void FindTestMaHDSuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Mã hoá đơn", "HD001");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo mã hóa đơn.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getMaHoaDon(findInvoiceInputDTO);
        boolean found = false;
        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            if (invoice.getMaHD().equals("HD001")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    // Ngày hóa đơn trống
    @Test
    public void FindTestNgayHDEmpty() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Ngày hoá đơn", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập ngày hóa đơn.",
        viewModel.getErrorMessage());
    }

    // Ngày hóa đơn không tồn tại
    @Test
    public void FindTestNgayHDError() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Ngày hoá đơn", "32");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Ngày hóa đơn không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm thấy ngày hóa đơn
    @Test
    public void FindTestNgayHDSuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Ngày hoá đơn", "10/10/2024");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo ngày hóa đơn.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getNgayHoaDon(findInvoiceInputDTO);

        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            assertEquals("2024-10-10", invoice.getNgayHD().toString());
        }

    }

    // Tên khách hàng trống
    @Test
    public void FindTestTenKHError() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Tên khách hàng", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập tên khách hàng.",
        viewModel.getErrorMessage());
    }

    // Tên khách hàng không tồn tại
    @Test
    public void FindTestTenKHExists() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Tên khách hàng", "z");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Tên khách hàng không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm thấy tên khách hàng
    @Test
    public void FindTestTenKHSuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Tên khách hàng", "Nguyễn");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo tên khách hàng.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getTenKhachHang(findInvoiceInputDTO);
        boolean found = false;
        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            if (invoice.getTenKH().contains("Nguyễn")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    // Mã phòng trống
    @Test
    public void FindTestMaPhongError() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Mã phòng", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập mã phòng.",
        viewModel.getErrorMessage());
    }

    // Mã phòng không tồn tại
    @Test
    public void FindTestMaPhongExists() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Mã phòng", "sad");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Mã phòng không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm thấy mã phòng
    @Test
    public void FindTestMaPhongSuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Mã phòng", "10");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo mã phòng.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getMaPhong(findInvoiceInputDTO);
        boolean found = false;
        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            if (invoice.getMaPhong().contains("10")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    // Đơn giá trống
    @Test
    public void FindTestDonGiaError() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Đơn giá", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập đơn giá.",
        viewModel.getErrorMessage());
    }

    // Đơn giá không tồn tại
    @Test
    public void FindTestDonGiaExists() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Đơn giá", "sad");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Đơn giá không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm thấy đơn giá
    @Test
    public void FindTestDonGiaSuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Đơn giá", "2100");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo đơn giá.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getDonGia(findInvoiceInputDTO);

        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            assertEquals("2100.0", String.valueOf(invoice.getDonGia()));
        }
    }

    // Loại hóa đơn trống
    @Test
    public void FindTestLoaiHDError() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Loại hoá đơn", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập loại hóa đơn.\n  *(Theo giờ, Theo ngày)",
        viewModel.getErrorMessage());
    }

    // Loại hóa đơn không tồn tại
    @Test
    public void FindTestLoaiHDExists() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Loại hoá đơn", "sad");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Loại hóa đơn không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm thấy loại hóa đơn Theo Giờ
    @Test
    public void FindTestLoaiHDHourSuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Loại hoá đơn", "Theo giờ");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo loại hóa đơn.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getLoaiHoaDon(findInvoiceInputDTO);
        boolean found = false;
        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            if (invoice.getLoaiHoaDon().contains("Theo Giờ")) {
                found = true;
                break;
            }
        }
        assertTrue(found);

    }

    // Tìm thấy loại hóa đơn Theo Ngày
    @Test
    public void FindTestLoaiHDDaySuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Loại hoá đơn", "Theo ngày");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo loại hóa đơn.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getLoaiHoaDon(findInvoiceInputDTO);
        boolean found = false;
        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            if (invoice.getLoaiHoaDon().contains("Theo Ngày")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    // Số giờ thuê trống
    @Test
    public void FindTestSoGioThueError() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Số giờ thuê", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập số giờ thuê.",
        viewModel.getErrorMessage());
    }

    // Số giờ thuê không tồn tại
    @Test
    public void FindTestSoGioThueExists() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Số giờ thuê", "99");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Số giờ thuê không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm thấy số giờ thuê
    @Test
    public void FindTestSoGioThueSuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Số giờ thuê", "10");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo số giờ thuê.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getSoGioThue(findInvoiceInputDTO);

        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            assertNotNull("10", String.valueOf(invoice.getSoGioThue()));
        }

    }

    // Số ngày thuê trống
    @Test
    public void FindTestSoGioNgayError() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Số ngày thuê", "");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Vui lòng nhập số ngày thuê.",
        viewModel.getErrorMessage());
    }

    // Số ngày thuê không tồn tại
    @Test
    public void FindTestSoGioNgayExists() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Số ngày thuê", "99");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);

        assertEquals("Số ngày thuê không tồn tại.",
        viewModel.getErrorMessage());
    }

    // Tìm ngày số giờ thuê
    @Test
    public void FindTestSoGioNgaySuccess() throws ClassNotFoundException, SQLException {

        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO("Số ngày thuê", "10");

        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
        assertEquals("Đã tìm thấy hóa đơn theo số ngày thuê.",
        viewModel.getMessage());

        List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getSoNgayThue(findInvoiceInputDTO);

        for (FindInvoiceOutPutDTO invoice : invoiceList) {
            assertNotNull("10", String.valueOf(invoice.getSoNgayThue()));
        }

    }
}
