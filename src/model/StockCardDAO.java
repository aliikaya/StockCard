package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockCardDAO {

    private Connection connection;

    public StockCardDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean deleteStockCard(String stockCode) {
        String query = "DELETE FROM stock_card WHERE stock_code = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, stockCode);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean addStockCard(String stockCode, String stockName, String stockType, String unit, String barcode, String vatTypeName, String description) {
        String insertQuery = "INSERT INTO stock_card (stock_code, stock_name, stock_type, unit, barcode, vat_type_id, description) VALUES (?, ?, ?, ?, ?, (SELECT id FROM vat_type_card WHERE name = ?), ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, stockCode);
            preparedStatement.setString(2, stockName);
            preparedStatement.setString(3, stockType);
            preparedStatement.setString(4, unit);
            preparedStatement.setString(5, barcode);
            preparedStatement.setString(6, vatTypeName);
            preparedStatement.setString(7, description);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStockCard(String stockCode, String stockName, String stockType, String unit, String barcode, String vatTypeName, String description) {
        String query = "UPDATE stock_card SET stock_name = ?, stock_type = ?, unit = ?, barcode = ?, vat_type_id = (SELECT id FROM vat_type_card WHERE name = ?), description = ? WHERE stock_code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, stockName);
            preparedStatement.setString(2, stockType);
            preparedStatement.setString(3, unit);
            preparedStatement.setString(4, barcode);
            preparedStatement.setString(5, vatTypeName);
            preparedStatement.setString(6, description);
            preparedStatement.setString(7, stockCode);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean copyStockCard(String oldStockCode, String newStockCode) {
        String query = "INSERT INTO stock_card (stock_code, stock_name, stock_type, unit, barcode, vat_type_id, description, creation_time) " +
                "SELECT ?, stock_name, stock_type, unit, barcode, vat_type_id, description, creation_time FROM stock_card WHERE stock_code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newStockCode);
            preparedStatement.setString(2, oldStockCode);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
