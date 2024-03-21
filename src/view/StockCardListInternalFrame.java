package view;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockCardListInternalFrame extends JInternalFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private Connection connection;




    public StockCardListInternalFrame(Connection connection){
        this.connection = connection;


        setTitle("Stok Kartı Listesi");
        setSize(800, 600);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        // Tablo modeli oluşturma
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Stok Kodu");
        tableModel.addColumn("Stok Adı");
        tableModel.addColumn("Stok Tipi");
        tableModel.addColumn("Birim");
        tableModel.addColumn("Barkod");
        tableModel.addColumn("KDV Kodu");
        tableModel.addColumn("KDV Adı");
        tableModel.addColumn("KDV Oranı");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // Tabloyu doldurma
        fillTable();




    }

    private void fillTable() {
        String query = "SELECT sc.stock_code, sc.stock_name, sc.stock_type, sc.unit, sc.barcode, " +
                "vtc.code AS vat_code, vtc.name AS vat_name, vtc.rate AS vat_rate " +
                "FROM stock_card sc INNER JOIN vat_type_card vtc ON sc.vat_type_id = vtc.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String stockCode = resultSet.getString("stock_code");
                String stockName = resultSet.getString("stock_name");
                int stockType = resultSet.getInt("stock_type");
                String unit = resultSet.getString("unit");
                String barcode = resultSet.getString("barcode");
                String vatCode = resultSet.getString("vat_code");
                String vatName = resultSet.getString("vat_name");
                double vatRate = resultSet.getDouble("vat_rate");

                Object[] row = {stockCode, stockName, stockType, unit, barcode, vatCode, vatName, vatRate};
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
