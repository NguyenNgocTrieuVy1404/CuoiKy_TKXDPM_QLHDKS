package totalQuantityItemDisplayKS.UI;

import totalQuantityItemKS.UseCase.TotalQuantityInputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityInputDTO;

public class TotalQuantityDisplayController {
    private TotalQuantityInputBoundary totalQuantityInputBoundary;

    public TotalQuantityDisplayController(TotalQuantityInputBoundary totalQuantityInputBoundary){
        this.totalQuantityInputBoundary=totalQuantityInputBoundary;
    }
    public void executeRequest(TotalQuantityInputDTO totalQuantityInputDTO){
        totalQuantityInputBoundary.execute(totalQuantityInputDTO);
    }
}
