package findInvoiceKS.UseCase;

import java.sql.SQLException;
import java.util.List;

public interface FindInvoiceDatabaseBoundary {

    boolean maHoaDonExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getMaHoaDon(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;

    boolean ngayHoaDonExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getNgayHoaDon(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;

    boolean tenKhachHangExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getTenKhachHang(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;

    boolean maPhongExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getMaPhong(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;

    boolean donGiaExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getDonGia(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;

    boolean loaiHoaDonExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getLoaiHoaDon(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;

    boolean soGioThueExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getSoGioThue(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;

    boolean soNgayThueExists(FindInvoiceInputDTO findInvoiceInputDTO);

    List<FindInvoiceOutPutDTO> getSoNgayThue(FindInvoiceInputDTO findInvoiceInputDTO) throws SQLException;
}
