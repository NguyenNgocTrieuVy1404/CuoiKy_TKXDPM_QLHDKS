package editInvoiceDisplayKS.UI;

import java.util.List;
import java.util.Observable;

import editInvoiceDisplayKS.UseCase.EditDisplayOutputBoundary;
import editInvoiceDisplayKS.UseCase.InvoiceListDTO;
import editInvoiceDisplayKS.UseCase.InvoiceTypeDTO;

import java.util.ArrayList;

import editInvoiceKS.UseCase.DataMessageEdit;
import editInvoiceKS.UseCase.EditInvoiceOutputBoundary;
import editInvoiceKS.UseCase.ResponseDataEdit;

public class EditDisplayPresenter extends Observable implements EditInvoiceOutputBoundary, EditDisplayOutputBoundary {
    private DataMessageEdit dataMessage;
    private EditViewModel modelView;

    public EditDisplayPresenter(EditViewModel modelView) {
        this.modelView = modelView;
    }
    
    @Override
    public void exportMessage(ResponseDataEdit responseData) {
        dataMessage = (DataMessageEdit) responseData;

        if (dataMessage.getErrorMessage() != null) {
            modelView.setErrorMessage(dataMessage.getErrorMessage());
            setChanged();
            notifyObservers();
        } else if (dataMessage.getMessage() != null) {
            modelView.setMessage(dataMessage.getMessage());
            setChanged();
            notifyObservers("SuaHoaDon");
        }

    }

    @Override
    public void presentType(List<InvoiceTypeDTO> listTypeInvoiceDTOs) {
        List<String[]> listType = new ArrayList<>();
        for (InvoiceTypeDTO dto : listTypeInvoiceDTOs) {
            String[] data = { dto.getLoaiHoaDon() };
            listType.add(data);
        }

        modelView.setTypeInvoices(listType);
        setChanged();
        notifyObservers("TypeInvoice");
    }

    @Override
    public void presentList(List<InvoiceListDTO> listInvoices) {

        List<String[]> invoiceList = new ArrayList<>();

        for (InvoiceListDTO dto : listInvoices) {
            String[] data = {
                    dto.getMaHD(),
                    dto.getNgayHD().toString(),
                    dto.getTenKH(),
                    dto.getMaPhong(),
                    String.valueOf(dto.getDonGia()),
                    dto.getLoaiHoaDon(),
                    dto.getSoGioThue() != null ? String.valueOf(dto.getSoGioThue()) : "",
                    dto.getSoNgayThue() != null ? String.valueOf(dto.getSoNgayThue()) : ""
            };
            invoiceList.add(data);
        }
        modelView.setInvoiceList(invoiceList);
        setChanged();
        notifyObservers("ListInvoice");
    }

}
