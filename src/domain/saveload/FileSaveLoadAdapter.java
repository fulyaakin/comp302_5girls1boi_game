package domain.saveload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import domain.UnshieldedAtom;
import domain.AtomType;
import domain.Blender;
import domain.BuildingMode;
import domain.FallingPowerup;
import domain.KUvidGame;
import domain.Molecule;
import domain.MoleculeType;
import domain.Player;
import domain.ReactionBlocker;
import domain.Shooter;

public class FileSaveLoadAdapter implements SaveLoadAdapter {


	private String username = "save";

	@Override
	public void save() {
		try {
			File file = new File("./saved_files/" + username + ".txt");
			Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			bufferedWriter.write("UnshieldedAtoms: \n");
			//type, posx, posy, width, height, isshot
			for (UnshieldedAtom a : (List<UnshieldedAtom>) KUvidGame.gameObjects.get("atoms")) {
				bufferedWriter.write(a.getType() + " " + a.getPositionX() + " " + a.getPositionY()
				+ " " + a.getWidth() + " " + a.getHeight() + " " + a.isShot() + "\n");
			}
			bufferedWriter.write("Molecules: \n");
			//type, posx, posy, width, height, iszigzag, direction
			for (Molecule a : (List<Molecule>) KUvidGame.gameObjects.get("molecules")) {
				bufferedWriter.write(a.getType() + " " + a.getPositionX() + " " + a.getPositionY() 
				+ " " + a.getWidth() + " " + a.getHeight() + " " + a.isZigZag() + " " + a.getDirection() + "\n");
			}
			bufferedWriter.write("Shooter: \n");
			Shooter s = (Shooter) KUvidGame.gameObjects.get("shooter");
			//angle, posx, posy, width, height
			bufferedWriter.write(s.getAngle() + " " + s.getPositionX() + " " + s.getPositionY()
			+ " " + s.getWidth() + " " + s.getHeight() + "\n");
			
			//Player
			//username,score,time,health
			bufferedWriter.write("Player: \n");
			Player p = (Player) KUvidGame.gameObjects.get("player");
			bufferedWriter.write(p.getScore() + " " + p.getTime()
			+ " " + p.getHealthPoints() + "\n");
			
			//Blender
			//alpha,beta,gamma,sigma
			bufferedWriter.write("Blender: \n");
			Blender b = (Blender) KUvidGame.gameObjects.get("blender");
			bufferedWriter.write(b.getAlphaAtoms() + " " + b.getBetaAtoms() + " " + b.getGammaAtoms()
			+ " " + b.getSigmaAtoms() + "\n");
			
			//BuildingMode
			//alpha,beta,gamma,sigma,UnshieldedAtom,molecule,blocker,powerup,difficulty,spinning,gametime
			bufferedWriter.write("BuildingMode: \n");
			BuildingMode bm = (BuildingMode) KUvidGame.gameObjects.get("buildingMode");
			bufferedWriter.write(bm.getAlphaAtoms() + " " + bm.getBetaAtoms() + " " + bm.getGamaAtoms()
			+ " " + bm.getSigmaAtoms() + " " + bm.getAtomNumber() + " " + bm.getMoleculeNumber() + " " + 
					bm.getBlockerNumber() + " " + bm.getPowerupNumber() + " " + bm.getDifficulty() + " " + 
					bm.getSpinningAnimation() + " " + bm.getGameTime() + "\n");
			
			bufferedWriter.write("UnshieldedAtoms Shot: \n");
			//type, posx, posy, width, height, isshot
			for (UnshieldedAtom a : (List<UnshieldedAtom>) KUvidGame.gameObjects.get("atoms")) {
				if(a.isShot()) {
				bufferedWriter.write(a.getType() + " " + a.getPositionX() + " " + a.getPositionY()
				+ " " + a.getWidth() + " " + a.getHeight() + " " + a.isShot() + "\n");
				}
			}
			bufferedWriter.write("Blockers: \n");
//			//type, posx, posy, width, height, iszigzag, direction
//			for (ReactionBlocker a : (List<ReactionBlocker>) KUvidGame.gameObjects.get("blockers")) {
//				bufferedWriter.write(a.getType() + " " + a.getPositionX() + " " + a.getPositionY() 
//				+ " " + a.getWidth() + " " + a.getHeight() + " " + a.isZigZag() + " " + a.getDirection() + "\n");
//			}
//			bufferedWriter.write("Falling Powerups: \n");
//			//type, posx, posy, width, height, iszigzag, direction
//			for (FallingPowerup a : (List<FallingPowerup>) KUvidGame.gameObjects.get("fallingpowerups")) {
//				bufferedWriter.write(a.getTypeName() + " " + a.getPositionX() + " " + a.getPositionY() 
//				+ " " + a.getWidth() + " " + a.getHeight() + "\n");
//			}
//			bufferedWriter.write("Shootable Powerups: \n");
//			//type, posx, posy, width, height, iszigzag, direction
//			for (ReactionBlocker a : (List<ReactionBlocker>) KUvidGame.gameObjects.get("shootablepowerups")) {
//				bufferedWriter.write(a.getType() + " " + a.getPositionX() + " " + a.getPositionY() 
//				+ " " + a.getWidth() + " " + a.getHeight() + "\n");
//			} 
			bufferedWriter.write("END");
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		KUvidGame.gameObjects = new HashMap<String, Object>();
		List<UnshieldedAtom> UnshieldedAtoms = new ArrayList<>();
		List<String> lines = new ArrayList<>();
		List<Molecule> molecules = new ArrayList<>();
		List<UnshieldedAtom> UnshieldedAtomsShot = new ArrayList<>();
		Shooter shooter;
		Player player;
		Blender blender;
		BuildingMode buildingMode = BuildingMode.getInstance();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./saved_files/" + username + ".txt"));
			String line;
			line = reader.readLine();
			while(line != null) {
				lines.add(line);
				line = reader.readLine();
			}

			int UnshieldedAtomIndex = lines.indexOf("UnshieldedAtoms: ");
			int moleculeIndex = lines.indexOf("Molecules: ");
			int shooterIndex = lines.indexOf("Shooter: ");
			int playerIndex = lines.indexOf("Player: ");
			int blenderIndex = lines.indexOf("Blender: ");
			int buildingModeIndex = lines.indexOf("BuildingMode: ");
			int UnshieldedAtomShotIndex = lines.indexOf("UnshieldedAtoms Shot: ");
			int endIndex = lines.indexOf("END");

			
			//UnshieldedAtoms
			for(int i=UnshieldedAtomIndex+1; i<moleculeIndex; i++) {
				String[] a = lines.get(i).split(" ");
				UnshieldedAtom newUnshieldedAtom = new UnshieldedAtom(Integer.parseInt(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]));
				newUnshieldedAtom.setShot(Boolean.parseBoolean(a[5]));
				switch(a[0]) {
				case "ALPHA":
					newUnshieldedAtom.setType(AtomType.generateType(1)); 
					break;
				case "BETA":
					newUnshieldedAtom.setType(AtomType.generateType(2)); 
					break;
				case "GAMMA":
					newUnshieldedAtom.setType(AtomType.generateType(3)); 
					break;
				case "SIGMA":
					newUnshieldedAtom.setType(AtomType.generateType(4)); 
					break;
				}
				newUnshieldedAtom.setStartX(Integer.parseInt(a[1]));
				newUnshieldedAtom.setStartY(Integer.parseInt(a[2]));
				UnshieldedAtoms.add(newUnshieldedAtom);
			}
			
			//Molecules
			/*for(int i=moleculeIndex+1; i<shooterIndex; i++) {
				String[] m = lines.get(i).split(" ");
				Molecule newMolecule = new Molecule(Integer.parseInt(m[1]), Integer.parseInt(m[2]), Integer.parseInt(m[3]), Integer.parseInt(m[4]));
				newMolecule.setZigZag(Boolean.parseBoolean(m[5]));
				newMolecule.setDirection(Integer.parseInt(m[6]));
				switch(m[0]) {
				case "ALPHA_1":
					newMolecule.setType(MoleculeType.generateType(1)); 
					break;
				case "ALPHA_2":
					newMolecule.setType(MoleculeType.generateType(2)); 
					break;
				case "BETA_1":
					newMolecule.setType(MoleculeType.generateType(3)); 
					break;
				case "BETA_2":
					newMolecule.setType(MoleculeType.generateType(4)); 
					break;
				case "GAMMA":
					newMolecule.setType(MoleculeType.generateType(5)); 
					break;
				case "SIGMA":
					newMolecule.setType(MoleculeType.generateType(5)); 
					break;
				}
				molecules.add(newMolecule);
			}*/
			
			//Shooter
			String[] s = lines.get(shooterIndex+1).split(" ");
			shooter = new Shooter(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), Integer.parseInt(s[0]),
					KUvidGame.WIDTH / 5 * 4, (int) KUvidGame.WIDTH);
			
