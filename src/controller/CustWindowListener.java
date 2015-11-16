package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import view.WindowLogin;
import view.WindowUtama;

public class CustWindowListener implements WindowListener {

    private Core core;
    private JFrame activeForm;

    public CustWindowListener(JFrame form) {
        this.activeForm = form;
    }

    public CustWindowListener(Core core, JFrame form) {
        this.core = core;
        this.activeForm = form;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (activeForm instanceof WindowUtama) {
            core.logout();
        } else if (activeForm instanceof WindowLogin) {
            System.exit(0);
        } else {
            core.backToUtama(activeForm);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
