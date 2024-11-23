package getAllInvoiceList.UseCase;

import java.util.List;

public interface GetInvoiceListOutputBoundary {
    void presentList(List<GetInvoiceOuputDTO> listInvoices);

    List<GetInvoiceOuputDTO> getInvoiceList();
}
