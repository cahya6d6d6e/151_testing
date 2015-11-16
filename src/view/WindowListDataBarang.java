package view;

import controller.CustWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class WindowListDataBarang extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JTable tbl;

    public WindowListDataBarang() {
        super("Data Transaksi");

        setResizable(false);
        setSize(480, 360);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        JTable tbl = new JTable();

        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        JPanel pan = new JPanel();
        pan.setBackground(Color.WHITE);
        pan.setBounds(0, 0, 480, 320);
        pan.setLayout(new BorderLayout());
        pan.add(tbl, BorderLayout.CENTER);
        pan.add(tbl.getTableHeader(), BorderLayout.NORTH);

        getContentPane().add(pan);
        this.addWindowListener(new CustWindowListener(this));

    }

}
