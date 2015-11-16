package model;

public class DetilTransaksi {
	private Produk barang;
	private int jumlah;
	private Transaksi transaksi;

	public DetilTransaksi(Transaksi transaksi, Produk barang, int jumlah) {
		this.transaksi = transaksi;
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public DetilTransaksi(Produk barang, int jumlah) {
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public Produk getBarang() {
		return barang;
	}

	public int getJumlah() {
		return jumlah;
	}

	public Transaksi getTransaksi() {
		return transaksi;
	}

}
