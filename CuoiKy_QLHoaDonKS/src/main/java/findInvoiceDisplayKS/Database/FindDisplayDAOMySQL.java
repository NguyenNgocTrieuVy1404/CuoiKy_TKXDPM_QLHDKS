package findInvoiceDisplayKS.Database;

import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import findInvoiceDisplayKS.UseCase.FindDisplayDatabaseBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayOutputDTO;

public class FindDisplayDAOMySQL implements FindDisplayDatabaseBoundary {
    private ConnectionDBFind connectionDB;

    public FindDisplayDAOMySQL(ConnectionDBFind connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public List<FindDisplayOutputDTO> getAllTypeInvoice() {
        List<FindDisplayOutputDTO> types = new ArrayList<>();
        String query = "SELECT TenTieuChi FROM TieuCHi";
        Connection connection = connectionDB.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String tenHienThi = resultSet.getString("TenTieuChi");

                FindDisplayOutputDTO dto = new FindDisplayOutputDTO(tenHienThi);
                types.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

}
