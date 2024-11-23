package getInvoiceType.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import getInvoiceType.UseCase.GetTypeDatabaseBoundary;
import getInvoiceType.UseCase.GetTypeOutputDTO;

public class GetTypeDAOMySQL implements GetTypeDatabaseBoundary {
    private ConnectionDBType connectionDB;

    public GetTypeDAOMySQL(ConnectionDBType connectionDB) {
        this.connectionDB = connectionDB;
    }
    
    @Override
    public List<GetTypeOutputDTO> getTypeInvoice() {
        List<GetTypeOutputDTO> types = new ArrayList<>();
        String query = "SELECT LoaiHoaDon FROM LoaiHoaDon";

        try {
            Connection connection = connectionDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String loaiHoaDon = resultSet.getString("LoaiHoaDon");
                GetTypeOutputDTO dto = new GetTypeOutputDTO(loaiHoaDon);
                types.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
}
