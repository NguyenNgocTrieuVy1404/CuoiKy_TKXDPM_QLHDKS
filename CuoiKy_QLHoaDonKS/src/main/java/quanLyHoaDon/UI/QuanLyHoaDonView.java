package quanLyHoaDon.UI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import addInvoiceDisplayKS.UI.AddDisplayPresenter;
import addInvoiceDisplayKS.UI.AddDisplayView;
import addInvoiceDisplayKS.UI.AddViewModel;
import addInvoiceDisplayKS.UseCase.AddDisplayInputBoundary;
import addInvoiceDisplayKS.UseCase.AddDisplayOutputBoundary;
import addInvoiceDisplayKS.UseCase.AddDisplayUseCase;
import addInvoiceKS.Database.ConnectionDB;
import addInvoiceKS.Database.AddInvoiceDAOMySQL;
import deleteInvoiceDisplayKS.UI.DeleteDisplayController;
import deleteInvoiceDisplayKS.UI.DeleteDisplayPresenter;
import deleteInvoiceDisplayKS.UI.DeleteDisplayView;
import deleteInvoiceDisplayKS.UI.DeleteViewModel;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayUseCase;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayOutputBoundary;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayDatabaseBoundary;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayInputBoundary;
import deleteInvoiceDisplayKS.Database.ConnectionDBDDisplay;
import deleteInvoiceDisplayKS.Database.DeleteDisplayDAOMySQL;
import deleteInvoiceKS.UseCase.DeleteInvoiceOutputBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceUseCase;
import deleteInvoiceKS.UseCase.ResponseDataDelete;

import editInvoiceDisplayKS.UI.EditDisplayPresenter;
import editInvoiceDisplayKS.UI.EditDisplayView;
import editInvoiceDisplayKS.UI.EdittDisplayController;
import editInvoiceDisplayKS.UI.EditViewModel;
import editInvoiceDisplayKS.UseCase.EditDisplayInputBoundary;
import editInvoiceDisplayKS.UseCase.EditDisplayOutputBoundary;
import editInvoiceDisplayKS.UseCase.EditDisplayUseCase;
import editInvoiceKS.UseCase.DataMessageEdit;
import editInvoiceKS.UseCase.EditInvoiceDatabaseBoundary;
import editInvoiceKS.UseCase.EditInvoiceInputBoundary;
import editInvoiceKS.UseCase.EditInvoiceOutputBoundary;
import editInvoiceKS.UseCase.EditInvoiceUseCase;
import editInvoiceKS.UseCase.ResponseDataEdit;
import findInvoiceDisplayKS.Database.ConnectionDBFind;
import findInvoiceDisplayKS.Database.FindDisplayDAOMySQL;
import findInvoiceDisplayKS.UI.FindDisplayInvoiceController;
import findInvoiceDisplayKS.UI.FindDisplayInvoicePresenter;
import findInvoiceDisplayKS.UI.FindDisplayInvoiceView;
import findInvoiceDisplayKS.UI.FindViewModel;
import findInvoiceDisplayKS.UseCase.FindDisplayDatabaseBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayInputBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayOutputBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayUseCase;
import findInvoiceKS.Database.ConnectionDBFindInvoice;
import findInvoiceKS.Database.FindInvoiceDAOMySQL;
import findInvoiceKS.UseCase.DataMessageFind;
import findInvoiceKS.UseCase.FindInvoiceDatabaseBoundary;
import findInvoiceKS.UseCase.FindInvoiceInputBoundary;
import findInvoiceKS.UseCase.FindInvoiceOutputBoundary;
import findInvoiceKS.UseCase.FindInvoiceUseCase;
import findInvoiceKS.UseCase.ResponseDataFind;
import editInvoiceKS.Database.ConnectionDBEdit;
import editInvoiceKS.Database.EditInvoiceDAOMySQL;
import deleteInvoiceKS.Database.ConnectionDBDelete;
import deleteInvoiceKS.Database.DeleteInvoiceDAOMySQL;
import deleteInvoiceKS.UseCase.DataMessageDelete;
import deleteInvoiceKS.UseCase.DeleteInvoiceDatabaseBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceInputBoundary;
import getAllInvoiceList.DataBase.ConnectionDBGet;
import getAllInvoiceList.DataBase.GetInvoiceListDAOMySQL;
import getAllInvoiceList.UI.GetInvoiceListPresenter;
import getAllInvoiceList.UseCase.GetInvoiceListDatabaseBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListInputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListUseCase;
import getAllInvoiceList.UseCase.GetInvoiceListOutputBoundary;
import getInvoiceType.Database.ConnectionDBType;
import getInvoiceType.Database.GetTypeDAOMySQL;
import getInvoiceType.UI.GetTypePresenter;
import getInvoiceType.UseCase.GetTypeDatabaseBoundary;
import getInvoiceType.UseCase.GetTypeInputBoundary;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeUseCase;

