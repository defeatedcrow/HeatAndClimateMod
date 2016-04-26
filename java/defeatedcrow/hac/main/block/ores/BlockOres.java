package defeatedcrow.hac.main.block.ores;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.main.MainInit;

public class BlockOres extends DCSimpleBlock {

	private Random rand = new Random();

	public BlockOres(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
	}

	@Override
	public void setHarvestLevel(String toolClass, int level) {
		for (int i = 0; i < 16; i++) {
			switch (i) {
			case 0:
			case 1:
			case 4:
			case 6:
			case 8:
			case 9:
				super.setHarvestLevel("pickaxe", 1, this.getStateFromMeta(i));
				break;
			default:
				super.setHarvestLevel("pickaxe", 2, this.getStateFromMeta(i));
			}
		}
	}

	/* Drop Itemの管理 */

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int meta = this.getMetaFromState(state);
		DropTable table = this.getTable(meta);
		if (table.dropItem == null) {
			return Item.getItemFromBlock(this);
		} else {
			return table.dropItem;
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		int meta = this.getMetaFromState(state);
		DropTable table = this.getTable(meta);
		int amo = table.amount;
		if (table.isFortuneEffective && fortune > 0) {
			int max = MathHelper.ceiling_double_int(1 + fortune * 0.5D);
			int d1 = random.nextInt(max);
			return table.amount + d1;
		} else {
			return table.amount;
		}
	}

	@Override
	public int getExpDrop(net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		int meta = this.getMetaFromState(state);
		DropTable table = this.getTable(meta);
		if (table.isFortuneEffective) {
			int d1 = rand.nextInt(5);
			return d1;
		} else {
			return 0;
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		int meta = this.getMetaFromState(state);
		DropTable table = this.getTable(meta);
		if (table.dropItem == null) {
			return meta;
		} else {
			return table.dropMeta;
		}
	}

	public enum DropTable {
		GYPSUM(MainInit.gems, 1, 3, false), HEMATITE(MainInit.oreDust, 1, 5, false), CHAL_B(MainInit.gems, 1, 0, true), SAPPIRE(
				MainInit.gems, 1, 4, true), CHAL_W(MainInit.gems, 1, 2, false), CRYSTAL(Items.quartz, 1, 0, true), DIAMOND(Items.diamond,
				1, 0, true), EMERALD(Items.emerald, 1, 0, true), NONE(null, 1, 0, false);

		public Item dropItem;
		public int amount;
		public int dropMeta;
		public boolean isFortuneEffective;

		private DropTable(Item item, int amo, int meta, boolean f) {
			dropItem = item;
			amount = amo;
			dropMeta = meta;
			isFortuneEffective = f;
		}
	}

	private DropTable getTable(int meta) {
		DropTable[] table = {
				DropTable.GYPSUM,
				DropTable.HEMATITE,
				DropTable.CHAL_B,
				DropTable.SAPPIRE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.CHAL_W,
				DropTable.CRYSTAL,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.DIAMOND,
				DropTable.EMERALD,
				DropTable.NONE };
		if (meta < 16) {
			return table[meta];
		}
		return DropTable.NONE;
	}
}
