package deleteInvoiceDisplayKS.UseCase;

import java.util.List;

public interface DeleteDisplayOutputBoundary {
    void presentInvoiceIds(List<DeleteInvoiceOutputDTO> listOutput);
} 