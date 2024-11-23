package avgAmountDisplayKS.UI;


import avgAmountItemKS.UseCase.AverageAmountInputBoundary;
import avgAmountItemKS.UseCase.AverageAmountInputDTO;

public class AvgAmountDisplayController {
    private AverageAmountInputBoundary averageAmountInputBoundary;

    public AvgAmountDisplayController(
            AverageAmountInputBoundary averageAmountInputBoundary) {
        this.averageAmountInputBoundary = averageAmountInputBoundary;
    }

    public void executeRequest(AverageAmountInputDTO averageAmountInputDTO) {
        averageAmountInputBoundary.execute(averageAmountInputDTO);
    }

}
