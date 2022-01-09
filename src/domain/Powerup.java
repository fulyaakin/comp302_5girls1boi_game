package domain;

import java.util.ArrayList;
import java.util.List;

import ui.frame.GameFrame;

public abstract class Powerup extends Rectangle {

	
	protected final static int LEFT = 0;
	protected final static int RIGHT = 0;
	private PowerupType typeName;	
	protected int startX;


	public Powerup( int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		typeName = PowerupType.generateType();		
		this.startX=posX;
	
	}
	
	public PowerupType getTypeName() {
		return typeName;
	}

	public void setTypeName(PowerupType typeName) {
		this.typeName = typeName;
	}

}
