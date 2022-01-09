package domain;

public enum PowerupType {
	ALPHA("alpha"), BETA("beta"), GAMMA("gamma"), SIGMA("sigma");
	
	PowerupType(String name) {
		this.name = name;
	}
	
	String name;

	public String getName() {
		return name;
	}
	//for getting type
	public static PowerupType generateType() {
		PowerupType[] atomTypes = PowerupType.values();
		return atomTypes[(int)(Math.random() * atomTypes.length)];
	}
}
