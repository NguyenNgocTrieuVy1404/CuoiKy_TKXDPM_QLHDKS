package totalQuantityItemKS.Entity;

import java.util.Date;

public class InvoiceHour extends Invoice {
    private int soGioThue;

    public InvoiceHour(String maHD, Date ngayHD, String tenKH, String maPhong, double donGia, int soGioThue) {
        super(maHD, ngayHD, tenKH, maPhong, "Theo Giờ", donGia);
        this.soGioThue = soGioThue;
    }

    @Override
    public double tinhThanhTien() {
        if (soGioThue > 24 && soGioThue < 30) {
            return 24 * getDonGia();
        } else {
            return soGioThue * getDonGia();
        }
    }
    public int getSoLuong() {
        return soGioThue;
    }
}
