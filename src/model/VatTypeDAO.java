package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VatTypeDAO {

    private Connection connection;

    public VatTypeDAO(Connection connection) {
        this.connection = connection;
    }

    // KDV tipi ekleme
    public boolean addVatType(String code, String name, double rate) {
        String query = "INSERT INTO vat_type_card (code, name, rate) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, rate);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    // KDV tipi silme
    public boolean deleteVatType(String code) {
        String query = "DELETE FROM vat_type_card WHERE code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, code);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
