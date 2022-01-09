package domain;

import java.util.ArrayList;
import java.util.List;

public class ZetaShield extends ShieldDecorator {

	private double zeta_efficiency_boost = 0.2;
	private UnshieldedAtom atom;
	private List<Observer> listeners = new ArrayList<>();

	public ZetaShield(UnshieldedAtom atom, int positionX, int positionY, int width, int heigth) {
		super(positionX, positionY, width, heigth);
		this.atom = atom;
		this.type = atom.getType();
		this.reduceSpeed();
	}

	@Override
	public double calculateEfficiency() {
		if (this.getParticles()[1] == this.getParticles()[2]) {
			return atom.calculateEfficiency() + ((1 - atom.calculateEfficiency()) * zeta_efficiency_boost);
		} else {
			return atom.calculateEfficiency();
		}
	}

	@Override
	public double[] getParticles() {
		return atom.getParticles();
	}

	@Override
	public Double reduceSpeed() {
		setSpeed(atom.getSpeed() - 0.11);
		return atom.getSpeed() - 0.11;
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
