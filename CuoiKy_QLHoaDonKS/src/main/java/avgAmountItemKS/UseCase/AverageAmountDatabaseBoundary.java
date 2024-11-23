package avgAmountItemKS.UseCase;

import java.util.List;

import avgAmountItemKS.Entity.Invoice;

public interface AverageAmountDatabaseBoundary {

    public List<Invoice> getAverageAmountInvoices(AverageAmountInputDTO averageAmountInputDTO);
}
