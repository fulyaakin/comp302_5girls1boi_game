package domain;

public enum MoleculeType {
	ALPHA_1("alpha-1", AtomType.ALPHA),
	ALPHA_2("alpha-2", AtomType.ALPHA),
	BETA_1("beta-1", AtomType.BETA),
	BETA_2("beta-2", AtomType.BETA),
	GAMMA("gamma-", AtomType.GAMMA),
	SIGMA("sigma-", AtomType.SIGMA);
	
	MoleculeType(String name, AtomType atomType) {
		this.name = name;
		this.atomType = atomType;
	}
	
	private AtomType atomType;
	private String name;

	public String getName() {
		return name;
	}

	public AtomType getAtomType() {
		return atomType;
	}
	
	public static MoleculeType generateType(int i) {
		MoleculeType[] moleculeTypes = MoleculeType.values();
		switch(i) {
		case 0:
			return moleculeTypes[(int)(Math.random() * moleculeTypes.length)];
		case 1:
			return moleculeTypes[0];
		case 2:
			return moleculeTypes[1];
		case 3:
			return moleculeTypes[2];
		case 4: 
			return moleculeTypes[3];
		case 5: 
			return moleculeTypes[4];
		case 6: 
			return moleculeTypes[5];
		default:
			return moleculeTypes[(int)(Math.random() * moleculeTypes.length)];
		}
	}

	
}
