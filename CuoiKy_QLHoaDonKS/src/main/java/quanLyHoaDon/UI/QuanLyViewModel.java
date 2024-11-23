package quanLyHoaDon.UI;

import java.util.List;

public class QuanLyViewModel {
    List<String[]> listInvoices;

    public List<String[]> getListInvoices() {
        return listInvoices;
    }

    public void setListInvoices(List<String[]> invoiceList) {
        this.listInvoices = invoiceList;
    }
}
