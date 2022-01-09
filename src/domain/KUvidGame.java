package domain;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.saveload.DatabaseSaveLoadAdapter;
import domain.saveload.FileSaveLoadAdapter;
import domain.saveload.SaveLoadAdapter;
import ui.component.AtomComponent;
import ui.component.MoleculeComponent;
import ui.frame.GameFrame;
import ui.panel.BlenderPanel;
import ui.panel.GamePanel;
import ui.panel.InformationPanel;
import ui.panel.PowerUpPanel;
import ui.panel.ShieldPanel;

public class KUvidGame {
	private GamePanel gamePanel;

	public static final double WIDTH = GameFrame.WIDTH;
	public static final double HEIGHT = GameFrame.HEIGHT;
	public static final double L = GameFrame.L;

	private BlenderPanel blenderPanel;
	private PowerUpPanel powerUpPanel;
	private InformationPanel informationPanel;
	private ShieldPanel shieldPanel;

	private static Shooter shooter;
	private static List<UnshieldedAtom> unshieldedAtoms = new ArrayList<>();
	private static List<Molecule> molecules = new ArrayList<>();

	private static Blender blender = new Blender();
	private static Player player = new Player();

	private static List<FallingPowerup> fallingpowerups = new ArrayList<>();
	private static List<ShootablePowerup> shootablepowerups = new ArrayList<>();
	private static String shootablePowerupType = "alpha";
	private static List<ReactionBlocker> blockers = new ArrayList<>();

	private static ComponentFactory factory = new ComponentFactory();
	
	private SaveLoadAdapter adapter;
	public static HashMap<String, Object> gameObjects;

	public KUvidGame(GamePanel gamePanel, BlenderPanel blenderPanel, PowerUpPanel powerUpPanel,
			InformationPanel informationPanel, ShieldPanel shieldPanel) {
		if(BuildingMode.getInstance().getSaveOption() == "Database") {
			adapter = new DatabaseSaveLoadAdapter();
		} else {
			adapter = new FileSaveLoadAdapter();
		}
		//super();
		this.gamePanel = gamePanel;
		this.blenderPanel = blenderPanel;
		this.powerUpPanel = powerUpPanel;
		this.informationPanel = informationPanel;
		this.shieldPanel = shieldPanel;
		

	}
	
