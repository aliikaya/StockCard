package view;
import controller.DeleteVatTypeController;
import controller.SaveVatTypeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VatTypeInternalFrame extends JInternalFrame {
    private JLabel lblKodu, lblAdi, lblOrani;
    private JTextField txtKodu, txtAdi;
    private JComboBox<Double> cmbOrani;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;


    private SaveVatTypeController saveController;
    private DeleteVatTypeController deleteController;

    public VatTypeInternalFrame() {
        // Internal Frame ayarları
        setTitle("KDV Tipi Kartı");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        setSize(600, 400);
        setLayout(new GridLayout(0, 2));


        saveController = new SaveVatTypeController();
        deleteController = new DeleteVatTypeController();

        // UI bileşenlerinin oluşturulması
        lblKodu = new JLabel("Kodu:");
        txtKodu = new JTextField(20);
        lblAdi = new JLabel("Adı:");
        txtAdi = new JTextField(20);
        lblOrani = new JLabel("Oranı:");
        cmbOrani = new JComboBox<>(new Double[]{0.0, 1.0, 8.0, 18.0});
        btnAdd = new JButton("Ekle");
        btnDelete = new JButton("Sil");

        // UI bileşenlerinin internal frame'e eklenmesi
        add(lblKodu);
        add(txtKodu);
        add(lblAdi);
        add(txtAdi);
        add(lblOrani);
        add(cmbOrani);
        add(btnAdd);
        add(btnDelete);

        setVisible(true);

        // Butonlara aksiyonları ekle
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = txtKodu.getText();
                String name = txtAdi.getText();
                double rate = (double) cmbOrani.getSelectedItem();
                boolean result = saveController.addVatType(code, name, rate);
                if (result) {
                    JOptionPane.showMessageDialog(null, "KDV Tipi eklendi.");
                } else {
                    JOptionPane.showMessageDialog(null, "KDV Tipi eklenirken hata oluştu.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = txtKodu.getText();
                boolean result = deleteController.deleteVatType(code);
                if (result) {
                    JOptionPane.showMessageDialog(null, "KDV Tipi silindi.");
                } else {
                    JOptionPane.showMessageDialog(null, "KDV Tipi silinirken hata oluştu.");
                }
            }
        });
    }
}
