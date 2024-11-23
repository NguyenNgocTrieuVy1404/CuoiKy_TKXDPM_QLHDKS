package deleteInvoiceKS.UseCase;

public interface DeleteInvoiceDatabaseBoundary {
    boolean deleteInvoice(DeleteInvoiceInputDTO deleteInvoiceInputDTO);

    boolean invoiceExists(DeleteInvoiceInputDTO deleteInvoiceInputDTO);
}
