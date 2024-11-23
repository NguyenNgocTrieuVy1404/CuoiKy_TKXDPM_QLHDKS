package addInvoiceDisplayKS.UI;

import java.util.List;

public class AddViewModel {
    private List<String[]> typeInvoices;
    private String message;
    private String errorMessage;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String[]> getTypeInvoices() {
        return typeInvoices;
    }

    public void setTypeInvoices(List<String[]> typeInvoices) {
        this.typeInvoices = typeInvoices;
    }

}
