package controller;

import model.DetilTransaksi;
import model.Karyawan;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.*;

public class CustActionListener implements ActionListener {

    public final static int LOGIN = 0,
            FORM_TRANSAKSI_ADDBARANG = 1,
            FORM_TRANSAKSI_SUBMIT = 2,
            FORM_TRANSAKSI_SELECTITEM = 3,
            LOGOUT = 4,
            INSERT_BARANG = 5,
            SELECT_BARANG = 6,
            DELETE_BARANG = 7,
            INSERT_KARYAWAN = 8,
            SELECT_KARYAWAN = 9,
            DELETE_KARYAWAN = 10,
            INSERT_SUPPLIER = 11,
            SELECT_SUPPLIER = 12,
            DELETE_SUPPLIER = 13,
            REPORT_BARANG = 14,
            SELECT_TRANSAKSI = 15,
            DELETE_TRANS = 16,
            TAMPILKAN_FORM_PRODUK = 17,
            TAMPILKAN_FORM_KARYAWAN = 18,
            TAMPILKAN_FORM_SUPPLIER = 19,
            TAMPILKAN_FORM_TRANS_MASUK = 20,
            TAMPILKAN_FORM_TRANS_KELUAR = 21;
    private Core core;

    private JFrame jf;
    private WindowLogin frmLogin;
    private WindowReport frmReport;
    private WindowFormBarang frmFormBarang;
    private WindowFormKaryawan frmFormKaryawan;
    private WindowFormSupplier frmFormSupplier;
    private WindowFormTransaksiKeluar frmFormTrans;
    private WindowDataTransaksi frmDataTrans;
//    private WindowDataBarang frmDataBarang;

    private JButton btn;
    private JComboBox cb;
    private JTable tbl;

    private JTextField tf;

    private int mode;
    private int mode2;

    public CustActionListener(Core core, WindowLogin frm, JButton btn) {
        this.core = core;
        this.frmLogin = frm;
        this.jf = frm;
        this.btn = btn;
        mode = LOGIN;
    }

    public CustActionListener(Core core, WindowFormTransaksiKeluar frm, JButton btn) {
        this.core = core;
        this.frmFormTrans = frm;
        this.jf = frm;
        this.btn = btn;
        this.mode = FORM_TRANSAKSI_SUBMIT;
    }

    public CustActionListener(WindowFormTransaksiKeluar frm, JButton btn) {
        this.frmFormTrans = frm;
        this.jf = frm;
        this.btn = btn;
        this.mode = FORM_TRANSAKSI_ADDBARANG;
    }

    public CustActionListener(WindowFormTransaksiKeluar frm, JComboBox cb) {
        this.frmFormTrans = frm;
        this.jf = frm;
        this.cb = cb;
        mode = FORM_TRANSAKSI_SELECTITEM;
    }

//    public CustActionListener(Core core, JFrame jf, JMenuItem mi, int mode) {
//        this.core = core;
//        this.jf = jf;
//        this.mi = mi;
//        this.mode = mode;
//    }
//
//    public CustActionListener(Core core, WindowFormBarang frm, JMenuItem mi,
//            int mode) {
//        this.core = core;
//        this.frmFormBarang = frm;
//        this.mi = mi;
//        this.mode = mode;
//    }
    public CustActionListener(Core core, WindowFormBarang frm, JTable tbl, JButton btn,
            int mode) {
        this.core = core;
        this.frmFormBarang = frm;
        this.jf = frm;
        this.btn = btn;
        this.tbl = tbl;
        this.mode = mode;
    }

    public CustActionListener(Core core, WindowFormKaryawan frm, JTable tbl, JButton btn,
            int mode) {
        this.core = core;
        this.frmFormKaryawan = frm;
        this.jf = frm;
        this.btn = btn;
        this.tbl = tbl;
        this.mode = mode;
    }

    public CustActionListener(Core core, WindowFormSupplier frm, JTable tbl, JButton btn,
            int mode) {
        this.core = core;
        this.frmFormSupplier = frm;
        this.jf = frm;
        this.btn = btn;
        this.tbl = tbl;
        this.mode = mode;
    }

    public CustActionListener(Core core, WindowDataTransaksi frm, JTable tbl,
            JButton btn, int mode) {
        this.core = core;
        this.tbl = tbl;
        this.frmDataTrans = frm;
        this.jf = frm;
        this.btn = btn;
        this.mode = mode;
    }

