package ui.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

import domain.KUvidGame;
import domain.Observer;

public class ShooterComponent extends JLabel implements Observer {

	private final String shooterImagePath = "./assets/shooter.png";
	private BufferedImage image;

	public ShooterComponent() {
		KUvidGame.initShooter();
		try {
			image = ImageIO.read(new File(shooterImagePath));
		} catch (Exception ex) {
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, KUvidGame.getShooter().getPositionX(), KUvidGame.getShooter().getPositionY(), 
				KUvidGame.getShooter().getWidth(), KUvidGame.getShooter().getHeight(), null);

		int x = (int) (KUvidGame.WIDTH / 5 * 4 * Math.cos(Math.toRadians(KUvidGame.getShooter().getAngle())));
		int y = (int) (KUvidGame.WIDTH / 5 * 4 * Math.sin(Math.toRadians(KUvidGame.getShooter().getAngle())));
		g.drawLine(KUvidGame.getShooter().getPositionX() + 25, KUvidGame.getShooter().getPositionY() + 5, x + KUvidGame.getShooter().getPositionX() + 25, (int) (((int) KUvidGame.WIDTH / 5 * 4) - y));

	}

	@Override
	public void onEvent() {
		repaint();
	}
	
	public void initialize() {
		KUvidGame.getShooter().addListener(this);
	}

}
