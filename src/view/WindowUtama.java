package view;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import controller.Core;
import controller.CustActionListener;
import controller.CustWindowListener;

public class WindowUtama extends JFrame {

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField tfID, tfNama, tfAlamat, tfPosisi;
	private JPasswordField tfPassword;

	private JLabel lbBarang, lbNama, lbJumlah, lbSatuan, lbHarga, lbPassword;
	private Core core;

	public WindowUtama(Core core) {
		super("Data Karyawan");

		this.core = core;

		setResizable(false);

		setSize(305, 102);
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		setLayout(null);
		Container container = this.getContentPane();
		container.setBackground(Color.WHITE);
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);

		JMenuItem miLogOut = new JMenuItem("Log Out");

		JMenu menuTrans = new JMenu("Data Master");
		JMenuItem miTransBarang = new JMenuItem("Master Barang");
		JMenuItem miTransKaryawan = new JMenuItem("Master Karyawan");
		JMenuItem miTransSupplier = new JMenuItem("Master Supplier");
		miTransBarang.addActionListener(new CustActionListener(core, this, CustActionListener.TAMPILKAN_FORM_PRODUK));
		miTransKaryawan.addActionListener(new CustActionListener(core, this, CustActionListener.TAMPILKAN_FORM_KARYAWAN));
		miTransSupplier.addActionListener(new CustActionListener(core, this, CustActionListener.TAMPILKAN_FORM_SUPPLIER));
		
		JMenu menuUtamaTransaksi = new JMenu("Transaksi");
		JMenuItem miTransTerima = new JMenuItem("Peneerimaan Barang");
		JMenuItem miTransKeluar = new JMenuItem("Pengeluaran Barang");
		miTransTerima.addActionListener(new CustActionListener(core, this, CustActionListener.TAMPILKAN_FORM_TRANS_MASUK));
		miTransKeluar.addActionListener(new CustActionListener(core, this, CustActionListener.TAMPILKAN_FORM_TRANS_KELUAR));
		/*
		 * JMenuItem miTransCetak = new JMenuItem("Cetak Transaksi");
		 */

		JMenu menuLaporan = new JMenu("Laporan Bulanan");
		// JMenuItem miBarangData = new JMenuItem("Data Barang");
		/*
		 * miBarangData.addActionListener(new CustActionListener(core, this,
		 * miBarangData, CustActionListener.SELECT_BARANG));
		 */
		JMenuItem miLaporanMasuk = new JMenuItem("Laporan Pembelian");
		JMenuItem miLaporanKeluar = new JMenuItem("Laporan Penjualan");

		menu.add(menuTrans);
		// menuTrans.add(miTransCetak);
		menuTrans.add(miTransBarang);
		menuTrans.add(miTransKaryawan);
		menuTrans.add(miTransSupplier);

		menu.add(menuUtamaTransaksi);
		menuUtamaTransaksi.add(miTransTerima);
		menuUtamaTransaksi.add(miTransKeluar);

		menu.add(menuLaporan);
		// menuBarang.add(miBarangData);
		menuLaporan.add(miLaporanMasuk);
		menuLaporan.add(miLaporanKeluar);

		JPanel panTbl = new JPanel();

		panTbl.setLayout(new BorderLayout());
		panTbl.setBackground(Color.WHITE);
		JLabel labelHeader = new JLabel("SELAMAT DATANG DI APLIKASI ADMINISTRASI");
		labelHeader.setBounds(16, 0, 300, 20);

		// Add Content
		container.add(labelHeader);
		this.addWindowListener(new CustWindowListener(core, this));

	}

}
