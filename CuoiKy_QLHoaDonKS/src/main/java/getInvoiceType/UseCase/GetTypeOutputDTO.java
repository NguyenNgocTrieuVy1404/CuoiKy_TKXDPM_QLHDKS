package getInvoiceType.UseCase;

public class GetTypeOutputDTO {
    String loaiHoaDon;

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public GetTypeOutputDTO(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }
}
