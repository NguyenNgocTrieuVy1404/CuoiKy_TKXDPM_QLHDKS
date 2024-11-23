package addInvoiceDisplayKS.UI;

import addInvoiceKS.UseCase.AddInvoiceInputBoundary;
import addInvoiceKS.UseCase.AddInvoiceInputDTO;

public class AddDisplayController {
    private AddInvoiceInputBoundary addInvoiceInputBoundary;

    public AddDisplayController(AddInvoiceInputBoundary addInvoiceInputBoundary) {
        this.addInvoiceInputBoundary = addInvoiceInputBoundary;
    }

    public void executeRequest(AddInvoiceInputDTO addInvoiceInputDTO) {
        addInvoiceInputBoundary.execute(addInvoiceInputDTO);
    }
}
