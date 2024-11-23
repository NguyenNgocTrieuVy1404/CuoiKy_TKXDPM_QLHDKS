package totalQuantityItemDisplayKS.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import totalQuantityItemDisplayKS.UseCase.ListTypeInvoiceDTO;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayOutputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityOutPutDTO;
import totalQuantityItemKS.UseCase.TotalQuantityOutputBoundary;

public class TotalQuantityDisplayPresenter extends Observable
        implements TotalQuantityDisplayOutputBoundary, TotalQuantityOutputBoundary {
    private TotalViewModel viewModel;

    public TotalQuantityDisplayPresenter(TotalViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(List<TotalQuantityOutPutDTO> totalQuantityOutPutDTOs) {
        List<String[]> summary = new ArrayList<>();
        for (TotalQuantityOutPutDTO dto : totalQuantityOutPutDTOs) {
            String[] data = {
                    String.valueOf(dto.getLoaiHoaDon()),
                    String.valueOf(dto.getTongSoLuong()),
                    String.valueOf(dto.getTongSoTien()),
            };
            summary.add(data);
        }
        viewModel.setTotalQuantityList(summary);
        setChanged();
        notifyObservers("TotalQuantity");
    }

    @Override
    public void presentType(List<ListTypeInvoiceDTO> listTypeInvoiceDTOs) {
        List<String[]> listType = new ArrayList<>();
        for (ListTypeInvoiceDTO dto : listTypeInvoiceDTOs) {
            String[] data = { dto.getLoaiHoaDon() };
            listType.add(data);
        }

        viewModel.setLoaiHoaDon(listType);
        setChanged();
        notifyObservers("TypeInvoice");
    }

}
