import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sql2o.Connection;

public class SignupPanel extends JFrame {
    public SignupPanel(){
        setSize(500,500);
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelSignUp = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();          //GridBagConstraints untuk posisi
        gbc.insets = new Insets(0, 5, 0, 5);  //insets semacam padding

        JLabel usernameLabel = new JLabel("Username");
        gbc.gridx = 0;                                              //(0,0) artinya di pojok kiri atas
        gbc.gridy = 0;
        panelSignUp.add(usernameLabel, gbc);

        JTextField TFusername = new JTextField(20);
        gbc.gridx = 1;                                              //(1,0) artinya di kanan atas
        gbc.gridy = 0;                                              //sebelumnya x=0 skrng =1 artinya geser ke kanan 1
        panelSignUp.add(TFusername, gbc);

        JLabel passwordLabel = new JLabel("Password");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelSignUp.add(passwordLabel, gbc);

        JPasswordField TFpassword = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelSignUp.add(TFpassword, gbc);

        JButton submit = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelSignUp.add(submit,gbc);

        add(panelSignUp);
        setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernamee = TFusername.getText();
                String passwordd = new String(TFpassword.getPassword());
                insertDataPelanggan(usernamee, passwordd);
                new LoginPanel();
                setVisible(false);
            }
        });
    }

    public int generateIdPelanggan (){
        int idPelanggan = 0;

        //sql statement MaxId
        String sql = "SELECT MAX(idPelanggan) AS maxId FROM pelanggan"; //caranya pake MAX untuk mengambil id si pesanan
        try (Connection con = Database.db.open()) {
            Integer maxId = con.createQuery(sql)
                    .executeScalar(Integer.class);

            // Periksa apakah maxId tidak null (artinya ada catatan dalam tabel)
            if (maxId != null) {
                idPelanggan = maxId + 1;
            } else {
                // Jika tidak ada catatan, mulai dengan ID 1
                idPelanggan = 1;
            }
            return idPelanggan;
        }
    }

    public int generateIdKeranjang (){
        int idKeranjang = 0;

        //sql statement MaxId
        String sql = "SELECT MAX(idKeranjang) AS maxId FROM keranjang"; //caranya pake MAX untuk mengambil id si pesanan
        try (Connection con = Database.db.open()) {
            Integer maxId = con.createQuery(sql)
                    .executeScalar(Integer.class);

            // Periksa apakah maxId tidak null (artinya ada catatan dalam tabel)
            if (maxId != null) {
                idKeranjang = maxId + 1;
            } else {
                // Jika tidak ada catatan, mulai dengan ID 1
                idKeranjang = 1;
            }
            return idKeranjang;
        }
    }

    public void insertDataKeranjang(int idPelanggan){
        String sql = "INSERT INTO keranjang VALUES (:idKeranjang, :idPelanggan)";
        try (Connection con = Database.db.open()){
            int idKeranjang = generateIdKeranjang();
            con.createQuery(sql)
                    .addParameter("idKeranjang",idKeranjang)
                    .addParameter("idPelanggan", idPelanggan)
                    .executeUpdate();
        }
    }

    public void insertDataPelanggan (String username, String password){
        String sql = "INSERT INTO pelanggan VALUES (:idPelanggan, :username, :password)";
        int idPelanggan = generateIdPelanggan();
        try (Connection con = Database.db.open()){
            con.createQuery(sql)
                    .addParameter("idPelanggan", idPelanggan )
                    .addParameter("username", username)
                    .addParameter("password", password)
                    .executeUpdate();
        }
        insertDataKeranjang(idPelanggan);
    }
}