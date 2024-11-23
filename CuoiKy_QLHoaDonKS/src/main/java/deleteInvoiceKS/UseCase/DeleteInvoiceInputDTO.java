package deleteInvoiceKS.UseCase;

public class DeleteInvoiceInputDTO {
    private String maHD;

    public DeleteInvoiceInputDTO(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHD() {
        return maHD;
    }
}
