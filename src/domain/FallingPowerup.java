package domain;


import java.util.ArrayList;
import java.util.List;

public class FallingPowerup extends Powerup implements FallingObjects {


	private int startY;
	private int angle;
	protected PowerupType typeName;
	
	private List<Observer> listeners = new ArrayList<>();
	public FallingPowerup(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		typeName = PowerupType.generateType();		
		this.startX=posX;
	
	}
	

	@Override
	//REQUIRES:positionY and typeName of the powerups
	//MODIFIES: powerups fall in straight lines according to their type
	//MODIFIES: powerups stop when they reach the ground
	//EFFECTS: positionY of Powerups
	public void fall() {
		switch (typeName) {			
			case BETA:
				positionY++;
			break;
			case GAMMA:
				positionY++;
				break;
			case ALPHA:
				positionY++;
				break;
			case SIGMA:
				positionY++;
					break;
			}
			publishEvent();
		}
	
	public void addListener(Observer o) {
		listeners.add(o);
	}

	public void publishEvent() {
		for(Observer o : listeners) {
			o.onEvent();
		}
	}
}

