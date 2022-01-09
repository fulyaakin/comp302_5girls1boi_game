package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class ShieldDecorator extends UnshieldedAtom {
	

	public ShieldDecorator(int positionX, int positionY, int width, int heigth) {
		super(positionX, positionY, width, heigth);
		this.type = super.getType();
	}
	private List<Observer> listeners = new ArrayList<>();


	public abstract double calculateEfficiency();

	public abstract double[] getParticles();

	public abstract Double reduceSpeed();

	public abstract void addListener(Observer o);

	public abstract void publishEvent();
}
