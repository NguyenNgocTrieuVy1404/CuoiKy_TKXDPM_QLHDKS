package avgAmountItemKS.Entity;
import java.util.List;

import avgAmountItemKS.UseCase.AverageAmountOutPutDTO;


public interface AverageAmount {
     List<AverageAmountOutPutDTO> calculate(List<Invoice> invoices);
}
