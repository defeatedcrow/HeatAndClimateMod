package defeatedcrow.hac.core.worldgen.vein;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;

public class OreSet {

	private final int weight;
	private final Block ore;
	private final Block ore2;
	private final Block ore3;
	private final boolean hasSec;
	private final boolean hasTert;
	private final int probability1;
	private final int probability2;

	public OreSet(int weightIn, @Nonnull Block oreIn, @Nullable Block secondOreIn, int chanceIn1, @Nullable Block tertOreIn, int chanceIn2) {
		weight = weightIn;
		ore = oreIn;
		ore2 = secondOreIn;
		ore3 = tertOreIn;
		hasSec = (secondOreIn != null);
		hasTert = (tertOreIn != null);
		probability1 = chanceIn1;
		probability2 = chanceIn2;
	}

	public OreSet(int weightIn, @Nonnull Block oreIn, @Nullable Block secondOreIn, int chanceIn1) {
		weight = weightIn;
		ore = oreIn;
		ore2 = secondOreIn;
		ore3 = oreIn;
		hasSec = (secondOreIn != null);
		hasTert = false;
		probability1 = chanceIn1;
		probability2 = 0;
	}

	public OreSet(int weightIn, @Nonnull Block oreIn) {
		weight = weightIn;
		ore = oreIn;
		ore2 = oreIn;
		ore3 = oreIn;
		hasSec = false;
		hasTert = false;
		probability1 = 0;
		probability2 = 0;
	}

	public Block getOre() {
		return ore;
	}

	public Block getSecondOre() {
		return ore2;
	}

	public Block getTertOre() {
		return ore3;
	}

	public int getWeight() {
		return weight;
	}

	public int getSecondChance() {
		return probability1;
	}

	public int getTertiaryChance() {
		return probability2;
	}

	public boolean hasSecondOre() {
		return hasSec;
	}

	public boolean hasTertOre() {
		return hasTert;
	}

	@Override
	public int hashCode() {
		int i1 = ore.hashCode();
		int i2 = ore2.hashCode();
		int i3 = ore3.hashCode();
		return weight + i1 * 3 + i2 * 7 + i3 * 11 + probability1 * 13 + probability2 * 17;
	}

}
