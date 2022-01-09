package domain;

import java.util.ArrayList;
import java.util.List;

public class LotaShield extends ShieldDecorator {

	private double lota_efficiency_boost = 0.2;
	private UnshieldedAtom atom;
	private List<Observer> listeners = new ArrayList<>();

	
	public LotaShield(UnshieldedAtom atom, int positionX, int positionY, int width, int heigth) {
		super(positionX, positionY, width, heigth);
		this.atom = atom;
		this.type = atom.getType();
		this.reduceSpeed();
	}

	@Override
	public double calculateEfficiency() {
		return atom.calculateEfficiency() + ((1 - atom.calculateEfficiency()) * lota_efficiency_boost);
	}

	@Override
	public double[] getParticles() {
		return atom.getParticles();

	}

	@Override
	public Double reduceSpeed() {
		setSpeed(atom.getSpeed() - 0.07);
		return atom.getSpeed() - 0.07;
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

