package getAllInvoiceList.UseCase;

import java.util.List;

public class GetInvoiceListUseCase implements GetInvoiceListInputBoundary {
    private GetInvoiceListDatabaseBoundary getInvoiceListDatabaseBoundary = null;
    private GetInvoiceListOutputBoundary getInvoiceOutputBoundary;

    public GetInvoiceListUseCase(GetInvoiceListDatabaseBoundary getInvoiceListDatabaseBoundary,
            GetInvoiceListOutputBoundary getInvoiceOutputBoundary) {
        this.getInvoiceListDatabaseBoundary = getInvoiceListDatabaseBoundary;
        this.getInvoiceOutputBoundary = getInvoiceOutputBoundary;
    }

    @Override
    public void execute() {
        List<GetInvoiceOuputDTO> listInvoices = getInvoiceListDatabaseBoundary.getAllInvoiceList();
        getInvoiceOutputBoundary.presentList(listInvoices);
    }
}
