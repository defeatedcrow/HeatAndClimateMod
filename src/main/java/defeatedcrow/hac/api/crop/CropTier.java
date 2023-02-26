package defeatedcrow.hac.api.crop;

import net.minecraft.world.item.Rarity;

public enum CropTier {
	WILD(true, 100, Rarity.COMMON, -1),
	COMMON(false, 70, Rarity.UNCOMMON, 0),
	RARE(false, 25, Rarity.RARE, 1),
	EPIC(false, 5, Rarity.EPIC, 2);

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