    public CustActionListener(Core core, WindowUtama frm, int mode) {
        this.mode = mode;
        this.core = core;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (mode) {
            case LOGIN:
                Karyawan user = Operator.login(core.getConnection(), frmLogin.getUser(), frmLogin.getPass());
                if (user == null) {
                    JOptionPane.showMessageDialog(frmLogin,
                            "Username atau password tidak tepat");
                } else {
                    frmLogin.setVisible(false);
                    core.login(user);
                }
                break;
            case FORM_TRANSAKSI_SELECTITEM:
                int index = cb.getSelectedIndex();
                frmFormTrans.fillFormByIndex(index);
                break;
            case FORM_TRANSAKSI_ADDBARANG:
                frmFormTrans.addBarangToTable(new DetilTransaksi(frmFormTrans
                        .getSelectedBarang(), frmFormTrans.getQtyBarang()));
                break;
            case FORM_TRANSAKSI_SUBMIT:
                frmFormTrans.submitToDB();
                break;
            case LOGOUT:
                core.logout();
                break;
            case SELECT_BARANG:
                if (core.frmDataBarang == null) {
                } else {
                    core.frmDataBarang.dispose();
                }
                core.frmDataBarang = new WindowDataBarang(core);
                core.frmDataBarang.setVisible(true);
                break;
            case SELECT_TRANSAKSI:
                if (core.frmDataTrans == null) {
                } else {
                    core.frmDataTrans.dispose();
                }
                core.frmDataTrans = new WindowDataTransaksi(core);
                core.frmDataTrans.setVisible(true);
                break;
            case REPORT_BARANG:
                core.printReport(frmFormBarang.getListBarang());
                break;
            case INSERT_BARANG:
                frmFormBarang.submitToDB();
                break;
            case DELETE_BARANG:
                if (tbl == null) {
                } else {
                    if (Operator.hapusProduk(frmFormBarang.getSelectedBarang(),
                            core.getConnection())) {
                        JOptionPane.showMessageDialog(frmFormBarang,
                                "Data barang dihapus");
                    }
                    frmFormBarang.resetForm();
                }
                break;
            case INSERT_KARYAWAN:
                frmFormKaryawan.submitToDB();
                break;
            case DELETE_KARYAWAN:
                if (tbl == null) {
                } else {
                    if (Operator.hapusKaryawan(frmFormKaryawan.getSelectedKaryawan(),
                            core.getConnection())) {
                        JOptionPane.showMessageDialog(frmFormKaryawan,
                                "Data karyawan dihapus");
                    }
                    frmFormKaryawan.resetForm();
                }
                break;
            case INSERT_SUPPLIER:
                frmFormSupplier.submitToDB();
                break;
            case DELETE_SUPPLIER:
                if (tbl == null) {
                } else {
                    if (Operator.hapusSupplier(frmFormSupplier.getSelectedSupplier(),
                            core.getConnection())) {
                        JOptionPane.showMessageDialog(frmFormSupplier,
                                "Data supplier dihapus");
                    }
                    frmFormSupplier.resetForm();
                }
                break;
            case DELETE_TRANS:
                if (tbl == null) {
                } else {
                    if (Operator.hapusTransaksiMasuk(frmDataTrans.getTransaksi(),
                            core.getConnection())) {
                        JOptionPane.showMessageDialog(frmDataTrans,
                                "Data transaksi dihapus");
                    }
                    frmDataTrans.resetForm();
                }
                break;
            case TAMPILKAN_FORM_KARYAWAN:
                core.winUtama.setVisible(false);
                new WindowFormKaryawan(core).setVisible(true);
                break;
            case TAMPILKAN_FORM_PRODUK:
                core.winUtama.setVisible(false);
                new WindowFormBarang(core).setVisible(true);
                break;
            case TAMPILKAN_FORM_SUPPLIER:
                core.winUtama.setVisible(false);
                new WindowFormSupplier(core).setVisible(true);
                break;
            case TAMPILKAN_FORM_TRANS_MASUK:
                core.winUtama.setVisible(false);
                new WindowFormTransaksiMasuk(core).setVisible(true);
                break;
            case TAMPILKAN_FORM_TRANS_KELUAR:
                core.winUtama.setVisible(false);
                new WindowFormTransaksiKeluar(core).setVisible(true);
                break;

        }
    }
}