import quanLyHoaDon.UseCase.InputRequestDTO;
import quanLyHoaDon.UseCase.QuanLyInputBoundary;
import quanLyHoaDon.UseCase.QuanLyOutputBoundary;
import quanLyHoaDon.UseCase.QuanLyUseCase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import addInvoiceKS.UseCase.AddInvoiceDatabaseBoundary;
import addInvoiceKS.UseCase.AddInvoiceInputBoundary;
import addInvoiceKS.UseCase.AddInvoiceOutputBoundary;
import addInvoiceKS.UseCase.AddInvoiceUseCase;
import addInvoiceKS.UseCase.DataMessageAdd;
import addInvoiceKS.UseCase.ResponseDataAdd;
import avgAmountDisplayKS.Database.AvgAmountDisplayDAOMySQL;
import avgAmountDisplayKS.Database.ConnectionDBAvg;
import avgAmountDisplayKS.UI.AvgAmountDisplayController;
import avgAmountDisplayKS.UI.AvgAmountDisplayPresenter;
import avgAmountDisplayKS.UI.AvgAmountDisplayView;
import avgAmountDisplayKS.UI.AvgAmountDisplayViewModel;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayDatabaseBoundary;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayInputBoundary;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayUseCase;
import addInvoiceDisplayKS.UI.AddDisplayController;
import avgAmountItemKS.Database.AverageAmountDAOMySQL;
import avgAmountItemKS.Database.ConnectionDBAmount;
import avgAmountItemKS.Entity.AverageAmount;
import avgAmountItemKS.Entity.AverageAmountImpl;
import avgAmountItemKS.UseCase.AverageAmountOutputBoundary;
import avgAmountItemKS.UseCase.AverageAmountUseCase;
import avgAmountItemKS.UseCase.AverageAmountInputBoundary;
import avgAmountItemKS.UseCase.AverageAmountDatabaseBoundary;

import totalQuantityItemDisplayKS.UI.TotalQuantityDisplayController;
import totalQuantityItemDisplayKS.UI.TotalQuantityDisplayPresenter;
import totalQuantityItemDisplayKS.UI.TotalQuantityView;
import totalQuantityItemDisplayKS.UI.TotalViewModel;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayInputBoundary;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayOutputBoundary;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayUseCase;
import totalQuantityItemKS.Database.ConnectionDBTotal;
import totalQuantityItemKS.Database.TotalQuantityDAOMySQL;
import totalQuantityItemKS.Entity.TotalQuantityBusinessRules;
import totalQuantityItemKS.Entity.TotalQuantityBusinessRulesImpl;
import totalQuantityItemKS.UseCase.TotalQuantityDatabaseBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityInputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityOutputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityUseCase;

import javax.swing.border.Border;
import javax.swing.border.AbstractBorder;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.Insets;

class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String text) {
        super(text);
        this.radius = 20;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
    }
}

class CustomShadowBorder extends AbstractBorder {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ đổ bóng
        int shadowSize = 3;
        for (int i = 0; i < shadowSize; i++) {
            g2.setColor(new Color(0, 0, 0, 20 - i * 6));
            g2.drawRoundRect(x + i, y + i, width - 2 * i - 1, height - 2 * i - 1, 10, 10);
        }

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 4, 4, 4);
    }
}

// Custom Border cho title với đổ bóng
class TitleBorder implements Border {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 30));
        g2.setFont(c.getFont());

        if (c instanceof JLabel) {
            String text = ((JLabel) c).getText();
            FontMetrics fm = g2.getFontMetrics();
            int textX = x + (width - fm.stringWidth(text)) / 2;
            int textY = y + ((height - fm.getHeight()) / 2) + fm.getAscent();
            g2.drawString(text, textX + 1, textY + 1);
        }

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 3, 1);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}

