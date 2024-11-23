package avgAmountDisplayKS.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import avgAmountDisplayKS.UseCase.AvgAmountDisplayDatabaseBoundary;

public class AvgAmountDisplayDAOMySQL implements AvgAmountDisplayDatabaseBoundary {
    private ConnectionDBAvg connectionDBAvg;

    @Override
    public List<String> getMonthList() {
        List<String> monthList = new ArrayList<>();
        String query = "SELECT DISTINCT MONTH(NgayHoaDon) as month FROM hoadon ORDER BY month";

        try {
            Connection connection = connectionDBAvg.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String month = String.format("%02d", rs.getInt("month"));
                monthList.add(month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthList;
    }

    public AvgAmountDisplayDAOMySQL(ConnectionDBAvg connectionDBAvg) {
        this.connectionDBAvg = connectionDBAvg;
    }
}
