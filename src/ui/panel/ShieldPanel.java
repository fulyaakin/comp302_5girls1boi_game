package ui.panel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.BuildingMode;
import domain.KUvidGame;

public class ShieldPanel extends JPanel {

	private JLabel thetaNumber = new JLabel();
	private JLabel zetaNumber = new JLabel();
	private JLabel etaNumber = new JLabel();
	private JLabel lotaNumber = new JLabel();

	private JLabel thetaButton = new JLabel();
	private JLabel zetaButton = new JLabel();
	private JLabel etaButton = new JLabel();
	private JLabel lotaButton = new JLabel();

	public ShieldPanel() {
		add(etaButton);
		add(lotaButton);
		add(etaNumber);
		add(lotaNumber);
		add(thetaButton);
		add(zetaButton);
		add(thetaNumber);
		add(zetaNumber);
		etaButton.setText("Eta");
		lotaButton.setText("Lota");
		thetaButton.setText("Theta");
		zetaButton.setText("Zeta");
		etaNumber.setText("                      " + BuildingMode.getInstance().getEtaNumber());
		lotaNumber.setText("                      " + BuildingMode.getInstance().getLotaNumber());
		thetaNumber.setText("                      " + BuildingMode.getInstance().getThetaNumber());
		zetaNumber.setText("                      " + BuildingMode.getInstance().getZetaNumber());
	}

	public void updateLabels(String shield) {
		if (shield.equals("eta")) {
			BuildingMode.getInstance().setEtaNumber(BuildingMode.getInstance().getEtaNumber() - 1);
			etaNumber.setText("                      " + BuildingMode.getInstance().getEtaNumber());
		}
		if (shield.equals("lota")) {
			BuildingMode.getInstance().setLotaNumber(BuildingMode.getInstance().getLotaNumber() - 1);
			lotaNumber.setText("                      " + BuildingMode.getInstance().getLotaNumber());
		}
		if (shield.equals("theta")) {
			BuildingMode.getInstance().setThetaNumber(BuildingMode.getInstance().getThetaNumber() - 1);
			thetaNumber.setText("                      " + BuildingMode.getInstance().getThetaNumber());
		}
		if (shield.equals("zeta")) {
			BuildingMode.getInstance().setZetaNumber(BuildingMode.getInstance().getZetaNumber() - 1);
			zetaNumber.setText("                      " + BuildingMode.getInstance().getZetaNumber());
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

	}

}
