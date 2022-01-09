package domain;

import java.util.ArrayList;
import java.util.List;

public class ShootablePowerup extends Rectangle implements Shootable {
	protected boolean isShot = false;
	protected PowerupType type;

	private int startX;
	private int startY;

	private double length = 0;
	private double angle;

	private List<Observer> listeners = new ArrayList<>();


	public ShootablePowerup(int positionX, int positionY, int width, int heigth, String type) {
		super(positionX, positionY, width, heigth);
		switch(type) {
		case "alpha":
			this.type =PowerupType.ALPHA;
			break;
		case "beta":
			this.type =PowerupType.BETA;
			break;
		case "gamma":
			this.type =PowerupType.GAMMA;
			break;
		case "sigma":
			this.type =PowerupType.SIGMA;
			break;
		}
		
	}
	
	@Override
	public void shot(int startX, int startY, int angle) {
		isShot = true;

		this.startX = startX;
		this.positionX = startX;

		this.startY = startY;
		this.positionY = startY;

		this.angle = angle;

		publishEvent();
	
	}
	
	@Override
	public void move() {
		length += Math.sqrt(2);

		int x = (int) (length * Math.cos(Math.toRadians(angle)));
		int y = (int) (length * Math.sin(Math.toRadians(angle)));

		this.positionX = this.startX + x;
		this.positionY = this.startY - y;
		publishEvent();
	}
	
	

	public PowerupType getType() {
		return type;
	}

	public boolean isShot() {
		return isShot;
	}

	public void setShot(boolean isShot) {
		this.isShot = isShot;
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
