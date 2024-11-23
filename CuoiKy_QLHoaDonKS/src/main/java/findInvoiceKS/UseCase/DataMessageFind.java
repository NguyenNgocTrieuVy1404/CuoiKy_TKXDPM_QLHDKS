package findInvoiceKS.UseCase;

public class DataMessageFind implements ResponseDataFind {
    private String Message = null;
    private String errorMessage = null;

    public String getMessage() {
        return Message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
