package deleteInvoiceDisplayKS.UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import deleteInvoiceKS.UseCase.DataMessageDelete;
import deleteInvoiceKS.UseCase.ResponseDataDelete;
import deleteInvoiceKS.Database.ConnectionDBDelete;
import deleteInvoiceKS.Database.DeleteInvoiceDAOMySQL;
import deleteInvoiceKS.UseCase.DeleteInvoiceDatabaseBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceOutputBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceUseCase;
import deleteInvoiceKS.UseCase.DeleteInvoiceInputDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class DeleteDisplayView extends JFrame implements Observer {
    private JComboBox<String> maHDComboBox;
    private JLabel titleLabel, maHDLabel;
    private JButton deleteButton;

    private DeleteDisplayController deleteDisplayController = null;
    private DeleteViewModel deleteIVModel = null;

    public void setDeleteDisplayController(DeleteDisplayController deleteDisplayController) {
        this.deleteDisplayController = deleteDisplayController;
    }

    public void setDeleteIVModel(DeleteViewModel deleteIVModel) {
        this.deleteIVModel = deleteIVModel;
    }

    public DeleteDisplayView() {
        setTitle("Xóa Hóa Đơn");
        buildPanel();
        setSize(400, 300);
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
        titleLabel = new JLabel("Xóa Hóa Đơn");
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
        maHDLabel = new JLabel("Mã Hóa Đơn:");
        inputPanel.add(maHDLabel, inputGbc);
        maHDComboBox = new JComboBox<>();
        maHDComboBox.setEditable(true);
        Dimension comboBoxSize = new Dimension(150, 30);
        maHDComboBox.setPreferredSize(comboBoxSize);
        maHDComboBox.setMaximumSize(comboBoxSize);
        maHDComboBox.setMinimumSize(comboBoxSize);
        inputGbc.gridx = 1;
        inputPanel.add(maHDComboBox, inputGbc);

        jPanel.add(inputPanel, gbc);

        JPanel buttonPanel = new JPanel();
        deleteButton = new JButton("Xóa Hóa Đơn");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInvoice();
            }
        });

        buttonPanel.add(deleteButton);

        mainPanel.add(jPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void deleteInvoice() {
        String maHD = maHDComboBox.getEditor().getItem().toString();
        DeleteInvoiceInputDTO deleteInvoiceInputDTO = new DeleteInvoiceInputDTO(maHD);
        deleteDisplayController.executeRequest(deleteInvoiceInputDTO);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (deleteIVModel.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    deleteIVModel.getErrorMessage(),
                    "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
            deleteIVModel.setErrorMessage(null);
            return;
        }
        if (deleteIVModel.getMessage() != null) {
            this.dispose();
            JOptionPane.showMessageDialog(
                    this,
                    deleteIVModel.getMessage(),
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            deleteIVModel.setMessage(null);
            return;
        }

        if (arg != null) {
            switch (arg.toString()) {
                case "UPDATE_INVOICE_IDS":
                    maHDComboBox.removeAllItems();
                    for (String id : deleteIVModel.getInvoiceIds()) {
                        maHDComboBox.addItem(id);
                    }
                    SwingUtilities.invokeLater(() -> {
                        setLocationRelativeTo(null);
                        setVisible(true);
                        toFront();
                        requestFocus();
                    });
                    break;

                case "CONFIRM_DELETE":
                    int response = JOptionPane.showConfirmDialog(
                            this,
                            deleteIVModel.getConfirmMessage(),
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        deleteInvoice();
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DeleteViewModel deleteIVModel = new DeleteViewModel();
        ConnectionDBDelete connectionDBDelete = ConnectionDBDelete.getInstance();
        ResponseDataDelete dataMessage = new DataMessageDelete();
        DeleteInvoiceDatabaseBoundary deleteInvoiceDatabaseBoundary = new DeleteInvoiceDAOMySQL(connectionDBDelete);
        DeleteInvoiceOutputBoundary deleteInvoiceOutputBoundary = new DeleteDisplayPresenter(deleteIVModel);
        DeleteInvoiceUseCase deleteInvoiceUseCase = new DeleteInvoiceUseCase(deleteInvoiceDatabaseBoundary,
                deleteInvoiceOutputBoundary, dataMessage);

        DeleteDisplayController dInvoiceController = new DeleteDisplayController(deleteInvoiceUseCase);

        DeleteDisplayView dInvoiceView = new DeleteDisplayView();

        ((DeleteDisplayPresenter) deleteInvoiceOutputBoundary).addObserver(dInvoiceView);
        dInvoiceView.setDeleteDisplayController(dInvoiceController);
        dInvoiceView.setDeleteIVModel(deleteIVModel);
    }
}
