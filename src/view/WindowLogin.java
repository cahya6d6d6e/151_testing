package view;

import controller.Core;
import java.awt.*;
import javax.swing.*;
import controller.CustActionListener;
import controller.CustWindowListener;

public class WindowLogin extends JFrame {

    private Core core;
    private JButton btn_Login;
    private JTextField tbox_Usr;
    private JPasswordField tbox_Psw;
    private JLabel lbl_Usr, lbl_Psw;

    private Container container;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public WindowLogin(Core core) {
        super("Login");
        this.core = core;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(260, 175);
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        setResizable(false);
        JLabel labelHeader = new JLabel("Aplikasi Penjualan Perangkat Komputer");
        labelHeader.setBounds(16, 0, 250, 20);

        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(Color.WHITE);
        btn_Login = new JButton("Login");
        tbox_Usr = new JTextField(15);
        tbox_Psw = new JPasswordField(15);
        lbl_Usr = new JLabel("Username");
        lbl_Psw = new JLabel("Password");

        lbl_Usr.setHorizontalAlignment(JLabel.RIGHT);
        lbl_Psw.setHorizontalAlignment(JLabel.RIGHT);

        lbl_Usr.setBounds(10, 30, 60, 20);
        tbox_Usr.setBounds(75, 30, 170, 20);
        lbl_Psw.setBounds(10, 55, 60, 20);
        tbox_Psw.setBounds(75, 55, 170, 20);
        btn_Login.setBounds(10, 110, 235, 25);

        btn_Login.addActionListener(new CustActionListener(core, this, btn_Login));
        container.add(labelHeader);
        container.add(lbl_Usr);
        container.add(tbox_Usr);
        container.add(lbl_Psw);
        container.add(tbox_Psw);
        container.add(btn_Login);
        this.addWindowListener(new CustWindowListener(core, this));

    }

    public String getUser() {
        return tbox_Usr.getText();
    }

    public String getPass() {
        return String.valueOf(tbox_Psw.getPassword());
    }

}

class ComboItem {

    private String value;
    private String label;

    public ComboItem(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label;
    }
}