// Custom Border cho panel với đổ bóng và bo góc
class RoundedShadowBorder extends AbstractBorder {
    private Color shadowColor;
    private int shadowSize;
    private int cornerRadius;

    public RoundedShadowBorder(Color shadowColor, int shadowSize, int cornerRadius) {
        this.shadowColor = shadowColor;
        this.shadowSize = shadowSize;
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ đổ bóng
        for (int i = 0; i < shadowSize; i++) {
            g2.setColor(new Color(
                    shadowColor.getRed(),
                    shadowColor.getGreen(),
                    shadowColor.getBlue(),
                    shadowColor.getAlpha() / (i + 1)));
            g2.drawRoundRect(
                    x + i,
                    y + i,
                    width - 2 * i - 1,
                    height - 2 * i - 1,
                    cornerRadius,
                    cornerRadius);
        }

        // Vẽ viền
        g2.setColor(c.getBackground());
        g2.drawRoundRect(
                x + shadowSize,
                y + shadowSize,
                width - 2 * shadowSize - 1,
                height - 2 * shadowSize - 1,
                cornerRadius,
                cornerRadius);

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(shadowSize + 1, shadowSize + 1, shadowSize + 2, shadowSize + 1);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}

public class QuanLyHoaDonView extends JFrame implements Observer {
    private RoundedButton addButton;
    private RoundedButton deleteButton;
    private RoundedButton editButton;
    private RoundedButton searchButton;
    private JTable jTableSet;
    private DefaultTableModel jTableModel;

    private QuanLyViewModel quanLyViewModel;
    private QuanLyController quanLyController;

    public void setQuanLyViewModel(QuanLyViewModel quanLyViewModel) {
        this.quanLyViewModel = quanLyViewModel;
    }

    public void setQuanLyController(QuanLyController quanLyController) {
        this.quanLyController = quanLyController;
    }

    public QuanLyHoaDonView() {
        setTitle("App Hóa Đơn Khách Sạn");
        buildPanel();
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void buildPanel() {
        // Set up frame
        setTitle("Quản Lý Hóa Đơn Khách Sạn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 1800));

        // Tạo thanh menu hiện đại
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255, 255, 255));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(233, 236, 239)));

        JMenu statisticsMenu = new JMenu("Thống kê");
        statisticsMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Menu items
        JMenuItem totalQuantityItem = new JMenuItem("Tính tổng số lượng");
        JMenuItem averageAmountItem = new JMenuItem("Tính trung bình thành tiền");

        styleMenuItem(totalQuantityItem);
        styleMenuItem(averageAmountItem);

        // Thêm action listeners cho menu items
        totalQuantityItem.addActionListener(e -> {
            InputRequestDTO inputRequestDTO = new InputRequestDTO("Tính tổng số lượng");
            quanLyController.executeRequest(inputRequestDTO);
        });

        averageAmountItem.addActionListener(e -> {
            InputRequestDTO inputRequestDTO = new InputRequestDTO("Tính trung bình thành tiền");
            quanLyController.executeRequest(inputRequestDTO);
        });

        statisticsMenu.add(totalQuantityItem);
        statisticsMenu.add(averageAmountItem);
        menuBar.add(statisticsMenu);
        setJMenuBar(menuBar);

        // Panel chính với màu nền nhẹ nhàng
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(new Color(248, 249, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // Header Panel với khoảng cách nhỏ hơn
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                new CustomShadowBorder(),
                // Giảm padding của header panel
                BorderFactory.createEmptyBorder(10, 25, 10, 25) // Giảm từ 20 xuống 10
        ));

