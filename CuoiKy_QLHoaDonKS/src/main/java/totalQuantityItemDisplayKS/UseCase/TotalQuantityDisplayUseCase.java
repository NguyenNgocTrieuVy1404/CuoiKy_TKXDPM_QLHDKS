package totalQuantityItemDisplayKS.UseCase;

import java.util.ArrayList;
import java.util.List;

import getInvoiceType.UseCase.GetTypeInputBoundary;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeOutputDTO;

public class TotalQuantityDisplayUseCase implements TotalQuantityDisplayInputBoundary {
    private GetTypeInputBoundary getTypeInputBoundary;
    private TotalQuantityDisplayOutputBoundary outputBoundary;
    private GetTypeOutputBoundary getTypeOutputBoundary;

    public TotalQuantityDisplayUseCase(GetTypeInputBoundary getTypeInputBoundary,
            TotalQuantityDisplayOutputBoundary outputBoundary, GetTypeOutputBoundary getTypeOutputBoundary) {
        this.getTypeInputBoundary = getTypeInputBoundary;
        this.outputBoundary = outputBoundary;
        this.getTypeOutputBoundary = getTypeOutputBoundary;
    }

    @Override
    public void execute() {
        getTypeInputBoundary.executeGetTypeInvoice();
        List<GetTypeOutputDTO> listType = getTypeOutputBoundary.getTypeInvoice();
        List<ListTypeInvoiceDTO> listTypeInvoiceDTOs = new ArrayList<>();
        for (GetTypeOutputDTO type : listType) {
            ListTypeInvoiceDTO dto = new ListTypeInvoiceDTO(type.getLoaiHoaDon());
            listTypeInvoiceDTOs.add(dto);
        }
        outputBoundary.presentType(listTypeInvoiceDTOs);
    }

}
