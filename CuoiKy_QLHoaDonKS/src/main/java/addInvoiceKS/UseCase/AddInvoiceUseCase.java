package addInvoiceKS.UseCase;

import java.util.Calendar;
import java.util.Date;

public class AddInvoiceUseCase implements AddInvoiceInputBoundary {
    private AddInvoiceDatabaseBoundary addInvoiceDatabaseBoundary;
    private AddInvoiceOutputBoundary addInvoiceOutputBoundary;
    private DataMessageAdd dataMessage = null;

    public AddInvoiceUseCase(AddInvoiceDatabaseBoundary addInvoiceDatabaseBoundary,
            AddInvoiceOutputBoundary addInvoiceOutputBoundary, ResponseDataAdd dataMessage) {
        this.addInvoiceDatabaseBoundary = addInvoiceDatabaseBoundary;
        this.addInvoiceOutputBoundary = addInvoiceOutputBoundary;
        this.dataMessage = (DataMessageAdd) dataMessage;
    }

    @Override
    public void execute(AddInvoiceInputDTO addInvoiceInputDTO) {

        if (addInvoiceInputDTO.getMaHD() == null || addInvoiceInputDTO.getMaHD().trim().isEmpty()) {
            dataMessage.setErrorMessage("Mã hóa đơn không được để trống.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (addInvoiceDatabaseBoundary.invoiceExists(addInvoiceInputDTO)) {
            dataMessage.setErrorMessage("Hóa đơn đã tồn tại.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (addInvoiceInputDTO.getNgayHD() == null) {
            dataMessage.setErrorMessage("Ngày hóa đơn không được để trống.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
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

        Date ngayHD = addInvoiceInputDTO.getNgayHD();

        if (ngayHD.before(today.getTime())) {
            dataMessage.setErrorMessage("Ngày hóa đơn phải từ hôm nay trở đi.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (ngayHD.after(maxDate.getTime())) {
            dataMessage.setErrorMessage("Ngày hóa đơn không được vượt quá 7 ngày từ hôm nay.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (addInvoiceInputDTO.getTenKH() == null || addInvoiceInputDTO.getTenKH().trim().isEmpty()) {
            dataMessage.setErrorMessage("Tên khách hàng không được để trống.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (addInvoiceInputDTO.getMaPhong() == null || addInvoiceInputDTO.getMaPhong().trim().isEmpty()) {
            dataMessage.setErrorMessage("Mã phòng không được để trống.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (addInvoiceInputDTO.getDonGia() == null || addInvoiceInputDTO.getDonGia().trim().isEmpty()) {
            dataMessage.setErrorMessage("Đơn giá không được để trống.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        try {
            double donGia = Double.parseDouble(addInvoiceInputDTO.getDonGia());
            if (donGia <= 0) {
                dataMessage.setErrorMessage("Đơn giá phải lớn hơn 0.");
                dataMessage.setMessage(null);
                addInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }
        } catch (NumberFormatException e) {
            dataMessage.setErrorMessage("Đơn giá phải là số hợp lệ.");
            dataMessage.setMessage(null);
            addInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (addInvoiceInputDTO.getLoaiHoaDon().equals("Theo Giờ")) {
            if (addInvoiceInputDTO.getSoGioThue() == null || addInvoiceInputDTO.getSoGioThue().trim().isEmpty()) {
                dataMessage.setErrorMessage("Số giờ thuê không được để trống.");
                dataMessage.setMessage(null);
                addInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }

            try {
                int soGioThue = Integer.parseInt(addInvoiceInputDTO.getSoGioThue());
                if (soGioThue < 1 || soGioThue > 30) {
                    dataMessage.setErrorMessage("Số giờ thuê phải lớn hơn 0 và nhỏ hơn hoặc bằng 30.");
                    dataMessage.setMessage(null);
                    addInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }
            } catch (NumberFormatException e) {
                dataMessage.setErrorMessage("Số giờ thuê phải là số hợp lệ.");
                dataMessage.setMessage(null);
                addInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }

        } else if (addInvoiceInputDTO.getLoaiHoaDon().equals("Theo Ngày")) {
            if (addInvoiceInputDTO.getSoNgayThue() == null || addInvoiceInputDTO.getSoNgayThue().trim().isEmpty()) {
                dataMessage.setErrorMessage("Số ngày thuê không được để trống.");
                dataMessage.setMessage(null);
                addInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }

            try {
                int soNgayThue = Integer.parseInt(addInvoiceInputDTO.getSoNgayThue());
                if (soNgayThue < 1) {
                    dataMessage.setErrorMessage("Số ngày thuê phải lớn hơn 0.");
                    dataMessage.setMessage(null);
                    addInvoiceOutputBoundary.exportMessage(dataMessage);
                    return;
                }
            } catch (NumberFormatException e) {
                dataMessage.setErrorMessage("Số ngày thuê phải là số hợp lệ.");
                dataMessage.setMessage(null);
                addInvoiceOutputBoundary.exportMessage(dataMessage);
                return;
            }
        }

        boolean success = addInvoiceDatabaseBoundary.addInvoiceDB(addInvoiceInputDTO);
        if (success) {
            dataMessage.setMessage("Thêm hóa đơn thành công");
            dataMessage.setErrorMessage(null);
        } else {
            dataMessage.setErrorMessage("Thêm hóa đơn thất bại");
            dataMessage.setMessage(null);
        }

        dataMessage.setErrorMessage(null);
        addInvoiceOutputBoundary.exportMessage(dataMessage);
    }
}
