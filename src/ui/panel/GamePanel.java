package ui.panel;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import domain.BuildingMode;
import domain.KUvidGame;
import ui.component.AtomComponent;
import ui.component.BlockerComponent;
import ui.component.FallingPowerupComponent;
import ui.component.MoleculeComponent;
import ui.component.ShootablePowerupComponent;
import ui.component.ShooterComponent;
import ui.frame.GameFrame;

public class GamePanel extends JPanel {

	private static ArrayList<MoleculeComponent> moleculeList = new ArrayList<>();
	private ArrayList<AtomComponent> atomsShot = new ArrayList<>();
	private ShooterComponent shooter = new ShooterComponent();
	private int atomIndex = 0;
	private int moleculeIndex = 0;
	private int fallingPowerupIndex = 0;
	private int shootablePowerupIndex = 0;
	private int blockerIndex = 0;
	private String name = "ALPHA";

	private static List<AtomComponent> atomList = new ArrayList<>();
	private ArrayList<BlockerComponent> blockerList = new ArrayList<>();
	private ArrayList<FallingPowerupComponent> FallingPowerupList = new ArrayList<>();
	private ArrayList<ShootablePowerupComponent> powerupsShot = new ArrayList<>();
	private ArrayList<ShootablePowerupComponent> AlphaShootablepowerupList = new ArrayList<>();
	private ArrayList<ShootablePowerupComponent> GammaShootablepowerupList = new ArrayList<>();
	private ArrayList<ShootablePowerupComponent> SigmaShootablepowerupList = new ArrayList<>();
	private ArrayList<ShootablePowerupComponent> BetaShootablepowerupList = new ArrayList<>();

	private boolean isPaused = false;
	private int time = 1;
	private BlenderPanel blenderPanel;
	private InformationPanel informationPanel;
	protected static boolean gameOver;
	private PowerUpPanel powerupPanel;

	public GamePanel(BlenderPanel blenderPanel, InformationPanel informationPanel, PowerUpPanel powerupPanel) {
		moleculeList.add(new MoleculeComponent(moleculeIndex));
		atomList.add(new AtomComponent(shooter, atomIndex));
		FallingPowerupList.add(new FallingPowerupComponent(fallingPowerupIndex));
		blockerList.add(new BlockerComponent(blockerIndex));
		this.blenderPanel = blenderPanel;
		this.informationPanel = informationPanel;
		this.powerupPanel = powerupPanel;

	}

	@Override
	public void paint(Graphics g) {
		checkIntersection();
		super.paint(g);
		time++;
		if (time % 100 == 0 
				&& moleculeList.size() < BuildingMode.getInstance().getMoleculeNumber()
				&& FallingPowerupList.size() < BuildingMode.getInstance().getPowerupNumber()
				&& blockerList.size() < BuildingMode.getInstance().getBlockerNumber()) {
			moleculeList.add(new MoleculeComponent(++moleculeIndex));
			FallingPowerupList.add(new FallingPowerupComponent(++fallingPowerupIndex));
			blockerList.add(new BlockerComponent(++blockerIndex));
		}

		shooter.paint(g);

		for (AtomComponent a : atomList) {
			a.paint(g);
		}

		for (MoleculeComponent m : moleculeList) {
			m.paint(g);
		}
		for (FallingPowerupComponent p : FallingPowerupList) {
			if (KUvidGame.getFallingPowerups().get(p.getIndex()).getPositionY() != KUvidGame.getShooter()
					.getPositionY())
				p.paint(g);
		}

		for (AtomComponent a : atomsShot) {
			a.paint(g);
		}
		for (BlockerComponent a : blockerList) {
			a.paint(g);
		}

		for (ShootablePowerupComponent p : powerupsShot) {
			p.paint(g);
		}

		for (ShootablePowerupComponent p : AlphaShootablepowerupList) {
			p.paint(g);
		}
		for (ShootablePowerupComponent p : BetaShootablepowerupList) {
			p.paint(g);
		}
		for (ShootablePowerupComponent p : GammaShootablepowerupList) {
			p.paint(g);
		}
		for (ShootablePowerupComponent p : SigmaShootablepowerupList) {
			p.paint(g);
		}

		try {
			switch (BuildingMode.getInstance().getDifficulty()) {
			case "Medium":
				Thread.sleep(50);
				if (isPaused == false)
					repaint();
				break;
			case "Hard":
				Thread.sleep(25);
				if (isPaused == false)
					repaint();
				break;
			default:
				Thread.sleep(100);
				if (isPaused == false)
					repaint();
				break;
			}

		} catch (InterruptedException e) {
		}

	}

