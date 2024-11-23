package avgAmountItemKS.UseCase;

public class AverageAmountInputDTO {
    private String thangHD;

    public AverageAmountInputDTO(String monthText) {
        this.thangHD = monthText;
    }

    public String getThangHD() {
        return thangHD;
    }

}