			//Player
			String[] p = lines.get(playerIndex+1).split(" ");
			player = new Player();
			player.setScore(Double.parseDouble(p[1]));
			player.setTime(Double.parseDouble(p[2]));
			player.setHealthPoints(Integer.parseInt(p[3]));

			//Blender
			String[] b = lines.get(blenderIndex+1).split(" ");
			blender = new Blender();
			blender.setAlphaAtoms(Integer.parseInt(b[0]));
			blender.setBetaAtoms(Integer.parseInt(b[1]));
			blender.setGammaAtoms(Integer.parseInt(b[2]));
			blender.setSigmaAtoms(Integer.parseInt(b[3]));
			
			//BuildingMode
			String[] bm = lines.get(buildingModeIndex+1).split(" ");
			buildingMode.setAlphaAtoms(Integer.parseInt(bm[0]));
			buildingMode.setBetaAtoms(Integer.parseInt(bm[1]));
			buildingMode.setGamaAtoms(Integer.parseInt(bm[2]));
			buildingMode.setSigmaAtoms(Integer.parseInt(bm[3]));
			buildingMode.setAtomNumber(Integer.parseInt(bm[4]));
			buildingMode.setMoleculeNumber(Integer.parseInt(bm[5]));
			buildingMode.setBlockerNumber(Integer.parseInt(bm[6]));
			buildingMode.setPowerupNumber(Integer.parseInt(bm[7]));
			buildingMode.setDifficulty(bm[8]);
			buildingMode.setSpinningAnimation(bm[9]);
			buildingMode.setGameTime(Double.parseDouble(bm[10]));
			buildingMode.setUsername(bm[11]);
			
