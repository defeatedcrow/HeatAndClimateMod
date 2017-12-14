package defeatedcrow.hac.main.worldgen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.IVeinTable;
import defeatedcrow.hac.main.api.orevein.OreSet;
import net.minecraft.block.Block;

public class VeinTable implements IVeinTable {

	public final List<OreSet> table1 = new ArrayList<OreSet>();
	public final List<OreSet> table2 = new ArrayList<OreSet>();
	public OreSet layerStone1;
	public OreSet layerStone2;
	public final EnumVein vein;
	public int tableCount1 = 1;
	public int tableCount2 = 1;

	public VeinTable(EnumVein veinIn, @Nonnull OreSet layer1, @Nonnull OreSet layer2) {
		layerStone1 = layer1;
		layerStone2 = layer2;
		vein = veinIn;
	}

	public void addOreToTable1(OreSet... stacks) {
		for (OreSet ore : stacks) {
			if (ore.getWeight() > 0) {
				table1.add(ore);
				tableCount1 += ore.getWeight();
			}
		}
	}

	public void addOreToTable2(OreSet... stacks) {
		for (OreSet ore : stacks) {
			if (ore.getWeight() > 0) {
				table2.add(ore);
				tableCount2 += ore.getWeight();
			}
		}
	}

	/* IVeinTable */

	@Override
	public OreSet getLayerBlock1() {
		return layerStone1;
	}

	@Override
	public OreSet getLayerBlock2() {
		return layerStone2;
	}

	@Override
	public EnumVein getType() {
		return vein;
	}

	@Override
	public List<OreSet> getOreTable1() {
		return table1;
	}

	@Override
	public List<OreSet> getOreTable2() {
		if (table2.isEmpty()) {
			return table1;
		}
		return table2;
	}

	@Override
	public void addOreToTable1(int weight, Block block, int meta) {
		if (block != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			table1.add(new OreSet(weight, set));
			tableCount1 += weight;
		}
	}

	@Override
	public void addOreToTable2(int weight, Block block, int meta) {
		if (block != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			table2.add(new OreSet(weight, set));
			tableCount2 += weight;
		}
	}

	@Override
	public void addOreToTable1(int weight, Block block, int meta, Block secondBlock, int secondMeta, int secondChance) {
		if (block != null && secondBlock != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			BlockSet set2 = new BlockSet(block, meta);
			table1.add(new OreSet(weight, set, set2, secondChance));
			tableCount1 += weight;
		}
	}

	@Override
	public void addOreToTable2(int weight, Block block, int meta, Block secondBlock, int secondMeta, int secondChance) {
		if (block != null && secondBlock != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			BlockSet set2 = new BlockSet(block, meta);
			table2.add(new OreSet(weight, set, set2, secondChance));
			tableCount2 += weight;
		}
	}

}
