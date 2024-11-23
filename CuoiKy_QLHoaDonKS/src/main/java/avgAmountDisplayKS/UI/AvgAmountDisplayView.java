package avgAmountDisplayKS.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import avgAmountDisplayKS.Database.AvgAmountDisplayDAOMySQL;
import avgAmountDisplayKS.Database.ConnectionDBAvg;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayDatabaseBoundary;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayInputBoundary;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayUseCase;

import avgAmountItemKS.UseCase.AverageAmountInputDTO;

import java.awt.*;

import java.util.Observable;
import java.util.Observer;

public class AvgAmountDisplayView extends JFrame implements Observer {
    private DefaultTableModel summaryTableModel;
    private JTable summaryTable;
    private AvgAmountDisplayController controller = null;
    private AvgAmountDisplayViewModel viewModel = null;
    private JComboBox<String> monthComboBox;

    public void setController(AvgAmountDisplayController controller) {
        this.controller = controller;
    }

    public void setViewModel(AvgAmountDisplayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public AvgAmountDisplayView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tính Trung Bình Thành Tiền Hóa Đơn");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        buildMainPanel();
    }

    private void buildMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createTitlePanel() {
        JLabel titleLabel = new JLabel("Tính Trung Bình Thành Tiền Hóa Đơn", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(createInputPanel(), BorderLayout.NORTH);
        centerPanel.add(createSummaryTable(), BorderLayout.CENTER);
        return centerPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel monthLabel = new JLabel("Chọn Tháng:");
        monthLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        monthComboBox = new JComboBox<>();
        monthComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        monthComboBox.setPreferredSize(new Dimension(100, 30));

        JButton calculateButton = createCalculateButton();

        inputPanel.add(monthLabel);
        inputPanel.add(monthComboBox);
        inputPanel.add(calculateButton);

        return inputPanel;
    }

    private JButton createCalculateButton() {
        JButton calculateButton = new JButton("Tính Trung Bình");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(0, 102, 204));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(e -> calculateAverage());
        return calculateButton;
    }

    private JScrollPane createSummaryTable() {
        String[] columnNames = { "Tổng Thành Tiền", "Trung Bình Thành Tiền" };
        summaryTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        summaryTable = new JTable(summaryTableModel);
        setupTableProperties();

        JScrollPane scrollPane = new JScrollPane(summaryTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return scrollPane;
    }

    private void setupTableProperties() {
        summaryTable.setFont(new Font("Arial", Font.PLAIN, 14));
        summaryTable.setRowHeight(30);

        // Căn giữa nội dung
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        summaryTable.setDefaultRenderer(Object.class, centerRenderer);

        // Định dạng header
        JTableHeader header = summaryTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        ((DefaultTableCellRenderer) header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void calculateAverage() {
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        if (selectedMonth != null) {
            AverageAmountInputDTO inputDTO = new AverageAmountInputDTO(selectedMonth);
            controller.executeRequest(inputDTO);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (viewModel.getErrorMessage() != null) {
            showErrorMessage();
            return;
        }

        if (arg instanceof String) {
            switch ((String) arg) {
                case "UPDATE_MONTH_LIST":
                    updateMonthComboBox();
                    SwingUtilities.invokeLater(() -> {
                        setLocationRelativeTo(null);
                        setVisible(true);
                        toFront();
                        requestFocus();
                    });
                    break;
            }
        }

        if (viewModel.getAverageAmountList() != null) {
            updateSummaryTable();
        }
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(
                this,
                viewModel.getErrorMessage(),
                "Cảnh báo",
                JOptionPane.WARNING_MESSAGE);
        viewModel.setErrorMessage(null);
    }

    private void updateMonthComboBox() {
        monthComboBox.removeAllItems();
        for (String month : viewModel.getMonthList()) {
            monthComboBox.addItem(month);
        }
    }

    private void updateSummaryTable() {
        summaryTableModel.setRowCount(0);
        for (String[] data : viewModel.getAverageAmountList()) {
            summaryTableModel.addRow(data);
        }
    }

    public static void main(String[] args) {
        try {

            AvgAmountDisplayViewModel viewModel = new AvgAmountDisplayViewModel();
            AvgAmountDisplayPresenter presenter = new AvgAmountDisplayPresenter(viewModel);

            ConnectionDBAvg connectionDB = ConnectionDBAvg.getInstance();

            AvgAmountDisplayDatabaseBoundary amountDisplayDatabaseBoundary = new AvgAmountDisplayDAOMySQL(connectionDB);

            AvgAmountDisplayInputBoundary avgAmountDisplayInputBoundary = new AvgAmountDisplayUseCase(
                    amountDisplayDatabaseBoundary, presenter);

            AvgAmountDisplayController controller = new AvgAmountDisplayController(null);
            AvgAmountDisplayView view = new AvgAmountDisplayView();
            presenter.addObserver(view);
            view.setController(controller);
            view.setViewModel(viewModel);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Lỗi khởi tạo ứng dụng: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
