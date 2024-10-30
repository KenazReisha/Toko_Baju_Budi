import org.sql2o.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUIOrder extends JPanel{

    JPanel panel2;
    JPanel contOrder;
    JPanel footer;
    GUIKeranjang keranjangpanel; // Menambahkan atribut keranjangpanel

    private static int jumlahbayar = 0;

    public GUIOrder(GUIKeranjang keranjangpanel ){
        this.keranjangpanel = keranjangpanel; // Simpan referensi ke keranjangpanel

        int hargaproduk = 50000;

        //Panel2
        panel2 = new JPanel(new BorderLayout());
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        contOrder = new JPanel();
        contOrder.setPreferredSize(new Dimension(650,500));
        contOrder.setMaximumSize(contOrder.getPreferredSize());
        contOrder.setLayout(null);
        contOrder.setBackground(Color.lightGray);

        JLabel label1 = new JLabel("Daftar Produk");
        Font labelFont = label1.getFont();
        label1.setFont(new Font(labelFont.getName(), Font.BOLD, 24)); // Ubah ukuran font menjadi 24
        label1.setBounds(225,0,650,100);

        //Untuk ukuran DROPDOWN baju
        String ukuranBaju[] = {"S", "M", "L", "XL","2XL"};

        //BAJU HITAM
        //Gambar
        ImageIcon bajuhitam = new ImageIcon("images/kaoshitam.jpg");
        Image bajuhitam1 = bajuhitam.getImage();  //ambil gambar buat resize
        //Resize Gambar
        Image bajuhitam2 = bajuhitam1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajuhitam3 = new ImageIcon(bajuhitam2);
        //
        JLabel bajuhitam4 = new JLabel(bajuhitam3);
        //Set Bound bajuhitam4
        bajuhitam4.setBounds(50,100,bajuhitam4.getPreferredSize().width,bajuhitam4.getPreferredSize().height);
        //Dropdown
        JComboBox dropdown1 = new JComboBox(ukuranBaju);
        //SetBound dropdown
        dropdown1.setBounds(50,225,dropdown1.getPreferredSize().width,dropdown1.getPreferredSize().height);
        //Textbox
        JTextField jumlahBaju1 = new JTextField(56);
        //SetBound textfield
        jumlahBaju1.setBounds(101,225,70,dropdown1.getPreferredSize().height);
        //submit
        JButton submit1 = new JButton("Submit");
        submit1.setBounds(73,255,75,25);

        // Menambahkan ActionListener untuk tombol "Submit" pada tab 1
        submit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keranjangpanel != null) {
                    try {
                        int quantity = Integer.parseInt(jumlahBaju1.getText()); //ngambil dari jumlahBaju1 kalo int disimpen di variable quantity
                        String selectedItem = "Baju Hitam - " + dropdown1.getSelectedItem() + " - " + quantity;
                        keranjangpanel.addItemToKeranjang(selectedItem);

                        int jumlah = Integer.parseInt(jumlahBaju1.getText());

                        //buat itung jumlahbayar
                        jumlahbayar = jumlahbayar + hitungHargaProduk(jumlah,hargaproduk);
                        //jumlahbayar + method hitungHargaProduk

                        int idKeranjang = LoginPanel.getIdKeranjang();

                        // dapetin selectedsize dari dropdown
                        String selectedSize = (String) dropdown1.getSelectedItem();

                        int idproduk = 0;
                        switch (selectedSize) {
                            case "S":
                                idproduk = 1;
                                break;
                            case "M":
                                idproduk = 2;
                                break;
                            case "L":
                                idproduk = 3;
                                break;
                            case "XL":
                                idproduk = 4;
                                break;
                            case "2XL":
                                idproduk = 5;
                                break;
                        }

                        insertDataPesanan(idproduk, idKeranjang,jumlah);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(GUIOrder.this, "Jumlah harus berupa angka.");
                    }
                } else {
                    System.err.println("keranjangpanel is null");
                }
            }
        });

        //BAJU PUTIH
        //Gambar
        ImageIcon bajuputih = new ImageIcon("images/kaosputih.jpg");
        Image bajuputih1 = bajuputih.getImage();
        //Gambar
        Image bajuputih2 = bajuputih1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajuputih3 = new ImageIcon(bajuputih2);
        //
        JLabel bajuputih4 = new JLabel(bajuputih3);
        //Set Bound bajuputih4
        bajuputih4.setBounds(250,100,bajuputih4.getPreferredSize().width,bajuputih4.getPreferredSize().height);
        //Dropdown
        JComboBox dropdown2 = new JComboBox(ukuranBaju);
        //SetBound dropdown
        dropdown2.setBounds(250,225,dropdown2.getPreferredSize().width,dropdown2.getPreferredSize().height);
        //Textbox
        JTextField jumlahBaju2 = new JTextField(5);
        //SetBound textbox
        jumlahBaju2.setBounds(300,225,70,dropdown2.getPreferredSize().height);

        //submit
        JButton submit2 = new JButton("Submit");
        submit2.setBounds(273,255,75,25);

        // Menambahkan ActionListener untuk tombol "Submit" pada tab 1
        submit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keranjangpanel != null) {
                    int quantity = Integer.parseInt(jumlahBaju2.getText()); //ngambil dari jumlahBaju1 kalo int disimpen di variable quantity
                    String selectedItem = "Baju Putih - " + dropdown2.getSelectedItem() + " - " + quantity;
                    keranjangpanel.addItemToKeranjang(selectedItem);
                    try {
                        int jumlah = Integer.parseInt(jumlahBaju2.getText());

                        //buat itung jumlahbayar
                        jumlahbayar = jumlahbayar + hitungHargaProduk(jumlah,hargaproduk);
                        //jumlahbayar + method hitungHargaProduk

                        int idKeranjang = LoginPanel.getIdKeranjang();

                        String selectedSize = (String) dropdown2.getSelectedItem();

                        int idproduk = 0;
                        switch (selectedSize) {
                            case "S":
                                idproduk = 6;
                                break;
                            case "M":
                                idproduk = 7;
                                break;
                            case "L":
                                idproduk = 8;
                                break;
                            case "XL":
                                idproduk = 9;
                                break;
                            case "2XL":
                                idproduk = 10;
                                break;
                        }

                        insertDataPesanan(idproduk,idKeranjang, jumlah);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(GUIOrder.this, "Jumlah harus berupa angka.");
                    }
                } else {
                    System.err.println("keranjangpanel is null");
                }
            }
        });

        //BAJU MERAH
        //Gambar
        ImageIcon bajumerah = new ImageIcon("images/kaosmerah.jpg");
        Image bajumerah1 = bajumerah.getImage();  //ambil gambar buat resize
        //Gambar
        Image bajumerah2 = bajumerah1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajumerah3 = new ImageIcon(bajumerah2);
        //
        JLabel bajumerah4 = new JLabel(bajumerah3);
        //Set Bound bajumerah4
        bajumerah4.setBounds(450,100,bajumerah4.getPreferredSize().width,bajumerah4.getPreferredSize().height);
        //Dropdown
        JComboBox dropdown3 = new JComboBox(ukuranBaju);
        //SetBound dropdown
        dropdown3.setBounds(450,225,dropdown3.getPreferredSize().width,dropdown3.getPreferredSize().height);
        //Textbox
        JTextField jumlahBaju3 = new JTextField(5);
        //Setbound textbox
        jumlahBaju3.setBounds(500,225,70,dropdown3.getPreferredSize().height);
        //submit
        JButton submit3 = new JButton("Submit");
        submit3.setBounds(473,255,75,25);

        // Menambahkan ActionListener untuk tombol "Submit" pada tab 1
        submit3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keranjangpanel != null) {
                    try {
                        int quantity = Integer.parseInt(jumlahBaju3.getText()); //ngambil dari jumlahBaju1 kalo int disimpen di variable quantity
                        String selectedItem = "Baju Merah - " + dropdown3.getSelectedItem() + " - " + quantity;
                        keranjangpanel.addItemToKeranjang(selectedItem);

                        int jumlah = Integer.parseInt(jumlahBaju3.getText());

                        //buat itung jumlahbayar
                        jumlahbayar = jumlahbayar + hitungHargaProduk(jumlah,hargaproduk);
                        //jumlahbayar + method hitungHargaProduk

                        int idKeranjang = LoginPanel.getIdKeranjang();


                        String selectedSize = (String) dropdown3.getSelectedItem();

                        int idproduk = 0;
                        switch (selectedSize) {
                            case "S":
                                idproduk = 11;
                                break;
                            case "M":
                                idproduk = 12;
                                break;
                            case "L":
                                idproduk = 13;
                                break;
                            case "XL":
                                idproduk = 14;
                                break;
                            case "2XL":
                                idproduk = 15;
                                break;
                        }
                        insertDataPesanan(idproduk,idKeranjang, jumlah);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(GUIOrder.this, "Jumlah harus berupa angka.");
                    }
                } else {
                    System.err.println("keranjangpanel is null");
                }
            }
        });

        //BAJU BIRU
        //Gambar
        ImageIcon bajubiru = new ImageIcon("images/kaosbiru.jpg");
        Image bajubiru1 = bajubiru.getImage();
        //Gambar
        Image bajubiru2 = bajubiru1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajubiru3 = new ImageIcon(bajubiru2);
        //
        JLabel bajubiru4 = new JLabel(bajubiru3);
        //Set Bound bajubiru4
        bajubiru4.setBounds(50,290,bajubiru4.getPreferredSize().width,bajubiru4.getPreferredSize().height);
        //Dropdown
        JComboBox dropdown4 = new JComboBox(ukuranBaju);
        //SetBound dropdown
        dropdown4.setBounds(50,415,dropdown4.getPreferredSize().width,dropdown4.getPreferredSize().height);
        //Textbox
        JTextField jumlahBaju4 = new JTextField(5);
        //SetBound textbox
        jumlahBaju4.setBounds(100,415,70,dropdown4.getPreferredSize().height);
        //submit
        JButton submit4 = new JButton("Submit");
        submit4.setBounds(73,445,75,25);

        // Menambahkan ActionListener untuk tombol "Submit" pada tab 1
        submit4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keranjangpanel != null) {
                    try {
                        int quantity = Integer.parseInt(jumlahBaju4.getText()); //ngambil dari jumlahBaju1 kalo int disimpen di variable quantity
                        String selectedItem = "Baju Biru - " + dropdown4.getSelectedItem() + " - " + quantity;
                        keranjangpanel.addItemToKeranjang(selectedItem);

                        int jumlah = Integer.parseInt(jumlahBaju4.getText());

                        //buat itung jumlahbayar
                        jumlahbayar = jumlahbayar + hitungHargaProduk(jumlah,hargaproduk);
                        //jumlahbayar + method hitungHargaProduk

                        int idKeranjang = LoginPanel.getIdKeranjang();


                        String selectedSize = (String) dropdown4.getSelectedItem();

                        int idproduk = 0;
                        switch (selectedSize) {
                            case "S":
                                idproduk = 16;
                                break;
                            case "M":
                                idproduk = 17;
                                break;
                            case "L":
                                idproduk = 18;
                                break;
                            case "XL":
                                idproduk = 19;
                                break;
                            case "2XL":
                                idproduk = 20;
                                break;
                        }

                        insertDataPesanan(idproduk,idKeranjang, jumlah);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(GUIOrder.this, "Jumlah harus berupa angka.");
                    }
                } else {
                    System.err.println("keranjangpanel is null");
                }
            }
        });

        //BAJU ABU
        //Gambar
        ImageIcon bajuabu = new ImageIcon("images/kaosabu.jpg");
        Image bajuabu1 = bajuabu.getImage();
        //Gambar
        Image bajuabu2 = bajuabu1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajuabu3 = new ImageIcon(bajuabu2);
        //
        JLabel bajuabu4 = new JLabel(bajuabu3);
        //Set Bound bajuabu4
        bajuabu4.setBounds(250,290,bajuabu4.getPreferredSize().width,bajuabu4.getPreferredSize().height);
        //Dropdown
        JComboBox dropdown5 = new JComboBox(ukuranBaju);
        //SetBound dropdown
        dropdown5.setBounds(250,415,dropdown5.getPreferredSize().width,dropdown5.getPreferredSize().height);
        //Textbox
        JTextField jumlahBaju5 = new JTextField(5);
        //SetBound textbox
        jumlahBaju5.setBounds(300,415,70,dropdown5.getPreferredSize().height);
        //submit
        JButton submit5 = new JButton("Submit");
        submit5.setBounds(273,445,75,25);

        // Menambahkan ActionListener untuk tombol "Submit" pada tab 1
        submit5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keranjangpanel != null) {
                    try {
                        int quantity = Integer.parseInt(jumlahBaju5.getText()); //ngambil dari jumlahBaju1 kalo int disimpen di variable quantity
                        String selectedItem = "Baju Abu - " + dropdown5.getSelectedItem() + " - " + quantity;
                        keranjangpanel.addItemToKeranjang(selectedItem);

                        int jumlah = Integer.parseInt(jumlahBaju5.getText());

                        //buat itung jumlahbayar
                        jumlahbayar = jumlahbayar + hitungHargaProduk(jumlah,hargaproduk);
                        //jumlahbayar + method hitungHargaProduk

                        int idKeranjang = LoginPanel.getIdKeranjang();

                        String selectedSize = (String) dropdown5.getSelectedItem();

                        int idproduk = 0;
                        switch (selectedSize) {
                            case "S":
                                idproduk = 21;
                                break;
                            case "M":
                                idproduk = 22;
                                break;
                            case "L":
                                idproduk = 23;
                                break;
                            case "XL":
                                idproduk = 24;
                                break;
                            case "2XL":
                                idproduk = 25;
                                break;
                        }

                        insertDataPesanan(idproduk,idKeranjang, jumlah);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(GUIOrder.this, "Jumlah harus berupa angka.");
                    }
                } else {
                    System.err.println("keranjangpanel is null");
                }
            }
        });

        footer = new JPanel();
        footer.setPreferredSize(new Dimension(650,35));
        footer.setMaximumSize(footer.getPreferredSize());
        footer.setBackground(Color.gray);

        JLabel label2 = new JLabel("©2023 TokoBudi");
        Font labelFont2 = label2.getFont();
        label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 20)); // Ubah ukuran font menjadi 24

        contOrder.add(label1);
        contOrder.add(bajuhitam4);
        contOrder.add(dropdown1);
        contOrder.add(jumlahBaju1);
        contOrder.add(submit1);
        contOrder.add(bajuputih4);
        contOrder.add(dropdown2);
        contOrder.add(jumlahBaju2);
        contOrder.add(submit2);
        contOrder.add(bajumerah4);
        contOrder.add(dropdown3);
        contOrder.add(jumlahBaju3);
        contOrder.add(submit3);
        contOrder.add(bajubiru4);
        contOrder.add(dropdown4);
        contOrder.add(jumlahBaju4);
        contOrder.add(submit4);
        contOrder.add(bajuabu4);
        contOrder.add(dropdown5);
        contOrder.add(jumlahBaju5);
        contOrder.add(submit5);

        panel2.add(contOrder);
        footer.add(label2);
        panel2.add(footer);
        add(panel2);
    }
    public int generateIdPesanan (){
        int idPesanan = 0;

        //sql statement MaxId
        String sql = "SELECT MAX(idPesanan) AS maxId FROM pesanan"; //caranya pake MAX untuk mengambil id si pesanan
        try (Connection con = Database.db.open()) {
            Integer maxId = con.createQuery(sql)
                    .executeScalar(Integer.class);

            // Periksa apakah maxId tidak null (artinya ada catatan dalam tabel)
            if (maxId != null) {
                idPesanan = maxId + 1;
            } else {
                // Jika tidak ada catatan, mulai dengan ID 1
                idPesanan = 1;
            }

            return idPesanan;
        }
    }

    public void insertDataPesanan(int idProduk,int idKeranjang, int jumlahProduk){
        String sql = "INSERT INTO pesanan VALUES (:idPesanan, :idProduk, :idKeranjang, :jumlahProduk)";
        try (Connection con = Database.db.open()){
            int idPesanan2 = generateIdPesanan();
            con.createQuery(sql)
                    .addParameter("idPesanan",idPesanan2)
                    .addParameter("idProduk", idProduk)
                    .addParameter("idKeranjang", idKeranjang)
                    .addParameter("jumlahProduk", jumlahProduk)
                    .executeUpdate();
        }
    }

    //method hitung harga produk
    private int hitungHargaProduk(int jumlah, int hargaPerItem) {
        return jumlah * hargaPerItem;
    }

    //method jumlahBayar buat dioper
    public static int getJumlahBayar(){
        return jumlahbayar;
    }
}
