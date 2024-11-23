package editInvoiceDisplayKS.UI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import editInvoiceKS.UseCase.EditInvoiceInputDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EditDisplayView extends JFrame implements Observer {
    private JTable jTableSet;
    private JScrollPane scrollPane;
    private JDateChooser ngayHDChooser;
    private DefaultTableModel jTableModel;

    private JTextField maHDField, tenKHField, maPhongField, donGiaField, soGioThueField, soNgayThueField;
    private JLabel titleLabel, maHDLabel, ngayHDLabel, tenKHLabel, maPhongLabel, donGiaLabel, loaiHoaDonLabel,
            soGioThueLabel, soNgayThueLabel;
    private JComboBox<String> loaiHoaDonComboBox;
    private JButton editButton;
    private EditViewModel modelView = null;
    private EdittDisplayController edittDisplayController = null;

    public EditDisplayView() {
        setTitle("Chỉnh Sửa Hóa Đơn Khách Sạn");
        buildPanel();
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void setModelView(EditViewModel modelView) {
        this.modelView = modelView;
    }

    public void setEdittDisplayController(EdittDisplayController edittDisplayController) {
        this.edittDisplayController = edittDisplayController;
    }

    public void executeGetList() throws SQLException {
        edittDisplayController.executeGetInvoice();
    }

    public void buildPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        jPanel.setBackground(new Color(245, 245, 245));

        // Tiêu đề
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        titleLabel = new JLabel("Sửa Hóa Đơn");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 204));
        jPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        String[] columnNames = { "Mã Hóa Đơn", "Ngày HD", "Tên KH", "Mã Phòng", "Đơn giá", "Loại Hóa Đơn",
                "Số Giờ Thuê", "Số Ngày Thuê" };
        jTableModel = new DefaultTableModel(columnNames, 0);
        jTableSet = new JTable(jTableModel);

        jTableSet.setFillsViewportHeight(true);
        jTableSet.setRowHeight(30);
        jTableSet.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jTableSet.getTableHeader().setBackground(new Color(224, 224, 224));
        jTableSet.setFont(new Font("SansSerif", Font.PLAIN, 14));

        scrollPane = new JScrollPane(jTableSet);
        jPanel.add(scrollPane, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints inputGbc = new GridBagConstraints();
        inputGbc.insets = new Insets(5, 5, 5, 5);
        inputGbc.anchor = GridBagConstraints.WEST;

        inputGbc.gridx = 0;
        inputGbc.gridy = 0;
        maHDLabel = new JLabel("Mã Hóa Đơn:");
        inputPanel.add(maHDLabel, inputGbc);
        maHDField = new JTextField(15);
        maHDField.setEditable(false);
        inputGbc.gridx = 1;
        inputPanel.add(maHDField, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        ngayHDLabel = new JLabel("Ngày Hóa Đơn:");
        inputPanel.add(ngayHDLabel, inputGbc);
        ngayHDChooser = new JDateChooser();
        ngayHDChooser.setPreferredSize(new Dimension(160, 25));
        inputGbc.gridx = 1;
        inputPanel.add(ngayHDChooser, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        tenKHLabel = new JLabel("Tên Khách Hàng:");
        inputPanel.add(tenKHLabel, inputGbc);
        tenKHField = new JTextField(15);
        inputGbc.gridx = 1;
        inputPanel.add(tenKHField, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        maPhongLabel = new JLabel("Mã Phòng:");
        inputPanel.add(maPhongLabel, inputGbc);
        maPhongField = new JTextField(15);
        inputGbc.gridx = 1;
        inputPanel.add(maPhongField, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        donGiaLabel = new JLabel("Đơn Giá:");
        inputPanel.add(donGiaLabel, inputGbc);
        donGiaField = new JTextField(15);
        inputGbc.gridx = 1;
        inputPanel.add(donGiaField, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        loaiHoaDonLabel = new JLabel("Loại Hóa Đơn:");
        inputPanel.add(loaiHoaDonLabel, inputGbc);
        loaiHoaDonComboBox = new JComboBox<>(new String[] { "Theo Giờ", "Theo Ngày" });
        inputGbc.gridx = 1;
        inputPanel.add(loaiHoaDonComboBox, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        soGioThueLabel = new JLabel("Số Giờ Thuê:");
        inputPanel.add(soGioThueLabel, inputGbc);
        soGioThueField = new JTextField(15);
        soGioThueField.setEnabled(false);
        inputGbc.gridx = 1;
        inputPanel.add(soGioThueField, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        soNgayThueLabel = new JLabel("Số Ngày Thuê:");
        inputPanel.add(soNgayThueLabel, inputGbc);
        soNgayThueField = new JTextField(15);
        soNgayThueField.setEnabled(false);
        inputGbc.gridx = 1;
        inputPanel.add(soNgayThueField, inputGbc);

        jPanel.add(inputPanel, gbc);

        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Sửa Hóa Đơn");
        editButton.setBackground(Color.BLUE);
        editButton.setForeground(Color.WHITE);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editInvoice();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        jTableSet.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && jTableSet.getSelectedRow() != -1) {
                    int selectedRow = jTableSet.getSelectedRow();

                    String maHoaDon = jTableSet.getValueAt(selectedRow, 0).toString();
                    String ngayHD = jTableSet.getValueAt(selectedRow, 1).toString();
                    String tenKH = jTableSet.getValueAt(selectedRow, 2).toString();
                    String maPhong = jTableSet.getValueAt(selectedRow, 3).toString();
                    String donGia = jTableSet.getValueAt(selectedRow, 4).toString();
                    String loaiHoaDon = jTableSet.getValueAt(selectedRow, 5).toString();
                    String soGioThue = jTableSet.getValueAt(selectedRow, 6).toString();
                    String soNgayThue = jTableSet.getValueAt(selectedRow, 7).toString();

                    maHDField.setText(maHoaDon);
                    tenKHField.setText(tenKH);
                    maPhongField.setText(maPhong);
                    donGiaField.setText(donGia);
                    loaiHoaDonComboBox.setSelectedItem(loaiHoaDon);
                    soGioThueField.setText(soGioThue);
                    soNgayThueField.setText(soNgayThue);

                    // Định dạng ngày và cập nhật JDateChooser
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date ngayHoaDon = sdf.parse(ngayHD);
                        if (ngayHoaDon != null) {
                            ngayHDChooser.setDate(ngayHoaDon);
                        } else {
                            System.out.println("Ngày hóa đơn không hợp lệ: " + ngayHD);
                        }
                    } catch (java.text.ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        loaiHoaDonComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedLoaiHoaDon = (String) loaiHoaDonComboBox.getSelectedItem();
                if ("Theo Giờ".equals(selectedLoaiHoaDon)) {
                    soGioThueField.setEnabled(true);
                    soNgayThueField.setEnabled(false);
                    soNgayThueField.setText("");
                } else if ("Theo Ngày".equals(selectedLoaiHoaDon)) {
                    soGioThueField.setEnabled(false);
                    soNgayThueField.setEnabled(true);
                    soGioThueField.setText("");
                }
            }
        });

        buttonPanel.add(editButton);

        mainPanel.add(jPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void editInvoice() throws SQLException {
        String maHD = maHDField.getText();
        Date ngayHD = ngayHDChooser.getDate();
        String tenKH = tenKHField.getText();
        String maPhong = maPhongField.getText();
        String donGia = donGiaField.getText();
        String loaiHoaDon = (String) loaiHoaDonComboBox.getSelectedItem();
        String soGioThue = "";
        String soNgayThue = "";

        if (loaiHoaDon.equals("Theo Giờ")) {
            soGioThue = soGioThueField.getText();
        } else if (loaiHoaDon.equals("Theo Ngày")) {
            soNgayThue = soNgayThueField.getText();
        }

        EditInvoiceInputDTO editInvoiceInputDTO = new EditInvoiceInputDTO(
                maHD, ngayHD, tenKH, maPhong, donGia, loaiHoaDon, soGioThue, soNgayThue);

        edittDisplayController.executeRequest(editInvoiceInputDTO);
    }

    public void updateTypeHoaDon() {
        List<String[]> invoiceList = modelView.getTypeInvoices();
        loaiHoaDonComboBox.removeAllItems();
        for (String[] invoice : invoiceList) {
            if (invoice.length > 0) {
                loaiHoaDonComboBox.addItem(invoice[0]);
            }
        }
    }

    public void updateInvoiceList() throws ParseException {

        List<String[]> invoiceList = modelView.getInvoiceList();
        jTableModel.setRowCount(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (String[] invoice : invoiceList) {
            String formattedDate = "";
            Date date = originalFormat.parse(invoice[1]);
            formattedDate = dateFormat.format(date);

            Object[] rowData = {
                    invoice[0],
                    formattedDate,
                    invoice[2],
                    invoice[3],
                    invoice[4],
                    invoice[5],
                    invoice[6],
                    invoice[7]
            };
            jTableModel.addRow(rowData);
        }
        jTableModel.fireTableDataChanged();
    }

    private void clearInputFields() {
        maHDField.setText("");
        tenKHField.setText("");
        ngayHDChooser.setDate(null);
        maPhongField.setText("");
        donGiaField.setText("");
        soGioThueField.setText("");
        soNgayThueField.setText("");
    }

    @Override
    public void update(Observable o, Object arg) {
        if ("ListInvoice".equals(arg)) {
            try {
                updateInvoiceList();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if ("TypeInvoice".equals(arg)) {
            updateTypeHoaDon();
        }
        SwingUtilities.invokeLater(() -> {
            setLocationRelativeTo(null);
            setVisible(true);
            toFront();
            requestFocus();
        });

        if (modelView.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(null, modelView.getErrorMessage(), "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            modelView.setErrorMessage(null);
        } else if (modelView.getMessage() != null) {
            JOptionPane.showMessageDialog(null, modelView.getMessage(), "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            clearInputFields();
            modelView.setMessage(null);
            try {
                executeGetList();
                toFront();
                requestFocus();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
