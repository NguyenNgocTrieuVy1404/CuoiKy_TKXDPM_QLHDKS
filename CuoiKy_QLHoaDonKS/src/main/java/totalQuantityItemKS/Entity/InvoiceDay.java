package totalQuantityItemKS.Entity;

import java.util.Date;

public class InvoiceDay extends Invoice {
    private int soNgayThue;

    public InvoiceDay(String maHD, Date ngayHD, String tenKH, String maPhong, double donGia, int soNgayThue) {
        super(maHD, ngayHD, tenKH, maPhong, "Theo Ngày", donGia);
        this.soNgayThue = soNgayThue;
    }

    @Override
    public double tinhThanhTien() {
        if (soNgayThue <= 7) {
            return soNgayThue * getDonGia();
        } else {
            int ngayGiamGia = soNgayThue - 7;
            double giaGiam = getDonGia() * 0.8;
            return (7 * getDonGia()) + (ngayGiamGia * giaGiam);
        }
    }

    public int getSoLuong() {
        return soNgayThue;
    }

}
