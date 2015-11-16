package model;

public class Karyawan {

    private int id_karyawan;
    private String nama_karyawan, alamat_karyawan, telp_karyawan, posisi, password;

    public Karyawan(int id_karyawan, String nama_karyawan, String alamat_karyawan, String telp_karyawan, String posisi, String password) {
        this.id_karyawan = id_karyawan;
        this.nama_karyawan = nama_karyawan;
        this.alamat_karyawan = alamat_karyawan;
        this.telp_karyawan = telp_karyawan;
        this.posisi = posisi;
        this.password = password;
    }

    public Karyawan(String nama_karyawan, String alamat_karyawan, String telp_karyawan, String posisi, String password) {
        this.nama_karyawan = nama_karyawan;
        this.alamat_karyawan = nama_karyawan;
        this.telp_karyawan = telp_karyawan;
        this.posisi = posisi;
        this.password = password;
    }

    public Karyawan(Karyawan usr) {
        this.id_karyawan = usr.getId();
        this.nama_karyawan = usr.getNama();
        this.alamat_karyawan = usr.getNama();
        this.telp_karyawan = usr.getTelp();
        this.posisi = usr.getPosisi();
        this.password = usr.getPassword();
    }

    public int getId() {
        return id_karyawan;
    }

    public String getNama() {
        return nama_karyawan;
    }

    public String getAlamat() {
        return alamat_karyawan;
    }

    public String getTelp() {
        return telp_karyawan;
    }

    public String getPosisi() {
        return posisi;
    }

    public String getPassword() {
        return password;
    }
}
