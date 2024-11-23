package deleteInvoiceDisplayKS.UI;

import deleteInvoiceKS.UseCase.DeleteInvoiceInputBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceInputDTO;

public class DeleteDisplayController {
    private DeleteInvoiceInputBoundary deleteInvoiceInputBoundary;

    public DeleteDisplayController(DeleteInvoiceInputBoundary deleteInvoiceInputBoundary) {
        this.deleteInvoiceInputBoundary = deleteInvoiceInputBoundary;
    }

    public void executeRequest(DeleteInvoiceInputDTO deleteInvoiceInputDTO) {
        deleteInvoiceInputBoundary.execute(deleteInvoiceInputDTO);
    }
}
