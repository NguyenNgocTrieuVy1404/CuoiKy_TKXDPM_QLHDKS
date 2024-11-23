package addInvoiceDisplayKS.UseCase;

import java.util.ArrayList;
import java.util.List;

import getInvoiceType.UseCase.GetTypeInputBoundary;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeOutputDTO;

public class AddDisplayUseCase implements AddDisplayInputBoundary {
    private GetTypeInputBoundary getTypeInputBoundary;
    private GetTypeOutputBoundary getTypeOutputBoundary;
    private AddDisplayOutputBoundary addDisplayOutputBoundary;

    public AddDisplayUseCase(GetTypeInputBoundary getTypeInputBoundary, GetTypeOutputBoundary getTypeOutputBoundary,
            AddDisplayOutputBoundary addDisplayOutputBoundary) {
        this.getTypeInputBoundary = getTypeInputBoundary;
        this.getTypeOutputBoundary = getTypeOutputBoundary;
        this.addDisplayOutputBoundary = addDisplayOutputBoundary;
    }

    public void execute() {
        getTypeInputBoundary.executeGetTypeInvoice();
        List<GetTypeOutputDTO> listTypes = getTypeOutputBoundary.getTypeInvoice();

        List<ListTypeInvoiceDTO> listTypeInvoiceDTOs = new ArrayList<>();
        for (GetTypeOutputDTO type : listTypes) {
            ListTypeInvoiceDTO dto = new ListTypeInvoiceDTO(type.getLoaiHoaDon());
            listTypeInvoiceDTOs.add(dto);
        }
        addDisplayOutputBoundary.presentType(listTypeInvoiceDTOs);
    }
}
