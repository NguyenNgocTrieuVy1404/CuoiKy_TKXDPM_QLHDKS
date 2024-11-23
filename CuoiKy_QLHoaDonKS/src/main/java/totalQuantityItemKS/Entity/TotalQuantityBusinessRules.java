package totalQuantityItemKS.Entity;
import java.util.List;
import totalQuantityItemKS.UseCase.TotalQuantityOutPutDTO;

public interface TotalQuantityBusinessRules {
     List<TotalQuantityOutPutDTO> calculate(List<Invoice> invoices);
}
