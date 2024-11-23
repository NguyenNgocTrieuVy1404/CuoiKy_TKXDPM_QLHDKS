package totalQuantityItemKS.UseCase;

import java.util.List;

import totalQuantityItemKS.Entity.Invoice;
import totalQuantityItemKS.Entity.TotalQuantityBusinessRules;

public class TotalQuantityUseCase implements TotalQuantityInputBoundary {

    private TotalQuantityOutputBoundary totalQuantityOutputBoundary;
    private TotalQuantityDatabaseBoundary totalQuantityDatabaseBoundary;
    private TotalQuantityBusinessRules totalQuantityBusinessRules;

    public TotalQuantityUseCase(TotalQuantityOutputBoundary totalQuantityOutputBoundary,
            TotalQuantityDatabaseBoundary totalQuantityDatabaseBoundary,
            TotalQuantityBusinessRules totalQuantityBusinessRules) {
        this.totalQuantityOutputBoundary = totalQuantityOutputBoundary;
        this.totalQuantityDatabaseBoundary = totalQuantityDatabaseBoundary;
        this.totalQuantityBusinessRules = totalQuantityBusinessRules;
    }
    public void setTotalQuantityBusinessRules(TotalQuantityBusinessRules totalQuantityBusinessRules) {
        this.totalQuantityBusinessRules = totalQuantityBusinessRules;
    }

    @Override
    public void execute(TotalQuantityInputDTO totalQuantityInputDTO) {
        List<Invoice> invoices = totalQuantityDatabaseBoundary.getTotalQuantityInvoices(totalQuantityInputDTO);
        List<TotalQuantityOutPutDTO> summaryList = totalQuantityBusinessRules.calculate(invoices);
        totalQuantityOutputBoundary.present(summaryList);
    }
}