        // Tiêu đề với khoảng cách nhỏ hơn
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(240, 242, 255),
                        getWidth(), getHeight(), new Color(250, 252, 255));
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Quản Lý Hóa Đơn");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        titleLabel.setForeground(new Color(63, 100, 240));

        // Tạo compound border với padding và đổ bóng
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
                new TitleBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // Panel chứa title với border đổ bóng và bo góc
        JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleContainer.setBackground(new Color(240, 242, 255));
        titleContainer.setBorder(BorderFactory.createCompoundBorder(
                new RoundedShadowBorder(new Color(0, 0, 0, 50), 3, 15),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        titleContainer.add(titleLabel);

        // Thêm hiệu ứng hover
        titleContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                titleLabel.setForeground(new Color(41, 65, 240));
                titleContainer.setBackground(new Color(245, 247, 255));
                titleContainer.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                titleLabel.setForeground(new Color(63, 100, 240));
                titleContainer.setBackground(new Color(240, 242, 255));
                titleContainer.repaint();
            }
        });

        // Thêm vào header panel
        headerPanel.add(titleContainer, BorderLayout.CENTER);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                new CustomShadowBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // Cấu hình bảng
        String[] columns = { "Mã HD", "Ngày HD", "Tên KH", "Mã Phòng", "Đơn giá", "Loại HD", "Số Giờ", "Số Ngày" };
        jTableModel = new DefaultTableModel(columns, 0);
        jTableSet = new JTable(jTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        styleTable(jTableSet);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Tạo các nút với kích thước lớn hơn
        addButton = createActionButton("Thêm mới", new Color(40, 167, 69));
        editButton = createActionButton("Chỉnh sửa", new Color(255, 193, 7));
        deleteButton = createActionButton("Xóa", new Color(220, 53, 69));
        searchButton = createActionButton("Tìm kiếm", new Color(0, 123, 255));

        // Action listeners
        addButton.addActionListener(e -> {
            InputRequestDTO inputRequestDTO = new InputRequestDTO("Thêm hóa đơn");
            quanLyController.executeRequest(inputRequestDTO);
        });

        editButton.addActionListener(e -> {
            InputRequestDTO inputRequestDTO = new InputRequestDTO("Sửa hóa đơn");
            quanLyController.executeRequest(inputRequestDTO);
        });

        deleteButton.addActionListener(e -> {
            InputRequestDTO inputRequestDTO = new InputRequestDTO("Xóa hóa đơn");
            quanLyController.executeRequest(inputRequestDTO);
        });

        searchButton.addActionListener(e -> {
            InputRequestDTO inputRequestDTO = new InputRequestDTO("Tìm hóa đơn");
            quanLyController.executeRequest(inputRequestDTO);
        });

        actionPanel.add(addButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);
        actionPanel.add(searchButton);

        tablePanel.add(actionPanel, BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(jTableSet), BorderLayout.CENTER);

        // Thêm components vào main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    // Helper method để tạo button với style thống nhất
    private RoundedButton createActionButton(String text, Color color) {
        RoundedButton button = new RoundedButton(text);
        // Giảm chiều cao của button
        button.setPreferredSize(new Dimension(130, 35));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Thêm hiệu ứng hover mượt mà hơn
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
                button.setFont(new Font("Segoe UI", Font.BOLD, 15)); // Phóng to chữ khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
                button.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Trở về kích thước bình thường
            }
        });

        return button;
    }

    // Custom TextField với viền và hiệu ứng focus
    class CustomTextField extends JTextField {
        public CustomTextField(int columns) {
            super(columns);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(206, 212, 218)),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }
    }

    // Helper methods
    private void styleTable(JTable table) {
        // Header style
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(248, 249, 250));
        header.setForeground(new Color(73, 80, 87));
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(233, 236, 239)));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);

        // Table style
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(40);
        table.setGridColor(new Color(233, 236, 239));
        table.setShowVerticalLines(false);
        table.setSelectionBackground(new Color(233, 236, 239));
        table.setSelectionForeground(new Color(33, 37, 41));

        // Cell renderer for alignment
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void styleMenuItem(JMenuItem item) {
        item.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        item.setBackground(Color.WHITE);
        item.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                item.setBackground(new Color(233, 236, 239));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                item.setBackground(Color.WHITE);
            }
        });
    }

    public void executeGetList() {
        InputRequestDTO inputRequestDTO = new InputRequestDTO("GetList");
        quanLyController.executeRequest(inputRequestDTO);
    }

    public void updateHoaDonFind(List<String[]> invoiceList) throws ParseException {
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

    public void updateHoaDon() throws java.text.ParseException {
        List<String[]> invoiceList = quanLyViewModel.getListInvoices();
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

    @Override
    public void update(Observable o, Object arg) {
        if ("GETLIST".equals(arg)) {
            try {
                updateHoaDon();
                SwingUtilities.invokeLater(() -> {
                    setLocationRelativeTo(null);
                    setVisible(true);
                    toFront();
                    requestFocus();
                });
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if ("ThemHoaDon".equals(arg)) {
            executeGetList();
        }
        if ("SuaHoaDon".equals(arg)) {
            executeGetList();
        }
        if ("DELETE".equals(arg)) {
            executeGetList();
        } else if (arg instanceof List<?>) {
            List<?> list = (List<?>) arg;
            if (!list.isEmpty() && list.get(0) instanceof String[]) {
                try {
                    updateHoaDonFind((List<String[]>) list);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // GetInvoiceList
        QuanLyViewModel quanLyViewModel = new QuanLyViewModel();
        ConnectionDBGet connectionDBGet = ConnectionDBGet.getInstance();
        GetInvoiceListDatabaseBoundary getInvoiceListDatabaseBoundary = new GetInvoiceListDAOMySQL(connectionDBGet);
        QuanLyOutputBoundary quanLyOutputBoundary = new QuanLyPresenter(quanLyViewModel);
        GetInvoiceListOutputBoundary getInvoiceOutputBoundary = new GetInvoiceListPresenter();
        GetInvoiceListInputBoundary getInvoiceListInputBoundary = new GetInvoiceListUseCase(
                getInvoiceListDatabaseBoundary, getInvoiceOutputBoundary);

        // AddDisplay (Type)
        ConnectionDBType connectionDBType = ConnectionDBType.getInstance();
        GetTypeDatabaseBoundary getTypeDatabaseBoundary = new GetTypeDAOMySQL(connectionDBType);
        GetTypeOutputBoundary getTypeOutputBoundary = new GetTypePresenter();
        GetTypeInputBoundary getTypeInputBoundary = new GetTypeUseCase(
                getTypeDatabaseBoundary,
                getTypeOutputBoundary);

        // AddInvoice
        ConnectionDB connectionDB = ConnectionDB.getInstance();
        ResponseDataAdd dataMessage = new DataMessageAdd();
        AddInvoiceDatabaseBoundary addInvoiceDatabaseBoundary = new AddInvoiceDAOMySQL(connectionDB);
        AddViewModel addDisplayViewModel = new AddViewModel();
        AddInvoiceOutputBoundary addInvoiceOutputBoundary = new AddDisplayPresenter(addDisplayViewModel);
        AddInvoiceInputBoundary addInvoiceInputBoundary = new AddInvoiceUseCase(
                addInvoiceDatabaseBoundary,
                addInvoiceOutputBoundary,
                dataMessage);

        AddDisplayOutputBoundary addDisplayOutputBoundary = new AddDisplayPresenter(addDisplayViewModel);
        AddDisplayInputBoundary addDisplayInputBoundary = new AddDisplayUseCase(
                getTypeInputBoundary,
                getTypeOutputBoundary,
                addDisplayOutputBoundary);

        // AddDisplay View
        AddDisplayController addDisplayController = new AddDisplayController(addInvoiceInputBoundary);
        AddDisplayView addDisplayView = new AddDisplayView();
        addDisplayView.setViewModel(addDisplayViewModel);
        addDisplayView.setAddDisplayController(addDisplayController);
        ((AddDisplayPresenter) addDisplayOutputBoundary).addObserver(addDisplayView);
        ((AddDisplayPresenter) addInvoiceOutputBoundary).addObserver(addDisplayView);

        // DeleteDisplay
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        ConnectionDBDDisplay connectionDBDDisplay = ConnectionDBDDisplay.getInstance();
        DeleteDisplayDatabaseBoundary deleteDisplayDatabaseBoundary = new DeleteDisplayDAOMySQL(connectionDBDDisplay);
        DeleteDisplayOutputBoundary deleteDisplayOutputBoundary = new DeleteDisplayPresenter(deleteViewModel);

        // Delete
        ResponseDataDelete dataMessageDelete = new DataMessageDelete();
        ConnectionDBDelete connectionDBDelete = ConnectionDBDelete.getInstance();
        DeleteInvoiceDatabaseBoundary deleteInvoiceDatabaseBoundary = new DeleteInvoiceDAOMySQL(connectionDBDelete);
        DeleteInvoiceOutputBoundary deleteInvoiceOutputBoundary = (DeleteInvoiceOutputBoundary) deleteDisplayOutputBoundary;
        DeleteInvoiceInputBoundary deleteInvoiceUseCase = new DeleteInvoiceUseCase(
                deleteInvoiceDatabaseBoundary,
                deleteInvoiceOutputBoundary,
                dataMessageDelete);

        DeleteDisplayController deleteDisplayController = new DeleteDisplayController(deleteInvoiceUseCase);

        DeleteDisplayInputBoundary deleteDisplayUseCase = new DeleteDisplayUseCase(
                deleteDisplayDatabaseBoundary,
                deleteDisplayOutputBoundary);

        DeleteDisplayView deleteDisplayView = new DeleteDisplayView();
        ((DeleteDisplayPresenter) deleteDisplayOutputBoundary).addObserver(deleteDisplayView);
        deleteDisplayView.setDeleteDisplayController(deleteDisplayController);
        deleteDisplayView.setDeleteIVModel(deleteViewModel);

        // EditDisplay
        EditViewModel editViewModel = new EditViewModel();
        EditDisplayOutputBoundary editDisplayOutputBoundary = new EditDisplayPresenter(editViewModel);

        // EditInvoice
        ResponseDataEdit dataMessageEdit = new DataMessageEdit();
        ConnectionDBEdit connectionDBEdit = ConnectionDBEdit.getInstance();
        EditInvoiceDatabaseBoundary editInvoiceDatabaseBoundary = new EditInvoiceDAOMySQL(connectionDBEdit);
        EditInvoiceOutputBoundary editInvoiceOutputBoundary = (EditInvoiceOutputBoundary) editDisplayOutputBoundary;
        EditInvoiceInputBoundary editInvoiceUseCase = new EditInvoiceUseCase(
                editInvoiceDatabaseBoundary,
                editInvoiceOutputBoundary,
                dataMessageEdit);

        // EditDisplay
        EdittDisplayController editDisplayController = new EdittDisplayController(editInvoiceUseCase);
        EditDisplayInputBoundary editDisplayUseCase = new EditDisplayUseCase(
                getInvoiceListInputBoundary,
                getInvoiceOutputBoundary,
                getTypeInputBoundary,
                getTypeOutputBoundary,
                editDisplayOutputBoundary);

        editDisplayController.setEditDisplayInputBoundary(editDisplayUseCase);
        EditDisplayView editDisplayView = new EditDisplayView();
        ((EditDisplayPresenter) editDisplayOutputBoundary).addObserver(editDisplayView);
        editDisplayView.setEdittDisplayController(editDisplayController);
        editDisplayView.setModelView(editViewModel);

        // AvgAmountDisplay
        ConnectionDBAvg connectionDBAvg = ConnectionDBAvg.getInstance();
        AvgAmountDisplayViewModel avgViewModel = new AvgAmountDisplayViewModel();
        AvgAmountDisplayPresenter avgPresenter = new AvgAmountDisplayPresenter(avgViewModel);
        AvgAmountDisplayDatabaseBoundary avgDatabaseBoundary = new AvgAmountDisplayDAOMySQL(connectionDBAvg);
        AvgAmountDisplayInputBoundary avgInputBoundary = new AvgAmountDisplayUseCase(
                avgDatabaseBoundary,
                avgPresenter);
        // AverageAmount
        AverageAmount averageAmount = new AverageAmountImpl();
        ConnectionDBAmount connectionDBAmount = ConnectionDBAmount.getInstance();
        AverageAmountDatabaseBoundary averageAmountDatabaseBoundary = new AverageAmountDAOMySQL(connectionDBAmount);
        AverageAmountOutputBoundary averageAmountOutputBoundary = avgPresenter;
        AverageAmountInputBoundary averageAmountInputBoundary = new AverageAmountUseCase(
                averageAmountOutputBoundary,
                averageAmountDatabaseBoundary,
                averageAmount);
        // AvgAmount View
        AvgAmountDisplayController avgController = new AvgAmountDisplayController(averageAmountInputBoundary);
        AvgAmountDisplayView avgView = new AvgAmountDisplayView();
        avgPresenter.addObserver(avgView);
        avgView.setController(avgController);
        avgView.setViewModel(avgViewModel);

        // FindInvoice + FindDisplay
        ConnectionDBFind connectionDBFind = ConnectionDBFind.getInstance();
        ConnectionDBFindInvoice connectionDBFindInvoice = ConnectionDBFindInvoice.getInstance();
        FindDisplayDatabaseBoundary findDisplayDAO = new FindDisplayDAOMySQL(connectionDBFind);
        FindInvoiceDatabaseBoundary findInvoiceDAO = new FindInvoiceDAOMySQL(connectionDBFindInvoice);
        FindViewModel findDisplayViewModel = new FindViewModel();
        FindDisplayOutputBoundary findDisplayPresenter = new FindDisplayInvoicePresenter(findDisplayViewModel);
        FindInvoiceOutputBoundary findInvoicePresenter = new FindDisplayInvoicePresenter(findDisplayViewModel);
        FindDisplayInputBoundary findDisplayInputBoundary = new FindDisplayUseCase(findDisplayDAO,
                findDisplayPresenter);
        ResponseDataFind dataMessageFind = new DataMessageFind();
        FindInvoiceInputBoundary findInvoiceInputBoundary = new FindInvoiceUseCase(findInvoiceDAO, findInvoicePresenter,
                dataMessageFind);

        FindDisplayInvoiceView findDisplayInvoiceView = new FindDisplayInvoiceView();
        FindDisplayInvoiceController findDisplayInvoiceController = new FindDisplayInvoiceController(
                findInvoiceInputBoundary);

        findDisplayInvoiceView.setFindInvoiceController(findDisplayInvoiceController);
        findDisplayInvoiceView.setViewModel(findDisplayViewModel);

        ((FindDisplayInvoicePresenter) findDisplayPresenter).addObserver(findDisplayInvoiceView);
        ((FindDisplayInvoicePresenter) findInvoicePresenter).addObserver(findDisplayInvoiceView);

        // TotalQuantity
        TotalViewModel totalViewModel = new TotalViewModel();
        TotalQuantityView totalQuantityView = new TotalQuantityView();

        ConnectionDBTotal connectionDBTotal = ConnectionDBTotal.getInstance();
        TotalQuantityDatabaseBoundary totalQuantityDAO = new TotalQuantityDAOMySQL(connectionDBTotal);
        TotalQuantityBusinessRules businessRules = new TotalQuantityBusinessRulesImpl();

        TotalQuantityOutputBoundary totalQuantityOutputBoundary = new TotalQuantityDisplayPresenter(totalViewModel);
        TotalQuantityDisplayOutputBoundary totalDisplayOutputBoundary = (TotalQuantityDisplayPresenter) totalQuantityOutputBoundary;

        TotalQuantityInputBoundary totalQuantityUseCase = new TotalQuantityUseCase(
                totalQuantityOutputBoundary,
                totalQuantityDAO,
                businessRules);

        TotalQuantityDisplayInputBoundary totalDisplayUseCase = new TotalQuantityDisplayUseCase(
                getTypeInputBoundary,
                totalDisplayOutputBoundary,
                getTypeOutputBoundary);

        TotalQuantityDisplayController totalQuantityController = new TotalQuantityDisplayController(
                totalQuantityUseCase);

        totalQuantityView.settDisplayController(totalQuantityController);
        totalQuantityView.setViewModel(totalViewModel);

        ((TotalQuantityDisplayPresenter) totalQuantityOutputBoundary).addObserver(totalQuantityView);

        // QuanLyUseCase Đây nhó
        QuanLyInputBoundary quanLyInputBoundary = new QuanLyUseCase(
                quanLyOutputBoundary,
                getInvoiceListInputBoundary,
                getInvoiceOutputBoundary,
                addDisplayInputBoundary,
                deleteDisplayUseCase,
                editDisplayUseCase,
                avgInputBoundary,
                findDisplayInputBoundary,
                totalDisplayUseCase);

        QuanLyController quanLyController = new QuanLyController(quanLyInputBoundary);
        QuanLyHoaDonView introView = new QuanLyHoaDonView();

        ((QuanLyPresenter) quanLyOutputBoundary).addObserver(introView);
        ((AddDisplayPresenter) addInvoiceOutputBoundary).addObserver(introView);
        ((DeleteDisplayPresenter) deleteDisplayOutputBoundary).addObserver(introView);
        ((EditDisplayPresenter) editDisplayOutputBoundary).addObserver(introView);
        ((FindDisplayInvoicePresenter) findInvoicePresenter).addObserver(introView);

        introView.setQuanLyController(quanLyController);
        introView.setQuanLyViewModel(quanLyViewModel);
        introView.executeGetList();
    }
}
