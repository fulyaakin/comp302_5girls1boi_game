package ui.panel;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.BuildingMode;
import domain.KUvidGame;
import domain.Observer;
import ui.component.AtomComponent;

public class InformationPanel extends JPanel implements Observer {

	private double gameTimePassed;
	private double gameTime;
	private JLabel score = new JLabel();
	private JLabel gameTimeLabel = new JLabel();
	private JLabel health = new JLabel();

	protected Timer timer;
	protected boolean gameOver;

	public InformationPanel() {
		gameTime = BuildingMode.getInstance().getGameTime();
		gameTimePassed = 0;
		timer = new Timer();
		timerStart(timer);
	}

	public void timerStart(Timer timer) {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (gameTime != 0) {
					gameTime -= 0.1;
					gameTimePassed += 0.1;
					updateTime();
				} else {
					timer.cancel();
					gameOver = true;
				}
			}
		}, 1000, 100);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		updateScore();
		updateHealth();
		add(score);
		add(health);
		add(gameTimeLabel);
	}

	public void collisionBlockerShooter() {
		double health = KUvidGame.getPlayer().getHealthPoints();
		KUvidGame.getPlayer().setHealthPoints(health - 10);
	}

	private void updateHealth() {
		health.setText("Health:" + String.format("%.3f", KUvidGame.getPlayer().getHealthPoints()));
	}

	// REQUIRES: Intersection happened between an atom and its corresponding
	// molecule,
	// MODIFIES: Player's score, UI label score
	// EFFECTS: It increases score by 1 + 1/gameTimePassed and updates the UI label
	// score.
	public void intersectionHappened(double efficiency) {
		double score = KUvidGame.getPlayer().getScore();
		double calculatedScore = (1 / gameTimePassed) + efficiency;
		KUvidGame.getPlayer().setScore(score + calculatedScore);
	}

	private void updateScore() {
		score.setText("Score: " + String.format("%.3f", KUvidGame.getPlayer().getScore()));
	}

	private void updateTime() {
		gameTimeLabel.setText("Time: " + String.format("%.1f", gameTime));
	}

	@Override
	public void onEvent() {
		repaint();
	}

}
