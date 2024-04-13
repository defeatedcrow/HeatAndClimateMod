package defeatedcrow.hac.core.worldgen.vein;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.world.level.block.Block;

public class VeinTable {

	public final String veinName;
	public final String color;
	public final int generateProbability;
	public final int altitude;
	public final int radius;
	public final boolean isDeep;
	public final String layerStone;
	public final List<OreSetData> entries = Lists.newArrayList();

	public VeinTable(String colorIn, int p, int a, int r, boolean deep, String layer) {
		veinName = colorIn;
		color = colorIn;
		generateProbability = p;
		altitude = a;
		radius = r;
		isDeep = deep;
		layerStone = layer;
	}

	public VeinTable(String name, String colorIn, int p, int a, int r, boolean deep, String layer) {
		veinName = (name == null) ? colorIn : name;
		color = colorIn;
		generateProbability = p;
		altitude = a;
		radius = r;
		isDeep = deep;
		layerStone = layer;
	}

	public EnumVein getVeinType() {
		return EnumVein.getType(getColor());
	}

	public VeinTable setData(OreSet data) {
		entries.add(new OreSetData(data, getLayer()));
		return this;
	}

	public Block getLayer() {
		return DCItemUtil.getBlockFromString(layerStone);
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

	public String getColor() {
		return color == null ? veinName : color;
	}

	public String getName() {
		return veinName == null ? color : veinName;
	}
}
