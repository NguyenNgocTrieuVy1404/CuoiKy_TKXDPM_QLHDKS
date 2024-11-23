package getInvoiceType.UseCase;

import java.util.List;

public interface GetTypeOutputBoundary {

    void exportType(List<GetTypeOutputDTO> listTypes);

    List<GetTypeOutputDTO> getTypeInvoice();
}
