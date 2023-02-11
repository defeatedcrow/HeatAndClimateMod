package defeatedcrow.hac.api.crop;

import net.minecraft.world.item.Rarity;

public enum CropTier {
	WILD(true, 100, Rarity.COMMON, 1),
	COMMON(false, 70, Rarity.COMMON, 2),
	RARE(false, 25, Rarity.UNCOMMON, 3),
	EPIC(false, 5, Rarity.RARE, 4);

	private final boolean isWild;
	private final int mutationChance;
	private final Rarity rarity;
	private final int taste;

	private CropTier(boolean a, int chance, Rarity r, int i) {
		isWild = a;
		mutationChance = chance;
		rarity = r;
		taste = i;
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

	public int getTaste() {
		return taste;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

}
