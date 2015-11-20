package view;

import controller.Operator;
import controller.Core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Produk;
import model.DetilTransaksiKeluar;
import model.TransaksiKeluar;
import model.Karyawan;

import controller.CustActionListener;
import controller.CustKeyListener;
import controller.CustWindowListener;
import model.Supplier;

public class WindowFormTransaksiKeluar extends JFrame {

	final int TGL = 0, KASIR = 1, PRODUK = 2, HARGA = 3, JUMLAH = 4;

	private Core core;

	private Karyawan user;
	private TransaksiKeluar t;

	private JPanel panLeft, panRight, panTable, panGrand;
	private JTextField tfTgl, tfKasir, tfHarga, tfJumlah;
	private JLabel lbl[] = new JLabel[5];
	private JComboBox cbProduk;
	private JButton btnTambahBarang, btnTambahTransaksi;
	private JTable tbl;

	private DefaultTableModel model;

	private Container container;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Vector<String> nmBarang = new Vector<String>();
	private Vector<Produk> barang = new Vector<Produk>();

	public WindowFormTransaksiKeluar(Core core) {
		super("Formulir Pengeluaran Barang - " + core.getDateAsString());
		this.core = core;
		this.user = core.getLoggedInUser();

		setResizable(false);
		setSize(720, 400);
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		setLayout(null);
		container = this.getContentPane();
		container.setBackground(Color.WHITE);
		model = new DefaultTableModel();
		model.addColumn("Nama Item");
		model.addColumn("Jumlah Item");
		model.addColumn("Total Harga");
		tbl = new JTable(model);
		Operator.disableTableEdit(tbl);

		ResultSet rs_barang = Operator.getListProduk(core.getConnection());
		nmBarang.removeAllElements();
		barang.removeAllElements();
		try {
			while (rs_barang.next()) {
				barang.add(new Produk(rs_barang.getInt(1), rs_barang.getString(2), rs_barang.getInt(3),
						rs_barang.getInt(4), rs_barang.getInt(5)));
				if (barang.lastElement().getJumlah() > 0) {
					nmBarang.add(barang.lastElement().getNama());
				} else {
					barang.removeElement(barang.lastElement());
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		cbProduk = new JComboBox(nmBarang);

		tfTgl = new JTextField(core.getDateAsString());
		tfKasir = new JTextField(user.getNama());
		tfJumlah = new JTextField();
		tfHarga = new JTextField();
		fillFormByIndex(cbProduk.getSelectedIndex());

		panTable = new JPanel(new BorderLayout());
		panTable.setBounds(275, 0, 440, 300);
		panTable.setBackground(Color.WHITE);
		panGrand = new JPanel(null);
		panGrand.setBounds(275, 300, 440, 100);
		panLeft = new JPanel(null);
		panLeft.setBounds(0, 0, 275, 400);

		lbl[TGL] = new JLabel("Tanggal");
		lbl[KASIR] = new JLabel("Nama Kasir");
		lbl[PRODUK] = new JLabel("Nama Barang ");
		lbl[HARGA] = new JLabel("Harga Rp.");
		lbl[JUMLAH] = new JLabel("Jumlah Item");

		tfTgl.setEnabled(false);
		tfKasir.setEnabled(false);
		tfHarga.setEnabled(false);
		tfTgl.setBounds(95, 10, 170, 20);
		tfKasir.setBounds(95, 35, 170, 20);
		cbProduk.setBounds(95, 60, 170, 20);
		tfHarga.setBounds(95, 85, 170, 20);
		tfJumlah.setBounds(95, 110, 170, 20);

		lbl[TGL].setBounds(10, 10, 80, 20);
		lbl[KASIR].setBounds(10, 35, 80, 20);
		lbl[PRODUK].setBounds(10, 60, 80, 20);
		lbl[HARGA].setBounds(10, 85, 80, 20);
		lbl[JUMLAH].setBounds(10, 110, 80, 20);

		btnTambahBarang = new JButton("Tambah Barang");
		btnTambahBarang.setBounds(105, 155, 140, 20);
		btnTambahBarang.addActionListener(new CustActionListener(this, btnTambahBarang));
		btnTambahTransaksi = new JButton("Selesai & Print");
		btnTambahTransaksi.setBounds(165, 10, 120, 20);
		btnTambahTransaksi.addActionListener(new CustActionListener(core, this, btnTambahTransaksi));
		tfJumlah.addKeyListener(new CustKeyListener(this, tfJumlah, CustKeyListener.NUMBER_ONLY));
		tfJumlah.addKeyListener(new CustKeyListener(this, tfJumlah, CustKeyListener.ON_STOCK));
		cbProduk.addActionListener(new CustActionListener(this, cbProduk));
		panLeft.add(cbProduk);
		panLeft.add(tfTgl);
		panLeft.add(tfKasir);
		panLeft.add(tfHarga);
		panLeft.add(tfJumlah);
		for (int i = 0; i < lbl.length; i++) {
			lbl[i].setHorizontalAlignment(JLabel.RIGHT);
			panLeft.add(lbl[i]);
		}
		panTable.add((JTableHeader) tbl.getTableHeader(), BorderLayout.NORTH);
		panTable.add(new JScrollPane(tbl), BorderLayout.CENTER);
		panGrand.add(btnTambahTransaksi);
		panLeft.add(btnTambahBarang);

		container.add(panLeft);
		container.add(panTable);
		container.add(panGrand);
		this.addWindowListener(new CustWindowListener(core, this));
		resetForm();
	}

	public void fillFormByIndex(int index) {
		tfJumlah.setText("1");
		tfHarga.setText(barang.get(index).getHargaBeli() * Integer.parseInt(tfJumlah.getText()) + "");
	}

	public void resetForm() {
		int row = tbl.getRowCount() - 1;
		for (int i = row; i >= 0; i--) {
			((DefaultTableModel) tbl.getModel()).removeRow(i);
		}
		t = new TransaksiKeluar(core.getDate(), user);
	}

	public void addBarangToTable(DetilTransaksiKeluar dt) {
		model.addRow(new String[] { dt.getBarang().getNama(), dt.getJumlah() + "",
				dt.getBarang().getHargaBeli() * dt.getJumlah() + "" });
		t.addDetilTransaksi(dt);
	}

	public Vector<Produk> getListBarang() {
		return barang;
	}

	public Produk getSelectedBarang() {
		return barang.get(cbProduk.getSelectedIndex());
	}

	public int getQtyBarang() {
		return Integer.parseInt(tfJumlah.getText());
	}

	public TransaksiKeluar getTransaksi() {
		return t;
	}

	public Vector<DetilTransaksiKeluar> getDetilTransaksi() {
		return t.getDetilTransaksi();
	}

	public void submitToDB() {
		if (Operator.tambahTransaksiKeluar(getTransaksi(), core.getConnection())) {
			JOptionPane.showMessageDialog(this, "Data telah ditambahkan!");
		} else {
			JOptionPane.showMessageDialog(this, "Terjadi kesalahan!");
		}
		if (JOptionPane.showConfirmDialog(this, "Cetak transaksi?", "", JOptionPane.YES_NO_OPTION) == 0) {
			core.printReport(t);
		}
		resetForm();
	}
}
