package controller;

import model.DatabaseManager;
import model.StockCardDAO;

import java.sql.Connection;

public class UpdateStockCardController {

    private StockCardDAO stockCardDAO;

    public UpdateStockCardController() {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.getConnection();

        stockCardDAO = new StockCardDAO(connection);
    }

    public boolean updateStockCard(String stockCode, String stockName, String stockType, String unit, String barcode, String vatTypeName, String description) {
        return stockCardDAO.updateStockCard(stockCode, stockName, stockType, unit, barcode, vatTypeName, description);
    }
}
