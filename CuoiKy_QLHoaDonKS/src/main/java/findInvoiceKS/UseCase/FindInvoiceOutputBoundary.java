package findInvoiceKS.UseCase;

import java.util.List;

public interface FindInvoiceOutputBoundary {

    void exportResult(List<FindInvoiceOutPutDTO> list);

    void exportMessage(ResponseDataFind responseData);
}
