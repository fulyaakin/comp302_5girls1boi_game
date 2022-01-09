package ui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.KUvidGame;
import ui.panel.BlenderPanel;
import ui.panel.GamePanel;
import ui.panel.InformationPanel;
import ui.panel.PowerUpPanel;
import ui.panel.ShieldPanel;

public class GameFrame extends JFrame {

	public static final double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final double L = HEIGHT / 10;

	private GamePanel gamePanel;
	private BlenderPanel blenderPanel;
	private PowerUpPanel powerUpPanel;
	private InformationPanel informationPanel;
	private ShieldPanel shieldPanel;
	protected static boolean gameOver;

	public void initializeGame() {
		blenderPanel = new BlenderPanel();
		blenderPanel.setPreferredSize(new Dimension(300, 300));
		blenderPanel.setLayout(new GridLayout(10, 1));
		blenderPanel.setBorder(BorderFactory.createTitledBorder("blenderPanel"));

		powerUpPanel = new PowerUpPanel();
		powerUpPanel.setBorder(BorderFactory.createTitledBorder("powerUpPanel"));
		powerUpPanel.setPreferredSize(new Dimension(80, 80));

		shieldPanel = new ShieldPanel();
		shieldPanel.setPreferredSize(new Dimension(100, 100));
		shieldPanel.setBorder(BorderFactory.createTitledBorder("shieldPanel"));
		shieldPanel.setLayout(new GridLayout(4, 1));

		informationPanel = new InformationPanel();
		informationPanel.setBorder(BorderFactory.createTitledBorder("informationPanel"));

		JPanel menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(400, 400));
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

		menuPanel.add(informationPanel);
		menuPanel.add(powerUpPanel);
		menuPanel.add(blenderPanel);
		menuPanel.add(shieldPanel);

		gamePanel = new GamePanel(blenderPanel, informationPanel,powerUpPanel);
		gamePanel.setBorder(BorderFactory.createTitledBorder("gamePanel"));

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));
		// mainPanel.setLayout(new GridLayout(1,1));
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(gamePanel, BorderLayout.CENTER);
		mainPanel.add(menuPanel, BorderLayout.EAST);

		getContentPane().add(mainPanel);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		makeFrameFullSize();

		KUvidGame KUvidGame = new KUvidGame(gamePanel, blenderPanel, powerUpPanel, informationPanel,shieldPanel);

		addEventlistener(KUvidGame);

		setVisible(true);

	}

	private void addEventlistener(KUvidGame kUvidGame) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				kUvidGame.handleEvent(event);
			}
		});
		
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean isOver) {
		gameOver = isOver;
	}

	private void makeFrameFullSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
	}
}
