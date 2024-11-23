package quanLyHoaDon.UI;

import quanLyHoaDon.UseCase.InputRequestDTO;
import quanLyHoaDon.UseCase.QuanLyInputBoundary;

public class QuanLyController {
    private QuanLyInputBoundary quanLyInputBoundary;

    public QuanLyController(QuanLyInputBoundary quanLyInputBoundary) {
        this.quanLyInputBoundary = quanLyInputBoundary;
    }

    public void executeRequest(InputRequestDTO inputRequestDTO) {
        quanLyInputBoundary.execute(inputRequestDTO);
    }
}
