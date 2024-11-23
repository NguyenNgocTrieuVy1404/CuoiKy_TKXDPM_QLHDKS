package editInvoiceDisplayKS.UI;

import java.util.List;

public class EditViewModel {
    private List<String[]> invoiceList;
    private List<String[]> typeInvoices;
    private String Message;
    private String errorMessage;

    public List<String[]> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<String[]> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
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
