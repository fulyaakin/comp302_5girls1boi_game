package domain;

public enum BlockerType {
	ALPHA("alpha-b"),
	BETA("beta-b"),
	GAMMA("gamma-b"),
	SIGMA("sigma-b");
	
	BlockerType(String name) {
		this.name = name;
		
	}
	
	private MoleculeType moleculeType;
	private String name;

	public String getName() {
		return name;
	}

	public MoleculeType getMoleculeType() {
		return moleculeType;
	}
	
	public static BlockerType generateType() {
		BlockerType[] blockerTypes = BlockerType.values();
		return blockerTypes[(int)(Math.random() * blockerTypes.length)];
	}
}
