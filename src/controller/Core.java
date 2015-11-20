package controller;

import view.WindowReport;
import view.WindowDataTransaksi;
import view.WindowFormBarang;
import view.WindowDataBarang;
import view.WindowFormTransaksiKeluar;
import view.WindowLogin;
import model.Produk;
import model.DetilTransaksiKeluar;
import model.DetilTransaksiMasuk;
import model.Karyawan;
import model.TransaksiKeluar;
import model.TransaksiMasuk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import view.WindowUtama;

public class Core {

    final public static boolean GUI = true, CUI = false;
    final public static int BARANG = 0, DETIL_TRANSAKSI = 1, TRANSAKSI = 2,
            USER = 3;

    final private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final private static String
            DB_URL = "jdbc:mysql://localhost/testing",
            DB_USER = "root",
            DB_PASS = "",
            DB_USER_2 = "testing",
            DB_PASS_2 = "testing";

    public WindowLogin frmLogin = new WindowLogin(this);
    public WindowReport frmReport;
    public WindowFormTransaksiKeluar frmFormTrans;
    public WindowFormBarang frmFormBarang;
    public WindowDataTransaksi frmDataTrans;
    public WindowDataBarang frmDataBarang;
    public WindowUtama winUtama;

    private Connection con;
    private Karyawan loggedUser;

    private static Calendar tgl = Calendar.getInstance();
    private static SimpleDateFormat formatter = new SimpleDateFormat(
            "E, dd MMMM yyyy");

    public Core(boolean GUI) {
        buildConnection();
        frmLogin.setVisible(true);
    }

    private void buildConnection() {
        try {
            Class.forName(this.JDBC_DRIVER);
            con = DriverManager.getConnection(Core.DB_URL, Core.DB_USER, Core.DB_PASS);
        } catch (Exception e) {
            try {
                Class.forName(this.JDBC_DRIVER);
                con = DriverManager.getConnection(Core.DB_URL, Core.DB_USER_2, Core.DB_PASS_2);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR " + e.getLocalizedMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "ERROR " + ex.getLocalizedMessage());
                ex.printStackTrace();
                System.exit(0);
            }
        }
    }

    public void printReport(TransaksiKeluar trns) {
        int ID = Operator.getLastIDTransMasuk(con);

        String header = "\n"
                + "\t\t        PC Part Shop"
                + "\n\t\t  Hi Tech Mall Surabaya "
                + "\n\t \t\t  No. "
                + ID
                + "\nKasir : "
                + loggedUser.getId()
                + "\n=================================================================", data = "", footer = "\n"
                + "\n---------------------------------------"
                + "\nTotal : "
                + trns.getTotalItem()
                + " Item      "
                + trns.getTotalHrg()
                + "\n================================================================="
                + "\nTgl " + trns.getTglAsString();
        for (int i = 0; i < trns.getDetilTransaksi().size(); i++) {
            DetilTransaksiKeluar dt = trns.getDetilTransaksi().get(i);
            data = data + "\n" + dt.getBarang().getNama() + "\t"
                    + dt.getJumlah() + "x\t" + dt.getJumlah()
                    * dt.getBarang().getHargaBeli();
        }
        frmReport = new WindowReport(this,
                new String[]{header, data, footer});
        frmReport.setVisible(true);
    }
    public void printReport(TransaksiMasuk trns) {
        int ID = Operator.getLastIDTransMasuk(con);

        String header = "\n"
                + "\t\t        PC Part Shop"
                + "\n\t\t  Hi Tech Mall Surabaya "
                + "\n\t \t\t  No. "
                + ID
                + "\nKasir : "
                + loggedUser.getId()
                + "\n=================================================================", data = "", footer = "\n"
                + "\n---------------------------------------"
                + "\nTotal : "
                + trns.getTotalItem()
                + " Item      "
                + trns.getTotalHrg()
                + "\n================================================================="
                + "\nTgl " + trns.getTglAsString();
        for (int i = 0; i < trns.getDetilTransaksi().size(); i++) {
            DetilTransaksiMasuk dt = trns.getDetilTransaksi().get(i);
            data = data + "\n" + dt.getBarang().getNama() + "\t"
                    + dt.getJumlah() + "x\t" + dt.getJumlah()
                    * dt.getBarang().getHargaBeli();
        }
        frmReport = new WindowReport(this,
                new String[]{header, data, footer});
        frmReport.setVisible(true);
    }

    public void printReport(Vector<Produk> brg) {
        String header = "\n"
                + "\t\t    PC Part Shop"
                + "\n\t\t Hi Tech Mall Surabaya"
                + "\n\t   Stok barang tgl "
                + getDateAsString()
                + "\nKasir : "
                + loggedUser.getId()
                + "\n===============================================================", data = "", footer = "\n===============================================================";

        for (int i = 0; i < brg.size(); i++) {

            Produk _brg = brg.get(i);
            data = data + "\n  " + _brg.getNama();
            for (int a = 0; a < 25 - _brg.getNama().length(); a++) {
                data = data + " ";
            }

            data = data + _brg.getJumlah();
            for (int a = 0; a < 10 - ("" + _brg.getJumlah()).length(); a++) {
                data = data + " ";
            }

            data += _brg.getHargaJual();
            for (int a = 0; a < 10 - ("" + _brg.getHargaJual()).length(); a++) {
                data = data + " ";
            }

            data += _brg.getHargaBeli();
        }
        frmReport = new WindowReport(this,
                new String[]{header, data, footer});
        frmReport.setVisible(true);
    }

    public void login(Karyawan usr) {
        this.loggedUser = new Karyawan(usr);
        winUtama = new WindowUtama(this);
        winUtama.setVisible(true);
    }

    public void logout() {
        winUtama.setVisible(false);
        frmLogin.dispose();
        frmLogin = new WindowLogin(this);
        frmLogin.setVisible(true);
        loggedUser = null;
    }

    public void backToUtama(JFrame activeForm) {
        activeForm.setVisible(false);
        winUtama = new WindowUtama(this);
        winUtama.setVisible(true);
    }

    public Karyawan getLoggedInUser() {
        return loggedUser;
    }

    public Connection getConnection() {
        return con;
    }

    public Date getDate() {
        return (Date) tgl.getTime();
    }

    public String getDateAsString() {
        return formatter.format(tgl.getTime());
    }
}
