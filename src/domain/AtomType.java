package domain;

public enum AtomType {
	ALPHA("alpha"), BETA("beta"), GAMMA("gamma"), SIGMA("sigma");
	
	AtomType(String name) {
		this.name = name;
	}
	
	String name;

	public String getName() {
		return name;
	}
	
	public static AtomType generateType(int i) {
		AtomType[] atomTypes = AtomType.values();
		switch(i) {
		case 0:
			return atomTypes[(int)(Math.random() * atomTypes.length)];
		case 1:
			return atomTypes[0];
		case 2:
			return atomTypes[1];
		case 3: 
			return atomTypes[2];
		case 4: 
			return atomTypes[3];
		default:
			return atomTypes[(int)(Math.random() * atomTypes.length)];
		}
	}
}
