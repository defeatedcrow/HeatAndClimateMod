package defeatedcrow.hac.core.worldgen.vein;

import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.world.level.block.Block;

public class OreSetData {

	private final int weight;
	private final String ore;
	private final String ore2;
	private final String ore3;
	private final boolean hasSecondary;
	private final boolean hasTertiary;
	private final int probability1;
	private final int probability2;

	public OreSetData(int weightIn, String oreIn, String secondOreIn, int chanceIn1, String tertOreIn, int chanceIn2) {
		weight = weightIn;
		ore = oreIn;
		ore2 = secondOreIn;
		ore3 = tertOreIn;
		hasSecondary = (secondOreIn != null);
		hasTertiary = (tertOreIn != null);
		probability1 = chanceIn1;
		probability2 = chanceIn2;
	}

	public OreSetData(OreSet oreIn) {
		weight = oreIn.getWeight();
		ore = DCUtil.getBlockRegName(oreIn.getOre());
		ore2 = DCUtil.getBlockRegName(oreIn.getSecondOre());
		ore3 = DCUtil.getBlockRegName(oreIn.getTertOre());
		hasSecondary = oreIn.hasSecondOre();
		hasTertiary = oreIn.hasTertOre();
		probability1 = oreIn.getSecondChance();
		probability2 = oreIn.getTertiaryChance();
	}

	public OreSet getOreSet() {
		int w = weight;
		Block o1 = DCItemUtil.getBlockFromString(ore);
		Block o2 = DCItemUtil.getBlockFromString(ore2);
		Block o3 = DCItemUtil.getBlockFromString(ore3);
		boolean b1 = hasSecondary;
		boolean b2 = hasTertiary;
		int p1 = probability1;
		int p2 = probability2;
		return new OreSet(w, o1, o2, p1, o3, p2);
	}

}
