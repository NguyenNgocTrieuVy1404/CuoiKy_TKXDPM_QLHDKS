package findInvoiceDisplayKS.UI;


import java.util.List;

public class FindViewModel {
    private List<String[]> invoiceList;
    private String Message;
    private String errorMessage;
    private List<String> searchCriteria;

    public List<String[]> getAllTypeInvoiceList() {
        return invoiceList;
    }

    public void setAllTypeInvoiceList(List<String[]> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setSearchCriteria(List<String> criteria) {
        this.searchCriteria = criteria;
    }

    public List<String> getSearchCriteria() {
        return searchCriteria;
    }
}
