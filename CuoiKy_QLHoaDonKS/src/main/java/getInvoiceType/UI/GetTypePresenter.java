package getInvoiceType.UI;

import java.util.List;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeOutputDTO;

public class GetTypePresenter implements GetTypeOutputBoundary {
    private List<GetTypeOutputDTO> listTypes;

    @Override
    public void exportType(List<GetTypeOutputDTO> listTypes) {
        this.listTypes = listTypes;
    }

    @Override
    public List<GetTypeOutputDTO> getTypeInvoice() {
        return this.listTypes;
    }
}