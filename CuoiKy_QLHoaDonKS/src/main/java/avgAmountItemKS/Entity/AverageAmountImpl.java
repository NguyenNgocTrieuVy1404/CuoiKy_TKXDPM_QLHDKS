package avgAmountItemKS.Entity;

import java.util.List;

import avgAmountItemKS.UseCase.AverageAmountOutPutDTO;

import java.util.ArrayList;

public class AverageAmountImpl implements AverageAmount {

    @Override
    public List<AverageAmountOutPutDTO> calculate(List<Invoice> invoices) {
        double totalAmount = 0.0;

        for (Invoice invoice : invoices) {
            totalAmount += invoice.tinhThanhTien();
        }

        AverageAmountOutPutDTO summary = new AverageAmountOutPutDTO();
        int invoiceCount = invoices.size();

        if (invoiceCount > 0) {
            summary.setTongSoTien(Math.round(totalAmount * 100.0) / 100.0);
            summary.setTrungBinhThanhTien(Math.round((totalAmount / invoiceCount) * 100.0) / 100.0);
        } else {
            summary.setTongSoTien(0);
            summary.setTrungBinhThanhTien(0);
        }

        List<AverageAmountOutPutDTO> summaryList = new ArrayList<>();
        summaryList.add(summary);

        return summaryList;
    }
}