	private void checkIntersection() {
		AtomComponent removeAtom = null;
		MoleculeComponent removeMolecule = null;
		BlockerComponent removeBlocker = null;
		FallingPowerupComponent removePowerup = null;
		for (AtomComponent a : atomsShot) {
			for (MoleculeComponent m : moleculeList) {
				if (a.getType().equals(m.getType()) && a.intersectedMolecule(m.getIndex())) {
					removeAtom = a;
					removeMolecule = m;
					informationPanel.intersectionHappened(a.calculateScore());
				}
			}
		}
		for (AtomComponent a : atomsShot) {
			for (FallingPowerupComponent p : FallingPowerupList) {
				if (a.getType().equals(p.getType()) && a.intersectedPowerup(p.getIndex())) {
					removeAtom = a;
					removePowerup = p;
					informationPanel.intersectionHappened(0);
				}
			}
		}
		for (AtomComponent a : atomsShot) {
			for (BlockerComponent p : blockerList) {
				if (a.getType().equals(p.getType()) && a.intersectedBlocker(p.getIndex())) {
					removeAtom = a;
					removeBlocker = p;
					System.out.println(a.getType() + p.getType());
				}
			}
		}
		for (MoleculeComponent a : moleculeList) {
			for (BlockerComponent p : blockerList) {
				if (a.getType().equals(p.getType()) && a.intersectedBlocker(p.getIndex())) {
					removeMolecule = a;
					removeBlocker = p;
				}
			}
		}
		for (ShootablePowerupComponent a : powerupsShot) {
			for (BlockerComponent p : blockerList) {
				if (a.getType().equals(p.getType()) && a.intersectedBlocker(p.getIndex())) {
					removeBlocker = p;
				}
			}
		}
	
		for (BlockerComponent p : blockerList) {
			int index = p.getIndex();
			if (KUvidGame.getShooter().getPositionY() == KUvidGame.getBlockers().get(index).getPositionY() + 100) {
				if (Math.abs(KUvidGame.getShooter().getPositionX() - KUvidGame.getBlockers().get(index).getPositionX()) <= 100) {
					removeBlocker = p;
					informationPanel.collisionBlockerShooter();
				}
			}
		}
		if (removeBlocker != null) {
			blockerList.remove(removeBlocker);
		}
		if (removeMolecule != null && removeAtom != null) {
			atomsShot.remove(removeAtom);
			moleculeList.remove(removeMolecule);
		}

		if (removePowerup != null && removeAtom != null) {
			atomsShot.remove(removeAtom);
			FallingPowerupList.remove(removePowerup);
		}
		if (removeBlocker != null && removeMolecule != null) {
			moleculeList.remove(removeMolecule);
			blockerList.remove(removeBlocker);
		}
		if (removeBlocker != null && removeAtom != null) {
			atomsShot.remove(removeAtom);
			blockerList.remove(removeBlocker);
		}

	}

	public void pause() {
		isPaused = true;
		informationPanel.timer.cancel();
	}

	// REQUIRES: isPaused is true
	// MODIFIES: isPaused, informationPanel's gameTime and gameTimeLabel
	// EFFECTS: sets isPaused to false and restarts the timer by giving a new timer
	// into informationPanel
	public void resume() {
		if (isPaused) {
			isPaused = false;
			Timer timer = new Timer();
			informationPanel.timerStart(timer);
			repaint();
		}
	}

