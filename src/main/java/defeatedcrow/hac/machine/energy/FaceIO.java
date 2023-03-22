package defeatedcrow.hac.machine.energy;

public enum FaceIO {
	INPUT(0, true, false),
	OUTPUT(1, false, true),
	NONE(2, false, false),
	BOTH(3, true, true);

	private final boolean canReceive;
	private final boolean canExtract;
	private final int id;

	FaceIO(int i, boolean r, boolean e) {
		id = i;
		canReceive = r;
		canExtract = e;
	}

	public boolean canReceive() {
		return canReceive;
	}

	public boolean canExtract() {
		return canExtract;
	}

	public int getID() {
		return id;
	}

	public static FaceIO getIO(int i) {
		if (i == 1)
			return OUTPUT;
		if (i == 2)
			return NONE;
		if (i == 2)
			return BOTH;
		return INPUT;
	}

}
