package findInvoiceDisplayKS.UseCase;

public class FindDisplayOutputDTO {

    String loaiHoaDon;
    

    public FindDisplayOutputDTO(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }


    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }
    
}
