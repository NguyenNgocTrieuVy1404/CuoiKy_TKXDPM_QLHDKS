package editInvoiceKS.UseCase;

import java.util.Date;

public class EditInvoiceInputDTO {
    private String maHD;
    private Date ngayHD;
    private String tenKH;
    private String maPhong;
    private String donGia;
    private String loaiHoaDon;
    private String soGioThue;
    private String soNgayThue;

    public EditInvoiceInputDTO(String maHD, Date ngayHD, String tenKH, String maPhong, String donGia, String loaiHoaDon,
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

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(Date ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public String getSoGioThue() {
        return soGioThue;
    }

    public void setSoGioThue(String soGioThue) {
        this.soGioThue = soGioThue;
    }

    public String getSoNgayThue() {
        return soNgayThue;
    }

    public void setSoNgayThue(String soNgayThue) {
        this.soNgayThue = soNgayThue;
    }

    
}
