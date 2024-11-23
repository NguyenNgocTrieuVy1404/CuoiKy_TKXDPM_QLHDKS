package quanLyHoaDon.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import quanLyHoaDon.UseCase.InvoiceOuputDTO;
import quanLyHoaDon.UseCase.QuanLyOutputBoundary;

public class QuanLyPresenter extends Observable implements QuanLyOutputBoundary {
    private QuanLyViewModel quanLyViewModel;

    public QuanLyPresenter(QuanLyViewModel quanLyViewModel) {
        this.quanLyViewModel = quanLyViewModel;
    }

    @Override
    public void exportResult(List<InvoiceOuputDTO> listInvoices) {
        List<String[]> invoiceList = new ArrayList<>();

        for (InvoiceOuputDTO dto : listInvoices) {
            String[] data = {
                    dto.getMaHD(),
                    dto.getNgayHD().toString(),
                    dto.getTenKH(),
                    dto.getMaPhong(),
                    String.valueOf(dto.getDonGia()),
                    dto.getLoaiHoaDon(),
                    dto.getSoGioThue() != null ? String.valueOf(dto.getSoGioThue()) : "",
                    dto.getSoNgayThue() != null ? String.valueOf(dto.getSoNgayThue()) : ""
            };
            invoiceList.add(data);
        }

        quanLyViewModel.setListInvoices(invoiceList);
        setChanged();
        notifyObservers("GETLIST");
    }
}
