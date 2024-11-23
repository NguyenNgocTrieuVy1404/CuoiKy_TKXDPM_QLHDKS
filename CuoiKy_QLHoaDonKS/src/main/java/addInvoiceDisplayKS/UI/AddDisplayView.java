package addInvoiceDisplayKS.UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import addInvoiceDisplayKS.UseCase.AddDisplayOutputBoundary;
import addInvoiceDisplayKS.UseCase.AddDisplayUseCase;
import addInvoiceKS.UseCase.AddInvoiceInputDTO;
import getInvoiceType.Database.ConnectionDBType;
import getInvoiceType.Database.GetTypeDAOMySQL;
import getInvoiceType.UI.GetTypePresenter;
import getInvoiceType.UseCase.GetTypeDatabaseBoundary;
import getInvoiceType.UseCase.GetTypeInputBoundary;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeUseCase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AddDisplayView extends JFrame implements Observer {
    private JDateChooser ngayHDChooser;

    private JTextField maHDField, tenKHField, maPhongField, donGiaField, soGioThueField, soNgayThueField;
    private JLabel titleLabel, maHDLabel, ngayHDLabel, tenKHLabel, maPhongLabel, donGiaLabel, loaiHoaDonLabel,
            soGioThueLabel, soNgayThueLabel;
    private JComboBox<String> loaiHoaDonComboBox;
    private JButton addButton;

    private AddViewModel viewModel = null;
    private AddDisplayController addDisplayController = null;

    public void setViewModel(AddViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setAddDisplayController(AddDisplayController addDisplayController) {
        this.addDisplayController = addDisplayController;
    }

    public AddDisplayView() {
        setTitle("Thêm Hóa Đơn Khách Sạn");
        buildPanel();
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void buildPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 102, 204)),
                "", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16),
                new Color(0, 102, 204)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        jPanel.setBackground(new Color(245, 245, 245));

        // Tiêu đề
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        titleLabel = new JLabel("Thêm Hóa Đơn");
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

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints inputGbc = new GridBagConstraints();
        inputGbc.insets = new Insets(5, 5, 5, 5);
        inputGbc.anchor = GridBagConstraints.WEST;

        inputGbc.gridx = 0;
        inputGbc.gridy = 0;
        maHDLabel = new JLabel("Mã Hóa Đơn:");
        inputPanel.add(maHDLabel, inputGbc);
        maHDField = new JTextField(15);
        inputGbc.gridx = 1;
        inputPanel.add(maHDField, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        ngayHDLabel = new JLabel("Ngày Hóa Đơn:");
        inputPanel.add(ngayHDLabel, inputGbc);
        ngayHDChooser = new JDateChooser();
        ngayHDChooser.setPreferredSize(new Dimension(150, 25));
        JTextField dateEditor = ((JTextField) ngayHDChooser.getDateEditor().getUiComponent());
        dateEditor.setEditable(false);
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
        loaiHoaDonComboBox = new JComboBox<>();
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
        addButton = new JButton("Thêm Hóa Đơn");
        addButton.setBackground(new Color(0, 102, 204));
        addButton.setForeground(Color.WHITE);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addInvoice();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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

        buttonPanel.add(addButton);

        mainPanel.add(jPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addInvoice() throws SQLException {
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
        AddInvoiceInputDTO addInvoiceInputDTO = new AddInvoiceInputDTO(
                maHD, ngayHD, tenKH, maPhong, donGia, loaiHoaDon, soGioThue, soNgayThue);
        addDisplayController.executeRequest(addInvoiceInputDTO);
    }

    public void updateTypeHoaDon() {
        List<String[]> invoiceList = viewModel.getTypeInvoices();
        loaiHoaDonComboBox.removeAllItems();
        for (String[] invoice : invoiceList) {
            if (invoice.length > 0) {
                loaiHoaDonComboBox.addItem(invoice[0]);
            }
        }
    }

    private void clearInputFields() {
        maHDField.setText("");
        ngayHDChooser.setDate(null);
        tenKHField.setText("");
        maPhongField.setText("");
        donGiaField.setText("");
        soGioThueField.setText("");
        soNgayThueField.setText("");
        loaiHoaDonComboBox.setSelectedIndex(0);
    }

    @Override
    public void update(Observable o, Object arg) {

        if ("TypeInvoice".equals(arg)) {
            updateTypeHoaDon();
            SwingUtilities.invokeLater(() -> {
                setLocationRelativeTo(null);
                setVisible(true);
                toFront();
                requestFocus();
            });
        }

        if (viewModel.getMessage() != null) {
            this.dispose();
            JOptionPane.showMessageDialog(null, viewModel.getMessage(), "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            clearInputFields();
            viewModel.setMessage(null);
            
        } else if (viewModel.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(null, viewModel.getErrorMessage(), "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
            viewModel.setErrorMessage(null);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Khởi tạo ViewModel
        AddViewModel viewModel = new AddViewModel();

        // Khởi tạo các dependency cho GetType
        ConnectionDBType connectionDBType = ConnectionDBType.getInstance();
        GetTypeDatabaseBoundary getTypeDatabaseBoundary = new GetTypeDAOMySQL(connectionDBType);
        GetTypeOutputBoundary getTypeOutputBoundary = new GetTypePresenter();
        GetTypeInputBoundary getTypeInputBoundary = new GetTypeUseCase(getTypeDatabaseBoundary, getTypeOutputBoundary);

        // Khởi tạo AddDisplay components
        AddDisplayOutputBoundary addDisplayOutputBoundary = new AddDisplayPresenter(viewModel);
        AddDisplayUseCase addDisplayUseCase = new AddDisplayUseCase(getTypeInputBoundary, getTypeOutputBoundary,
                addDisplayOutputBoundary);

        // Khởi tạo View
        AddDisplayView addDisplayView = new AddDisplayView();
        addDisplayView.setViewModel(viewModel); // Set ViewModel cho View
        ((AddDisplayPresenter) addDisplayOutputBoundary).addObserver(addDisplayView);

        // Thực thi use case
        addDisplayUseCase.execute();
    }
}