import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import org.sql2o.Connection;

public class GUIPembayaran extends JFrame {

    JPanel panel;
    JPanel contPembayaran;
    JPanel footer;

    public GUIPembayaran() {
        //Frame
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Biar ditengah pas run

        //Panel
        panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Label untuk password
        JLabel password = new JLabel("Masukkan Password Anda");
        password.setBounds(240, 100, password.getPreferredSize().width, password.getPreferredSize().height);

        //Textfield untuk memasukkan password
        JPasswordField tbpass = new JPasswordField();
        tbpass.setBounds(240, 125, 155, 25);

        //Button untuk acc pembayran
        JButton button1 = new JButton("Submit");
        button1.setBounds(275, 160, 75, 25);

        contPembayaran = new JPanel();
        contPembayaran.setPreferredSize(new Dimension(650, 565));
        contPembayaran.setMaximumSize(contPembayaran.getPreferredSize());
        contPembayaran.setLayout(null);
        contPembayaran.setBackground(Color.lightGray);

        footer = new JPanel();
        footer.setPreferredSize(new Dimension(650, 35));
        footer.setMaximumSize(footer.getPreferredSize());
        footer.setBackground(Color.gray);

        JLabel label2 = new JLabel("©2023 TokoBudi");
        Font labelFont2 = label2.getFont();
        label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 20)); // Ubah ukuran font menjadi 24

        contPembayaran.add(password);
        contPembayaran.add(tbpass);
        contPembayaran.add(button1);

        footer.add(label2);
        panel.add(contPembayaran);
        panel.add(footer);

        add(panel);

        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(tbpass.getPassword());
                cekPass(password);
            }
        });
    }

    public void cekPass(String password) {
        int idPelanggan2 = LoginPanel.getIdPelanggan();
        String sql = "SELECT password FROM PELANGGAN WHERE idPelanggan = :idPelanggan";
        try (Connection con = Database.db.open()) {
            String dbPassword = con.createQuery(sql)
                    .addParameter("idPelanggan", idPelanggan2)
                    .executeScalar(String.class);

            if (password.equals(dbPassword)){
                insertDataPembayaran();
                new TabPanel();
                dispose();
            }else {
                JOptionPane.showMessageDialog(GUIPembayaran.this,"Password Salah ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int generateIdPembayaran () throws SQLException {
        int idPembayaran = 0;

        //sql statement MaxId
        String sql = "SELECT MAX(idPembayaran) AS maxId FROM pembayaran"; //caranya pake MAX untuk mengambil id si pesanan
        try (Connection con = Database.db.open()) {
            Integer maxId = con.createQuery(sql)
                    .executeScalar(Integer.class);

            // Periksa apakah maxId tidak null (artinya ada catatan dalam tabel)
            if (maxId != null) {
                idPembayaran = maxId + 1;
            } else {
                // Jika tidak ada catatan, mulai dengan ID 1
                idPembayaran = 1;
            }

            return idPembayaran;
        }
    }

    public void insertDataPembayaran(){
        String sql = "INSERT INTO pembayaran VALUES (:idPembayaran,:idKeranjang, :totalHarga)";
        try (Connection con = Database.db.open()){
            int idPembayaran2 = generateIdPembayaran();
            con.createQuery(sql)
                    .addParameter("idPembayaran",idPembayaran2)
                    .addParameter("idKeranjang", LoginPanel.getIdKeranjang())
                    .addParameter("totalHarga", GUIOrder.getJumlahBayar())
                    .executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}