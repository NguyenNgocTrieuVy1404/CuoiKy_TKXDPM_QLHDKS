package addInvoiceDisplayKS.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import addInvoiceDisplayKS.UseCase.AddDisplayOutputBoundary;
import addInvoiceDisplayKS.UseCase.ListTypeInvoiceDTO;
import addInvoiceKS.UseCase.AddInvoiceOutputBoundary;
import addInvoiceKS.UseCase.DataMessageAdd;
import addInvoiceKS.UseCase.ResponseDataAdd;

public class AddDisplayPresenter extends Observable implements AddDisplayOutputBoundary, AddInvoiceOutputBoundary {
    private AddViewModel viewModel;
    private DataMessageAdd dataMessage;

    public AddDisplayPresenter(AddViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentType(List<ListTypeInvoiceDTO> listTypeInvoiceDTOs) {
        List<String[]> listType = new ArrayList<>();
        for (ListTypeInvoiceDTO dto : listTypeInvoiceDTOs) {
            String[] data = { dto.getLoaiHoaDon() };
            listType.add(data);
        }

        viewModel.setTypeInvoices(listType);
        setChanged();
        notifyObservers("TypeInvoice");
    }

    @Override
    public void exportMessage(ResponseDataAdd responseData) {
        dataMessage = (DataMessageAdd) responseData;
        if (dataMessage.getMessage() != null) {
            viewModel.setMessage(dataMessage.getMessage());
            setChanged();
            notifyObservers("ThemHoaDon");
        } else if (dataMessage.getErrorMessage() != null) {
            viewModel.setErrorMessage(dataMessage.getErrorMessage());
            setChanged();
            notifyObservers();
        }

    }
}