package totalQuantityItemKS.UseCase;

public class TotalQuantityInputDTO {
    private String loaiHD;

    public TotalQuantityInputDTO(String loaiHD) {
        this.loaiHD = loaiHD;
    }

    public String getLoaiHD() {
        return loaiHD;
    }

    public void setLoaiHD(String loaiHD) {
        this.loaiHD = loaiHD;
    }

}
