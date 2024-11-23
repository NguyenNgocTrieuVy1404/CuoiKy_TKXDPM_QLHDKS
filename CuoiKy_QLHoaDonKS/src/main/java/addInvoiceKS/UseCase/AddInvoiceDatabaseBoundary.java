package addInvoiceKS.UseCase;

public interface AddInvoiceDatabaseBoundary {

    boolean addInvoiceDB(AddInvoiceInputDTO addInvoiceInputDTO) ;

    //public List<AddInvoiceOutputDTO> getInvoiceList() throws SQLException;

    boolean invoiceExists(AddInvoiceInputDTO addInvoiceInputDTO);
}
