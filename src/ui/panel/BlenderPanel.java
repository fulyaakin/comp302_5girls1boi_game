package ui.panel;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Blender;
import domain.KUvidGame;

public class BlenderPanel extends JPanel {

	private JLabel alphaNumber = new JLabel();
	private JLabel betaNumber = new JLabel();
	private JLabel gamaNumber = new JLabel();
	private JLabel sigmaNumber = new JLabel();

	private ImageIcon mixer = new ImageIcon("./assets/mixer.png");
	private JLabel mixerIcon = new JLabel();

	private ImageIcon alpha = new ImageIcon("./assets/atoms/alpha.png");
	private JLabel alphaIcon = new JLabel();

	private ImageIcon beta = new ImageIcon("./assets/atoms/beta.png");
	private JLabel betaIcon = new JLabel();

	private ImageIcon gama = new ImageIcon("./assets/atoms/gamma.png");
	private JLabel gamaIcon = new JLabel();

	private ImageIcon sigma = new ImageIcon("./assets/atoms/sigma.png");
	private JLabel sigmaIcon = new JLabel();


	private boolean isBPressed = false;
	private int source = -1;

	public BlenderPanel() {
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);

		updateLabels();

		mixerIcon.setIcon(mixer);
		alphaIcon.setIcon(alpha);
		betaIcon.setIcon(beta);
		gamaIcon.setIcon(gama);
		sigmaIcon.setIcon(sigma);

		add(mixerIcon);
		add(alphaIcon);
		add(alphaNumber);
		add(betaIcon);
		add(betaNumber);
		add(gamaIcon);
		add(gamaNumber);
		add(sigmaIcon);
		add(sigmaNumber);

	}

	public void bPressed() {
		this.isBPressed = true;
	}

	public void pressedNumber(int number) {
		if (isBPressed == false || number < 0 || number > 4) 
			return;

		if (source == -1) {
			source = number;
			return;
		}

		KUvidGame.getBlender().blendAtom(source, number);

		isBPressed = false;
		source = -1;

		updateLabels();
	}


	public void updateLabels() {
		alphaNumber.setText(KUvidGame.getBlender().getAlphaAtoms() + "");
		betaNumber.setText(KUvidGame.getBlender().getBetaAtoms() + "");
		gamaNumber.setText(KUvidGame.getBlender().getGammaAtoms() + "");
		sigmaNumber.setText(KUvidGame.getBlender().getSigmaAtoms() + "");	
	}
}
