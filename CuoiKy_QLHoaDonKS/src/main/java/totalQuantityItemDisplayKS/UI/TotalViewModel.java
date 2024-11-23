package totalQuantityItemDisplayKS.UI;

import java.util.List;

public class TotalViewModel {
    private List<String[]> loaiHoaDon;
    private List<String[]> totalQuantityList;


    public List<String[]> getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public List<String[]> getTotalQuantityList() {
        return totalQuantityList;
    }

    public void setLoaiHoaDon(List<String[]> loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public void setTotalQuantityList(List<String[]> totalQuantityList) {
        this.totalQuantityList = totalQuantityList;
    }

}
