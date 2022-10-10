package defeatedcrow.hac.api.crop;

import net.minecraft.world.item.Rarity;

public enum CropTier {
	WILD(true, 100, Rarity.COMMON),
	COMMON(false, 75, Rarity.COMMON),
	RARE(false, 20, Rarity.UNCOMMON),
	EPIC(false, 5, Rarity.RARE);

	private final boolean isWild;
	private final int mutationChance;
	private final Rarity rarity;

	private CropTier(boolean a, int chance, Rarity r) {
		isWild = a;
		mutationChance = chance;
		rarity = r;
	}

	public boolean canUseBonemeal() {
		return !isWild;
	}

	public int getMutationChance() {
		return mutationChance;
	}

	public Rarity getRarity() {
		return rarity;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

}
