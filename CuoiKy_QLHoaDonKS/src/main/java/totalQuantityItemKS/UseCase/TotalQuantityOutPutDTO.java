package totalQuantityItemKS.UseCase;

public class TotalQuantityOutPutDTO {
    private String loaiHoaDon;
    private double tongSoLuong = 0;
    private double tongSoTien = 0.0;

    public TotalQuantityOutPutDTO(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public double getTongSoLuong() {
        return tongSoLuong;
    }

    public double getTongSoTien() {
        return tongSoTien;
    }

    public void setTongSoLuong(double tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public void setTongSoTien(double tongSoTien) {
        this.tongSoTien = tongSoTien;
    }
}
