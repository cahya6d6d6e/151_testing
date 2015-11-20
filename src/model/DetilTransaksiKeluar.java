package model;

public class DetilTransaksiKeluar {
	private Produk barang;
	private int jumlah;
	private TransaksiKeluar transaksi;

	public DetilTransaksiKeluar(TransaksiKeluar transaksi, Produk barang, int jumlah) {
		this.transaksi = transaksi;
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public DetilTransaksiKeluar(Produk barang, int jumlah) {
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public Produk getBarang() {
		return barang;
	}

	public int getJumlah() {
		return jumlah;
	}

	public TransaksiKeluar getTransaksi() {
		return transaksi;
	}

}
