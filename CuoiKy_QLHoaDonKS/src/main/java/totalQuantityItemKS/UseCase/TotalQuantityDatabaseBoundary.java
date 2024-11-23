package totalQuantityItemKS.UseCase;

import java.util.List;

import totalQuantityItemKS.Entity.Invoice;

public interface TotalQuantityDatabaseBoundary {

    //public List<InvoiceExportData> getInvoiceList() throws SQLException;

    public List<Invoice> getTotalQuantityInvoices(TotalQuantityInputDTO totalQuantityInputDTO);
}
