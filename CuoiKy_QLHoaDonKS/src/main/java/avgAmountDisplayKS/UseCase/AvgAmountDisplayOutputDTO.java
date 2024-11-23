package avgAmountDisplayKS.UseCase;

import java.util.List;

public class AvgAmountDisplayOutputDTO {
    private List<String> monthList;

    public AvgAmountDisplayOutputDTO(List<String> monthList) {
        this.monthList = monthList;
    }

    public List<String> getMonthList() {
        return monthList;
    }
}
