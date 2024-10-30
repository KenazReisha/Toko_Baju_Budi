import javax.swing.*;

public class TabPanel extends JFrame {
    JFrame frame = new JFrame("Aplikasi Toko Budi");

    // Membuat instance dari class-panel Home dan Order
    GUIHome homePanel = new GUIHome();
    GUIKeranjang keranjangpanel = new GUIKeranjang();
    GUIOrder orderPanel = new GUIOrder(keranjangpanel);
    GUIBon bonpanel = new GUIBon();

    JTabbedPane tab = new JTabbedPane();

    public TabPanel() {
        // Menambahkan panel1 dan panel2 ke JTabbedPane
        tab.add("Home", homePanel.panel1);
        tab.add("Order", orderPanel.panel2);
        tab.add("Keranjang", keranjangpanel.panel3);
        tab.add("Riwayat", bonpanel.panel4);

        frame.add(tab);
        frame.setSize(650, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Biar ditengah pas run
        frame.setVisible(true);
    }
}