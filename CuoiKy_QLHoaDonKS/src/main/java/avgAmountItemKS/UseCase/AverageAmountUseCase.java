package avgAmountItemKS.UseCase;

import java.util.List;

import avgAmountItemKS.Entity.AverageAmount;
import avgAmountItemKS.Entity.Invoice;

public class AverageAmountUseCase implements AverageAmountInputBoundary {
    private AverageAmountOutputBoundary averageAmountOutputBoundary;
    private AverageAmountDatabaseBoundary averageAmountDatabaseBoundary;
    private AverageAmount averageAmount;

    public AverageAmountUseCase(AverageAmountOutputBoundary averageAmountOutputBoundary,
            AverageAmountDatabaseBoundary averageAmountDatabaseBoundary, AverageAmount averageAmount) {
        this.averageAmountOutputBoundary = averageAmountOutputBoundary;
        this.averageAmountDatabaseBoundary = averageAmountDatabaseBoundary;
        this.averageAmount = averageAmount;
    }

    @Override
    public void execute(AverageAmountInputDTO averageAmountInputDTO) {
        List<Invoice> invoices = averageAmountDatabaseBoundary.getAverageAmountInvoices(averageAmountInputDTO);
        List<AverageAmountOutPutDTO> listOut = averageAmount.calculate(invoices);
        averageAmountOutputBoundary.present(listOut);
    }
}
