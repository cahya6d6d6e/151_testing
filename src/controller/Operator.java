package controller;

import model.Produk;
import model.Karyawan;
import model.Transaksi;
import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Supplier;

public class Operator {

    public static ResultSet getListProduk(Connection con) {
        return select(con, "SELECT * FROM produk ORDER BY id_produk");
    }

    public static ResultSet getListKaryawan(Connection con) {
        return select(con, "SELECT * FROM karyawan ORDER BY id_karyawan");
    }

    public static ResultSet getListSupplier(Connection con) {
        return select(con, "SELECT * FROM supplier ORDER BY id_supplier");
    }

    public static ResultSet getListTransaksiMasuk(Connection con) {
        return select(con, "SELECT * FROM trans_masuk ORDER BY id_masuk");
    }

    public static ResultSet getListDetilTransaksiMasuk(Connection con,
            int idTransaksi) {
        return select(con, "SELECT * FROM trans_masuk_detail WHERE id_masuk="
                + idTransaksi);
    }

    public static ResultSet getListTransaksiKeluar(Connection con) {
        return select(con, "SELECT * FROM trans_keluar ORDER BY id_keluar");
    }

    public static ResultSet getListDetilTransaksiKeluar(Connection con,
            int idTransaksi) {
        return select(con, "SELECT * FROM trans_keluar_detail WHERE id_keluar="
                + idTransaksi);
    }

    public static int getLastIDTransMasuk(Connection con) {
        ResultSet rs = select(con, "SELECT MAX(id_masuk) FROM trans_masuk");
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static int getLastIDTransKeluar(Connection con) {
        ResultSet rs = select(con, "SELECT MAX(id_keluar) FROM trans_keluar");
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static int getLastIDProduk(Connection con) {
        ResultSet rs = select(con, "SELECT MAX(id_produk) FROM produk");
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static int getLastIDSupplier(Connection con) {
        ResultSet rs = select(con, "SELECT MAX(id_supplier) FROM supplier");
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static int getLastIDKaryawan(Connection con) {
        ResultSet rs = select(con, "SELECT MAX(id_karyawan) FROM karyawan");
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean tambahProduk(Produk brg, Connection con) {
        return update(
                con,
                "INSERT INTO produk "
                + "VALUES(" + (getLastIDProduk(con) + 1) + ",'"
                + brg.getNama() + "'," + brg.getJumlah() + ","
                + brg.getHargaJual() + "," + brg.getHargaBeli() + ")");
    }

    public static boolean tambahKaryawan(Karyawan karyawan, Connection con) {
        return update(con, "INSERT INTO karyawan "
                + "VALUES(" + (getLastIDKaryawan(con) + 1) + ",'"
                + karyawan.getNama() + "','"
                + karyawan.getAlamat() + "','"
                + karyawan.getTelp() + "','"
                + karyawan.getPosisi() + "','"
                + karyawan.getPassword().toString()
                + "')");
    }

    public static boolean tambahSupplier(Supplier supplier, Connection con) {
        return update(con, "INSERT INTO supplier "
                + "VALUES(" + (getLastIDSupplier(con) + 1) + ",'"
                + supplier.getNama() + "','"
                + supplier.getAlamat() + "','"
                + supplier.getTelp()
                + "')");
    }

    public static boolean hapusProduk(Produk produk, Connection con) {
        return update(con, "DELETE FROM produk WHERE id_produk='" + produk.getId() + "'");
    }

    public static boolean hapusKaryawan(Karyawan karyawan, Connection con) {
        return update(con, "DELETE FROM karyawan WHERE id_karyawan='" + karyawan.getId() + "'");
    }

    public static boolean hapusSupplier(Supplier supplier, Connection con) {
        return update(con, "DELETE FROM supplier WHERE id_supplier='" + supplier.getId() + "'");
    }

    public static boolean tambahTransaksi(Transaksi trns, Connection con) {
        boolean returnValue = true;
        int id = getLastIDTransMasuk(con) + 1;
        returnValue = update(con, "INSERT INTO tran_masuk VALUES(" + id + ", '"
                + trns.getTglAsString() + "', '" + trns.getUser().getId()
                + "')");
        for (int i = 0; i < trns.getDetilTransaksi().size(); i++) {
            returnValue = returnValue
                    & update(con, "INSERT INTO trans_masuk_detail "
                            + "VALUES("
                            + id
                            + ", "
                            + + +trns.getDetilTransaksi().get(i).getBarang()
                            .getId() + ", "
                            + trns.getDetilTransaksi().get(i).getJumlah() + ")");
            returnValue = returnValue
                    & update(con, "UPDATE produk SET jumlah = jumlah-"
                            + trns.getDetilTransaksi().get(i).getJumlah()
                            + " WHERE id_produk="
                            + trns.getDetilTransaksi().get(i).getBarang()
                            .getId());
        }
        return returnValue;
    }

    public static boolean hapusTransaksiMasuk(Transaksi trns, Connection con) {
        boolean returnData = true;
        if (trns == null) {
            return false;
        } else {
            returnData = returnData
                    & update(con,
                            "DELETE FROM trans_masuk WHERE id_masuk="
                            + trns.getId());
            returnData = returnData
                    & update(con, "DELETE FROM trans_masuk WHERE id_masuk="
                            + trns.getId());
        }
        return returnData;
    }

    public static boolean hapusTransaksiKeluar(Transaksi trns, Connection con) {
        boolean returnData = true;
        if (trns == null) {
            return false;
        } else {
            returnData = returnData
                    & update(con,
                            "DELETE FROM trans_keluar WHERE id_keluar="
                            + trns.getId());
            returnData = returnData
                    & update(con, "DELETE FROM trans_keluar WHERE id_keluar="
                            + trns.getId());
        }
        return returnData;
    }

    public static Karyawan login(Connection con, String id_karyawan, String password) {
        ResultSet rs = select(con, "SELECT * FROM karyawan "
                + "WHERE id_karyawan='" + id_karyawan + "'");
        try {
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    return new Karyawan(
                            rs.getInt("id_karyawan"),
                            rs.getString("nama_karyawan"),
                            rs.getString("alamat_karyawan"),
                            rs.getString("telp_karyawan"),
                            rs.getString("posisi"),
                            rs.getString("password"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    private static ResultSet select(Connection con, String query) {
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    private static boolean update(Connection con, String query) {
        JOptionPane.showMessageDialog(null, query);
        try {
            con.createStatement().executeUpdate(query);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static DefaultTableModel resultSetToTableModel(ResultSet rs) {
        try {
            ResultSetMetaData meta = rs.getMetaData();

            Vector<String> col = new Vector<String>();
            int columnCount = meta.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                col.add(meta.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, col);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return new DefaultTableModel();
    }

    public static void disableTableEdit(JTable tbl) {
        for (int c = 0; c < tbl.getColumnCount(); c++) {
            Class<?> col_class = tbl.getColumnClass(c);
            tbl.setDefaultEditor(col_class, null);
        }
    }
}
