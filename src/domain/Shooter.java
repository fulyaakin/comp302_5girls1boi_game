package domain;

import java.util.ArrayList;
import java.util.List;

public class Shooter extends Rectangle {
	
	protected int angle;
	protected double lineLength;
	private int maxWidth;
	
	private List<Observer> listeners = new ArrayList<>();
	
	public Shooter(int positionX, int positionY, int width, int heigth, int angle, double lineLength, int maxWidth) {
		super(positionX, positionY, width, heigth);
		this.angle = angle;
		this.lineLength = lineLength;
		this.maxWidth = maxWidth;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public void shooterLeft(int n) {
		if (getPositionX() > 0)
			setPositionX(getPositionX() - n);
		publishEvent();
	}

	public void shooterRight(int n) {
		if (getPositionX() < maxWidth - 100)
			setPositionX(getPositionX() + n);
		publishEvent();
	}

	public void shooterAngleLeft() {
		if (!(getAngle() + 10 > 180))
			setAngle(getAngle() + 10);
		publishEvent();
	}

	public void shooterAngleRight() {
		if (!(getAngle() - 10 < 0))
			setAngle(getAngle() - 10);
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