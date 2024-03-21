package controller;

import model.DatabaseManager;
import model.VatTypeDAO;

import java.sql.Connection;

public class SaveVatTypeController {

    private VatTypeDAO vatTypeDAO;

    public SaveVatTypeController() {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.getConnection();

        vatTypeDAO = new VatTypeDAO(connection);
    }

    public boolean addVatType(String code, String name, double rate) {
        return vatTypeDAO.addVatType(code, name, rate);
    }
}
