package editInvoiceDisplayKS.UI;

import java.sql.SQLException;

import editInvoiceDisplayKS.UseCase.EditDisplayInputBoundary;
import editInvoiceKS.UseCase.EditInvoiceInputBoundary;
import editInvoiceKS.UseCase.EditInvoiceInputDTO;

public class EdittDisplayController {

    private EditInvoiceInputBoundary editInvoiceInputBoundary;
    private EditDisplayInputBoundary editDisplayInputBoundary;

    public EdittDisplayController(EditInvoiceInputBoundary editInvoiceInputBoundary) {
        this.editInvoiceInputBoundary = editInvoiceInputBoundary;
    }
    
    public void setEditDisplayInputBoundary(EditDisplayInputBoundary editDisplayInputBoundary) {
        this.editDisplayInputBoundary = editDisplayInputBoundary;
    }

    public void executeRequest(EditInvoiceInputDTO editInvoiceInputDTO) {
        editInvoiceInputBoundary.execute(editInvoiceInputDTO);
    }

    public void executeGetInvoice() throws SQLException {
        editDisplayInputBoundary.execute();
    }
}