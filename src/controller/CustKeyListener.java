package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import controller.Core;
import controller.Operator;
import view.WindowFormBarang;
import view.WindowFormTransaksiKeluar;
import view.WindowFormTransaksiMasuk;

public class CustKeyListener implements KeyListener {

	public static final int NUMBER_ONLY = 0, ON_STOCK = 1;
	private int mode;
	private JTextField jf;
	private WindowFormTransaksiKeluar frmFormTransKeluar;
	private WindowFormTransaksiMasuk frmFormTransMasuk;
	private WindowFormBarang frmFormBarang;
	private JButton btn;
	private Core core;

	public CustKeyListener(WindowFormTransaksiKeluar frmFormTrans, JTextField jf,
			int mode) {
		this.frmFormTransKeluar = frmFormTrans;
		this.jf = jf;
		this.mode = mode;
	}
	public CustKeyListener(WindowFormTransaksiMasuk frmFormTrans, JTextField jf,
			int mode) {
		this.frmFormTransMasuk = frmFormTrans;
		this.jf = jf;
		this.mode = mode;
	}

	public CustKeyListener(Core core, WindowFormBarang frm, JTextField jf,
			int mode) {
		this.core = core;
		this.frmFormBarang = frm;
		this.jf = jf;
		this.mode = mode;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent ev) {
		switch (mode) {
		case ON_STOCK:
			final int LIMIT = frmFormTransKeluar.getSelectedBarang().getJumlah();
			if (jf.getText().equalsIgnoreCase("")) {

			} else if (Integer.parseInt(jf.getText()) > LIMIT) {
				jf.setText("" + LIMIT);
			}
			break;
		case NUMBER_ONLY:
			if (jf.getText().equalsIgnoreCase("")) {
				jf.setText("1");
			}
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent ev) {
		switch (mode) {
		case NUMBER_ONLY:
			if (ev.getKeyChar() < '0' || ev.getKeyChar() > '9') {
				ev.consume();
			}
			break;
		case ON_STOCK:
			final int LIMIT = frmFormTransKeluar.getSelectedBarang().getJumlah();
			if (jf.getText().equalsIgnoreCase("")) {

			} else if (Integer.parseInt(jf.getText()) > LIMIT) {
				ev.consume();
			}
			break;
		}

	}
}
