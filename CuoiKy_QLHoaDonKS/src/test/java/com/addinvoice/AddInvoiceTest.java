package com.addinvoice;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import addInvoiceKS.Database.AddInvoiceDAOMySQL;
import addInvoiceKS.Database.ConnectionDB;
import addInvoiceDisplayKS.UI.AddDisplayPresenter;
import addInvoiceDisplayKS.UI.AddViewModel;
import addInvoiceKS.UseCase.AddInvoiceDatabaseBoundary;
import addInvoiceKS.UseCase.AddInvoiceInputBoundary;
import addInvoiceKS.UseCase.AddInvoiceInputDTO;
import addInvoiceKS.UseCase.AddInvoiceOutputBoundary;
import addInvoiceKS.UseCase.AddInvoiceUseCase;
import addInvoiceKS.UseCase.DataMessageAdd;
import addInvoiceKS.UseCase.ResponseDataAdd;

public class AddInvoiceTest {
        private AddViewModel modelView;
        private ConnectionDB connectionDB;
        private ResponseDataAdd dataMessage;
        private AddInvoiceDatabaseBoundary addInvoiceDatabaseBoundary;
        private AddInvoiceOutputBoundary addInvoiceOutputBoundary;
        private AddInvoiceInputBoundary addInvoiceUseCase;

        @Before
        public void setUp() throws ClassNotFoundException, SQLException {
                modelView = new AddViewModel();
                connectionDB = ConnectionDB.getInstance();
                dataMessage = new DataMessageAdd();
                addInvoiceDatabaseBoundary = new AddInvoiceDAOMySQL(connectionDB);
                addInvoiceOutputBoundary = new AddDisplayPresenter(modelView);
                addInvoiceUseCase = new AddInvoiceUseCase(addInvoiceDatabaseBoundary,
                                addInvoiceOutputBoundary, dataMessage);
        }

        // null hết
        @Test
        public void ErrorTest() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 19);
                Date ngayHD = calendar.getTime();

                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO(null, ngayHD, null, null, null, null,
                                null, null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Mã hóa đơn không được để trống.", modelView.getErrorMessage());
        }

        // Mã hóa đơn trống
        @Test
        public void ErrorTestMaHDEmpty() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();

                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO(null, ngayHD, "Vỹ", "P030", "100",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Mã hóa đơn không được để trống.", modelView.getErrorMessage());
        }

        // Hóa đơn đã tồn tại (trùng mã hóa đơn)
        @Test
        public void ErrorTestMaHDExists() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 9);
                Date ngayHD = calendar.getTime();

                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD001", ngayHD, null, null, null, null,
                                null, null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Hóa đơn đã tồn tại.", modelView.getErrorMessage());
        }

        // Ngày hóa đơn trống
        @Test
        public void ErrorTestNgayHDEmpty() throws ClassNotFoundException, SQLException {

                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", null, "Vỹ", "P030", "100",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Ngày hóa đơn không được để trống.", modelView.getErrorMessage());
        }

        // Ngày hóa đơn lập sau ngày hôm nay
        @Test
        public void ErrorTestNgayHDSauNgayLap() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 11);
                Date ngayHD = calendar.getTime();
                // test tên KH trống
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "100",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Ngày hóa đơn phải từ hôm nay trở đi.", modelView.getErrorMessage());
        }

        // Ngày hóa đơn vượt quá 7 ngày lập hóa đơn
        @Test
        public void ErrorTestNgayHD() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 29);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("112121", ngayHD, null, null, null, null,
                                null, null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Ngày hóa đơn không được vượt quá 7 ngày từ hôm nay.",
                                modelView.getErrorMessage());
        }

        // Tên khách hàng trống
        @Test
        public void ErrorTestTenKH() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, null, "P030", "100",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Tên khách hàng không được để trống.",
                                modelView.getErrorMessage());
        }

        // Mã phòng trống
        @Test
        public void ErrorTestMaPhong() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", null, "100",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Mã phòng không được để trống.",
                                modelView.getErrorMessage());
        }

        // Đơn giá trống
        @Test
        public void ErrorTestDonGia() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", null,
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Đơn giá không được để trống.",
                                modelView.getErrorMessage());
        }

        // Đơn giá bằng 0
        @Test
        public void ErrorTestDonGiaBang() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "0",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Đơn giá phải lớn hơn 0.",
                                modelView.getErrorMessage());
        }

        // Đơn giá nhỏ hơn 0
        @Test
        public void ErrorTestDonGiaNho() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "-1",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Đơn giá phải lớn hơn 0.",
                                modelView.getErrorMessage());
        }

        // Đơn giá phải là số
        @Test
        public void ErrorTestDonGiaSo() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "sad",
                                "Theo Giờ",
                                "22", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Đơn giá phải là số hợp lệ.",
                                modelView.getErrorMessage());
        }

        // Số giờ thuê trống
        @Test
        public void ErrorTestSoGioThue() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "199",
                                "Theo Giờ",
                                null, null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Số giờ thuê không được để trống.",
                                modelView.getErrorMessage());
        }

        // Số giờ thuê nhỏ hơn 1
        @Test
        public void ErrorTestSoGioThueNho() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "199",
                                "Theo Giờ",
                                "-1", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Số giờ thuê phải lớn hơn 0 và nhỏ hơn hoặc bằng 30.",
                                modelView.getErrorMessage());
        }

        // Số giờ thuê lớn hơn 30
        @Test
        public void ErrorTestSoGioThueLon() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "199",
                                "Theo Giờ",
                                "31", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Số giờ thuê phải lớn hơn 0 và nhỏ hơn hoặc bằng 30.",
                                modelView.getErrorMessage());
        }

        // Số giờ thuê không hợp lệ
        @Test
        public void ErrorTestSoGioThueKhongHopLe() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "199",
                                "Theo Giờ",
                                "sad", null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Số giờ thuê phải là số hợp lệ.",
                                modelView.getErrorMessage());
        }

        // Số ngày thuê trống
        @Test
        public void ErrorTestSoGioNgay() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "199",
                                "Theo Ngày",
                                null, null);

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Số ngày thuê không được để trống.",
                                modelView.getErrorMessage());
        }

        // Số ngày thuê nhỏ hơn 0
        @Test
        public void ErrorTestSoGioNgayNho() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "199",
                                "Theo Ngày",
                                null, "-1");

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Số ngày thuê phải lớn hơn 0.",
                                modelView.getErrorMessage());
        }

        // Số ngày thuê không hợp lệ
        @Test
        public void ErrorTestSoGioNgayKhongHopLe() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD030", ngayHD, "Vỹ", "P030", "199",
                                "Theo Ngày",
                                null, "sad");

                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Số ngày thuê phải là số hợp lệ.",
                                modelView.getErrorMessage());
        }

        // Thêm thành công
        @Test
        public void AddInvoiceTestOke() throws ClassNotFoundException, SQLException {

                Calendar calendar = Calendar.getInstance();
                calendar.set(2024, Calendar.NOVEMBER, 23);
                Date ngayHD = calendar.getTime();
                AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO("HD0PC", ngayHD, "ACD", "P001", "100",
                                "Theo Ngày", null, "5");
                addInvoiceUseCase.execute(addInvoiceInputDTO);

                assertEquals("Thêm hóa đơn thành công", modelView.getMessage());

        }

}
