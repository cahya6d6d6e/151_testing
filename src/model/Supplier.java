package model;

public class Supplier {

    private int id_supplier;
    private String nama_supplier, alamat_supplier, telp_supplier;

    public Supplier(int id_supplier, String nama_supplier, String alamat_supplier, String telp_supplier) {
        this.id_supplier = id_supplier;
        this.nama_supplier = nama_supplier;
        this.alamat_supplier = alamat_supplier;
        this.telp_supplier = telp_supplier;
    }

    public Supplier(String nama_supplier, String alamat_supplier, String telp_supplier) {
        this.nama_supplier = nama_supplier;
        this.alamat_supplier = alamat_supplier;
        this.telp_supplier = telp_supplier;
    }

    public int getId() {
        return this.id_supplier;
    }

    public String getNama() {
        return this.nama_supplier;
    }

    public String getAlamat() {
        return this.alamat_supplier;
    }

    public String getTelp() {
        return this.telp_supplier;
    }

}
