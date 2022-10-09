package defeatedcrow.hac.core.worldgen.vein;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.world.level.block.Block;

public class VeinTable {

	public final String veinName;
	public final int generateProbability;
	public final int altitude;
	public final int radius;
	public final boolean isDeep;
	public final String layerStone;
	public final List<OreSetData> entries = Lists.newArrayList();

	public VeinTable(String name, int p, int a, int r, boolean deep, String layer) {
		veinName = name;
		generateProbability = p;
		altitude = a;
		radius = r;
		isDeep = deep;
		layerStone = layer;
	}

	public EnumVein getVeinType() {
		return EnumVein.getType(veinName);
	}

	public VeinTable setData(OreSet data) {
		entries.add(new OreSetData(data));
		return this;
	}

	public Block getLayer() {
		return DCUtil.getBlockFromString(layerStone);
	}

	public List<OreSet> getOreSet() {
		List<OreSet> ret = Lists.newArrayList();
		for (OreSetData data : entries) {
			ret.add(data.getOreSet());
		}
		return ret;
	}

	public int getWeightCount() {
		int i = 0;
		for (OreSetData data : entries) {
			i += data.getOreSet().getWeight();
		}
		return i;
	}

}
