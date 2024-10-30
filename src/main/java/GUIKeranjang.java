import org.sql2o.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIKeranjang extends JPanel {
    JPanel panel3;
    JPanel footer;
    JPanel contKeranjang;
    JLabel keranjangLabel;
    JTextArea outputKeranjang; // Menambahkan JTextArea untuk menampilkan item di keranjang

    public GUIKeranjang(){
        //Panel3
        panel3 = new JPanel(new BorderLayout());
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        contKeranjang = new JPanel();
        contKeranjang.setPreferredSize(new Dimension(650,500));
        contKeranjang.setMaximumSize(contKeranjang.getPreferredSize());
        contKeranjang.setLayout(null);
        contKeranjang.setBackground(Color.lightGray);

        keranjangLabel = new JLabel("Keranjang Belanja:");
        Font labelFont = keranjangLabel.getFont();
        keranjangLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 24));
        keranjangLabel.setBounds(15, 5, 400, 40);

        outputKeranjang = new JTextArea();
        //outputKeranjang.setBackground(Color.lightGray);
        outputKeranjang.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        outputKeranjang.setBounds(15, 60, 600,350);
        outputKeranjang.setEditable(false);    //supaya gabisa di edit

        // Membungkus JTextArea dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(outputKeranjang);
        scrollPane.setBounds(15, 60, 600, 350);

        JButton submit = new JButton("Pay");
        submit.setBounds(541,450, 75, 25);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIPembayaran();
                SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
            }
        });

        JButton delete = new JButton("Delete");
        delete.setBounds(450,450,75,25);

        footer = new JPanel();
        footer.setPreferredSize(new Dimension(650,35));
        footer.setMaximumSize(footer.getPreferredSize());
        footer.setBackground(Color.gray);

        JLabel label2 = new JLabel("©2023 TokoBudi");
        Font labelFont2 = label2.getFont();
        label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 20)); // Ubah ukuran font menjadi 24

        contKeranjang.add(keranjangLabel);
        contKeranjang.add(scrollPane); // Menambahkan JScrollPane sebagai gantinya JTextArea langsung
        contKeranjang.add(submit);
        contKeranjang.add(delete);

        footer.add(label2);
        panel3.add(contKeranjang);
        panel3.add(footer);
        add(panel3);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    DBdelete();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                String text = outputKeranjang.getText().trim();                 //ngambil text, trim buat /n
                int lastNewline = text.lastIndexOf("\n");                   //mencari /n diakhir row
                if (lastNewline >= 0) {                                         //kalo karakter terakhir text ditemukan (kalo karakter terakhir ditemukan berarti ada lebi dri satu item)
                    outputKeranjang.setText(text.substring(0, lastNewline) + "\n");    //text nya diambil dengan substring (diapus) dri index ke 0 sampai karakter terakhir
                } else {
                    outputKeranjang.setText("");                                //kalo karakter terakhit text ga ditemuin (item cuman 1) set ke "" (kosong)
                }
            }
        });
    }
    // Metode untuk menambahkan item ke dalam keranjang
    public void addItemToKeranjang(String item) {
        outputKeranjang.append(item + "\n");                            //ngambil item trus ditambah /n
    }

    public int getLastPesananId (){
        // sql statement MaxId
        String sql = "SELECT MAX(idPesanan) AS maxId FROM pesanan"; // caranya pake MAX untuk mengambil id si pesanan
        try (Connection con = Database.db.open()) {
            Integer maxId = con.createQuery(sql)
                    .executeScalar(Integer.class);

            if (maxId != null) {
                return maxId;
            } else {
                // Jika tidak ada catatan, mulai dengan ID 1
                return 1;
            }
        }
    }

    public void DBdelete() throws Exception {
        try {
            // Check outputkeranjang kosong atau engga
            if (!outputKeranjang.getText().isEmpty()) {  //jika isi text area tidak kosong maka akan lanjut ke sini
                //get idpesanan dari method getlastPesananID
                int idPesanan = getLastPesananId();

                // DELETE
                String sql = "DELETE FROM pesanan WHERE idPesanan = :idPesanan";
                try (Connection con = Database.db.open()){
                    con.createQuery(sql)
                            .addParameter("idPesanan", idPesanan)
                            .executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}