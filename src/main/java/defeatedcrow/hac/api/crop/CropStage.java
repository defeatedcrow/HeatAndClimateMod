package defeatedcrow.hac.api.crop;

/** 作物の成長段階を表す */
public enum CropStage {
	GROUND(0, true, false),
	YOUNG(1, true, false),
	FLOWER(2, true, false),
	GROWN(3, false, true),
	DEAD(4, false, false),
	SAPLING(5, true, false);

	private final boolean canBonemeal;
	private final boolean canHarvest;
	public final int id;

	private CropStage(int i, boolean a, boolean b) {
		id = i;
		canBonemeal = a;
		canHarvest = b;
	}

	public boolean canUseBonemeal() {
		return canBonemeal;
	}

	public boolean canHarvestCrop() {
		return canHarvest;
	}

	public CropStage getNextStage() {
		if (this == GROUND) {
			return YOUNG;
		} else if (this == YOUNG) {
			return FLOWER;
		} else if (this == FLOWER) {
			return GROWN;
		} else {
			return DEAD;
		}
	}

}
