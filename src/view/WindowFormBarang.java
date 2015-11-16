package view;

import controller.Operator;
import controller.Core;
import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import model.Produk;
import controller.CustActionListener;
import controller.CustKeyListener;
import controller.CustWindowListener;

public class WindowFormBarang extends JFrame {

    private Core core;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextField tfNama, tfJumlah, tfHargaBeli, tfHargaJual;
    private JTable tbl;
    private JLabel lbNama, lbJumlah, lbHargaBeli, lbHargaJual;

    private Vector<Produk> barang = new Vector<Produk>();
    private Vector<String> nmBarang = new Vector<String>();

    public WindowFormBarang(final Core core) {
        super("Formulir Barang");
        this.core = core;

        ResultSet rs = Operator.getListProduk(core.getConnection());
        try {
            while (rs.next()) {
                barang.add(new Produk(rs.getInt(1), rs.getString(2), rs
                        .getInt(3), rs.getInt(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        setResizable(false);

        setSize(810, 272);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        setLayout(null);
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);

        tbl = new JTable(Operator.resultSetToTableModel(Operator
                .getListProduk(core.getConnection())));
        Operator.disableTableEdit(tbl);
        JPanel panTbl = new JPanel();

        panTbl.setLayout(new BorderLayout());
        panTbl.setBackground(Color.WHITE);
        panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);

        tfNama = new JTextField();
        tfJumlah = new JTextField();
        tfHargaBeli = new JTextField();
        tfHargaJual = new JTextField();

        tfNama.setBounds(115, 10, 170, 20);
        tfJumlah.setBounds(115, 35, 170, 20);
        tfJumlah.addKeyListener(new CustKeyListener(core, this, tfJumlah,
                CustKeyListener.NUMBER_ONLY));

        tfHargaBeli.setBounds(115, 60, 170, 20);
        tfHargaBeli.addKeyListener(new CustKeyListener(core, this, tfHargaJual,
                CustKeyListener.NUMBER_ONLY));
        tfHargaJual.setBounds(115, 85, 170, 20);
        tfHargaJual.addKeyListener(new CustKeyListener(core, this, tfHargaJual,
                CustKeyListener.NUMBER_ONLY));
        panTbl.setBounds(295, 10, 500, 200);

        lbNama = new JLabel("Nama Barang");
        lbJumlah = new JLabel("Jumlah Barang");
        lbHargaBeli = new JLabel("Harga Beli");
        lbHargaJual = new JLabel("Harga Jual");

        lbNama.setBounds(10, 10, 100, 20);
        lbNama.setHorizontalAlignment(JLabel.RIGHT);
        lbJumlah.setBounds(10, 35, 100, 20);
        lbJumlah.setHorizontalAlignment(JLabel.RIGHT);

        lbHargaBeli.setBounds(10, 60, 100, 20);
        lbHargaBeli.setHorizontalAlignment(JLabel.RIGHT);
        lbHargaJual.setBounds(10, 85, 100, 20);
        lbHargaJual.setHorizontalAlignment(JLabel.RIGHT);

        JButton buttonTambah = new JButton("Tambah");
        JButton buttonDelete = new JButton("Delete");

        buttonTambah.setBounds(205, 115, 80, 20);
        buttonTambah.addActionListener(new CustActionListener(core, this, tbl,
                buttonTambah, CustActionListener.INSERT_BARANG));
        buttonDelete.setBounds(115, 115, 80, 20);
        buttonDelete.addActionListener(new CustActionListener(core, this, tbl,
                buttonDelete, CustActionListener.DELETE_BARANG));
        // Add Content
        container.add(tfNama);
        container.add(tfJumlah);
        container.add(tfHargaBeli);
        container.add(tfHargaJual);
        container.add(lbNama);
        container.add(lbJumlah);
        container.add(lbHargaBeli);
        container.add(lbHargaJual);
        container.add(panTbl);

        container.add(buttonDelete);
        container.add(buttonTambah);
        this.addWindowListener(new CustWindowListener(core, this));

    }

    public Vector<Produk> getListBarang() {
        return barang;
    }

    public Produk getSelectedBarang() {
        return barang.get(tbl.getSelectedRow());
    }

    public void submitToDB() {
        if (Operator.tambahProduk(getBarang(), core.getConnection())) {
            JOptionPane.showMessageDialog(this, "Data telah ditambahkan!");
        } else {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan!");
        }

        ((DefaultTableModel) tbl.getModel()).addRow(
                new Object[]{
                    Operator.getLastIDProduk(
                            core.getConnection()),
                    tfNama.getText(),
                    tfJumlah.getText(),
                    tfHargaBeli.getText(),
                    tfHargaJual.getText()
                });

        tfNama.setText("");
        tfJumlah.setText("");
        tfHargaBeli.setText("");
        tfHargaJual.setText("");
    }

    public void resetForm() {
        tfNama.setText("");
        tfJumlah.setText("");
        tfHargaBeli.setText("");
        tfHargaJual.setText("");

        if (tbl.getSelectedRow() >= 0) {
            ((DefaultTableModel) tbl.getModel())
                    .removeRow(tbl.getSelectedRow());
        }
    }

    public Produk getBarang() {
        return new Produk(tfNama.getText(),
                Integer.parseInt(tfJumlah.getText()),
                Integer.parseInt(tfHargaBeli.getText()),
                Integer.parseInt(tfHargaJual.getText()));
    }
}
