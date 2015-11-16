package view;

import controller.Operator;
import controller.Core;
import controller.CustWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class WindowDataBarang extends JFrame {

    private Core core;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JTable tbl;

    public WindowDataBarang(Core core) {
        super("Data Barang");
        this.core = core;

        this.core = core;
        setResizable(false);
        setSize(480, 360);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        JTable tbl = new JTable(Operator.resultSetToTableModel(Operator
                .getListProduk(core.getConnection())));
        Operator.disableTableEdit(tbl);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        JPanel pan = new JPanel();
        pan.setBackground(Color.WHITE);
        pan.setBounds(0, 0, 480, 320);
        pan.setLayout(new BorderLayout());
        pan.add(tbl, BorderLayout.CENTER);
        pan.add(tbl.getTableHeader(), BorderLayout.NORTH);

        getContentPane().add(pan);
        this.addWindowListener(new CustWindowListener(core, this));
    }
}
