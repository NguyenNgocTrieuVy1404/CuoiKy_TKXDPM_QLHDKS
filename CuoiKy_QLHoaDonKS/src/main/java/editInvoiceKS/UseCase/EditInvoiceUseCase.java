package editInvoiceKS.UseCase;

import java.util.Calendar;
import java.util.Date;

public class EditInvoiceUseCase implements EditInvoiceInputBoundary {
    private EditInvoiceDatabaseBoundary editInvoiceDatabaseBoundary;
    private EditInvoiceOutputBoundary editInvoiceOutputBoundary;
    private DataMessageEdit dataMessage;

    public EditInvoiceUseCase(EditInvoiceDatabaseBoundary editInvoiceDatabaseBoundary,
            EditInvoiceOutputBoundary editInvoiceOutputBoundary,
            ResponseDataEdit dataMessage) {
        this.editInvoiceDatabaseBoundary = editInvoiceDatabaseBoundary;
        this.editInvoiceOutputBoundary = editInvoiceOutputBoundary;
        this.dataMessage = (DataMessageEdit) dataMessage;
    }

    @Override
    public void execute(EditInvoiceInputDTO editInvoiceInputDTO) {
        if (editInvoiceInputDTO.getMaHD() == null || editInvoiceInputDTO.getMaHD().trim().isEmpty()) {
            dataMessage.setErrorMessage("Vui lòng chọn hóa đơn cần sửa");
            dataMessage.setMessage(null);
            editInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (editInvoiceInputDTO.getNgayHD() == null) {
            dataMessage.setErrorMessage("Ngày hóa đơn không được để trống.");
            dataMessage.setMessage(null);
            editInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.DAY_OF_YEAR, 7);
        maxDate.set(Calendar.HOUR_OF_DAY, 0);
        maxDate.set(Calendar.MINUTE, 0);
        maxDate.set(Calendar.SECOND, 0);
        maxDate.set(Calendar.MILLISECOND, 0);

        Date ngayHD = editInvoiceInputDTO.getNgayHD();

        if (ngayHD.after(maxDate.getTime())) {
            dataMessage.setErrorMessage("Ngày hóa đơn không được vượt quá 7 ngày từ hôm nay.");
            dataMessage.setMessage(null);
            editInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }
        if (editInvoiceInputDTO.getTenKH() == null || editInvoiceInputDTO.getTenKH().trim().isEmpty()) {
            dataMessage.setErrorMessage("Tên khách hàng không được để trống.");
            dataMessage.setMessage(null);
            editInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (editInvoiceInputDTO.getMaPhong() == null || editInvoiceInputDTO.getMaPhong().trim().isEmpty()) {
            dataMessage.setErrorMessage("Mã phòng không được để trống.");
            dataMessage.setMessage(null);
            editInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        try {
            double donGia = Double.parseDouble(editInvoiceInputDTO.getDonGia());
            if (donGia <= 0) {
                dataMessage.setErrorMessage("Đơn giá phải lớn hơn 0.");
                dataMessage.setMessage(null);
                editInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }
        } catch (NumberFormatException e) {
            dataMessage.setErrorMessage("Đơn giá phải là số hợp lệ.");
            dataMessage.setMessage(null);
            editInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (editInvoiceInputDTO.getLoaiHoaDon() == null || editInvoiceInputDTO.getLoaiHoaDon().trim().isEmpty()) {
            dataMessage.setErrorMessage("Loại hóa đơn không được để trống.");
            dataMessage.setMessage(null);
            editInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (editInvoiceInputDTO.getLoaiHoaDon().equals("Theo Giờ")) {
            if (editInvoiceInputDTO.getSoGioThue() == null || editInvoiceInputDTO.getSoGioThue().trim().isEmpty()) {
                dataMessage.setErrorMessage("Số giờ thuê không được để trống.");
                dataMessage.setMessage(null);
                editInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }

            try {
                int soGioThue = Integer.parseInt(editInvoiceInputDTO.getSoGioThue());
                if (soGioThue < 1 || soGioThue > 30) {
                    dataMessage.setErrorMessage("Số giờ thuê phải lớn hơn 0 và nhỏ hơn hoặc bằng 30.");
                    dataMessage.setMessage(null);
                    editInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }
            } catch (NumberFormatException e) {
                dataMessage.setErrorMessage("Số giờ thuê phải là số hợp lệ.");
                dataMessage.setMessage(null);
                editInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }

        } else if (editInvoiceInputDTO.getLoaiHoaDon().equals("Theo Ngày")) {
            if (editInvoiceInputDTO.getSoNgayThue() == null || editInvoiceInputDTO.getSoNgayThue().trim().isEmpty()) {
                dataMessage.setErrorMessage("Số ngày thuê không được để trống.");
                dataMessage.setMessage(null);
                editInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }

            try {
                int soNgayThue = Integer.parseInt(editInvoiceInputDTO.getSoNgayThue());
                if (soNgayThue < 1) {
                    dataMessage.setErrorMessage("Số ngày thuê phải lớn hơn 0.");
                    dataMessage.setMessage(null);
                    editInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }
            } catch (NumberFormatException e) {
                dataMessage.setErrorMessage("Số ngày thuê phải là số hợp lệ.");
                dataMessage.setMessage(null);
                editInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }
        }

        boolean success = editInvoiceDatabaseBoundary.editInvoiceDB(editInvoiceInputDTO);
        if (success) {
            dataMessage.setMessage("Sửa hóa đơn thành công");
            dataMessage.setErrorMessage(null);
        } else {
            dataMessage.setErrorMessage("Lỗi khi sửa hóa đơn.");
            dataMessage.setMessage(null);
        }
        editInvoiceOutputBoundary.exportMessage(dataMessage);
    }
}
