package controller;

import model.DatabaseManager;
import model.VatTypeDAO;

import java.sql.Connection;

public class DeleteVatTypeController {
    private VatTypeDAO vatTypeDAO;

    public DeleteVatTypeController() {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.getConnection();

        vatTypeDAO = new VatTypeDAO(connection);
    }

    public boolean deleteVatType(String code) {
        return vatTypeDAO.deleteVatType(code);
    }
}
