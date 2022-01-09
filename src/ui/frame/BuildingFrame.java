package ui.frame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import domain.BuildingMode;

public class BuildingFrame extends JFrame  {
	
	private JLabel title;
	private JLabel text;
	private JPanel panel;
	private JTextField moleculeText;
	private JTextField atomText;
	private JTextField powerupText;
	private JTextField rblockersText;
	private JRadioButton stationary;
	private JRadioButton spinning;
	private JComboBox difficulty;
	private JTextField gameTimeText;
	private JTextField shieldText;
	
	private static GameFrame gameFrame;

	
	public BuildingFrame() {
		
	}
	
	public void initializeGUI() {
		setSize(1000, 1000);
		setLayout(new GridLayout(2, 1));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		title = new JLabel("", JLabel.CENTER);
		text = new JLabel("", JLabel.CENTER);
		text.setSize(400, 150);
		title.setText("KUvid Game Settings");

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel molecules = new JLabel("Number of molecules: ");
		JLabel atoms = new JLabel("Number of atoms: ");
		JLabel powerups = new JLabel("Number of powerups: ");
		JLabel reactionBlockers = new JLabel("Number of reaction blockers:");
		JLabel animationLabel = new JLabel("Spinning animation:");
		JLabel shieldNumber = new JLabel("Number of shields:");
		moleculeText = new JTextField(3);
		atomText = new JTextField(3);
		powerupText = new JTextField(3);
		rblockersText = new JTextField(3);
		stationary = new JRadioButton("stationary");
		spinning = new JRadioButton("spinning");
		shieldText=new JTextField(3);

		ButtonGroup group = new ButtonGroup();
		group.add(stationary);
		group.add(spinning);

		String[] difficultyLevels = { "Easy", "Medium", "Hard" };
		difficulty = new JComboBox(difficultyLevels);
		JLabel difficultyLabel = new JLabel("Difficulty: ");
		
		JLabel gameTimeLabel = new JLabel("Game time: ");
		gameTimeText = new JTextField(3);
		
		panel.add(molecules);
		panel.add(moleculeText);
		panel.add(atoms);
		panel.add(atomText);
		panel.add(powerups);
		panel.add(powerupText);
		panel.add(reactionBlockers);
		panel.add(rblockersText);
		panel.add(animationLabel);
		panel.add(stationary);
		panel.add(spinning);
		panel.add(difficultyLabel);
		panel.add(difficulty);
		panel.add(gameTimeLabel);
		panel.add(gameTimeText);
		panel.add(shieldNumber);
		panel.add(shieldText);
		add(title);
		add(panel);
		add(text);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(atomText.getText().equals(""))) {
					BuildingMode.getInstance().setAtomNumber(Integer.parseInt(atomText.getText()));
					BuildingMode.getInstance().setAlphaAtoms(Integer.parseInt(atomText.getText()));
					BuildingMode.getInstance().setBetaAtoms(Integer.parseInt(atomText.getText()));
					BuildingMode.getInstance().setGamaAtoms(Integer.parseInt(atomText.getText()));
					BuildingMode.getInstance().setSigmaAtoms(Integer.parseInt(atomText.getText()));
				} else {
					BuildingMode.getInstance().setAtomNumber(100);
					BuildingMode.getInstance().setAlphaAtoms(100);
					BuildingMode.getInstance().setBetaAtoms(100);
					BuildingMode.getInstance().setGamaAtoms(100);
					BuildingMode.getInstance().setSigmaAtoms(100);
				}
				if (!(moleculeText.getText().equals(""))) {
					BuildingMode.getInstance().setMoleculeNumber(Integer.parseInt(moleculeText.getText()));
				} else {
					BuildingMode.getInstance().setMoleculeNumber(100);
				}
				if (!(powerupText.getText().equals(""))) {
					BuildingMode.getInstance().setPowerupNumber(Integer.parseInt(powerupText.getText()));
				} else {
					BuildingMode.getInstance().setPowerupNumber(20);
				}
				if (!(rblockersText.getText().equals(""))) {
					BuildingMode.getInstance().setBlockerNumber(Integer.parseInt(rblockersText.getText()));
				} else {
					BuildingMode.getInstance().setBlockerNumber(10);
				}
				
				BuildingMode.getInstance().setDifficulty((String) difficulty.getSelectedItem());				
				
				if (stationary.isSelected()) {
					BuildingMode.getInstance().setSpinningAnimation(stationary.getText());
				} else {
					BuildingMode.getInstance().setSpinningAnimation(spinning.getText());
				}
				
				if (!(gameTimeText.getText().equals(""))) {
					BuildingMode.getInstance().setGameTime(Double.parseDouble(gameTimeText.getText()));
				} else {
					BuildingMode.getInstance().setGameTime(60);
				}
				if (!(shieldText.getText().equals(""))) {
					BuildingMode.getInstance().setShieldNumber(Integer.parseInt(shieldText.getText()));
					BuildingMode.getInstance().setEtaNumber(Integer.parseInt(shieldText.getText()));
					BuildingMode.getInstance().setLotaNumber(Integer.parseInt(shieldText.getText()));
					BuildingMode.getInstance().setThetaNumber(Integer.parseInt(shieldText.getText()));
					BuildingMode.getInstance().setZetaNumber(Integer.parseInt(shieldText.getText()));

				} else {
					BuildingMode.getInstance().setShieldNumber(20);
					BuildingMode.getInstance().setEtaNumber(20);
					BuildingMode.getInstance().setLotaNumber(20);
					BuildingMode.getInstance().setThetaNumber(20);
					BuildingMode.getInstance().setZetaNumber(20);
				}

				// setVisible(false);
				gameFrame = new GameFrame();
				gameFrame.initializeGame();
				
			}
		});

		panel.add(submit);
		
		
		setVisible(true);
		makeFrameFullSize();
	}

	private void makeFrameFullSize() {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(screenSize.width, screenSize.height);
	}

}
