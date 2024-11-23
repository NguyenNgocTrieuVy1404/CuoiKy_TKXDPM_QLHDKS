package addInvoiceKS.UseCase;

import java.util.Date;

public class AddInvoiceInputDTO {
    private String maHD;
    private Date ngayHD;
    private String tenKH;
    private String maPhong;
    private String donGia; 
    private String loaiHoaDon;
    private String soGioThue;
    private String soNgayThue; 

    public AddInvoiceInputDTO(String maHD, Date ngayHD, String tenKH, String maPhong, String donGia, String loaiHoaDon,
            String soGioThue, String soNgayThue) {
        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.tenKH = tenKH;
        this.maPhong = maPhong;
        this.donGia = donGia;
        this.loaiHoaDon = loaiHoaDon;
        this.soGioThue = soGioThue;
        this.soNgayThue = soNgayThue;
    }

    public String getMaHD() {
        return maHD;
    }

    public Date getNgayHD() {
        return ngayHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getDonGia() {
        return donGia;
    }

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public String getSoGioThue() {
        return soGioThue;
    }

    public String getSoNgayThue() {
        return soNgayThue;
    }
}
