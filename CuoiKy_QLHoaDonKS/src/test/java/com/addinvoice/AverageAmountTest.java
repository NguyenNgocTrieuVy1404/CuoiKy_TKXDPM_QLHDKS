package com.addinvoice;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import avgAmountDisplayKS.UI.AvgAmountDisplayPresenter;
import avgAmountDisplayKS.UI.AvgAmountDisplayViewModel;
import avgAmountItemKS.Database.AverageAmountDAOMySQL;
import avgAmountItemKS.Database.ConnectionDBAmount;
import avgAmountItemKS.Entity.AverageAmount;
import avgAmountItemKS.Entity.AverageAmountImpl;
import avgAmountItemKS.UseCase.AverageAmountDatabaseBoundary;
import avgAmountItemKS.UseCase.AverageAmountInputDTO;
import avgAmountItemKS.UseCase.AverageAmountOutputBoundary;
import avgAmountItemKS.UseCase.AverageAmountUseCase;

public class AverageAmountTest {

    private AverageAmountDatabaseBoundary averageAmountDatabaseBoundary;
    private AverageAmountOutputBoundary averageAmountOutputBoundary;
    private AverageAmount averageAmountBusinessRules;
    private AverageAmountUseCase averageAmountUseCase;
    private AvgAmountDisplayViewModel viewModel;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        viewModel = new AvgAmountDisplayViewModel();
        ConnectionDBAmount connectionDBAmount = ConnectionDBAmount.getInstance();
        averageAmountDatabaseBoundary = new AverageAmountDAOMySQL(connectionDBAmount);
        averageAmountOutputBoundary = new AvgAmountDisplayPresenter(viewModel);
        averageAmountBusinessRules = new AverageAmountImpl();
        averageAmountUseCase = new AverageAmountUseCase(
                averageAmountOutputBoundary,
                averageAmountDatabaseBoundary,
                averageAmountBusinessRules);
    }

    @Test
    public void testExecute() {
        AverageAmountInputDTO averageAmountInputDTO = new AverageAmountInputDTO("10");
        averageAmountUseCase.execute(averageAmountInputDTO);

        String[] firstRow = viewModel.getAverageAmountList().get(0);
        assertEquals("325000.0", firstRow[0]);
        assertEquals("21666.67", firstRow[1]);
    }
}
