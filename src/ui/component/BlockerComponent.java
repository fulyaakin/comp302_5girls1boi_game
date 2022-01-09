package ui.component;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

import domain.KUvidGame;
import domain.Observer;

public class BlockerComponent extends 	JLabel implements Observer {
	
	private Image image;
	private int index;

	public BlockerComponent(int index) {
		KUvidGame.initBlocker();
		this.index = index;
		try {
			image = ImageIO.read(new File("./assets/blockers/" + KUvidGame.getBlockers().get(index).getType().getName() + ".png"));
			KUvidGame.getBlockers().get(index).setWidth(((BufferedImage)image).getWidth()*2);
			KUvidGame.getBlockers().get(index).setHeigth(((BufferedImage)image).getHeight()*2);
		} catch (Exception ex) {
		}
	}

	public void paint(Graphics g) {
		KUvidGame.getBlockers().get(index).fall();
		g.drawImage(image, KUvidGame.getBlockers().get(index).getPositionX(), KUvidGame.getBlockers().get(index).getPositionY() , null);
	}

	@Override
	public void onEvent() {
		repaint();
	}
	
	public void initialize() {
		KUvidGame.getBlockers().get(index).addListener(this);
	}
	
	public String getType() {
		return KUvidGame.getBlockers().get(index).getType().toString();
	}

	public int getIndex() {
		return index;
	}
	
	
}
