package model;

public class DetilTransaksiMasuk {
	private Produk barang;
	private int jumlah;
	private TransaksiMasuk transaksi;

	public DetilTransaksiMasuk(TransaksiMasuk transaksi, Produk barang, int jumlah) {
		this.transaksi = transaksi;
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public DetilTransaksiMasuk(Produk barang, int jumlah) {
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public Produk getBarang() {
		return barang;
	}

	public int getJumlah() {
		return jumlah;
	}

	public TransaksiMasuk getTransaksi() {
		return transaksi;
	}

}
