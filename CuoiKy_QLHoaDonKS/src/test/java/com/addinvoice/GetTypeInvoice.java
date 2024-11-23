package com.addinvoice;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import getInvoiceType.Database.ConnectionDBType;
import getInvoiceType.Database.GetTypeDAOMySQL;
import getInvoiceType.UI.GetTypePresenter;
import getInvoiceType.UseCase.GetTypeDatabaseBoundary;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeUseCase;




public class GetTypeInvoice {
     @Test
    public void TestListSize() throws ClassNotFoundException, SQLException {
        ConnectionDBType connectionDBType = ConnectionDBType.getInstance();
        GetTypeDatabaseBoundary databaseBoundary = new GetTypeDAOMySQL(connectionDBType);
        GetTypeOutputBoundary getInvoiceOutputBoundary = new GetTypePresenter();
        GetTypeUseCase getTypeUseCase = new GetTypeUseCase(databaseBoundary,
                getInvoiceOutputBoundary);
        
                getTypeUseCase.executeGetTypeInvoice();        
        
        assertEquals(2, getInvoiceOutputBoundary.getTypeInvoice().size());
    }
}
