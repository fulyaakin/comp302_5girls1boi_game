package ui.frame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import domain.BuildingMode;

public class LoginFrame extends JFrame implements ActionListener {

	private static JLabel userLabel = new JLabel("USERNAME");
	private static JRadioButton database = new JRadioButton("Database");
	private static JRadioButton file = new JRadioButton("File");
	private static JButton startButton = new JButton("Start New Game");
	private static JButton loadButton = new JButton("Load Game");
	private static JTextField userTextField = new JTextField();

	
	public LoginFrame() {
		setSize(500, 500);
		setLayout(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
	}

	public static void initializeLogin() {
		activateStartButton();
		ButtonGroup group = new ButtonGroup();
		group.add(database);
		group.add(file);
        LoginFrame frame = new LoginFrame();
        userLabel.setBounds(650, 150, 100, 30);
		userTextField.setBounds(750, 150, 150, 30);
		file.setBounds(700, 200, 100, 30);
		database.setBounds(800, 200, 100, 30);
		startButton.setBounds(600, 300, 150, 50);
		loadButton.setBounds(800, 300, 150, 50);
        frame.add(userLabel);
        frame.add(userTextField);
        frame.add(database);
        frame.add(file);
        frame.add(startButton);
        frame.add(loadButton);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setSize(screenSize.width, screenSize.height);
      
	}

	public static void activateStartButton() {
		startButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
		        if (e.getSource() == startButton) {
		        	BuildingMode.getInstance().setUsername(userTextField.getText());
		        	if (database.isSelected()) {
		    			BuildingMode.getInstance().setSaveOption(database.getText());
		    		} else {
		    			BuildingMode.getInstance().setSaveOption(file.getText());
		    		}
		        	System.out.println(BuildingMode.getInstance().getUsername()+BuildingMode.getInstance().getSaveOption());
		            BuildingFrame starterFrame = new BuildingFrame();
		            starterFrame.initializeGUI();
		        }
			}
		});
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}