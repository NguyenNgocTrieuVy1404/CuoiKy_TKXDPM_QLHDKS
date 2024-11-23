package addInvoiceKS.UseCase;

import java.util.Date;

public class AddInvoiceOutputDTO {
    private String maHD;
    private Date ngayHD;
    private String tenKH;
    private String maPhong;
    private double donGia;
    private String loaiHoaDon;
    private Integer soGioThue;
    private Integer soNgayThue;

    public AddInvoiceOutputDTO(String maHD, Date ngayHD, String tenKH, String maPhong, double donGia, String loaiHoaDon,
            Integer soGioThue, Integer soNgayThue) {
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public Integer getSoGioThue() {
        return soGioThue;
    }

    public void setSoGioThue(Integer soGioThue) {
        this.soGioThue = soGioThue;
    }

    public Integer getSoNgayThue() {
        return soNgayThue;
    }

    public void setSoNgayThue(Integer soNgayThue) {
        this.soNgayThue = soNgayThue;
    }
}
