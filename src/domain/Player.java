package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private double healthPoints;
	private double score;
	private double gameTimePassed;
	
	private List<Observer> listeners = new ArrayList<>();
	
	public Player() {
		score = 0;
		healthPoints = 100;
	}
	
	public double getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(double d) {
		this.healthPoints = d;
		publishEvent();
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
		publishEvent();
	}

	public double getTime() {
		return gameTimePassed;
	}

	public void setTime(double time) {
		this.gameTimePassed = time;
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
