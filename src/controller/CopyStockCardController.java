package controller;

import model.DatabaseManager;
import model.StockCardDAO;

import java.sql.Connection;

public class CopyStockCardController {


    private StockCardDAO stockCardDAO;

    public CopyStockCardController() {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.getConnection();

        stockCardDAO = new StockCardDAO(connection);
    }

    public boolean copyStockCard(String oldStockCode, String newStockCode) {
        return stockCardDAO.copyStockCard(oldStockCode, newStockCode);
    }
}
