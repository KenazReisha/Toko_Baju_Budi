import java.awt.*;
import javax.swing.*;

public class GUIHome extends JPanel {

    JPanel panel1;
    JPanel contHome;
    JPanel footer;

    public GUIHome() {
        // Panel1
        panel1 = new JPanel(new BorderLayout());
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        //y axis artinya disusun secara vertikal
        //artinya item yang ditambahkan dluan akan berada diatas
        //item yang ditambahkan sesudahnya akan berada dibawahnya

        contHome = new JPanel();
        contHome.setPreferredSize(new Dimension(650,500));
        contHome.setMaximumSize(contHome.getPreferredSize());           //biar pas frame diperbesar, conthome ukurannya ga nambah
        contHome.setLayout(null);   //clear layour boxlayout panel1 agar bisa setbound
        //contHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        contHome.setBackground(Color.lightGray);

        JLabel label1 = new JLabel("Selamat Datang Di Toko Baju Budi!!!");
        Font labelFont = label1.getFont();
        label1.setFont(new Font(labelFont.getName(), Font.BOLD, 24)); // Ubah ukuran font menjadi 24
        label1.setBounds(110,0,650,100);

        //Gambar
        ImageIcon bajuhitam = new ImageIcon("images/kaoshitam.jpg");
        Image bajuhitam1 = bajuhitam.getImage();  //ambil gambar buat resize
        //Resize Gambar
        Image bajuhitam2 = bajuhitam1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajuhitam3 = new ImageIcon(bajuhitam2);
        //
        JLabel bajuhitam4 = new JLabel(bajuhitam3);
            bajuhitam4.setBounds(50,100,bajuhitam4.getPreferredSize().width,bajuhitam4.getPreferredSize().height);
        JLabel deskripsi1 = new JLabel("Kaos Polos Hitam");
            deskripsi1.setBounds(50,230,deskripsi1.getPreferredSize().width,deskripsi1.getPreferredSize().height);
        JLabel hargahitam = new JLabel("Rp. 50.0000");
            hargahitam.setBounds(50,245,hargahitam.getPreferredSize().width,hargahitam.getPreferredSize().height);
        JLabel ratinghitam = new JLabel("⭐⭐⭐⭐⭐");
            ratinghitam.setBounds(50,260,100,ratinghitam.getPreferredSize().height);

        //Gambar
        ImageIcon bajuputih = new ImageIcon("images/kaosputih.jpg");
        Image bajuputih1 = bajuputih.getImage();
        //Gambar
        Image bajuputih2 = bajuputih1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajuputih3 = new ImageIcon(bajuputih2);
        //
        JLabel bajuputih4 = new JLabel(bajuputih3);
            bajuputih4.setBounds(250,100,bajuputih4.getPreferredSize().width,bajuputih4.getPreferredSize().height);
        JLabel deskripsi2 = new JLabel("Kaos Polos Putih");
            deskripsi2.setBounds(250,230,bajuputih4.getPreferredSize().width,deskripsi2.getPreferredSize().height);
        JLabel hargaputih = new JLabel("Rp. 50.0000");
            hargaputih.setBounds(250,245,hargaputih.getPreferredSize().width,hargaputih.getPreferredSize().height);
        JLabel ratingputih = new JLabel("⭐⭐⭐⭐⭐");
            ratingputih.setBounds(250,260,100,ratingputih.getPreferredSize().height);

        //Gambar
        ImageIcon bajumerah = new ImageIcon("images/kaosmerah.jpg");
        Image bajumerah1 = bajumerah.getImage();  //ambil gambar buat resize
        //Gambar
        Image bajumerah2 = bajumerah1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajumerah3 = new ImageIcon(bajumerah2);
        //
        JLabel bajumerah4 = new JLabel(bajumerah3);
            bajumerah4.setBounds(450,100,bajumerah4.getPreferredSize().width,bajumerah4.getPreferredSize().height);
        JLabel deskripsi3 = new JLabel("Kaos Polos Merah");
            deskripsi3.setBounds(450,230,deskripsi3.getPreferredSize().width,deskripsi3.getPreferredSize().height);
        JLabel hargamerah = new JLabel("Rp. 50.0000");
            hargamerah.setBounds(450,245,hargamerah.getPreferredSize().width,hargamerah.getPreferredSize().height);
        JLabel ratingmerah = new JLabel("⭐⭐⭐⭐⭐");
            ratingmerah.setBounds(450,260,100,ratingmerah.getPreferredSize().height);

        //Gambar
        ImageIcon bajubiru = new ImageIcon("images/kaosbiru.jpg");
        Image bajubiru1 = bajubiru.getImage();
        //Gambar
        Image bajubiru2 = bajubiru1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajubiru3 = new ImageIcon(bajubiru2);
        //
        JLabel bajubiru4 = new JLabel(bajubiru3);
            bajubiru4.setBounds(50,290,bajubiru4.getPreferredSize().width,bajubiru4.getPreferredSize().height);
        JLabel deskripsi4 = new JLabel("Kaos Polos Biru");
            deskripsi4.setBounds(50,420,100,deskripsi4.getPreferredSize().height);
        JLabel hargabiru = new JLabel("Rp. 50.0000");
            hargabiru.setBounds(50,435,hargabiru.getPreferredSize().width,hargabiru.getPreferredSize().height);
        JLabel ratingbiru = new JLabel("⭐⭐⭐⭐⭐");
            ratingbiru.setBounds(50,450,100,ratingbiru.getPreferredSize().height);


        //Gambar
        ImageIcon bajuabu = new ImageIcon("images/kaosabu.jpg");
        Image bajuabu1 = bajuabu.getImage();
        //Gambar
        Image bajuabu2 = bajuabu1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon bajuabu3 = new ImageIcon(bajuabu2);
        //
        JLabel bajuabu4 = new JLabel(bajuabu3);
            bajuabu4.setBounds(250,290,bajuabu4.getPreferredSize().width,bajuabu4.getPreferredSize().height);
        JLabel deskripsi5 = new JLabel("Kaos Polos Abu");
            deskripsi5.setBounds(250,420,100,deskripsi5.getPreferredSize().height);
        JLabel hargaabu = new JLabel("Rp. 50.0000");
            hargaabu.setBounds(250,435,hargaabu.getPreferredSize().width,hargaabu.getPreferredSize().height);
        JLabel ratingabu = new JLabel("⭐⭐⭐⭐⭐");
            ratingabu.setBounds(250,450,100,ratingabu.getPreferredSize().height);

        footer = new JPanel();
        footer.setPreferredSize(new Dimension(650,35));
        footer.setMaximumSize(footer.getPreferredSize());
        //footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setBackground(Color.gray);
        //footer.setLayout(new BoxLayout(footer, BoxLayout.PAGE_AXIS));

        JLabel label2 = new JLabel("©2023 TokoBudi");
        Font labelFont2 = label2.getFont();
        label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 20)); // Ubah ukuran font menjadi 24
        //label2.setBounds(10,10,10,10);

            contHome.add(label1);
                contHome.add(bajuhitam4);
                    contHome.add(deskripsi1);
                    contHome.add(hargahitam);
                    contHome.add(ratinghitam);
                contHome.add(bajuputih4);
                    contHome.add(deskripsi2);
                    contHome.add(hargaputih);
                    contHome.add(ratingputih);
                contHome.add(bajumerah4);
                    contHome.add(deskripsi3);
                    contHome.add(hargamerah);
                    contHome.add(ratingmerah);
                contHome.add(bajubiru4);
                    contHome.add(deskripsi4);
                    contHome.add(hargabiru);
                    contHome.add(ratingbiru);
                contHome.add(bajuabu4);
                    contHome.add(deskripsi5);
                    contHome.add(hargaabu);
                    contHome.add(ratingabu);
        panel1.add(contHome/*, BorderLayout.CENTER*/);
            footer.add(label2);
        panel1.add(footer/*, BorderLayout.SOUTH*/);

        add(panel1);

        setVisible(true);

    }

}