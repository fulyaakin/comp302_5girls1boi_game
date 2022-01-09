package ui.component;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import domain.KUvidGame;

import domain.Observer;

public class FallingPowerupComponent extends JLabel implements Observer {
//	private PowerupType type;
	private Image image;
	private int index;

	public FallingPowerupComponent(int index) {
		KUvidGame.initPowerup();
		this.index = index;
		try {
			image = ImageIO.read(new File("./assets/powerups/+"
					+ KUvidGame.getFallingPowerups().get(index).getTypeName().getName() + "-b.png"));
			KUvidGame.getFallingPowerups().get(index).setWidth(((BufferedImage) image).getWidth());
			KUvidGame.getFallingPowerups().get(index).setHeigth(((BufferedImage) image).getHeight());
		} catch (Exception ex) {
		}
	}

	public void paint(Graphics g) {
		if (KUvidGame.getFallingPowerups().get(index).getPositionY() != KUvidGame.getShooter().getPositionY()) {
			KUvidGame.getFallingPowerups().get(index).fall();
		}
		g.drawImage(image, KUvidGame.getFallingPowerups().get(index).getPositionX(), KUvidGame.getFallingPowerups().get(index).getPositionY(), (int) KUvidGame.L, (int) KUvidGame.L, null);
	}

	@Override
	public void onEvent() {
		repaint();
	}

	public void initialize() {
		KUvidGame.getFallingPowerups().get(index).addListener(this);
	}

	public String getType() {
		return KUvidGame.getFallingPowerups().get(index).getTypeName().toString();
	}

	public int getIndex() {
		return index;
	}
	public boolean intersectedBlocker(int i) {
		return KUvidGame.getFallingPowerups().get(index).intersected(KUvidGame.getBlockers().get(i));
	}

}
