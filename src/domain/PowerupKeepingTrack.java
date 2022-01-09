package domain;

public class PowerupKeepingTrack {
	
	private int alphaPowerups;
	private int betaPowerups;
	private int gamaPowerups;
	private int sigmaPowerups;
	
	public PowerupKeepingTrack() {
		
		BuildingMode starterData = BuildingMode.getInstance();
		this.alphaPowerups = starterData.getPowerupNumber();
		this.betaPowerups = starterData.getPowerupNumber();
		this.gamaPowerups = starterData.getPowerupNumber();
		this.sigmaPowerups = starterData.getPowerupNumber();
	}

	public int getAlphaPowerups() {
		return alphaPowerups;
	}

	public void setAlphaPowerups(int alphaPowerups) {
		this.alphaPowerups = alphaPowerups;
	}

	public int getBetaPowerups() {
		return betaPowerups;
	}

	public void setBetaPowerups(int betaPowerups) {
		this.betaPowerups = betaPowerups;
	}

	public int getGamaPowerups() {
		return gamaPowerups;
	}

	public void setGamaPowerups(int gamaPowerups) {
		this.gamaPowerups = gamaPowerups;
	}

	public int getSigmaPowerups() {
		return sigmaPowerups;
	}

	public void setSigmaPowerups(int sigmaPowerups) {
		this.sigmaPowerups = sigmaPowerups;
	}
	
	
	
}
