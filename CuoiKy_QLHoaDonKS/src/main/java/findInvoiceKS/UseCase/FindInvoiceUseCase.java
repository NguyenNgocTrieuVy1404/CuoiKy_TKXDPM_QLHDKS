package findInvoiceKS.UseCase;

import java.util.List;

import java.sql.SQLException;

public class FindInvoiceUseCase implements FindInvoiceInputBoundary {

    private FindInvoiceOutputBoundary findInvoiceOutputBoundary;
    private FindInvoiceDatabaseBoundary findInvoiceDatabaseBoundary;
    private DataMessageFind dataMessage;

    public FindInvoiceUseCase(FindInvoiceDatabaseBoundary findInvoiceDatabaseBoundary,
            FindInvoiceOutputBoundary findInvoiceOutputBoundary,
            ResponseDataFind dataMessage) {
        this.findInvoiceDatabaseBoundary = findInvoiceDatabaseBoundary;
        this.findInvoiceOutputBoundary = findInvoiceOutputBoundary;
        this.dataMessage = (DataMessageFind) dataMessage;
    }

    @Override
    public void execute(FindInvoiceInputDTO findInvoiceInputDTO) {
        try {
            if (findInvoiceInputDTO.getLuaChon().equals("Mã hoá đơn")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập mã hóa đơn.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.maHoaDonExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Mã hóa đơn không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo mã hóa đơn.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getMaHoaDon(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);
            }

            if (findInvoiceInputDTO.getLuaChon().equals("Ngày hoá đơn")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập ngày hóa đơn.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.ngayHoaDonExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Ngày hóa đơn không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo ngày hóa đơn.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getNgayHoaDon(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);
            }

            if (findInvoiceInputDTO.getLuaChon().equals("Tên khách hàng")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập tên khách hàng.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.tenKhachHangExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Tên khách hàng không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo tên khách hàng.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary
                        .getTenKhachHang(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);

            }

            if (findInvoiceInputDTO.getLuaChon().equals("Mã phòng")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập mã phòng.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.maPhongExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Mã phòng không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo mã phòng.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getMaPhong(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);
            }

            if (findInvoiceInputDTO.getLuaChon().equals("Đơn giá")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập đơn giá.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.donGiaExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Đơn giá không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo đơn giá.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getDonGia(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);

            }

            if (findInvoiceInputDTO.getLuaChon().equals("Loại hoá đơn")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập loại hóa đơn.\n  *(Theo giờ, Theo ngày)");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.loaiHoaDonExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Loại hóa đơn không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo loại hóa đơn.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getLoaiHoaDon(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);
            }

            if (findInvoiceInputDTO.getLuaChon().equals("Số giờ thuê")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập số giờ thuê.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.soGioThueExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Số giờ thuê không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo số giờ thuê.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getSoGioThue(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);
            }

            if (findInvoiceInputDTO.getLuaChon().equals("Số ngày thuê")) {
                if (findInvoiceInputDTO.getGiaTri().equals("")) {
                    dataMessage.setErrorMessage("Vui lòng nhập số ngày thuê.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }

                if (!findInvoiceDatabaseBoundary.soNgayThueExists(findInvoiceInputDTO)) {
                    dataMessage.setErrorMessage("Số ngày thuê không tồn tại.");
                    dataMessage.setMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                } else {
                    dataMessage.setMessage("Đã tìm thấy hóa đơn theo số ngày thuê.");
                    dataMessage.setErrorMessage(null);
                    findInvoiceOutputBoundary.exportMessage(dataMessage);
                }

                List<FindInvoiceOutPutDTO> invoiceList = findInvoiceDatabaseBoundary.getSoNgayThue(findInvoiceInputDTO);
                findInvoiceDatabaseBoundary.getSoNgayThue(findInvoiceInputDTO);
                findInvoiceOutputBoundary.exportResult(invoiceList);
            }

        } catch (SQLException e) {
            dataMessage.setErrorMessage("Lỗi khi truy xuất dữ liệu hóa đơn từ cơ sở dữ liệu.");
            dataMessage.setMessage(null);
            findInvoiceOutputBoundary.exportMessage(dataMessage);
            e.printStackTrace();
        }
    }
}
