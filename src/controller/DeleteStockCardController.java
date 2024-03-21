package controller;

import model.DatabaseManager;
import model.StockCardDAO;

import java.sql.Connection;

public class DeleteStockCardController {

    private StockCardDAO stockCardDAO;

    public DeleteStockCardController() {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.getConnection();

        stockCardDAO = new StockCardDAO(connection);
    }

    public boolean deleteStockCard(String stockCode) {
        return stockCardDAO.deleteStockCard(stockCode);
    }
}
