package com.addinvoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import totalQuantityItemDisplayKS.UI.TotalQuantityDisplayPresenter;
import totalQuantityItemDisplayKS.UI.TotalViewModel;
import totalQuantityItemKS.Database.ConnectionDBTotal;
import totalQuantityItemKS.Database.TotalQuantityDAOMySQL;
import totalQuantityItemKS.Entity.Invoice;
import totalQuantityItemKS.Entity.TotalQuantityBusinessRules;
import totalQuantityItemKS.Entity.TotalQuantityBusinessRulesImpl;
import totalQuantityItemKS.UseCase.TotalQuantityDatabaseBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityInputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityInputDTO;
import totalQuantityItemKS.UseCase.TotalQuantityOutPutDTO;
import totalQuantityItemKS.UseCase.TotalQuantityOutputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityUseCase;

public class TotalQuantityTest {

    private TotalQuantityDatabaseBoundary totalQuantityDatabaseBoundary;
    private TotalQuantityOutputBoundary totalQuantityOutputBoundary;
    private TotalQuantityBusinessRules totalQuantityBusinessRules;
    private TotalQuantityInputBoundary totalQuantityUseCase;
    private TotalViewModel viewModel;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        viewModel = new TotalViewModel();
        ConnectionDBTotal connectionDB = ConnectionDBTotal.getInstance();
        totalQuantityDatabaseBoundary = new TotalQuantityDAOMySQL(connectionDB);
        totalQuantityOutputBoundary = new TotalQuantityDisplayPresenter(viewModel);
        totalQuantityBusinessRules = new TotalQuantityBusinessRulesImpl();
        totalQuantityUseCase = new TotalQuantityUseCase(
                totalQuantityOutputBoundary,
                totalQuantityDatabaseBoundary,
                totalQuantityBusinessRules);
    }

    @Test
    public void testExecuteGio() {
        TotalQuantityInputDTO totalQuantityInputDTO = new TotalQuantityInputDTO("Theo Giờ");
        List<Invoice> summaryList = totalQuantityDatabaseBoundary.getTotalQuantityInvoices(totalQuantityInputDTO);

        List<TotalQuantityOutPutDTO> listCalc = totalQuantityBusinessRules.calculate(summaryList);
        totalQuantityUseCase.execute(totalQuantityInputDTO);

        TotalQuantityOutPutDTO hourlyInvoice = listCalc.stream()
                .filter(dto -> dto.getLoaiHoaDon().equals("Theo Giờ"))
                .findFirst()
                .orElse(null);
        assertNotNull(hourlyInvoice);
        assertEquals(83, hourlyInvoice.getTongSoLuong(), 0.0);

    }

    @Test
    public void testExecuteNgay() {
        TotalQuantityInputDTO totalQuantityInputDTO = new TotalQuantityInputDTO("Theo Ngày");
        List<Invoice> summaryList = totalQuantityDatabaseBoundary.getTotalQuantityInvoices(totalQuantityInputDTO);

        List<TotalQuantityOutPutDTO> listCalc = totalQuantityBusinessRules.calculate(summaryList);

        TotalQuantityOutPutDTO dailyInvoice = listCalc.stream()
                .filter(dto -> dto.getLoaiHoaDon().equals("Theo Ngày"))
                .findFirst()
                .orElse(null);
        assertNotNull(dailyInvoice);
        assertEquals(35, dailyInvoice.getTongSoLuong(), 0.0);
    }
}
