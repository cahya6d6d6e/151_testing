package view;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import controller.Core;
import controller.CustActionListener;
import controller.CustWindowListener;
import controller.Operator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Supplier;

public class WindowFormSupplier extends JFrame {

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField tfNama, tfAlamat, tfTelp;
	private JTable tbl;
	private JLabel lbNama, lbAlamat, lbTelp;
	private Core core;
	private Vector<Supplier> supplier = new Vector<Supplier>();

	public WindowFormSupplier(Core core) {
		super("Data Supplier");
		this.core = core;

		ResultSet rs = Operator.getListProduk(core.getConnection());
		try {
			while (rs.next()) {
				supplier.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

		setResizable(false);

		setSize(810, 272);
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		setLayout(null);
		Container container = this.getContentPane();
		container.setBackground(Color.WHITE);
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);

		tbl = new JTable(Operator.resultSetToTableModel(Operator.getListSupplier(core.getConnection())));
		Operator.disableTableEdit(tbl);

		JPanel panTbl = new JPanel();

		panTbl.setLayout(new BorderLayout());
		panTbl.setBackground(Color.WHITE);
		panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);

		tfNama = new JTextField();
		tfAlamat = new JTextField();
		tfTelp = new JTextField();

		tfNama.setBounds(115, 35, 170, 20);
		tfAlamat.setBounds(115, 60, 170, 20);
		tfTelp.setBounds(115, 85, 170, 20);

		panTbl.setBounds(295, 10, 500, 200);

		lbNama = new JLabel("Nama Supplier");
		lbAlamat = new JLabel("Alamat Supplier");
		lbTelp = new JLabel("Telepon Supplier");

		lbNama.setBounds(10, 35, 100, 20);
		lbNama.setHorizontalAlignment(JLabel.RIGHT);
		lbAlamat.setBounds(10, 60, 100, 20);
		lbAlamat.setHorizontalAlignment(JLabel.RIGHT);
		lbTelp.setBounds(10, 85, 100, 20);
		lbTelp.setHorizontalAlignment(JLabel.RIGHT);

		JButton buttonTambah = new JButton("Tambah");
		buttonTambah.addActionListener(
				new CustActionListener(core, this, tbl, buttonTambah, CustActionListener.INSERT_SUPPLIER));
		buttonTambah.setBounds(205, 135, 80, 20);

		JButton buttonDelete = new JButton("Delete");
		buttonDelete.addActionListener(
				new CustActionListener(core, this, tbl, buttonTambah, CustActionListener.DELETE_SUPPLIER));
		buttonDelete.setBounds(115, 135, 80, 20);

		// Add Content
		container.add(tfNama);
		container.add(tfAlamat);
		container.add(tfTelp);
		container.add(lbNama);
		container.add(lbAlamat);
		container.add(lbTelp);
		container.add(panTbl);
		container.add(buttonDelete);
		container.add(buttonTambah);
		this.addWindowListener(new CustWindowListener(core, this));

	}

	public Vector<Supplier> getListSupplier() {
		return supplier;
	}

	public Supplier getSelectedSupplier() {
		return supplier.get(tbl.getSelectedRow());
	}

	public void submitToDB() {
		if (Operator.tambahSupplier(getSupplier(), core.getConnection())) {
			JOptionPane.showMessageDialog(this, "Data telah ditambahkan!");
		} else {
			JOptionPane.showMessageDialog(this, "Terjadi kesalahan!");
		}

		((DefaultTableModel) tbl.getModel()).addRow(new Object[] { Operator.getLastIDSupplier(core.getConnection()),
				tfNama.getText(), tfAlamat.getText(), tfTelp.getText(), tfTelp.getText(), });
		tfNama.setText("");
		tfAlamat.setText("");
		tfTelp.setText("");
		tfTelp.setText("");
	}

	public void resetForm() {
		tfNama.setText("");
		tfAlamat.setText("");
		tfTelp.setText("");
		tfTelp.setText("");

		if (tbl.getSelectedRow() >= 0) {
			((DefaultTableModel) tbl.getModel()).removeRow(tbl.getSelectedRow());
		}
	}

	public Supplier getSupplier() {
		return new Supplier(tfNama.getText(), tfAlamat.getText(), tfTelp.getText());
	}
}
