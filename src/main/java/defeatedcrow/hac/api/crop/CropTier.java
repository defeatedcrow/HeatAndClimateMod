package defeatedcrow.hac.api.crop;

public enum CropTier {
	WILD(true, 100),
	COMMON(false, 75),
	RARE(false, 20),
	EPIC(false, 5);

	private final boolean isWild;
	private final int mutationChance;

	private CropTier(boolean a, int chance) {
		isWild = a;
		mutationChance = chance;
	}

	public boolean canUseBonemeal() {
		return !isWild;
	}

	public int getMutationChance() {
		return mutationChance;
	}

}
