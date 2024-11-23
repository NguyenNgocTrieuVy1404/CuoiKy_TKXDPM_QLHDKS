package getAllInvoiceList.UI;

import java.util.List;

import getAllInvoiceList.UseCase.GetInvoiceListOutputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceOuputDTO;

public class GetInvoiceListPresenter implements GetInvoiceListOutputBoundary {
    List<GetInvoiceOuputDTO> listInvoices;

    @Override
    public void presentList(List<GetInvoiceOuputDTO> listInvoices) {
        this.listInvoices = listInvoices;
    }

    @Override
    public List<GetInvoiceOuputDTO> getInvoiceList() {
        return this.listInvoices;
    }

}
