package quanLyHoaDon.UseCase;

import java.util.List;

public interface QuanLyOutputBoundary {
    void exportResult(List<InvoiceOuputDTO> listInvoices);
}
