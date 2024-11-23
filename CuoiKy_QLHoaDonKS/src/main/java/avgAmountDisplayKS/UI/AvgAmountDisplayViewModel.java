package avgAmountDisplayKS.UI;

import java.util.ArrayList;
import java.util.List;

public class AvgAmountDisplayViewModel {
    private List<String> monthList;
    private List<String[]> averageAmountList;
    private String message;
    private String errorMessage;

    public AvgAmountDisplayViewModel() {
        this.monthList = new ArrayList<>();
    }

    public List<String> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<String> monthList) {
        this.monthList = monthList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setAverageAmountList(List<String[]> averageAmountList) {
        this.averageAmountList = averageAmountList;
    }

    public List<String[]> getAverageAmountList() {
        return averageAmountList;
    }
}
