package ui.component;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import domain.KUvidGame;

import domain.Observer;
import ui.panel.PowerUpPanel;


public class ShootablePowerupComponent extends JLabel implements Observer {

	private Image image;
	private int index;
	private ShooterComponent shooter;
	public boolean isShot;
	
	public ShootablePowerupComponent(ShooterComponent shooter, int index, String type) {
		KUvidGame.setShootablePowerupType(type);
		KUvidGame.initShootablePowerup();
		this.index = index;
		this.shooter = shooter;
		loadImage();
		
	}
	
	public void paint(Graphics g) {
		if(!KUvidGame.getShootablePowerups().get(index).isShot()) {
			switch (KUvidGame.getShootablePowerups().get(index).getType().getName()) {
			case "ALPHA" :
				if(PowerUpPanel.isAlphaIsClicked()) {
					g.drawImage(image, KUvidGame.getShootablePowerups().get(index).getPositionX(), KUvidGame.getShootablePowerups().get(index).getPositionY(), (int) KUvidGame.L, (int)KUvidGame.L, null);
				
				}
				break;
			case "BETA" :
				if(PowerUpPanel.isBetaIsClicked()) {
					g.drawImage(image, KUvidGame.getShootablePowerups().get(index).getPositionX(), KUvidGame.getShootablePowerups().get(index).getPositionY(), (int) KUvidGame.L, (int)KUvidGame.L, null);
				}
				break;
			case "GAMMA" :
				if(PowerUpPanel.isGammaIsClicked()) {
					g.drawImage(image, KUvidGame.getShootablePowerups().get(index).getPositionX(), KUvidGame.getShootablePowerups().get(index).getPositionY(), (int) KUvidGame.L, (int)KUvidGame.L, null);
				}
				break;
			case "SIGMA" :
				if(PowerUpPanel.isSigmaIsClicked()) {
					g.drawImage(image, KUvidGame.getShootablePowerups().get(index).getPositionX(), KUvidGame.getShootablePowerups().get(index).getPositionY(), (int) KUvidGame.L, (int)KUvidGame.L, null);
				}
			break;
			
			}
		}else {
			KUvidGame.getShootablePowerups().get(index).move();
	        g.drawImage(image, KUvidGame.getShootablePowerups().get(index).getPositionX(), KUvidGame.getShootablePowerups().get(index).getPositionY(), (int) KUvidGame.L, (int)KUvidGame.L, null);
	}
	}
	@Override
	public void onEvent() {
		repaint();
	}
	
	public void shot() {
		KUvidGame.getShootablePowerups().get(index).shot(KUvidGame.getShooter().getPositionX() + 5, KUvidGame.getShooter().getPositionY() -40, KUvidGame.getShooter().getAngle());
	
	}
	
	public boolean intersectedBlocker(int i) {
		return KUvidGame.getShootablePowerups().get(index).intersected(KUvidGame.getBlockers().get(i));
	}
	
	
	private void loadImage() {
		try {
			image = ImageIO.read(new File("./assets/powerups/+" + KUvidGame.getShootablePowerups().get(index).getType().getName() + "-b.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
	public void initialize() {
		KUvidGame.getShootablePowerups().get(index).addListener(this);
	}
	
	public String getType() {
		return KUvidGame.getShootablePowerups().get(index).getType().toString();
	}

	public int getIndex() {
		return index;
	}
	
	public boolean intersected(int i) {
		return KUvidGame.getShootablePowerups().get(index).intersected(KUvidGame.getBlockers().get(i));
		
	}
	public boolean isShot() {
		return isShot;
	}
	public void setShot(boolean isShot) {
		this.isShot = isShot;
	}
	
}
