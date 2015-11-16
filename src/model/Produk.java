package model;

public class Produk {

    private int id, jumlah, harga, satuan;
    private String nama;

    public Produk(int id, String nama, int jumlah, int harga_beli, int harga_jual) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
        this.satuan = harga_beli;
        this.harga = harga_jual;
    }

    public Produk(String nama, int jumlah, int harga_beli, int harga_jual) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.satuan = harga_beli;
        this.harga = harga_jual;
    }

    public int getId() {
        return id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getNama() {
        return nama;
    }

    public int getHargaJual() {
        return satuan;
    }

    public int getHargaBeli() {
        return harga;
    }
}
