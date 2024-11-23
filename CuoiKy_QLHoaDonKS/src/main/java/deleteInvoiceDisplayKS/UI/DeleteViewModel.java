package deleteInvoiceDisplayKS.UI;

public class DeleteViewModel {
    private String message;
    private String errorMessage;
    private String confirmMesage;
    private String[] invoiceIds;

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

    public String getConfirmMessage() {
        return confirmMesage;
    }

    public void setConfirmMessage(String confirmMesage) {
        this.confirmMesage = confirmMesage;
    }

    public void setInvoiceIds(String[] invoiceIds) {
        this.invoiceIds = invoiceIds;
    }

    public String[] getInvoiceIds() {
        return invoiceIds;
    }
}
