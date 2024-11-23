package quanLyHoaDon.UseCase;

import java.util.ArrayList;
import java.util.List;

import addInvoiceDisplayKS.UseCase.AddDisplayInputBoundary;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayInputBoundary;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayInputBoundary;
import editInvoiceDisplayKS.UseCase.EditDisplayInputBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayInputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListInputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceOuputDTO;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayInputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListOutputBoundary;

public class QuanLyUseCase implements QuanLyInputBoundary {
    private QuanLyOutputBoundary quanLyOutputBoundary;
    private GetInvoiceListInputBoundary getInvoiceListInputBoundary;
    private GetInvoiceListOutputBoundary getInvoiceOutputBoundary;
    private AddDisplayInputBoundary addDisplayInputBoundary;
    private DeleteDisplayInputBoundary deleteDisplayInputBoundary;
    private EditDisplayInputBoundary editDisplayInputBoundary;
    private AvgAmountDisplayInputBoundary avgAmountDisplayInputBoundary;
    private FindDisplayInputBoundary findDisplayInputBoundary;
    private TotalQuantityDisplayInputBoundary totalQuantityDisplayInputBoundary;

    public QuanLyUseCase(QuanLyOutputBoundary quanLyOutputBoundary,
            GetInvoiceListInputBoundary getInvoiceListInputBoundary,
            GetInvoiceListOutputBoundary getInvoiceOutputBoundary, AddDisplayInputBoundary addDisplayInputBoundary,
            DeleteDisplayInputBoundary deleteDisplayInputBoundary, EditDisplayInputBoundary editDisplayInputBoundary,
            AvgAmountDisplayInputBoundary avgAmountDisplayInputBoundary,
            FindDisplayInputBoundary findDisplayInputBoundary,
            TotalQuantityDisplayInputBoundary totalQuantityDisplayInputBoundary) {
        this.quanLyOutputBoundary = quanLyOutputBoundary;
        this.getInvoiceListInputBoundary = getInvoiceListInputBoundary;
        this.getInvoiceOutputBoundary = getInvoiceOutputBoundary;
        this.addDisplayInputBoundary = addDisplayInputBoundary;
        this.deleteDisplayInputBoundary = deleteDisplayInputBoundary;
        this.editDisplayInputBoundary = editDisplayInputBoundary;
        this.avgAmountDisplayInputBoundary = avgAmountDisplayInputBoundary;
        this.findDisplayInputBoundary = findDisplayInputBoundary;
        this.totalQuantityDisplayInputBoundary = totalQuantityDisplayInputBoundary;
    }

    @Override
    public void execute(InputRequestDTO inputRequestDTO) {
        if (inputRequestDTO.getInputRequest().equals("GetList")) {
            getInvoiceListInputBoundary.execute();
            List<GetInvoiceOuputDTO> listInvoice = getInvoiceOutputBoundary.getInvoiceList();

            List<InvoiceOuputDTO> listInvoiceDTOs = new ArrayList<>();
            for (GetInvoiceOuputDTO type : listInvoice) {
                InvoiceOuputDTO dto = new InvoiceOuputDTO(
                        type.getMaHD(),
                        type.getNgayHD(),
                        type.getTenKH(),
                        type.getMaPhong(),
                        type.getDonGia(),
                        type.getLoaiHoaDon(),
                        type.getSoGioThue(),
                        type.getSoNgayThue());
                listInvoiceDTOs.add(dto);
            }
            quanLyOutputBoundary.exportResult(listInvoiceDTOs);
        }

        if (inputRequestDTO.getInputRequest().equals("Thêm hóa đơn")) {
            addDisplayInputBoundary.execute();
        } else if (inputRequestDTO.getInputRequest().equals("Xóa hóa đơn")) {
            deleteDisplayInputBoundary.execute();
        } else if (inputRequestDTO.getInputRequest().equals("Sửa hóa đơn")) {
            editDisplayInputBoundary.execute();
        } else if (inputRequestDTO.getInputRequest().equals("Tìm hóa đơn")) {
            findDisplayInputBoundary.execute();
        } else if (inputRequestDTO.getInputRequest().equals("Tính tổng số lượng")) {
            totalQuantityDisplayInputBoundary.execute();
        } else if (inputRequestDTO.getInputRequest().equals("Tính trung bình thành tiền")) {
            avgAmountDisplayInputBoundary.execute();
        }
    }
}
