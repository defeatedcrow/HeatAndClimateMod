package defeatedcrow.hac.main.block.ores;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.main.MainInit;

public class BlockOres2 extends DCSimpleBlock {

	private Random rand = new Random();

	public BlockOres2(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
	}

	@Override
	public void setHarvestLevel(String toolClass, int level) {
		for (int i = 0; i < 16; i++) {
			if (i < 2) {
				super.setHarvestLevel("pickaxe", 1, this.getStateFromMeta(i));
			} else {
				super.setHarvestLevel("pickaxe", 2, this.getStateFromMeta(i));
			}
		}
	}

	/* Drop Itemの管理 */

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (state.getBlock() != this)
			return super.getItemDropped(state, rand, fortune);
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
		if (state.getBlock() != this)
			return 0;
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
	public int damageDropped(IBlockState state) {
		if (state.getBlock() != this)
			return 0;
		int meta = this.getMetaFromState(state);
		DropTable table = this.getTable(meta);
		if (table.dropItem == null) {
			return meta;
		} else {
			return table.dropMeta;
		}
	}

	public enum DropTable {
		SALT(MainInit.gems, 1, 8, true), NITER(MainInit.gems, 1, 9, true), SULFER(MainInit.gems, 1, 10, true), NONE(
				null,
				1,
				0,
				false);

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
				DropTable.NONE,
				DropTable.SALT,
				DropTable.NITER,
				DropTable.SULFER,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE };
		if (meta < 16) {
			return table[meta];
		}
		return DropTable.NONE;
	}
}
