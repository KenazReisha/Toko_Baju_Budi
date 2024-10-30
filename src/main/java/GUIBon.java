import java.awt.*;
import javax.swing.*;
import java.sql.*;
import org.sql2o.Connection;

public class GUIBon extends JPanel{
    JPanel panel4;
    JPanel footer;
    JPanel contBon;
    static JTextArea bon;


    public GUIBon (){
        //Panel4
        panel4 = new JPanel(new BorderLayout());
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

        contBon = new JPanel();
        contBon.setPreferredSize(new Dimension(650,500));
        contBon.setMaximumSize(contBon.getPreferredSize());
        contBon.setLayout(null);
        contBon.setBackground(Color.lightGray);

        JLabel label1 = new JLabel("Riwayat Pemesanan");
        Font labelFont = label1.getFont();
        label1.setFont(new Font(labelFont.getName(), Font.BOLD, 24)); // Ubah ukuran font menjadi 24
        label1.setBounds(185,0,650,100);

        JLabel LidPelanggan = new JLabel("Id Pelanggan");
        Font labelFont2 = LidPelanggan.getFont();
        LidPelanggan.setFont(new Font(labelFont2.getName(), Font.BOLD, 15)); // Ubah ukuran font menjadi 24
        LidPelanggan.setBounds(20,30,100,100);

        bon = new JTextArea();
        bon.setBounds(15, 70, 600,400);
        bon.setEditable(false);

        footer = new JPanel();
        footer.setPreferredSize(new Dimension(650,35));
        footer.setMaximumSize(footer.getPreferredSize());
        footer.setBackground(Color.gray);

        JLabel label2 = new JLabel("©2023 TokoBudi");
        Font labelFontX = label2.getFont();
        label2.setFont(new Font(labelFontX.getName(), Font.PLAIN, 20));

        contBon.add(bon);
        contBon.add(label1);

        footer.add(label2);
        panel4.add(contBon);
        panel4.add(footer);

        add(panel4);

        OutputDB();
    }
    //Method ini untuk menampilkan pesanan yang dari database pesanan
    public static void OutputDB() {
        try(Connection con = Database.db.open()) {
            // sqlQuery yang kita pakai adalah sebagai berikut
            String sqlQuery = "SELECT pr.namaProduk, pr.warnaProduk, pr.ukuranProduk, pe.jumlahproduk, pr.hargaproduk " +
                    "FROM tokobajubudi.produk pr JOIN tokobajubudi.pesanan pe using (idProduk) " +
                    "WHERE pe.idKeranjang = ?";

            try (PreparedStatement statement = Database.db.getConnectionSource().getConnection().prepareStatement(sqlQuery)) {
                statement.setInt(1, LoginPanel.getIdKeranjang());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String namaProduk = resultSet.getString("namaProduk");
                        String warnaProduk = resultSet.getString("warnaProduk");
                        String ukuranProduk = resultSet.getString("ukuranProduk");
                        int jumlahProduk = resultSet.getInt("jumlahproduk");
                        int hargaProduk = resultSet.getInt("hargaproduk");

                        // Nambahin data ke TextArea
                        bon.append("Nama Produk: " + namaProduk + ", Warna: " + warnaProduk +
                                ", Ukuran: " + ukuranProduk + ", Jumlah: " + jumlahProduk +
                                ", Harga: " + hargaProduk +"\n");
                        //append adalah untuk nge add untuk menggabungkan menjadi satu STRING
                    }
                }
                bon.append("Total Harga : " + GUIOrder.getJumlahBayar());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}