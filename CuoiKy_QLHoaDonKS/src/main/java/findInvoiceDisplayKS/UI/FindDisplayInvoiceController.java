package findInvoiceDisplayKS.UI;


import findInvoiceKS.UseCase.FindInvoiceInputBoundary;
import findInvoiceKS.UseCase.FindInvoiceInputDTO;

public class FindDisplayInvoiceController {

    private FindInvoiceInputBoundary findInvoiceInputBoundary = null;

    public FindDisplayInvoiceController(FindInvoiceInputBoundary findInvoiceInputBoundary) {
        this.findInvoiceInputBoundary = findInvoiceInputBoundary;
    }

    public void executeRequest(FindInvoiceInputDTO findInvoiceInputDTO) {
        findInvoiceInputBoundary.execute(findInvoiceInputDTO);
    }
}
