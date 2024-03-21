package view;
import controller.*;
import model.StockCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockCardInternalFrame extends JInternalFrame {

    private JLabel lblStockCode, lblStockName, lblStockType, lblUnit, lblBarcode, lblVatType, lblDescription, lblCreationTime;
    private JTextField txtStockCode, txtStockName, txtBarcode, txtCreationTime;
    private JComboBox<String> cmbStockType, cmbUnit, cmbVatType;
    private JTextArea txtDescription;
    private JButton btnAdd, btnUpdate, btnDelete, btnCopy;
    private CopyStockCardController copyController;
    private DeleteStockCardController deleteController;
    private SaveStockCardController saveController;
    private UpdateStockCardController updateController;

    private Connection connection;

    public StockCardInternalFrame(Connection connection) {
        this.connection = connection;

        copyController = new CopyStockCardController();
        deleteController = new DeleteStockCardController();
        saveController = new SaveStockCardController();
        updateController = new UpdateStockCardController();




        // Internal Frame ayarları
        setTitle("Stok Kartı");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setSize(600, 400);
        setLayout(new GridLayout(0, 2));

        // UI bileşenlerinin oluşturulması
        lblStockCode = new JLabel("Stok Kodu:");
        txtStockCode = new JTextField(20);
        lblStockName = new JLabel("Stok Adı:");
        txtStockName = new JTextField(20);
        lblStockType = new JLabel("Stok Tipi:");
        cmbStockType = new JComboBox<>(new String[]{"1", "2", "3"});
        lblUnit = new JLabel("Birimi:");
        cmbUnit = new JComboBox<>(new String[]{"Adet", "Kg", "Litre"});
        lblBarcode = new JLabel("Barkodu:");
        txtBarcode = new JTextField(20);
        lblVatType = new JLabel("KDV Tipi:");
        cmbVatType = new JComboBox<>();
        lblDescription = new JLabel("Açıklama:");
        txtDescription = new JTextArea(5, 20);
        lblCreationTime = new JLabel("Oluşturma Zamanı:");
        txtCreationTime = new JTextField();
        btnAdd = new JButton("Ekle");
        btnUpdate = new JButton("Güncelle");
        btnDelete = new JButton("Sil");
        btnCopy = new JButton("Kopyala");

        // KDV Tipi ComboBox'ını doldurma
        fillVatTypeComboBox();

        // Oluşturma zamanını ayarlama
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        txtCreationTime.setText(sdf.format(new Date()));
        txtCreationTime.setEditable(false); // Kullanıcı tarafından değiştirilemez

        // UI bileşenlerinin internal frame'e eklenmesi
        add(lblStockCode);
        add(txtStockCode);
        add(lblStockName);
        add(txtStockName);
        add(lblStockType);
        add(cmbStockType);
        add(lblUnit);
        add(cmbUnit);
        add(lblBarcode);
        add(txtBarcode);
        add(lblVatType);
        add(cmbVatType);
        add(lblDescription);
        add(new JScrollPane(txtDescription));
        add(lblCreationTime);
        add(txtCreationTime);
        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(btnCopy);




        // Internal Frame'i görünür yapma
        setVisible(true);
        setResizable(true);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stockCode = txtStockCode.getText();
                boolean result = deleteController.deleteStockCard(stockCode);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Stok kartı silindi.");
                } else {
                    JOptionPane.showMessageDialog(null, "Stok kartı silinirken hata oluştu.");
                }
            }
        });




        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stockCode = txtStockCode.getText();
                String stockName = txtStockName.getText();
                String stockType = cmbStockType.getSelectedItem().toString();
                String unit = cmbUnit.getSelectedItem().toString();
                String barcode = txtBarcode.getText();
                String vatTypeName = cmbVatType.getSelectedItem().toString();
                String description = txtDescription.getText();

                boolean result = saveController.addStockCard(stockCode, stockName, stockType, unit, barcode, vatTypeName, description);

                if (result) {
                    JOptionPane.showMessageDialog(null, "Stok Kartı başarıyla eklendi.");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Stok Kartı eklenirken hata oluştu.");
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStockCard();
            }
        });


        btnCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldStockCode = txtStockCode.getText();
                String newStockCode = JOptionPane.showInputDialog(null, "Yeni Stok Kodunu Giriniz:");
                if (newStockCode != null && !newStockCode.isEmpty()) {
                    boolean result = copyController.copyStockCard(oldStockCode, newStockCode);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Stok Kartı kopyalandı.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Stok Kartı kopyalanırken hata oluştu.");
                    }
                }
            }
        });

    }

    private void fillVatTypeComboBox() {
        String query = "SELECT name FROM vat_type_card";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                cmbVatType.addItem(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtStockCode.setText("");
        txtStockName.setText("");
        cmbStockType.setSelectedIndex(0);
        cmbUnit.setSelectedIndex(0);
        txtBarcode.setText("");
        cmbVatType.setSelectedIndex(0);
        txtDescription.setText("");
    }

    private void updateStockCard() {
        String stockCode = txtStockCode.getText();
        String stockName = txtStockName.getText();
        String stockType = cmbStockType.getSelectedItem().toString();
        String unit = cmbUnit.getSelectedItem().toString();
        String barcode = txtBarcode.getText();
        String vatTypeName = cmbVatType.getSelectedItem().toString();
        String description = txtDescription.getText();

        boolean result = updateController.updateStockCard(stockCode, stockName, stockType, unit, barcode, vatTypeName, description);

        if (result) {
            JOptionPane.showMessageDialog(null, "Stok Kartı başarıyla güncellendi.");
        } else {
            JOptionPane.showMessageDialog(null, "Stok Kartı güncellenirken hata oluştu.");
        }
    }



}