			//UnshieldedAtoms Shot
			for(int i=UnshieldedAtomShotIndex+1; i<endIndex; i++) {
				String[] a = lines.get(i).split(" ");
				UnshieldedAtom newUnshieldedAtom = new UnshieldedAtom(Integer.parseInt(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]));
				newUnshieldedAtom.setShot(Boolean.parseBoolean(a[5]));
				switch(a[0]) {
				case "ALPHA":
					newUnshieldedAtom.setType(AtomType.generateType(1)); 
					break;
				case "BETA":
					newUnshieldedAtom.setType(AtomType.generateType(2)); 
					break;
				case "GAMMA":
					newUnshieldedAtom.setType(AtomType.generateType(3)); 
					break;
				case "SIGMA":
					newUnshieldedAtom.setType(AtomType.generateType(4)); 
					break;
				}
				UnshieldedAtomsShot.add(newUnshieldedAtom);
			}
			
			
			KUvidGame.gameObjects.put("UnshieldedAtoms", UnshieldedAtoms);
			KUvidGame.gameObjects.put("molecules", molecules);
			KUvidGame.gameObjects.put("shooter", shooter);
			KUvidGame.gameObjects.put("player", player);
			KUvidGame.gameObjects.put("blender", blender);
			KUvidGame.gameObjects.put("BuildingMode", buildingMode);
			KUvidGame.gameObjects.put("UnshieldedAtomsShot", UnshieldedAtomsShot);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		




		/*	System.out.println("UnshieldedAtoms: ");
		for (UnshieldedAtom a : (List<UnshieldedAtom>) gameObjects.get("UnshieldedAtoms")) {
			System.out.println("Type: " + a.type + "\nPosition X: " + a.positionX + "\nPosition Y: " + a.positionY 
					+ "\nWidth: " + a.width + "\nHeight: " + a.height + "\nIsShot: " + a.isShot + "\n");
		}
		System.out.println("Molecules: ");
		for (Molecule a : (List<Molecule>) gameObjects.get("molecules")) {
			System.out.println("Type: " + a.type + "\nPosition X: " + a.positionX + "\nPosition Y: " + a.positionY 
					+ "\nWidth: " + a.width + "\nHeight: " + a.height + "\nIsZigZag: " + a.isZigZag + "\n");
		}
		System.out.println("Shooter: ");
		Shooter s = (Shooter) gameObjects.get("shooter");
		System.out.println("Angle: " + s.angle + "\nPosition X: " + s.positionX + "\nPosition Y: " + s.positionY
				  + "\nWidth: " + s.width + "\nHeight: " + s.height + "\nLineLength: " + s.lineLength);	*/

	}

}
