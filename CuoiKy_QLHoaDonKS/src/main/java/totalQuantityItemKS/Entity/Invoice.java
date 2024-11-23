package totalQuantityItemKS.Entity;

import java.util.Date;

public abstract class Invoice {
    private String maHD;
    private Date ngayHD;
    private String tenKH;
    private String maPhong;
    private String loaiHoaDon;
    private double donGia;

    public Invoice(String maHD, Date ngayHD, String tenKH, String maPhong, String loaiHoaDon, double donGia) {
        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.tenKH = tenKH;
        this.maPhong = maPhong;
        this.loaiHoaDon = loaiHoaDon;
        this.donGia = donGia;
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

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public double getDonGia() {
        return donGia;
    }

    public abstract double tinhThanhTien();

    public abstract int getSoLuong();
}
