package deleteInvoiceDisplayKS.UseCase;

public class DeleteInvoiceOutputDTO {
    private String[] listMaHD;

    public DeleteInvoiceOutputDTO(String[] listMaHD) {
        this.listMaHD = listMaHD;
    }

    public String[] getListMaHD() {
        return listMaHD;
    }

    public void setListMaHD(String[] listMaHD) {
        this.listMaHD = listMaHD;
    }
}
