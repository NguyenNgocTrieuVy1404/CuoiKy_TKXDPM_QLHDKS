package totalQuantityItemDisplayKS.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import totalQuantityItemKS.UseCase.TotalQuantityInputDTO;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TotalQuantityView extends JFrame implements Observer {
    private JComboBox<String> invoiceTypeComboBox;
    private DefaultTableModel summaryTableModel;
    private JTable summaryTable;
    private TotalQuantityDisplayController tDisplayController;
    private TotalViewModel viewModel = null;

    public void settDisplayController(TotalQuantityDisplayController tDisplayController) {
        this.tDisplayController = tDisplayController;
    }

    public void setViewModel(TotalViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public TotalQuantityView() {
        setTitle("Tính Tổng Số Lượng Hóa Đơn");
        buildPanel();
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void buildPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new BorderLayout(0, 20));
        
        JLabel titleLabel = new JLabel("Tính Tổng Số Lượng Từng Loại Hóa Đơn", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JLabel typeLabel = new JLabel("Chọn loại hóa đơn:");
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        invoiceTypeComboBox = new JComboBox<>();
        invoiceTypeComboBox.setPreferredSize(new Dimension(250, 35));
        invoiceTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton calculateButton = new JButton("Tính Tổng Số Lượng");
        calculateButton.setPreferredSize(new Dimension(200, 35));
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(0, 87, 146));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(e -> totalQuantity());

        selectionPanel.add(typeLabel);
        selectionPanel.add(invoiceTypeComboBox);
        selectionPanel.add(calculateButton);
        
        topPanel.add(selectionPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout(0, 15));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JPanel tableTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel tableLabel = new JLabel("Kết Quả Thống Kê", SwingConstants.CENTER);
        tableLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tableLabel.setForeground(new Color(44, 62, 80));
        tableTitlePanel.add(tableLabel);
        tablePanel.add(tableTitlePanel, BorderLayout.NORTH);

        summaryTableModel = new DefaultTableModel(
                new String[]{"Loại Hóa Đơn", "Tổng Số Lượng", "Tổng Thành Tiền"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        summaryTable = new JTable(summaryTableModel);
        summaryTable.setFont(new Font("Arial", Font.PLAIN, 14));
        summaryTable.setRowHeight(35);
        summaryTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        summaryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        summaryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < summaryTable.getColumnCount(); i++) {
            summaryTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = summaryTable.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setBackground(new Color(108, 117, 125));
                setForeground(Color.WHITE);
                setFont(new Font("Arial", Font.BOLD, 16));
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(summaryTable);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
    }

    public void totalQuantity() {
        String selectedType = (String) invoiceTypeComboBox.getSelectedItem();
        if (selectedType != null) {
            TotalQuantityInputDTO totalQuantityInputDTO = new TotalQuantityInputDTO(selectedType);
            tDisplayController.executeRequest(totalQuantityInputDTO);
        }
    }

    public void updateTotalQuantity() {
        List<String[]> totalQuantityList = viewModel.getTotalQuantityList();
        summaryTableModel.setRowCount(0);

        for (String[] totalQuanty : totalQuantityList) {
            Object[] rowData = {
                    totalQuanty[0],
                    totalQuanty[1],
                    totalQuanty[2]
            };
            summaryTableModel.addRow(rowData);
        }
        summaryTableModel.fireTableDataChanged();
    }

    public void updateTypeHoaDon() {
        List<String[]> invoiceList = viewModel.getLoaiHoaDon();
        invoiceTypeComboBox.removeAllItems();
        for (String[] invoice : invoiceList) {
            if (invoice.length > 0) {
                invoiceTypeComboBox.addItem(invoice[0]);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if("TotalQuantity".equals(arg)){
            if (viewModel.getTotalQuantityList() != null) {
                updateTotalQuantity();
            }
        }
        if ("TypeInvoice".equals(arg)) {
            updateTypeHoaDon();
            SwingUtilities.invokeLater(() -> {
                setLocationRelativeTo(null);
                setVisible(true);
                toFront();
                requestFocus();
            });
        }
    }
}
