package ui.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

import domain.KUvidGame;
import domain.Observer;

public class AtomComponent extends JLabel implements Observer {

	private ShooterComponent shooter;
	private BufferedImage image;

	private int index;

	public AtomComponent(ShooterComponent shooter, int index) {
		KUvidGame.initAtom();
		this.index = index;
		this.shooter = shooter;
		loadImage();
	}
	
	public AtomComponent(int index) {
		this.index = index;
		loadImage();
	}


	private void loadImage() {
		try {
			String imagePath = "./assets/atoms/" + KUvidGame.getAtoms().get(index).getType().getName() + ".png";
			image = ImageIO.read(new File(imagePath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void shot() {
		KUvidGame.getAtoms().get(index).shot(KUvidGame.getShooter().getPositionX() + 5,
				KUvidGame.getShooter().getPositionY() - 40, KUvidGame.getShooter().getAngle());
	}

	public void paint(Graphics g) {
		if (KUvidGame.getAtoms().get(index).isShot() == false) {
			g.drawImage(image, KUvidGame.getShooter().getPositionX() + 5, KUvidGame.getShooter().getPositionY() - 40,
					40, 40, null);
		} else {
			KUvidGame.getAtoms().get(index).move();
			g.drawImage(image, KUvidGame.getAtoms().get(index).getPositionX(),
					KUvidGame.getAtoms().get(index).getPositionY(), 40, 40, null);
		}

	}

	public void changeAtom() {
		if (KUvidGame.getAtoms().get(index).change())
			loadImage();
	}

	public void decorateAtom(String shield) {
		if (shield.equals("eta")) {
			KUvidGame.getAtoms().set(index, KUvidGame.getEtaShield(KUvidGame.getAtoms().get(index)));
		}
		if (shield.equals("lota")) {
			KUvidGame.getAtoms().set(index, KUvidGame.getLotaShield(KUvidGame.getAtoms().get(index)));
		}
		if (shield.equals("theta")) {
			KUvidGame.getAtoms().set(index, KUvidGame.getThetaShield(KUvidGame.getAtoms().get(index)));
		}
		if (shield.equals("zeta")) {
			KUvidGame.getAtoms().set(index, KUvidGame.getZetaShield(KUvidGame.getAtoms().get(index)));
		}
	}

	@Override
	public void onEvent() {
		repaint();
	}

	public void initialize() {
		KUvidGame.getAtoms().get(index).addListener(this);
	}

	public String getType() {
		return KUvidGame.getAtoms().get(index).getType().toString();
	}

	public boolean intersectedMolecule(int i) {
		return KUvidGame.getAtoms().get(index).intersected(KUvidGame.getMolecules().get(i));
	}

	public boolean intersectedPowerup(int i) {
		return KUvidGame.getAtoms().get(index).intersected(KUvidGame.getFallingPowerups().get(i));
	}
	
	public boolean intersectedBlocker(int i) {
		return KUvidGame.getAtoms().get(index).intersected(KUvidGame.getBlockers().get(i));
	}
	public double calculateScore() {
		return KUvidGame.getAtoms().get(index).calculateEfficiency();
	}
}