	public void handleEvent(KeyEvent event) {
		if (!gamePanel.isPaused()) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				KUvidGame.getShooter().shooterLeft((int) GameFrame.L);
				break;
			case KeyEvent.VK_RIGHT:
				KUvidGame.getShooter().shooterRight((int) GameFrame.L);
				break;
			case KeyEvent.VK_A:
				KUvidGame.getShooter().shooterAngleLeft();
				break;
			case KeyEvent.VK_D:
				KUvidGame.getShooter().shooterAngleRight();
				break;
			case KeyEvent.VK_C:
				gamePanel.changeAtom();
				break;
			case KeyEvent.VK_B:
				blenderPanel.bPressed();
				break;
			case KeyEvent.VK_1:
				blenderPanel.pressedNumber(1);
				break;
			case KeyEvent.VK_2:
				blenderPanel.pressedNumber(2);
				break;
			case KeyEvent.VK_3:
				blenderPanel.pressedNumber(3);
				break;
			case KeyEvent.VK_4:
				blenderPanel.pressedNumber(4);
				break;
			case KeyEvent.VK_P:
				gamePanel.pause();
				break;
			case KeyEvent.VK_R:
				gamePanel.resume();
				break;
			case KeyEvent.VK_UP:
				String shot = gamePanel.shot();
				if (shot != null)
					blender.setAtom(shot);
				blenderPanel.updateLabels();
				break;
			case KeyEvent.VK_E:
				shieldPanel.updateLabels("eta");
				gamePanel.decorateAtom("eta");
				break;
			case KeyEvent.VK_L:
				shieldPanel.updateLabels("lota");
				gamePanel.decorateAtom("lota");
				break;
			case KeyEvent.VK_T:
				shieldPanel.updateLabels("theta");
				gamePanel.decorateAtom("theta");
				break;
			case KeyEvent.VK_Z:
				shieldPanel.updateLabels("zeta");
				gamePanel.decorateAtom("zeta");
				break;
			case KeyEvent.VK_S:
				gamePanel.pause();
				this.save();
				break;
			
			}
		} else {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_R:
				gamePanel.resume();
				break;
			case KeyEvent.VK_S:
				this.save();
				break;
			case KeyEvent.VK_L:
				//this.load();
				break;
			}
		}
	}


	public void decorate(UnshieldedAtom a, String shield) {
		if (shield.equals("eta")) {
			a = new EtaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
		}
		if (shield.equals("lota")) {
			a = new LotaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
		}
		if (shield.equals("theta")) {
			a = new ThetaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
		}
		if (shield.equals("zeta")) {
			a = new ZetaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
		}
	}
	
	public void save() {
		gameObjects = new HashMap<String, Object>();
		gameObjects.put("atoms", unshieldedAtoms);
		gameObjects.put("molecules", molecules);
		gameObjects.put("shooter", shooter);
		gameObjects.put("player", player);
		gameObjects.put("blender", blender);
		gameObjects.put("buildingMode", BuildingMode.getInstance());
		//gameObjects.put("blockers", powerups);
		//gameObjects.put("fallingpowerups", fallingpowerups);
		//gameObjects.put("shootablepowerups", shootablepowerups);
		adapter.save();
	}

	public void load() {
		adapter.load();
		//List<Atom> atomsList = (List<Atom>) gameObjects.get("atoms");
		shooter = (Shooter) gameObjects.get("shooter");
		unshieldedAtoms = (List<UnshieldedAtom>) gameObjects.get("atoms");
		molecules = (List<Molecule>) gameObjects.get("molecules");
		
		int atomIndex = 0;
		int moleculeIndex = 0;

		for (UnshieldedAtom a : unshieldedAtoms) {
			AtomComponent c = new AtomComponent(atomIndex);
			GamePanel.getAtomList().add(c);
			atomIndex++;
		}
		for (Molecule m : molecules) {
			MoleculeComponent c = new MoleculeComponent(moleculeIndex,5);
			GamePanel.getMoleculeList().add(c);
			moleculeIndex++;
		}
		
	}


	public static ThetaShield getThetaShield(UnshieldedAtom a) {
		return new ThetaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
	}

	public static EtaShield getEtaShield(UnshieldedAtom a) {
		return new EtaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
	}

	public static LotaShield getLotaShield(UnshieldedAtom a) {
		return new LotaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
	}

	public static ZetaShield getZetaShield(UnshieldedAtom a) {
		return new ZetaShield(a, a.getPositionX(), a.getPositionY(), a.getWidth(), a.getHeight());
	}

	public static Shooter getShooter() {
		return shooter;
	}

	public static Player getPlayer() {
		return player;
	}

	public static Blender getBlender() {
		return blender;
	}

	public static List<UnshieldedAtom> getAtoms() {
		return unshieldedAtoms;
	}

	public static List<Molecule> getMolecules() {
		return molecules;
	}

	public static void initShooter() {
		shooter = new Shooter((int) (KUvidGame.WIDTH / 2), (int) (KUvidGame.HEIGHT - 200), (int) KUvidGame.L / 2,
				(int) KUvidGame.L, 90, KUvidGame.WIDTH / 5 * 4, (int) KUvidGame.WIDTH);
	}

	public static void initAtom() {
		unshieldedAtoms.add((UnshieldedAtom) factory.getComponent("atom"));
	}

	public static void initMolecule() {
		molecules.add((Molecule) factory.getComponent("molecule"));
	}

	public static void initPowerup() {
		fallingpowerups.add((FallingPowerup) factory.getComponent("fallingPowerup"));
	}

	public static void initBlocker() {
		blockers.add((ReactionBlocker) factory.getComponent("blocker"));
	}

	public static List<FallingPowerup> getFallingPowerups() {
		// TODO Auto-generated method stub
		return fallingpowerups;
	}

	public static List<ShootablePowerup> getShootablePowerups() {
		// TODO Auto-generated method stub
		return shootablepowerups;
	}

	public static void initShootablePowerup() {
		shootablepowerups.add((ShootablePowerup) factory.getComponent("shootablePowerup"));

	}

	public static String getShootablePowerupType() {
		return shootablePowerupType;
	}

	public static void setShootablePowerupType(String shootablePowerupType) {
		KUvidGame.shootablePowerupType = shootablePowerupType;
	}

	public static List<ReactionBlocker> getBlockers() {
		return blockers;
	}

	public static void setBlockers(List<ReactionBlocker> blockers) {
		KUvidGame.blockers = blockers;
	}

}
