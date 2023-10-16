package defeatedcrow.hac.machine.energy;

public enum FaceIO {
	NONE(0, false, false),
	OUTPUT(1, true, false),
	INPUT(2, false, true),
	PIPE(3, true, true);

	private final boolean canReceive;
	private final boolean canExtract;
	private final int id;

	FaceIO(int i, boolean e, boolean r) {
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
			return INPUT;
		if (i == 3)
			return PIPE;
		return NONE;
	}

}
