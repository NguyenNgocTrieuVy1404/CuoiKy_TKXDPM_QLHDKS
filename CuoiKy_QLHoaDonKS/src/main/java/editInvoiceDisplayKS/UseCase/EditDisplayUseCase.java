package editInvoiceDisplayKS.UseCase;

import java.util.ArrayList;
import java.util.List;

import getAllInvoiceList.UseCase.GetInvoiceListInputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListOutputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceOuputDTO;
import getInvoiceType.UseCase.GetTypeInputBoundary;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeOutputDTO;

public class EditDisplayUseCase implements EditDisplayInputBoundary {
    private GetInvoiceListInputBoundary getInvoiceListInputBoundary;
    private GetInvoiceListOutputBoundary getInvoiceListOutputBoundary;

    private GetTypeInputBoundary getTypeInputBoundary;
    private GetTypeOutputBoundary gTypeOutputBoundary;

    private EditDisplayOutputBoundary editDisplayOutputBoundary;

    public EditDisplayUseCase(GetInvoiceListInputBoundary getInvoiceListInputBoundary,
            GetInvoiceListOutputBoundary getInvoiceListOutputBoundary, GetTypeInputBoundary getTypeInputBoundary,
            GetTypeOutputBoundary gTypeOutputBoundary, EditDisplayOutputBoundary editDisplayOutputBoundary) {
        this.getInvoiceListInputBoundary = getInvoiceListInputBoundary;
        this.getInvoiceListOutputBoundary = getInvoiceListOutputBoundary;
        this.getTypeInputBoundary = getTypeInputBoundary;
        this.gTypeOutputBoundary = gTypeOutputBoundary;
        this.editDisplayOutputBoundary = editDisplayOutputBoundary;
    }

    @Override
    public void execute() {
        getInvoiceListInputBoundary.execute();
        List<GetInvoiceOuputDTO> listInvoice = getInvoiceListOutputBoundary.getInvoiceList();

        List<InvoiceListDTO> listInvoiceDTOs = new ArrayList<>();
        for (GetInvoiceOuputDTO type : listInvoice) {
            InvoiceListDTO dto = new InvoiceListDTO(
                    type.getMaHD(),
                    type.getNgayHD(),
                    type.getTenKH(),
                    type.getMaPhong(),
                    type.getDonGia(),
                    type.getLoaiHoaDon(),
                    type.getSoGioThue(),
                    type.getSoNgayThue());
            listInvoiceDTOs.add(dto);
        }

        getTypeInputBoundary.executeGetTypeInvoice();
        List<GetTypeOutputDTO> listTypes = gTypeOutputBoundary.getTypeInvoice();

        List<InvoiceTypeDTO> listTypeInvoiceDTOs = new ArrayList<>();
        for (GetTypeOutputDTO type : listTypes) {
            InvoiceTypeDTO dto = new InvoiceTypeDTO(type.getLoaiHoaDon());
            listTypeInvoiceDTOs.add(dto);
        }
        editDisplayOutputBoundary.presentList(listInvoiceDTOs);
        editDisplayOutputBoundary.presentType(listTypeInvoiceDTOs);
    }

}