	// REQUIRES: AtomNo greater than 0
	// MODIFIES: the List atomsShot, the AtomComponent atomComponent
	// EFFECTS: shots the atom on the shooter, adds the shot atom to the list and
	// creates new atom to put on the shooter

	public String shot() {
		if (powerupPanel.isAlphaIsClicked()) {
			AlphaShootablepowerupList.add(new ShootablePowerupComponent(shooter, shootablePowerupIndex++, "alpha"));
			ShootablePowerupComponent shot = AlphaShootablepowerupList.get(0);
			AlphaShootablepowerupList.remove(shot);
			powerupsShot.add(shot);
			powerupsShot.get(powerupsShot.indexOf(shot)).shot();
			atomList.add(new AtomComponent(shooter, ++atomIndex));
			powerupPanel.setAlphaIsClicked(false);
			return null;
		}

		else if (powerupPanel.isBetaIsClicked()) {
			BetaShootablepowerupList.add(new ShootablePowerupComponent(shooter, shootablePowerupIndex++, "beta"));
			ShootablePowerupComponent shot = BetaShootablepowerupList.get(0);
			BetaShootablepowerupList.remove(shot);
			powerupsShot.add(shot);
			powerupsShot.get(powerupsShot.indexOf(shot)).shot();
			atomList.add(new AtomComponent(shooter, ++atomIndex));
			powerupPanel.setBetaIsClicked(false);
			return null;


		} else if (powerupPanel.isGammaIsClicked()) {
			GammaShootablepowerupList.add(new ShootablePowerupComponent(shooter, shootablePowerupIndex++, "gamma"));
			ShootablePowerupComponent shot = GammaShootablepowerupList.get(0);
			GammaShootablepowerupList.remove(shot);
			powerupsShot.add(shot);
			powerupsShot.get(powerupsShot.indexOf(shot)).shot();
			atomList.add(new AtomComponent(shooter, ++atomIndex));
			powerupPanel.setGammaIsClicked(false);
			return null;


		}

		else if (powerupPanel.isSigmaIsClicked()) {
			SigmaShootablepowerupList.add(new ShootablePowerupComponent(shooter, shootablePowerupIndex++, "sigma"));
			ShootablePowerupComponent shot = SigmaShootablepowerupList.get(0);
			SigmaShootablepowerupList.remove(shot);
			powerupsShot.add(shot);
			powerupsShot.get(powerupsShot.indexOf(shot)).shot();
			atomList.add(new AtomComponent(shooter, ++atomIndex));
			powerupPanel.setSigmaIsClicked(false);
			return null;


		} else {
			AtomComponent shot = atomList.get(atomList.size() - 1);
			name = shot.getType();
			atomList.remove(shot);
			atomsShot.add(shot);
			atomsShot.get(atomsShot.indexOf(shot)).shot();
			atomList.add(new AtomComponent(shooter, ++atomIndex));
			return name;
		}
	}

	public ShooterComponent getShooter() {
		return shooter;
	}

	public void changeAtom() {
		atomList.get(atomList.size() - 1).changeAtom();
	}

