package view;

import controller.CustWindowListener;
import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WindowListTransaksiMasuk extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JTable tbl;

    public WindowListTransaksiMasuk() {
        super("Data Transaksi");

        setResizable(false);

        setSize(500, 300);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        setLayout(null);
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);
        tbl = new JTable();

        JPanel panTbl = new JPanel();
        panTbl.setLayout(new BorderLayout());
        panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);
        panTbl.setBounds(0, 0, 495, 200);
        panTbl.setBackground(Color.WHITE);
        JButton buttonDelete = new JButton("Delete");

        buttonDelete.setBounds(0, 220, 500, 25);
        container.add(buttonDelete);
        container.add(panTbl);
        this.addWindowListener(new CustWindowListener(this));

    }

    public void resetForm() {
        if (tbl.getSelectedRow() >= 0) {
            ((DefaultTableModel) tbl.getModel())
                    .removeRow(tbl.getSelectedRow());
        }
    }
}
