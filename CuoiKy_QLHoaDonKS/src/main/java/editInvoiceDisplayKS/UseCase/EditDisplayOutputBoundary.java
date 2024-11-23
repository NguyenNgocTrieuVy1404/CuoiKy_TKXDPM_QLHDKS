package editInvoiceDisplayKS.UseCase;

import java.util.List;

public interface EditDisplayOutputBoundary {
    void presentType(List<InvoiceTypeDTO> listTypeInvoiceDTOs);

    void presentList(List<InvoiceListDTO> listInvoices);
}
