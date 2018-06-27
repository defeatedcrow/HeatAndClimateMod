package defeatedcrow.hac.main.worldgen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.api.orevein.OreSet;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.init.Blocks;

public class OreSetDC extends OreSet {

	private final int weight;
	private final BlockSet ore;
	private final BlockSet ore2;
	private final boolean hasSec;
	private final int probability;

	public OreSetDC(int weightIn, @Nonnull BlockSet oreIn, @Nullable BlockSet secondOreIn, int chanceIn,
			@Nonnull BlockSet sub) {
		weight = weightIn;
		if (WorldGenConfig.disables.contains(oreIn)) {
			ore = sub;
		} else {
			ore = oreIn;
		}
		if (WorldGenConfig.disables.contains(secondOreIn)) {
			ore2 = sub;
		} else {
			ore2 = secondOreIn;
		}
		hasSec = (secondOreIn != null);
		probability = chanceIn;
	}

	public OreSetDC(int weightIn, @Nonnull BlockSet oreIn, @Nullable BlockSet secondOreIn, int chanceIn) {
		weight = weightIn;
		if (WorldGenConfig.disables.contains(oreIn)) {
			ore = new BlockSet(Blocks.STONE, 0);
		} else {
			ore = oreIn;
		}
		if (WorldGenConfig.disables.contains(secondOreIn)) {
			ore2 = new BlockSet(Blocks.STONE, 0);
		} else {
			ore2 = secondOreIn;
		}
		hasSec = (secondOreIn != null);
		probability = chanceIn;
	}

	public OreSetDC(int weightIn, @Nonnull BlockSet oreIn, @Nonnull BlockSet sub) {
		weight = weightIn;
		if (WorldGenConfig.disables.contains(oreIn)) {
			ore = sub;
		} else {
			ore = oreIn;
		}
		ore2 = null;
		hasSec = false;
		probability = 0;
	}

	public OreSetDC(int weightIn, @Nonnull BlockSet oreIn) {
		weight = weightIn;
		if (WorldGenConfig.disables.contains(oreIn)) {
			ore = new BlockSet(Blocks.STONE, 0);
		} else {
			ore = oreIn;
		}
		ore2 = null;
		hasSec = false;
		probability = 0;
	}

	@Override
	public BlockSet getOre() {
		return ore;
	}

	@Override
	public BlockSet getSecondOre() {
		return ore2;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int getSecondChance() {
		return probability;
	}

	@Override
	public boolean hasSecondOre() {
		return hasSec;
	}

	@Override
	public int hashCode() {
		int i1 = ore.hashCode();
		int i2 = ore2.hashCode();
		return weight + i1 + i2 + probability * 13;
	}

}
