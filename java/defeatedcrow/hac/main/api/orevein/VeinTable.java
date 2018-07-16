package defeatedcrow.hac.main.api.orevein;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.worldgen.OreSetDC;
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
		List<OreSet> ret = ImmutableList.copyOf(table1);
		return ret;
	}

	@Override
	public List<OreSet> getOreTable2() {
		List<OreSet> ret = ImmutableList.copyOf(table2);
		return ret;
	}

	@Override
	public void addOreToTable1(int weight, Block block, int meta) {
		if (block != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			table1.add(new OreSetDC(weight, set));
			tableCount1 += weight;
		}
	}

	@Override
	public void addOreToTable2(int weight, Block block, int meta) {
		if (block != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			table2.add(new OreSetDC(weight, set));
			tableCount2 += weight;
		}
	}

	@Override
	public void addOreToTable1(int weight, Block block, int meta, Block secondBlock, int secondMeta, int secondChance) {
		if (block != null && secondBlock != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			BlockSet set2 = new BlockSet(block, meta);
			table1.add(new OreSetDC(weight, set, set2, secondChance));
			tableCount1 += weight;
		}
	}

	@Override
	public void addOreToTable2(int weight, Block block, int meta, Block secondBlock, int secondMeta, int secondChance) {
		if (block != null && secondBlock != null && weight > 0) {
			BlockSet set = new BlockSet(block, meta);
			BlockSet set2 = new BlockSet(block, meta);
			table2.add(new OreSetDC(weight, set, set2, secondChance));
			tableCount2 += weight;
		}
	}

	@Override
	public void removeOreFromTable1(Block block, int meta) {
		if (block != null) {
			BlockSet set = new BlockSet(block, meta);
			List<OreSet> removes = Lists.newArrayList();
			for (OreSet target : table1) {
				if (target.getOre().equals(set)) {
					removes.add(target);
				}
			}

			for (OreSet del : removes) {
				table1.remove(del);
				tableCount1 -= del.getWeight();
			}
		}
	}

	@Override
	public void removeOreFromTable2(Block block, int meta) {
		if (block != null) {
			BlockSet set = new BlockSet(block, meta);
			List<OreSet> removes = Lists.newArrayList();
			for (OreSet target : table2) {
				if (target.getOre().equals(set)) {
					removes.add(target);
				}
			}

			for (OreSet del : removes) {
				table2.remove(del);
				tableCount2 -= del.getWeight();
			}
		}
	}

}
