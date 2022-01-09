package ui.component;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

import domain.BuildingMode;
import domain.KUvidGame;
import domain.Observer;

public class MoleculeComponent extends 	JLabel implements Observer {
	
	private Image image;
	private int index;

	public MoleculeComponent(int index) {
		KUvidGame.initMolecule();
		this.index = index;
		try {
			if(KUvidGame.getMolecules().get(index).getType().getName().equals("alpha-1")) {
				if(BuildingMode.getInstance().getSpinningAnimation().equals("spinning")) {
					image = ImageIO.read(new File("./assets/molecules/alpha-1.png"));
				}
			}
			if(KUvidGame.getMolecules().get(index).getType().getName().equals("alpha-2")) {
				if(BuildingMode.getInstance().getSpinningAnimation().equals("stationary")) {
					image = ImageIO.read(new File("./assets/molecules/alpha-2.png"));
				}
			}
			if(KUvidGame.getMolecules().get(index).getType().getName().equals("beta-1")) {
				if(BuildingMode.getInstance().getSpinningAnimation().equals("spinning")) {
					image = ImageIO.read(new File("./assets/molecules/beta-1.png"));
				}
			}
			if(KUvidGame.getMolecules().get(index).getType().getName().equals("beta-2")) {
				if(BuildingMode.getInstance().getSpinningAnimation().equals("spinning")) {
					image = ImageIO.read(new File("./assets/molecules/beta-2.png"));
				} 
			}
			if(KUvidGame.getMolecules().get(index).getType().getName().equals("gamma-")) {
				image = ImageIO.read(new File("./assets/molecules/gamma-.png"));
			}
			if(KUvidGame.getMolecules().get(index).getType().getName().equals("sigma-")) {
				image = ImageIO.read(new File("./assets/molecules/sigma-.png"));
			}		
			KUvidGame.getMolecules().get(index).setWidth(((BufferedImage)image).getWidth());
			KUvidGame.getMolecules().get(index).setHeigth(((BufferedImage)image).getHeight());
		} 
		catch (Exception ex) 
		{
			
		}
	}
	
	public MoleculeComponent(int index, int lol) {
		this.index = index;
		try {
			image = ImageIO.read(new File("./assets/molecules/" + KUvidGame.getMolecules().get(index).getType().getName() + ".png"));
			KUvidGame.getMolecules().get(index).setWidth(((BufferedImage)image).getWidth());
			KUvidGame.getMolecules().get(index).setHeigth(((BufferedImage)image).getHeight());
		} catch (Exception ex) {
			
		}
	}


	public void paint(Graphics g) {
		KUvidGame.getMolecules().get(index).fall();
		g.drawImage(image, KUvidGame.getMolecules().get(index).getPositionX(), KUvidGame.getMolecules().get(index).getPositionY() , null);
	}

	@Override
	public void onEvent() {
		repaint();
	}
	
	public void initialize() {
		KUvidGame.getMolecules().get(index).addListener(this);
	}
	
	public String getType() {
		return KUvidGame.getMolecules().get(index).getType().getAtomType().toString();
	}

	public int getIndex() {
		return index;
	}
	public boolean intersectedBlocker(int i) {
		return KUvidGame.getMolecules().get(index).intersected(KUvidGame.getBlockers().get(i));
	}

	
}
