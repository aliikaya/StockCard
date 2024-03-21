package controller;

import model.DatabaseManager;
import model.StockCardDAO;

import java.sql.Connection;

public class SaveStockCardController {
    private StockCardDAO stockCardDAO;

    public SaveStockCardController() {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.getConnection();

        stockCardDAO = new StockCardDAO(connection);
    }

    public boolean addStockCard(String stockCode, String stockName, String stockType, String unit, String barcode, String vatTypeName, String description) {
        return stockCardDAO.addStockCard(stockCode, stockName, stockType, unit, barcode, vatTypeName, description);
    }


}
