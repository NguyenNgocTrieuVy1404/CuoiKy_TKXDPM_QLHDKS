
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import getAllInvoiceList.DataBase.ConnectionDBGet;
import getAllInvoiceList.DataBase.GetInvoiceListDAOMySQL;
import getAllInvoiceList.UI.GetInvoiceListPresenter;
import getAllInvoiceList.UseCase.GetInvoiceListUseCase;
import getAllInvoiceList.UseCase.GetInvoiceListOutputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListDatabaseBoundary;

public class GetListTest {
    @Test
    public void TestListSize() throws ClassNotFoundException, SQLException {
        ConnectionDBGet connectionDBGet = ConnectionDBGet.getInstance();
        GetInvoiceListDatabaseBoundary databaseBoundary = new GetInvoiceListDAOMySQL(connectionDBGet);
        GetInvoiceListOutputBoundary getInvoiceOutputBoundary = new GetInvoiceListPresenter();
        GetInvoiceListUseCase getInvoiceListUseCase = new GetInvoiceListUseCase(databaseBoundary,
                getInvoiceOutputBoundary);

        getInvoiceListUseCase.execute();

        assertEquals(18, getInvoiceOutputBoundary.getInvoiceList().size());
    }
}
