package domain;

import java.util.ArrayList;
import java.util.List;

public class EtaShield extends ShieldDecorator {

	private UnshieldedAtom atom;
	private double eta_efficiency_boost = 0.2;
	
	private List<Observer> listeners = new ArrayList<>();


	public EtaShield(UnshieldedAtom atom, int positionX, int positionY, int width, int heigth) {
		super(positionX, positionY, width, heigth);
		this.atom = atom;
		this.type = atom.getType();
		this.reduceSpeed();
	}

	@Override
	public double calculateEfficiency() {
		if (this.getParticles()[2] != this.getParticles()[1]) {
			return atom.calculateEfficiency() * 2;
		} else {
			return atom.calculateEfficiency() + ((1 - atom.calculateEfficiency()) * eta_efficiency_boost);
		}
	}

	@Override
	public Double reduceSpeed() {
		setSpeed(atom.getSpeed() - 0.05);
		return atom.getSpeed() - 0.05;
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