package deleteInvoiceKS.UseCase;

public class DeleteInvoiceUseCase implements DeleteInvoiceInputBoundary {

    private DeleteInvoiceDatabaseBoundary deleteInvoiceDatabaseBoundary;
    private DeleteInvoiceOutputBoundary deleteInvoiceOutputBoundary;
    private DataMessageDelete dataMessage = null;

    public DeleteInvoiceUseCase(DeleteInvoiceDatabaseBoundary deleteInvoiceDatabaseBoundary,
            DeleteInvoiceOutputBoundary deleteInvoiceOutputBoundary, ResponseDataDelete responseData) {
        this.deleteInvoiceDatabaseBoundary = deleteInvoiceDatabaseBoundary;
        this.deleteInvoiceOutputBoundary = deleteInvoiceOutputBoundary;
        this.dataMessage = (DataMessageDelete) responseData;
    }

    @Override
    public void execute(DeleteInvoiceInputDTO deleteInvoiceInputDTO) {
        if (deleteInvoiceInputDTO.getMaHD().isEmpty()) {
            dataMessage.setErrorMessage("Vui lòng nhập mã hóa đơn.");
            dataMessage.setMessage(null);
            dataMessage.setConfirmMessage(null);
            deleteInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (!deleteInvoiceDatabaseBoundary.invoiceExists(deleteInvoiceInputDTO)) {
            dataMessage.setErrorMessage("Hóa đơn không tồn tại.");
            dataMessage.setMessage(null);
            dataMessage.setConfirmMessage(null);
            deleteInvoiceOutputBoundary.exportMessage(dataMessage);
            return;
        }

        if (!dataMessage.isConfirmationRequired()) {
            dataMessage.setConfirmMessage("Bạn có chắc chắn muốn xóa hóa đơn này?");
            dataMessage.setErrorMessage(null);
            dataMessage.setMessage(null);
            dataMessage.setConfirmationRequired(true);
            deleteInvoiceOutputBoundary.exportMessage(dataMessage);
        } else {
            boolean success = deleteInvoiceDatabaseBoundary.deleteInvoice(deleteInvoiceInputDTO);
            if (success) {
                dataMessage.setMessage("Xóa hóa đơn thành công");
                dataMessage.setErrorMessage(null);
                dataMessage.setConfirmMessage(null);
            } else {
                dataMessage.setErrorMessage("Lỗi khi xóa hóa đơn.");
                dataMessage.setMessage(null);
                dataMessage.setConfirmMessage(null);
            }
            dataMessage.setConfirmationRequired(false);
            deleteInvoiceOutputBoundary.exportMessage(dataMessage);
        }
    }

}
