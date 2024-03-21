package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class MainFrame extends JFrame {
    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private Connection connection;
    private Set<JInternalFrame> internalFrames;

    public MainFrame(Connection connection) {
        this.connection = connection;
        internalFrames = new HashSet<>();

        // Frame başlık ve boyut ayarları
        setTitle("Stock Card");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // DesktopPane oluşturma ve eklenmesi
        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        // Menu bar oluşturma ve eklenmesi
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Stok menüsü oluşturma ve menu bar'a eklenmesi
        JMenu stockMenu = new JMenu("Stok");

        JMenuItem stockCardMenuItem = new JMenuItem("Stok Kartı");
        stockCardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStockCardInternalFrame();
            }
        });

        JMenuItem vatTypeMenuItem = new JMenuItem("KDV Tip Kartı");
        vatTypeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openVatTypeInternalFrame();
            }
        });

        JMenuItem stockListMenuItem = new JMenuItem("Stok Kart Listesi");
        stockListMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StockCardListInternalFrame stockCardListInternalFrame = new StockCardListInternalFrame(connection);
                desktopPane.add(stockCardListInternalFrame);
                stockCardListInternalFrame.setVisible(true);
            }
        });

        stockMenu.add(stockCardMenuItem);
        stockMenu.add(stockListMenuItem);
        stockMenu.add(vatTypeMenuItem);
        menuBar.add(stockMenu);

        // ToolBar oluşturma ve eklenmesi
        toolBar = new JToolBar();
        add(toolBar, BorderLayout.NORTH);

        // Frame'i görünür yapma
        setVisible(true);
    }

    // Stok Kartı penceresini açma metodunu
    private void openStockCardInternalFrame() {
        // Eğer daha önce açılmış bir Stok Kartı penceresi varsa, onu görünür yap
        for (JInternalFrame frame : internalFrames) {
            if (frame instanceof StockCardInternalFrame && frame.isVisible()) {
                frame.toFront();
                return;
            }
        }

        // Yoksa yeni bir Stok Kartı penceresi oluştur
        StockCardInternalFrame stockCardInternalFrame = new StockCardInternalFrame(connection);
        desktopPane.add(stockCardInternalFrame);
        stockCardInternalFrame.setVisible(true);
        internalFrames.add(stockCardInternalFrame);
    }

    // KDV Tip Kartı penceresini açma metodunu tanımlama
    private void openVatTypeInternalFrame() {
        // Eğer daha önce açılmış bir KDV Tip Kartı penceresi varsa, onu görünür yap
        for (JInternalFrame frame : internalFrames) {
            if (frame instanceof VatTypeInternalFrame && frame.isVisible()) {
                frame.toFront();
                return;
            }
        }

        // Yoksa yeni bir KDV Tip Kartı penceresi oluştur
        VatTypeInternalFrame vatTypeInternalFrame = new VatTypeInternalFrame();
        desktopPane.add(vatTypeInternalFrame);
        vatTypeInternalFrame.setVisible(true);
        internalFrames.add(vatTypeInternalFrame);
    }
}
