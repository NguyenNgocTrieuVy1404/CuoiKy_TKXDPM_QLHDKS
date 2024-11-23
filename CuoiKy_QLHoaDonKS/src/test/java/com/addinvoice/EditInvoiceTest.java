package com.addinvoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import editInvoiceDisplayKS.UI.EditDisplayPresenter;
import editInvoiceDisplayKS.UI.EditViewModel;
import editInvoiceKS.Database.ConnectionDBEdit;
import editInvoiceKS.Database.EditInvoiceDAOMySQL;
import editInvoiceKS.UseCase.EditInvoiceDatabaseBoundary;
import editInvoiceKS.UseCase.EditInvoiceInputBoundary;
import editInvoiceKS.UseCase.EditInvoiceInputDTO;
import editInvoiceKS.UseCase.EditInvoiceOutputBoundary;
import editInvoiceKS.UseCase.EditInvoiceUseCase;
import editInvoiceKS.UseCase.ResponseDataEdit;
import editInvoiceKS.UseCase.DataMessageEdit;

public class EditInvoiceTest {
        private EditViewModel modelView;
        private ConnectionDBEdit connectionDB;
        private ResponseDataEdit dataMessage;
        private EditInvoiceDatabaseBoundary editInvoiceDatabaseBoundary;
        private EditInvoiceOutputBoundary editInvoiceOutputBoundary;
        private EditInvoiceInputBoundary editInvoiceUseCase;

        @Before
        public void setUp() throws ClassNotFoundException, SQLException {
                modelView = new EditViewModel();
                connectionDB = ConnectionDBEdit.getInstance();
                dataMessage = new DataMessageEdit();
                editInvoiceDatabaseBoundary = new EditInvoiceDAOMySQL(connectionDB);
                editInvoiceOutputBoundary = new EditDisplayPresenter(modelView);
                editInvoiceUseCase = new EditInvoiceUseCase(editInvoiceDatabaseBoundary, editInvoiceOutputBoundary,
                                dataMessage);
        }

        // Thành công
        @Test
        public void testEditInvoiceSuccess() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 29);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO(
                                "HD002", ngayHD, "Le Thi Phong", "202", "3200", "Theo Ngày", null, "5");

                editInvoiceUseCase.execute(editInvoiceInputDTO);

        }
        //test tên khách hàng
        @Test
        public void testEditInvoiceTenKhachHangNull() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 3);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, null, "P001", "3000",
                                "Theo Ngày", null, "5");
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Tên khách hàng không được để trống.", modelView.getErrorMessage());
        }

        // null tất cả trường
        @Test
        public void testEditInvoiceNull() throws ClassNotFoundException, SQLException {

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO(null, null, null, null, null,
                                null, null, null);

                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertNotNull("Vui lòng chọn hóa đơn cần sửa", modelView.getErrorMessage());
        }
        //đơn giá
        @Test
        public void testEditInvoiceDonGia() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO(
                                "HD002", ngayHD, "Le Thi Phong", "202", "-100", "Theo Ngày", null, "5");

                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Đơn giá phải lớn hơn 0.", modelView.getErrorMessage());
        }
        //test ngay
        @Test
        public void testEditInvoiceDay() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 34);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO(
                                "HD002", ngayHD, "Le Thi Phong", "202", "100", "Theo Ngày", null, "5");

                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Ngày hóa đơn không được vượt quá 7 ngày từ hôm nay.",
                modelView.getErrorMessage());
        }
        //test loai hoa don
        @Test
        public void testEditInvoiceLoaiHoaDon() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong",
                                "202", "3000", "", null, "5");
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Loại hóa đơn không được để trống.", modelView.getErrorMessage());
        }
        //test số giờ thuê
        @Test
        public void testEditInvoiceSoGioThue() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong",
                                "202", "3000", "Theo Giờ", "0", null);
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Số giờ thuê phải lớn hơn 0 và nhỏ hơn hoặc bằng 30.",
                modelView.getErrorMessage());
        }
        // test số ngày thuê phải lớn hơn không
        @Test
        public void testEditInvoiceSoNgayThueZero() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong",
                                "202", "3000", "Theo Ngày", null, "0");
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Số ngày thuê phải lớn hơn 0.", modelView.getErrorMessage());
        }
        // test số giờ thuê lớn hơn 0 và nhỏ hơn hoặc bằng 30
        @Test
        public void testEditInvoiceSoGioThueTooMany() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong",
                                "202", "3000", "Theo Giờ", "31", null);
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Số giờ thuê phải lớn hơn 0 và nhỏ hơn hoặc bằng 30.",
                modelView.getErrorMessage());
        }

        @Test
        public void testEditInvoiceSoNgayThue() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong",
                                "202", "3000", "Theo Ngày", null, "-1");
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Số ngày thuê phải lớn hơn 0.", modelView.getErrorMessage());
        }

        @Test
        public void testEditInvoiceDonGiaKhongPhaiSo() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong",
                                "202", "abc", "Theo Ngày", null, "5");
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Đơn giá phải là số hợp lệ.", modelView.getErrorMessage());
        }

        @Test
        public void testEditInvoiceMaPhongKhongHopLe() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong", "",
                                "3000", "Theo Ngày", null, "5");
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Mã phòng không được để trống.", modelView.getErrorMessage());
        }

        @Test
        public void testEditInvoiceSoGioThueKhongDuocDeTrong() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO("HD002", ngayHD, "Le Thi Phong",
                                "202", "3000", "Theo Giờ", null, null);
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Số giờ thuê không được để trống.", modelView.getErrorMessage());
        }

        @Test
        public void testEditInvoiceSoGioThueKhongPhaiSo() throws ClassNotFoundException, SQLException {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO(
                                "HD002", ngayHD, "Le Thi Phong", "202", "3000", "Theo Giờ", "abc", null);
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Số giờ thuê phải là số hợp lệ.",
                modelView.getErrorMessage());
        }

        @Test
        public void testEditInvoiceSoNgayThueKhongDuocDeTrong() throws ClassNotFoundException, SQLException {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 1);
                Date ngayHD = calendar.getTime();

                EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO(
                                "HD002", ngayHD, "Le Thi Phong", "202", "3000", "Theo Ngày", null, null);
                editInvoiceUseCase.execute(editInvoiceInputDTO);

                assertEquals("Số ngày thuê không được để trống.",
                modelView.getErrorMessage());
        }



}