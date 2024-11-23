package com.addinvoice;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import deleteInvoiceDisplayKS.UI.DeleteDisplayPresenter;
import deleteInvoiceDisplayKS.UI.DeleteViewModel;
import deleteInvoiceKS.Database.DeleteInvoiceDAOMySQL;
import deleteInvoiceKS.Database.ConnectionDBDelete;
import deleteInvoiceKS.UseCase.DeleteInvoiceDatabaseBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceOutputBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceUseCase;
import deleteInvoiceKS.UseCase.DataMessageDelete;
import deleteInvoiceKS.UseCase.DeleteInvoiceInputDTO;
import deleteInvoiceKS.UseCase.ResponseDataDelete;

public class DeleteInvoiceTest {

    private DeleteInvoiceDatabaseBoundary deleteInvoiceDatabaseBoundary;
    private DeleteInvoiceOutputBoundary deleteInvoiceOutputBoundary;
    private DeleteInvoiceUseCase deleteInvoiceUseCase;
    private ResponseDataDelete dataMessage;
    private DeleteViewModel deleteIVModel;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        deleteIVModel = new DeleteViewModel();
        ConnectionDBDelete connectionDB = ConnectionDBDelete.getInstance();
        dataMessage = new DataMessageDelete();
        deleteInvoiceDatabaseBoundary = new DeleteInvoiceDAOMySQL(connectionDB);
        deleteInvoiceOutputBoundary = new DeleteDisplayPresenter(deleteIVModel);
        deleteInvoiceUseCase = new DeleteInvoiceUseCase(deleteInvoiceDatabaseBoundary, deleteInvoiceOutputBoundary,
                dataMessage);
    }

    // Thành công
    @Test
    public void testDeleteInvoiceExists() throws SQLException {
        DeleteInvoiceInputDTO deleteInvoiceInputDTO = new DeleteInvoiceInputDTO("3");
        ((DataMessageDelete) dataMessage).setConfirmationRequired(true);
        deleteInvoiceUseCase.execute(deleteInvoiceInputDTO);
        // Kiểm tra thông báo thành công
        assertEquals("Xóa hóa đơn thành công", deleteIVModel.getMessage());
    }

    // hóa đơn không tồn tại
    @Test
    public void testDeleteInvoiceNotExists() throws SQLException {
        String maHD = "HD00132";
        DeleteInvoiceInputDTO deleteInvoiceInputDTO = new DeleteInvoiceInputDTO(maHD);

        deleteInvoiceUseCase.execute(deleteInvoiceInputDTO);
        assertEquals("Hóa đơn không tồn tại.", deleteIVModel.getErrorMessage());
    }

    @Test
    public void testDeleteInvoicemaHDNull() throws SQLException {
        String maHD = "";
        DeleteInvoiceInputDTO deleteInvoiceInputDTO = new DeleteInvoiceInputDTO(maHD);

        deleteInvoiceUseCase.execute(deleteInvoiceInputDTO);
        // Kiểm tra khi không có thông tin
        assertEquals("Vui lòng nhập mã hóa đơn.", deleteIVModel.getErrorMessage());
    }

    // Xác nhận xóa
    @Test
    public void testDeleteInvoiceRequireConfirmation() throws SQLException {
        String maHD = "HD001";
        DeleteInvoiceInputDTO deleteInvoiceInputDTO = new DeleteInvoiceInputDTO(maHD);
        deleteInvoiceUseCase.execute(deleteInvoiceInputDTO);
        assertEquals("Bạn có chắc chắn muốn xóa hóa đơn này?",
        deleteIVModel.getConfirmMessage());
    }
}
