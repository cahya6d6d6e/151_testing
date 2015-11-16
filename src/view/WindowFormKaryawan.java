package view;

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
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import controller.Core;
import controller.CustActionListener;
import controller.CustWindowListener;
import controller.Operator;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Karyawan;

public class WindowFormKaryawan extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextField tfNama, tfAlamat, tfPosisi, tfTelp;
    private JPasswordField tfPassword;
    private JTable tbl;
    private JLabel lbAlamat, lbNama, lbPassword, lbPosisi, lbTelp;
    private Core core;

    private Vector<Karyawan> karyawan = new Vector<Karyawan>();
    private Vector<String> nmKaryawan = new Vector<String>();

    public WindowFormKaryawan(Core core) {
        super("Data Karyawan");
        this.core = core;

        ResultSet rs = Operator.getListKaryawan(core.getConnection());
        try {
            while (rs.next()) {
                karyawan.add(new Karyawan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        for (int i = 0; i < karyawan.size(); i++) {
            System.out.println(karyawan.get(i));
        }

        setResizable(false);

        setSize(810, 272);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        setLayout(null);
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);

        tbl = new JTable(Operator.resultSetToTableModel(Operator
                .getListKaryawan(core.getConnection())));
        Operator.disableTableEdit(tbl);

        JPanel panTbl = new JPanel();

        panTbl.setLayout(new BorderLayout());
        panTbl.setBackground(Color.WHITE);
        panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);

        tfNama = new JTextField();
        tfAlamat = new JTextField();
        tfPosisi = new JTextField();
        tfTelp = new JTextField();
        tfPassword = new JPasswordField();

        tfNama.setBounds(115, 10, 170, 20);
        tfAlamat.setBounds(115, 35, 170, 20);
        tfTelp.setBounds(115, 60, 170, 20);
        tfPosisi.setBounds(115, 85, 170, 20);
        tfPassword.setBounds(115, 110, 170, 20);

        panTbl.setBounds(295, 10, 500, 200);

        lbNama = new JLabel("Nama Karyawan");
        lbAlamat = new JLabel("Alamat Karyawan");
        lbPosisi = new JLabel("Posisi");
        lbTelp = new JLabel("Telpon");
        lbPassword = new JLabel("Password");

        lbNama.setBounds(10, 10, 100, 20);
        lbNama.setHorizontalAlignment(JLabel.RIGHT);
        lbAlamat.setBounds(10, 35, 100, 20);
        lbAlamat.setHorizontalAlignment(JLabel.RIGHT);
        lbTelp.setBounds(10, 60, 100, 20);
        lbTelp.setHorizontalAlignment(JLabel.RIGHT);
        lbPosisi.setBounds(10, 85, 100, 20);
        lbPosisi.setHorizontalAlignment(JLabel.RIGHT);
        lbPassword.setBounds(10, 110, 100, 20);
        lbPassword.setHorizontalAlignment(JLabel.RIGHT);

        JButton buttonTambah = new JButton("Tambah");
        buttonTambah.addActionListener(new CustActionListener(core, this, tbl,
                buttonTambah, CustActionListener.INSERT_KARYAWAN));
        buttonTambah.setBounds(205, 135, 80, 20);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(new CustActionListener(core, this, tbl,
                buttonTambah, CustActionListener.DELETE_KARYAWAN));
        buttonDelete.setBounds(115, 135, 80, 20);

        // Add Content
        container.add(tfNama);
        container.add(tfAlamat);
        container.add(tfTelp);
        container.add(tfPosisi);
        container.add(tfPassword);
        container.add(lbNama);
        container.add(lbAlamat);
        container.add(lbTelp);
        container.add(lbPosisi);
        container.add(lbPassword);
        container.add(panTbl);
        container.add(buttonDelete);
        container.add(buttonTambah);
        this.addWindowListener(new CustWindowListener(core, this));
    }

    public Vector<Karyawan> getListKaryawan() {
        return karyawan;
    }

    public Karyawan getSelectedKaryawan() {
        return karyawan.get(tbl.getSelectedRow());
    }

    public void submitToDB() {
        if (Operator.tambahKaryawan(getKaryawan(), core.getConnection())) {
            JOptionPane.showMessageDialog(this, "Data Karyawan telah ditambahkan!");
        } else {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan!");
        }

        ((DefaultTableModel) tbl.getModel()).addRow(
                new Object[]{
                    Operator.getLastIDKaryawan(core.getConnection()),
                    tfNama.getText(),
                    tfAlamat.getText(),
                    tfTelp.getText(),
                    tfPosisi.getText(),
                    tfPassword.getPassword()
                });
        tfNama.setText("");
        tfAlamat.setText("");
        tfTelp.setText("");
        tfPosisi.setText("");
        tfPassword.setText("");
    }

    public void resetForm() {
        tfNama.setText("");
        tfAlamat.setText("");
        tfPosisi.setText("");
        tfTelp.setText("");
        tfPassword.setText("");
        if (tbl.getSelectedRow() >= 0) {
            ((DefaultTableModel) tbl.getModel())
                    .removeRow(tbl.getSelectedRow());
        }
    }

    public Karyawan getKaryawan() {
        return new Karyawan(tfNama.getText(),
                tfAlamat.getText(),
                tfPosisi.getText(), tfTelp.getText(),
                String.valueOf(tfPassword.getPassword()));
    }
}
