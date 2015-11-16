package view;

import controller.Core;
import controller.CustWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class WindowFormTransaksiMasuk extends JFrame {

    final int TGL = 0, KASIR = 1, BARANG = 2, HARGA = 3, JUMLAH = 4;

    private JPanel panLeft, panRight, panTable, panGrand;
    private JTextField tfTgl, tfKasir, tfHarga, tfJumlah;
    private JLabel l[] = new JLabel[5];
    private JButton btnTambahBarang, btnTambahTransaksi;
    private JTable tbl;
    private JComboBox cb;

    private DefaultTableModel model;

    private Container container;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Vector<String> nmBarang = new Vector<String>();

    public WindowFormTransaksiMasuk(Core core) {
        super("Formulir Transaksi - ");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(720, 400);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        setLayout(null);
        container = this.getContentPane();
        container.setBackground(Color.WHITE);
        model = new DefaultTableModel();
        model.addColumn("Nama Item");
        model.addColumn("Jumlah Item");
        model.addColumn("Total Harga");
        tbl = new JTable(model);

        nmBarang.removeAllElements();

        cb = new JComboBox(nmBarang);
        tfTgl = new JTextField();
        tfKasir = new JTextField();
        tfJumlah = new JTextField();
        tfHarga = new JTextField();

        panTable = new JPanel(new BorderLayout());
        panTable.setBounds(275, 0, 440, 300);
        panTable.setBackground(Color.WHITE);
        panGrand = new JPanel(null);
        panGrand.setBounds(275, 300, 440, 100);
        panLeft = new JPanel(null);
        panLeft.setBounds(0, 0, 275, 400);

//        JMenuBar menu = new JMenuBar();
//        this.setJMenuBar(menu);

        /*
         * JMenu menuTrans = new JMenu("Transaksi"); JMenuItem miTransData = new
         * JMenuItem("Data Transaksi"); JMenuItem miTransCetak = new
         * JMenuItem("Cetak Transaksi");
         */
//        JMenu menuBarang = new JMenu("Barang");
//        JMenuItem miBarangData = new JMenuItem("Data Barang");
				// JMenuItem miBarangCetak = new JMenuItem("Cetak Barang");

        /*
         * menu.add(menuTrans); menuTrans.add(miTransData);
         * menuTrans.add(miTransCetak);
         */
//        menu.add(menuBarang);
//        menuBarang.add(miBarangData);
        // menuBarang.add(miBarangCetak);

        l[TGL] = new JLabel("Tanggal");
        l[KASIR] = new JLabel("Nama Kasir");
        l[BARANG] = new JLabel("Nama Barang ");
        l[HARGA] = new JLabel("Harga Rp.");
        l[JUMLAH] = new JLabel("Jumlah Item");

        tfTgl.setEnabled(false);
        tfKasir.setEnabled(false);
        tfHarga.setEnabled(false);
        tfTgl.setBounds(95, 10, 170, 20);
        tfKasir.setBounds(95, 35, 170, 20);
        cb.setBounds(95, 60, 170, 20);
        tfHarga.setBounds(95, 85, 170, 20);
        tfJumlah.setBounds(95, 110, 170, 20);

        l[TGL].setBounds(10, 10, 80, 20);
        l[KASIR].setBounds(10, 35, 80, 20);
        l[BARANG].setBounds(10, 60, 80, 20);
        l[HARGA].setBounds(10, 85, 80, 20);
        l[JUMLAH].setBounds(10, 110, 80, 20);

        btnTambahBarang = new JButton("Tambah Barang");
        btnTambahBarang.setBounds(105, 155, 140, 20);

        btnTambahTransaksi = new JButton("Selesai & Print");
        btnTambahTransaksi.setBounds(165, 10, 120, 20);

        panLeft.add(cb);
        panLeft.add(tfTgl);
        panLeft.add(tfKasir);
        panLeft.add(tfHarga);
        panLeft.add(tfJumlah);
        for (int i = 0; i < l.length; i++) {
            l[i].setHorizontalAlignment(JLabel.RIGHT);
            panLeft.add(l[i]);
        }
        panTable.add((JTableHeader) tbl.getTableHeader(), BorderLayout.NORTH);
        panTable.add(new JScrollPane(tbl), BorderLayout.CENTER);
        panGrand.add(btnTambahTransaksi);
        panLeft.add(btnTambahBarang);

        container.add(panLeft);
        container.add(panTable);
        container.add(panGrand);
        this.addWindowListener(new CustWindowListener(core, this));
    }

}
