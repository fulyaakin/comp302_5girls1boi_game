package domain;

import java.util.ArrayList;
import java.util.List;

public class UnshieldedAtom extends Rectangle implements Shootable {
	public boolean isShot = false;
	public AtomType type;

	private int startX;
	private int startY;

	private double length = 0;
	private double speed = Math.sqrt(2);
	private double angle;

	private int neutrons;
	private int protons;
	private double stability;
	private int randwith5;
	private int randwith3;

	private List<Observer> listeners = new ArrayList<>();

	public UnshieldedAtom(int positionX, int positionY, int width, int heigth) {
		super(positionX, positionY, width, heigth);

		type = AtomType.generateType(0);
	}

	// @REQUIRES: blender has enough atoms to shot, shooter is not null
	// @EFFECTS: sets positionX and positionY to startX and startY
	// @MODIFIES: positionX, positionY, angle
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

	// @REQUIRES: isShot must be true;
	// @EFFECTS: moves the atom by setting positionX and positionY to startX + x and
	// startY + y where x and y the distance to travel
	// @MODIFIES: positionX, positionY
	@Override
	public void move() {
		length += speed;
		
		int x = (int) (length * Math.cos(Math.toRadians(angle)));
		int y = (int) (length * Math.sin(Math.toRadians(angle)));
		
		if(positionX >= 0 && positionX <= KUvidGame.WIDTH-470) {
			this.positionX = this.startX + x;
		} 
		if(positionX-1 < 0) {
			positionX=0;
		}
		if(positionX > KUvidGame.WIDTH-470) {
			positionX=(int) KUvidGame.WIDTH-470;
		}
		this.positionY = this.startY - y;
		publishEvent();
	}

	// REQUIRES: Blender has at least 1 atom of a different atomType than current
	// atomType, isShot is false
	// EFFECTS: changes the atomType of atom randomly
	// MODIFIES: atomType of the atom

	public boolean change() {
		if (isShot)
			return false;

		switch (getType()) {
		case ALPHA:
			if (KUvidGame.getBlender().getBetaAtoms() + KUvidGame.getBlender().getGammaAtoms()
					+ KUvidGame.getBlender().getSigmaAtoms() == 0)
				return false;
			break;
		case BETA:
			if (KUvidGame.getBlender().getAlphaAtoms() + KUvidGame.getBlender().getGammaAtoms()
					+ KUvidGame.getBlender().getSigmaAtoms() == 0)
				return false;
			break;
		case GAMMA:
			if (KUvidGame.getBlender().getAlphaAtoms() + KUvidGame.getBlender().getBetaAtoms()
					+ KUvidGame.getBlender().getSigmaAtoms() == 0)
				return false;
			break;
		case SIGMA:
			if (KUvidGame.getBlender().getAlphaAtoms() + KUvidGame.getBlender().getBetaAtoms()
					+ KUvidGame.getBlender().getGammaAtoms() == 0)
				return false;
			break;

		}

		while (true) {
			AtomType newType = AtomType.generateType(0);

			if (newType == getType())
				continue;

			switch (getType()) {
			case ALPHA:
				if (KUvidGame.getBlender().getAlphaAtoms() == 0)
					continue;
				break;
			case BETA:
				if (KUvidGame.getBlender().getBetaAtoms() == 0)
					continue;
				break;
			case GAMMA:
				if (KUvidGame.getBlender().getGammaAtoms() == 0)
					continue;
				break;
			case SIGMA:
				if (KUvidGame.getBlender().getSigmaAtoms() == 0)
					continue;
				break;

			}

			type = newType;
			publishEvent();
			return true;
		}
	}

	public void setType(AtomType type) {
		this.type = type;
	}

	public double calculateEfficiency() {
		stability = (int) getParticles()[0];
		protons = (int) getParticles()[1];
		neutrons = (int) getParticles()[2];
		double efficiency = (1 - Math.abs(neutrons - protons) / protons) * stability;
		return efficiency;
	}

	public double[] getParticles() {
		randwith5 = (int) (Math.random() * 4) + 1;
		randwith3 = (int) (Math.random() * 2) + 1;
		AtomType type = getType();
		switch (type) {
		case ALPHA:
			stability = 0.85;
			protons = 8;
			if (randwith3 == 1) {
				neutrons = 7;
			} else if (randwith3 == 2) {
				neutrons = 8;
			} else {
				neutrons = 9;
			}
			break;
		case BETA:
			stability = 0.9;
			protons = 16;
			if (randwith5 == 1) {
				neutrons = 15;
			} else if (randwith5 == 2) {
				neutrons = 16;
			} else if (randwith5 == 3) {
				neutrons = 17;
			} else if (randwith5 == 4) {
				neutrons = 18;
			} else {
				neutrons = 21;
			}
			break;
		case GAMMA:
			stability = 0.8;
			protons = 32;
			if (randwith3 == 1) {
				neutrons = 29;
			} else if (randwith3 == 2) {
				neutrons = 32;
			} else {
				neutrons = 33;
			}
			break;
		case SIGMA:
			stability = 0.7;
			protons = 64;
			if (randwith3 == 1) {
				neutrons = 63;
			} else if (randwith3 == 2) {
				neutrons = 64;
			} else {
				neutrons = 67;
			}
			break;
		}
		double[] result = { stability, protons, neutrons };
		return result;
	}
	
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getNeutrons() {
		return neutrons;
	}

	public void setNeutrons(int neutrons) {
		this.neutrons = neutrons;
	}

	public int getProtons() {
		return protons;
	}

	public void setProtons(int protons) {
		this.protons = protons;
	}

	public double getStability() {
		return stability;
	}

	public void setStability(double stability) {
		this.stability = stability;
	}

	public int getRandwith5() {
		return randwith5;
	}

	public void setRandwith5(int randwith5) {
		this.randwith5 = randwith5;
	}

	public int getRandwith3() {
		return randwith3;
	}

	public void setRandwith3(int randwith3) {
		this.randwith3 = randwith3;
	}

	public List<Observer> getListeners() {
		return listeners;
	}

	public void setListeners(List<Observer> listeners) {
		this.listeners = listeners;
	}

	public AtomType getType() {
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
		for (Observer o : listeners) {
			o.onEvent();
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
