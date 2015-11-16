package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Transaksi {

    private int id;
    private Vector<DetilTransaksi> detilTransaksi = new Vector<DetilTransaksi>();
    private Date tgl;
    private Karyawan usr;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

    public Transaksi(int id, Vector<DetilTransaksi> detilTransaksi, Date tgl,
            Karyawan usr) {
        this.id = id;
        this.detilTransaksi = detilTransaksi;
        this.tgl = tgl;
        this.usr = usr;
    }

    public Transaksi(Date tgl, Karyawan usr) {
        this.tgl = tgl;
        this.usr = usr;
    }

    public Transaksi(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Vector<DetilTransaksi> getDetilTransaksi() {
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
            total += detilTransaksi.get(i).getBarang().getHargaBeli()
                    * detilTransaksi.get(i).getJumlah();
        }
        return total;
    }

    public void addDetilTransaksi(DetilTransaksi dt) {
        detilTransaksi.add(dt);
    }
}
