package avgAmountItemKS.UseCase;

public class AverageAmountOutPutDTO {
    private double tongSoTien = 0.0;
    private double trungBinhThanhTien = 0;

    public AverageAmountOutPutDTO() {
    }

    public void setTrungBinhThanhTien(double trungBinhThanhTien) {
        this.trungBinhThanhTien = trungBinhThanhTien;
    }

    public double getTongSoTien() {
        return tongSoTien;
    }

    public double getTrungBinhThanhTien() {
        return trungBinhThanhTien;
    }

    public void setTongSoTien(double tongSoTien) {
        this.tongSoTien = tongSoTien;
    }
}
