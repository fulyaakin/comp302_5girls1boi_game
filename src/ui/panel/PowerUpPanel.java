package ui.panel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.BuildingMode;
import domain.KUvidGame;
import domain.PowerupKeepingTrack;

public class PowerUpPanel extends JPanel {
	private PowerupKeepingTrack	powerupKeepingTrack;
	
	private JLabel alphaNumber = new JLabel();
	private JLabel betaNumber = new JLabel();
	private JLabel gammaNumber = new JLabel();
	private JLabel sigmaNumber = new JLabel();
	
	private ImageIcon alpha = new ImageIcon("./assets/powerups/+alpha-b.png");
	private JLabel alphaIcon = new JLabel();

	private ImageIcon beta = new ImageIcon("./assets/powerups/+beta-b.png");
	private JLabel betaIcon = new JLabel();

	private ImageIcon gamma = new ImageIcon("./assets/powerups/+gamma-b.png");
	private JLabel gammaIcon = new JLabel();

	private ImageIcon sigma = new ImageIcon("./assets/powerups/+sigma-b.png");
	private JLabel sigmaIcon = new JLabel();
	

	private boolean isClicked = false;
	private static boolean isAlphaIsClicked = false;
	private static boolean isBetaIsClicked = false;
	private static boolean isGammaIsClicked = false;
	private static boolean isSigmaIsClicked = false;

	
	public PowerUpPanel() {
		powerupKeepingTrack = new PowerupKeepingTrack();
	}
	
	
	private void updateLabels() {
		alphaNumber.setText(powerupKeepingTrack.getAlphaPowerups() + "");
		betaNumber.setText(powerupKeepingTrack.getBetaPowerups() + "");
		gammaNumber.setText(powerupKeepingTrack.getGamaPowerups() + "");
		sigmaNumber.setText(powerupKeepingTrack.getSigmaPowerups() + "");	
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		updateLabels();
		
		alphaIcon.setIcon(alpha);
		betaIcon.setIcon(beta);
		gammaIcon.setIcon(gamma);
		sigmaIcon.setIcon(sigma);		
		
		alphaIcon.setPreferredSize(new Dimension(60,60));
		betaIcon.setPreferredSize(new Dimension(60,60));
		gammaIcon.setPreferredSize(new Dimension(60,60));
		sigmaIcon.setPreferredSize(new Dimension(60,60));

		alphaIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setAlphaIsClicked(true);

			}

		});
		betaIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBetaIsClicked(true);

			}

		});
		gammaIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setGammaIsClicked(true);

			}

		});
		sigmaIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSigmaIsClicked(true);

			}

		});

	
		add(alphaIcon);
		add(alphaNumber);
		add(betaIcon);
		add(betaNumber);
		add(gammaIcon);
		add(gammaNumber);
		add(sigmaIcon);
		add(sigmaNumber);

	}


	public static boolean isAlphaIsClicked() {
		return isAlphaIsClicked;
	}


	public static void setAlphaIsClicked(boolean isAlphaIsClicked) {
		PowerUpPanel.isAlphaIsClicked = isAlphaIsClicked;
	}


	public static boolean isBetaIsClicked() {
		return isBetaIsClicked;
	}


	public static void setBetaIsClicked(boolean isBetaIsClicked) {
		PowerUpPanel.isBetaIsClicked = isBetaIsClicked;
	}


	public static boolean isGammaIsClicked() {
		return isGammaIsClicked;
	}


	public static void setGammaIsClicked(boolean isGammaIsClicked) {
		PowerUpPanel.isGammaIsClicked = isGammaIsClicked;
	}


	public static boolean isSigmaIsClicked() {
		return isSigmaIsClicked;
	}


	public static void setSigmaIsClicked(boolean isSigmaIsClicked) {
		PowerUpPanel.isSigmaIsClicked = isSigmaIsClicked;
	}
	
}
