package defeatedcrow.hac.api.crop;

public enum ForageType {
	EMPTY(0, false,false),
	HAY(1, false, true),
	SEED(2, true, false),
	COMPOUND(3, true, true);

	private final boolean bird;
	private final boolean hooves;
	private final int id;

	private ForageType(int i, boolean a, boolean b) {
		bird = a;
		hooves = b;
		id = i;
	}
	
	public boolean suitableForgBirds() {
		return bird;
	}
	
	public boolean suitableForUngulate() {
		return hooves;
	}
	
	public int getId() {
		return id;
	}

	public static ForageType getFromName(String name) {
		if (name != null)
			for (ForageType target : ForageType.values()) {
				if (target.toString().equalsIgnoreCase(name)) {
					return target;
				}
			}
		return EMPTY;
	}
	
	public static ForageType getFromId(int i) {
		switch(i){
		case 0: return EMPTY;
		case 1: return HAY;
		case 2: return SEED;
		case 3: return COMPOUND;
		default: return EMPTY;
		}
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

}
