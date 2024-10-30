import org.sql2o.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPanel extends JFrame{

    private static int idKeranjang;                                 //declare idkeranjang, static biar bisa diakses di GUIOrder
    private static int idPelanggan;

    public LoginPanel(){
        setSize(500,500);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelLogin = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();          //GridBagConstraints untuk posisi
        gbc.insets = new Insets(0, 5, 0, 5);  //insets semacam padding

        JLabel usernameLabel = new JLabel("Username");
        gbc.gridx = 0;                                              //(0,0) artinya di pojok kiri atas
        gbc.gridy = 0;
        panelLogin.add(usernameLabel, gbc);

        JTextField TFusername = new JTextField(20);
        gbc.gridx = 1;                                              //(1,0) artinya di kanan atas
        gbc.gridy = 0;                                              //sebelumnya x=0 skrng =1 artinya geser ke kanan 1
        panelLogin.add(TFusername, gbc);

        JLabel passwordLabel = new JLabel("Password");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLogin.add(passwordLabel, gbc);

        JPasswordField TFpassword = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelLogin.add(TFpassword, gbc);

        JButton login = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelLogin.add(login,gbc);

        JButton signup = new JButton("Sign Up");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelLogin.add(signup,gbc);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignupPanel();
                setVisible(false);
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = TFusername.getText();
                String password = new String(TFpassword.getPassword());

                cekLogin(username, password);
            }
        });

        add(panelLogin);
        setVisible(true);
    }

    public static int getIdKeranjang() {               //method buat kirim idkeranjang
        return idKeranjang;
    }

    public static int getIdPelanggan(){
        return idPelanggan;
    }

    //kalo void gaperelu return
    private void cekLogin(String username, String password) {
        String sql = "SELECT idPelanggan FROM pelanggan WHERE username = :username AND password = :password";
        try (Connection con = Database.db.open()){
            Integer idPel = con.createQuery(sql)
                    .addParameter("username",username)
                    .addParameter("password",password)
                    .executeScalar(Integer.class);
            //hasil sql disiimpen di variable idPel (hasil sql = idPelanggan) idPelanggan pasti > 0
            if(idPel != null){  //kalo idPelanggan terbaca
                LoginPanel.idPelanggan = idPel;
                int idKerr = getIdKeranjang(idPel); //panggil method getIdKeranjang
                LoginPanel.idKeranjang =  idKerr;   //set ke idKeranjang baut dioper ke GUIOrder
                if (idKerr > 0){
                    new TabPanel();
                    setVisible(false);
                }else {
                    System.out.println("id keranjang tidak ketemu");
                }
            }else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Username atau password salah");
            }
        }
    }

    private int getIdKeranjang(int idPelanggan) {
        int idKer = 0;      //inisiasi awal
        String queryKeranjang = "SELECT idKeranjang FROM keranjang WHERE idPelanggan = :idPelanggan";
        try (Connection con = Database.db.open()){
            idKer = con.createQuery(queryKeranjang)
                    .addParameter("idPelanggan", idPelanggan)
                    .executeScalar(Integer.class);
            //hasil query disimpen di idKer
        }
        return idKer;
    }
}