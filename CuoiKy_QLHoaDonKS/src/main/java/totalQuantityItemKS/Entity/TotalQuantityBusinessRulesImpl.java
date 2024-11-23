package totalQuantityItemKS.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import totalQuantityItemKS.UseCase.TotalQuantityOutPutDTO;

public class TotalQuantityBusinessRulesImpl implements TotalQuantityBusinessRules {

    @Override
    public List<TotalQuantityOutPutDTO> calculate(List<Invoice> invoices) {
        Map<String, TotalQuantityOutPutDTO> summaryMap = new HashMap<>();
        List<TotalQuantityOutPutDTO> summaryList = new ArrayList<>();

        for (Invoice invoice : invoices) {
            String loaiHoaDon = invoice.getLoaiHoaDon();
            TotalQuantityOutPutDTO totalQuantity = summaryMap.get(loaiHoaDon);

            if (totalQuantity == null) {
                totalQuantity = new TotalQuantityOutPutDTO(loaiHoaDon);
                summaryMap.put(loaiHoaDon, totalQuantity);
            }
            totalQuantity.setTongSoLuong(totalQuantity.getTongSoLuong() + invoice.getSoLuong());
            totalQuantity.setTongSoTien(totalQuantity.getTongSoTien() + invoice.tinhThanhTien());
        }

        summaryList.addAll(summaryMap.values());
        return summaryList;
    }
}
