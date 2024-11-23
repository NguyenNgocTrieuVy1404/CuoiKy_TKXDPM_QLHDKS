package findInvoiceDisplayKS.UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import findInvoiceKS.UseCase.FindInvoiceInputDTO;

import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class FindDisplayInvoiceView extends JFrame implements Observer {

    private JComboBox<String> searchCriteriaComboBox;
    private JTextField searchField;

    private FindDisplayInvoiceController findInvoiceController = null;
    private FindViewModel viewModel = null;

    public void setFindInvoiceController(FindDisplayInvoiceController findInvoiceController) {
        this.findInvoiceController = findInvoiceController;
    }

    public void setViewModel(FindViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public FindDisplayInvoiceView() {
        setTitle("Tìm Kiếm Hóa Đơn Khách Sạn");
        buildPanel();
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void buildPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204)),
                "", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16), new Color(0, 102, 204)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 10, 10);

        jPanel.setBackground(new Color(245, 245, 245));

        // Tiêu đề
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Tìm Kiếm Hóa Đơn");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 204));
        jPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

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
        JLabel label = new JLabel("Chọn tiêu chí tìm kiếm:");
        inputPanel.add(label, inputGbc);

        inputGbc.gridx = 1;
        searchCriteriaComboBox = new JComboBox<>();
        inputPanel.add(searchCriteriaComboBox, inputGbc);

        inputGbc.gridx = 0;
        inputGbc.gridy++;
        JLabel searchLabel = new JLabel("Từ khóa tìm kiếm:");
        inputPanel.add(searchLabel, inputGbc);

        inputGbc.gridx = 1;
        searchField = new JTextField(20);
        inputPanel.add(searchField, inputGbc);

        gbc.gridy++;
        jPanel.add(inputPanel, gbc);

        JPanel buttonPanel = new JPanel();
        JButton searchButton = new JButton("Tìm kiếm");
        searchButton.setBackground(new Color(0, 102, 204));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findInvoice();
            }
        });

        buttonPanel.add(searchButton);

        mainPanel.add(jPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void findInvoice() {
        String searchCriteria = searchCriteriaComboBox.getSelectedItem().toString();
        String searchValue = searchField.getText().trim();
        FindInvoiceInputDTO findInvoiceInputDTO = new FindInvoiceInputDTO(searchCriteria, searchValue);
        findInvoiceController.executeRequest(findInvoiceInputDTO);
    }

    @Override
    public void update(Observable o, Object arg) {
        if ("AllCriterion".equals(arg)) {
            SwingUtilities.invokeLater(() -> {
                List<String[]> listAll = viewModel.getAllTypeInvoiceList();
                searchCriteriaComboBox.removeAllItems();
                for (String[] criteria : listAll) {
                    searchCriteriaComboBox.addItem(criteria[0]);
                }
                setLocationRelativeTo(null);
                setVisible(true);
                toFront();
                requestFocus();
            });
        }
        if ("MessageFind".equals(arg)) {
            if (viewModel.getErrorMessage() != null && !viewModel.getErrorMessage().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        viewModel.getErrorMessage(),
                        "Thông báo lỗi",
                        JOptionPane.ERROR_MESSAGE);
                viewModel.setErrorMessage(null);
            } else if (viewModel.getMessage() != null && !viewModel.getMessage().isEmpty()) {
                this.dispose();
                JOptionPane.showMessageDialog(this,
                        viewModel.getMessage(),
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                clearInputFields();
                viewModel.setMessage(null);
            }
        }
    }

    private void clearInputFields() {
        searchField.setText("");
    }

}
