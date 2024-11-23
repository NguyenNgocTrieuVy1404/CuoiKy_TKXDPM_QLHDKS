package deleteInvoiceKS.UseCase;

public class DataMessageDelete implements ResponseDataDelete {
    private String confirmMessage;
    private String message = null;  
    private String errorMessage = null;
    private boolean confirmationRequired = false;  
    public String getMessage() {
        return message; 
    }

    public String getErrorMessage() {
        return errorMessage;  
    }

    public String getConfirmMessage() {
        return confirmMessage;
    }

    public void setConfirmMessage(String confirmMessage) {
        this.confirmMessage = confirmMessage;
    }

    public boolean isConfirmationRequired() {
        return confirmationRequired;  
    }

    public void setConfirmationRequired(boolean confirmationRequired) {
        this.confirmationRequired = confirmationRequired;  
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;  
    }

    public void setMessage(String message) {
        this.message = message;  
    }
}
