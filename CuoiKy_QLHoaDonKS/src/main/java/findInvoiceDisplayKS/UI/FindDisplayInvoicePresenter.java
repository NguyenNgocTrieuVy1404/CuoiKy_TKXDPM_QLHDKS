package findInvoiceDisplayKS.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findInvoiceDisplayKS.UseCase.FindDisplayOutputBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayOutputDTO;

import findInvoiceKS.UseCase.FindInvoiceOutputBoundary;
import findInvoiceKS.UseCase.ResponseDataFind;
import findInvoiceKS.UseCase.DataMessageFind;
import findInvoiceKS.UseCase.FindInvoiceOutPutDTO;

public class FindDisplayInvoicePresenter extends Observable implements FindInvoiceOutputBoundary, FindDisplayOutputBoundary {
    private DataMessageFind dataMessage;
    private FindViewModel viewModel;

    public FindDisplayInvoicePresenter(FindViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void exportMessage(ResponseDataFind responseData) {
        dataMessage = (DataMessageFind) responseData;
        if (dataMessage.getErrorMessage() != null) {
            viewModel.setErrorMessage(dataMessage.getErrorMessage());

        } else if (dataMessage.getMessage() != null) {
            viewModel.setMessage(dataMessage.getMessage());
        }
        setChanged();
        notifyObservers("MessageFind");
    }

    @Override
    public void exportResult(List<FindInvoiceOutPutDTO> listFindDTO) {

        List<String[]> invoiceList = new ArrayList<>();
        for (FindInvoiceOutPutDTO dto : listFindDTO) {
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
        viewModel.setAllTypeInvoiceList(invoiceList);
        ;
        setChanged();
        notifyObservers(invoiceList);
    }

    @Override
    public void presentType(List<FindDisplayOutputDTO> listTypeInvoiceDTOs) {

        List<String[]> listAllType = new ArrayList<>();
        for (FindDisplayOutputDTO dto : listTypeInvoiceDTOs) {
            String[] data = { dto.getLoaiHoaDon() };
            listAllType.add(data);
        }
        viewModel.setAllTypeInvoiceList(listAllType);

        setChanged();
        notifyObservers("AllCriterion");
    }

}
