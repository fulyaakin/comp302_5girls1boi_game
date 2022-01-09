package domain;

import java.util.ArrayList;
import java.util.List;

public class Molecule extends Rectangle implements FallingObjects {
	
	protected final static int LEFT = 0;
	protected final static int RIGHT = 0;
	
	protected MoleculeType type;
	
	protected boolean isZigZag;
	
	public boolean isZigZag() {
		return isZigZag;
	}

	public void setZigZag(boolean isZigZag) {
		this.isZigZag = isZigZag;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public List<Observer> getListeners() {
		return listeners;
	}

	public void setListeners(List<Observer> listeners) {
		this.listeners = listeners;
	}

	public static int getLeft() {
		return LEFT;
	}

	public static int getRight() {
		return RIGHT;
	}

	public void setType(MoleculeType type) {
		this.type = type;
	}

	protected int direction;
	
	protected int startX;
	
	private List<Observer> listeners = new ArrayList<>();

	public Molecule(int positionX, int positionY, int width, int heigth) {
		super(positionX, positionY, width, heigth);
		
		this.startX = positionX;
		
		type = MoleculeType.generateType(0);
		
		if (type == MoleculeType.SIGMA) {
			isZigZag = false;	
		} else {
			isZigZag = true;
		}
		
	}

	public MoleculeType getType() {
		return type;
	}

	//REQUIRES Type of the molecule, height of the KUvid Game, positionX, positionY, direction, startX and isZigZag of the molecule
	// MODIFIES positionY, positionX , direction and isZigZag
	// EFFECTS changes the falling pattern of the molecule
	@Override
	public void fall() {
		switch (type) {
		case BETA_1:
		case BETA_2:
			if (KUvidGame.HEIGHT / 4 < positionY) {
				isZigZag = false;
			}
			break;
		case GAMMA:
			if (KUvidGame.HEIGHT / 2 < positionY) {
				isZigZag = false;
			}
			break;
		}
		
		if(positionX < 0 ) {
			positionX=0;
			direction=RIGHT;
		}
		if(positionX > KUvidGame.WIDTH-470) {
			positionX=(int) KUvidGame.WIDTH-470;
			direction=LEFT;
		}
		
		if (isZigZag) {
			if (direction == LEFT) {
				if (positionX <= startX - KUvidGame.L)
					direction = RIGHT;
			} else {
				if (positionX >= startX + KUvidGame.L)
					direction = LEFT;
			}
			
			if (direction == LEFT) {
				positionX--;
			}	
			else {
				positionX++;
			}
				
		}
		
		positionY++;
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
