package view;

import controller.Operator;
import controller.Core;
import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Produk;
import model.Transaksi;
import controller.CustActionListener;
import controller.CustWindowListener;

public class WindowDataTransaksi extends JFrame {

    private Core core;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Vector<Produk> barang = new Vector<Produk>();
    private Vector<String> nmBarang = new Vector<String>();

    private JTable tbl;

    public WindowDataTransaksi(Core core) {
        super("Data Transaksi");
        this.core = core;
        setResizable(false);

        setSize(500, 300);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        setLayout(null);
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);
        tbl = new JTable(Operator.resultSetToTableModel(Operator
                .getListTransaksiMasuk(core.getConnection())));
        Operator.disableTableEdit(tbl);
        JPanel panTbl = new JPanel();
        panTbl.setLayout(new BorderLayout());
        panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);
        panTbl.setBounds(0, 0, 495, 200);
        panTbl.setBackground(Color.WHITE);
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(new CustActionListener(core, this, tbl,
                buttonDelete, CustActionListener.DELETE_TRANS));
        buttonDelete.setBounds(0, 220, 500, 25);
        container.add(buttonDelete);
        container.add(panTbl);
        this.addWindowListener(new CustWindowListener(core, this));

    }

    public Transaksi getTransaksi() {
        if (tbl.getSelectedRow() >= 0) {
            String val = tbl.getValueAt(tbl.getSelectedRow(), 0).toString();

            return new Transaksi(Integer.parseInt(val));
        } else {
            return null;
        }
    }

    public void resetForm() {
        if (tbl.getSelectedRow() >= 0) {
            ((DefaultTableModel) tbl.getModel())
                    .removeRow(tbl.getSelectedRow());
        }
    }
}
