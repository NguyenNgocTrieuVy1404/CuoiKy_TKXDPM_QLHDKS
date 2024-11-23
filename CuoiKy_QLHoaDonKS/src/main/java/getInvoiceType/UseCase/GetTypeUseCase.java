package getInvoiceType.UseCase;

import java.util.List;

public class GetTypeUseCase implements GetTypeInputBoundary {
    private GetTypeDatabaseBoundary getTypeDatabaseBoundary;
    private GetTypeOutputBoundary getTypeOutputBoundary;

    public GetTypeUseCase(GetTypeDatabaseBoundary getTypeDatabaseBoundary,
            GetTypeOutputBoundary getTypeOutputBoundary) {
        this.getTypeDatabaseBoundary = getTypeDatabaseBoundary;
        this.getTypeOutputBoundary = getTypeOutputBoundary;
    }

    @Override
    public void executeGetTypeInvoice() {
        List<GetTypeOutputDTO> getTypesOutpts = getTypeDatabaseBoundary.getTypeInvoice();
        getTypeOutputBoundary.exportType(getTypesOutpts);
    }
}
