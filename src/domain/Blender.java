package domain;

public class Blender {

	private int alphaAtoms;
	private int betaAtoms;
	private int gammaAtoms;
	private int sigmaAtoms;
	private int source;
	private int produce;

	public Blender() {
		alphaAtoms = BuildingMode.getInstance().getAlphaAtoms();
		betaAtoms = BuildingMode.getInstance().getAlphaAtoms();
		gammaAtoms = BuildingMode.getInstance().getAlphaAtoms();
		sigmaAtoms = BuildingMode.getInstance().getAlphaAtoms();
	}

	// REQUIRES:source and produce number
	// MODIFIES: changes atom number
	// EFFECTS: updates the atom number according to atom and produce

	public void blendAtom(int source, int produce) {
		// Alpha
		if (source == 1) {
			if (produce == 2) {
				if (alphaAtoms >= 2) {
					alphaAtoms -= 2;
					betaAtoms += 1;
				}
			}
			if (produce == 3) {
				if (alphaAtoms >= 3) {
					alphaAtoms -= 3;
					gammaAtoms += 1;
				}
			}
			if (produce == 4) {
				if (alphaAtoms >= 4) {
					alphaAtoms -= 4;
					sigmaAtoms += 1;
				}
			}
		}
		// Beta
		if (source == 2) {
			if (produce == 1) {
				if (betaAtoms >= 1) {
					betaAtoms -= 1;
					alphaAtoms += 2;
				}
			}
			if (produce == 3) {
				if (betaAtoms >= 2) {
					betaAtoms -= 2;
					gammaAtoms += 1;
				}
			}
			if (produce == 4) {
				if (betaAtoms >= 3) {
					betaAtoms -= 3;
					sigmaAtoms += 1;
				}
			}
		}
		// Gamma
		if (source == 3) {
			if (produce == 4) {
				if (gammaAtoms >= 2) {
					gammaAtoms -= 2;
					sigmaAtoms += 1;
				}
			}
			if (produce == 1) {
				if (gammaAtoms >= 1) {
					gammaAtoms -= 1;
					alphaAtoms += 3;
				}
			}
			if (produce == 2) {
				if (gammaAtoms >= 1) {
					gammaAtoms -= 1;
					betaAtoms += 2;
				}
			}
		}
		// Sigma
		if (source == 4) {
			if (produce == 2) {
				if (sigmaAtoms >= 1) {
					sigmaAtoms -= 1;
					betaAtoms += 3;
				}
			}
			if (produce == 3) {
				if (sigmaAtoms >= 1) {
					sigmaAtoms -= 1;
					gammaAtoms += 2;
				}
			}
			if (produce == 1) {
				if (sigmaAtoms >= 1) {
					sigmaAtoms -= 1;
					alphaAtoms += 4;
				}
			}
		}
	}

	public void setAtom(String type) {
		switch (type) {
		case "ALPHA":
			alphaAtoms--;
			break;
		case "BETA":
			betaAtoms--;
			break;
		case "GAMMA":
			gammaAtoms--;
			break;
		case "SIGMA":
			sigmaAtoms--;
			break;

		}
	}

	public int getAlphaAtoms() {
		return alphaAtoms;
	}

	public int getBetaAtoms() {
		return betaAtoms;
	}

	public int getGammaAtoms() {
		return gammaAtoms;
	}

	public int getSigmaAtoms() {
		return sigmaAtoms;
	}

	public void setAlphaAtoms(int alphaAtoms) {
		this.alphaAtoms = alphaAtoms;
	}

	public void setBetaAtoms(int betaAtoms) {
		this.betaAtoms = betaAtoms;
	}

	public void setGammaAtoms(int gammaAtoms) {
		this.gammaAtoms = gammaAtoms;
	}

	public void setSigmaAtoms(int sigmaAtoms) {
		this.sigmaAtoms = sigmaAtoms;
	}

}
