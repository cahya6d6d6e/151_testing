package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class TransaksiMasuk {

	private int id;
	private Vector<DetilTransaksiMasuk> detilTransaksi = new Vector<DetilTransaksiMasuk>();
	private Date tgl;
	private Karyawan usr;
	private Supplier supplier;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

	public TransaksiMasuk(int id, Vector<DetilTransaksiMasuk> detilTransaksi, Date tgl, Karyawan usr,
			Supplier supplier) {
		this.id = id;
		this.detilTransaksi = detilTransaksi;
		this.tgl = tgl;
		this.usr = usr;
		this.supplier = supplier;
	}

	public TransaksiMasuk(Date tgl, Karyawan usr) {
		this.tgl = tgl;
		this.usr = usr;
	}

	public TransaksiMasuk(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Vector<DetilTransaksiMasuk> getDetilTransaksi() {
		return detilTransaksi;
	}

	public Date getTgl() {
		return tgl;
	}

	public String getTglAsString() {
		return formatter.format(tgl.getTime());
	}

	public Karyawan getUser() {
		return usr;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public int getTotalItem() {
		int total = 0;
		for (int i = 0; i < detilTransaksi.size(); i++) {
			total += detilTransaksi.get(i).getJumlah();
		}
		return total;
	}

	public int getTotalHrg() {
		int total = 0;
		for (int i = 0; i < detilTransaksi.size(); i++) {
			total += detilTransaksi.get(i).getBarang().getHargaBeli() * detilTransaksi.get(i).getJumlah();
		}
		return total;
	}

	public void addDetilTransaksi(DetilTransaksiMasuk dt) {
		detilTransaksi.add(dt);
	}
}
