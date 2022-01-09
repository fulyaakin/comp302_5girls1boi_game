package domain;

import java.util.ArrayList;
import java.util.List;

public class ThetaShield extends ShieldDecorator {

	private double theta_efficiency_boost = Math.random() * ((0.15 - 0.05)) + 0.05;
	private UnshieldedAtom atom;
	private List<Observer> listeners = new ArrayList<>();

	public ThetaShield(UnshieldedAtom atom, int positionX, int positionY, int width, int heigth) {
		super(positionX, positionY, width, heigth);
		this.atom = atom;
		this.type = atom.getType();
		this.reduceSpeed();
	}

	@Override
	public double calculateEfficiency() {
		return atom.calculateEfficiency() + ((1 - atom.calculateEfficiency()) * theta_efficiency_boost);

	}

	@Override
	public Double reduceSpeed() {
		setSpeed(atom.getSpeed() - 0.09);
		return atom.getSpeed() - 0.09;
	}

	@Override
	public double[] getParticles() {
		return atom.getParticles();

	}
	
	public void addListener(Observer o) {
		listeners.add(o);
	}

	public void publishEvent() {
		for (Observer o : listeners) {
			o.onEvent();
		}
	}
}