	public void decorateAtom(String shield) {
		if (shield.equals("eta")) {
			atomList.get(atomList.size() - 1).decorateAtom("eta");
		}
		if (shield.equals("lota")) {
			atomList.get(atomList.size() - 1).decorateAtom("lota");
		}
		if (shield.equals("theta")) {
			atomList.get(atomList.size() - 1).decorateAtom("theta");
		}
		if (shield.equals("zeta")) {
			atomList.get(atomList.size() - 1).decorateAtom("zeta");
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean isOver) {
		gameOver = isOver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ArrayList<MoleculeComponent> getMoleculeList() {
		return moleculeList;
	}

	public void setMoleculeList(ArrayList<MoleculeComponent> moleculeList) {
		this.moleculeList = moleculeList;
	}

	public ArrayList<AtomComponent> getAtomsShot() {
		return atomsShot;
	}

	public void setAtomsShot(ArrayList<AtomComponent> atomsShot) {
		this.atomsShot = atomsShot;
	}

	public int getAtomIndex() {
		return atomIndex;
	}

	public void setAtomIndex(int atomIndex) {
		this.atomIndex = atomIndex;
	}

	public int getMoleculeIndex() {
		return moleculeIndex;
	}

	public void setMoleculeIndex(int moleculeIndex) {
		this.moleculeIndex = moleculeIndex;
	}

	public int getFallingPowerupIndex() {
		return fallingPowerupIndex;
	}

	public void setFallingPowerupIndex(int fallingPowerupIndex) {
		this.fallingPowerupIndex = fallingPowerupIndex;
	}

	public int getShootablePowerupIndex() {
		return shootablePowerupIndex;
	}

	public void setShootablePowerupIndex(int shootablePowerupIndex) {
		this.shootablePowerupIndex = shootablePowerupIndex;
	}

	public int getBlockerIndex() {
		return blockerIndex;
	}

	public void setBlockerIndex(int blockerIndex) {
		this.blockerIndex = blockerIndex;
	}

	public static List<AtomComponent> getAtomList() {
		return atomList;
	}

	public void setAtomList(List<AtomComponent> atomList) {
		this.atomList = atomList;
	}

	public ArrayList<BlockerComponent> getBlockerList() {
		return blockerList;
	}

	public void setBlockerList(ArrayList<BlockerComponent> blockerList) {
		this.blockerList = blockerList;
	}

	public ArrayList<FallingPowerupComponent> getFallingPowerupList() {
		return FallingPowerupList;
	}

	public void setFallingPowerupList(ArrayList<FallingPowerupComponent> fallingPowerupList) {
		FallingPowerupList = fallingPowerupList;
	}

	public ArrayList<ShootablePowerupComponent> getPowerupsShot() {
		return powerupsShot;
	}

	public void setPowerupsShot(ArrayList<ShootablePowerupComponent> powerupsShot) {
		this.powerupsShot = powerupsShot;
	}

	public ArrayList<ShootablePowerupComponent> getAlphaShootablepowerupList() {
		return AlphaShootablepowerupList;
	}

	public void setAlphaShootablepowerupList(ArrayList<ShootablePowerupComponent> alphaShootablepowerupList) {
		AlphaShootablepowerupList = alphaShootablepowerupList;
	}

	public ArrayList<ShootablePowerupComponent> getGammaShootablepowerupList() {
		return GammaShootablepowerupList;
	}

	public void setGammaShootablepowerupList(ArrayList<ShootablePowerupComponent> gammaShootablepowerupList) {
		GammaShootablepowerupList = gammaShootablepowerupList;
	}

	public ArrayList<ShootablePowerupComponent> getSigmaShootablepowerupList() {
		return SigmaShootablepowerupList;
	}

	public void setSigmaShootablepowerupList(ArrayList<ShootablePowerupComponent> sigmaShootablepowerupList) {
		SigmaShootablepowerupList = sigmaShootablepowerupList;
	}

	public ArrayList<ShootablePowerupComponent> getBetaShootablepowerupList() {
		return BetaShootablepowerupList;
	}

	public void setBetaShootablepowerupList(ArrayList<ShootablePowerupComponent> betaShootablepowerupList) {
		BetaShootablepowerupList = betaShootablepowerupList;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public BlenderPanel getBlenderPanel() {
		return blenderPanel;
	}

	public void setBlenderPanel(BlenderPanel blenderPanel) {
		this.blenderPanel = blenderPanel;
	}

	public InformationPanel getInformationPanel() {
		return informationPanel;
	}

	public void setInformationPanel(InformationPanel informationPanel) {
		this.informationPanel = informationPanel;
	}

	public PowerUpPanel getPowerupPanel() {
		return powerupPanel;
	}

	public void setPowerupPanel(PowerUpPanel powerupPanel) {
		this.powerupPanel = powerupPanel;
	}

	public void setShooter(ShooterComponent shooter) {
		this.shooter = shooter;
	}

}
