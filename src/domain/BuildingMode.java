package domain;

public class BuildingMode {

	private int atomNumber;
	private int moleculeNumber;
	private int blockerNumber;
	private int powerupNumber;
	private String difficulty;
	private String spinningAnimation;
	private int alphaAtoms;
	private int betaAtoms;
	private int sigmaAtoms;
	private int gammaAtoms;
	private double gameTime;
	private int shieldNumber;
	private int etaNumber;
	private int lotaNumber;
	private int thetaNumber;
	private int zetaNumber;
	private String saveOption;
	private String username;

	private static BuildingMode instance = new BuildingMode();

	
	private BuildingMode() {
	}
	
	public static BuildingMode getInstance() {
		return instance;
	}
	
	public int getAtomNumber() {
		return atomNumber;
	}

	public void setAtomNumber(int atomNumber) {
		this.atomNumber = atomNumber;
	}

	public int getMoleculeNumber() {
		return moleculeNumber;
	}

	public void setMoleculeNumber(int moleculeNumber) {
		this.moleculeNumber = moleculeNumber;
	}

	public int getBlockerNumber() {
		return blockerNumber;
	}

	public void setBlockerNumber(int blockerNumber) {
		this.blockerNumber = blockerNumber;
	}

	public int getPowerupNumber() {
		return powerupNumber;
	}

	public void setPowerupNumber(int powerupNumber) {
		this.powerupNumber = powerupNumber;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getSpinningAnimation() {
		return spinningAnimation;
	}

	public void setSpinningAnimation(String spinningAnimation) {
		this.spinningAnimation = spinningAnimation;
	}

	public int getAlphaAtoms() {
		return alphaAtoms;
	}

	public void setAlphaAtoms(int alphaAtoms) {
		this.alphaAtoms = alphaAtoms;
	}

	public int getBetaAtoms() {
		return betaAtoms;
	}

	public void setBetaAtoms(int betaAtoms) {
		this.betaAtoms = betaAtoms;
	}

	public int getSigmaAtoms() {
		return sigmaAtoms;
	}

	public void setSigmaAtoms(int sigmaAtoms) {
		this.sigmaAtoms = sigmaAtoms;
	}

	public int getGamaAtoms() {
		return gammaAtoms;
	}

	public void setGamaAtoms(int gammaAtoms) {
		this.gammaAtoms = gammaAtoms;
	}
	
	public double getGameTime() {
		return gameTime;
	}

	public void setGameTime(double gameTime) {
		this.gameTime = gameTime;
	}

	public int getShieldNumber() {
		return shieldNumber;
	}

	public void setShieldNumber(int shieldNumber) {
		this.shieldNumber = shieldNumber;
	}

	public int getEtaNumber() {
		return etaNumber;
	}

	public void setEtaNumber(int etaNumber) {
		this.etaNumber = etaNumber;
	}

	public int getLotaNumber() {
		return lotaNumber;
	}

	public void setLotaNumber(int lotaNumber) {
		this.lotaNumber = lotaNumber;
	}

	public int getThetaNumber() {
		return thetaNumber;
	}

	public void setThetaNumber(int thetaNumber) {
		this.thetaNumber = thetaNumber;
	}

	public int getZetaNumber() {
		return zetaNumber;
	}

	public void setZetaNumber(int zetaNumber) {
		this.zetaNumber = zetaNumber;
	}
	
	public String getSaveOption() {
		return saveOption;
	}
	public void setSaveOption(String text) {
		this.saveOption=text;
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String text) {
		this.username=text;
		
	}

}
