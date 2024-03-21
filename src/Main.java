import model.DatabaseManager;
import view.MainFrame;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();



        SwingUtilities.invokeLater(() -> new MainFrame(connection));
    }
}