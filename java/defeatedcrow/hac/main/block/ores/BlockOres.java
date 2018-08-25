package defeatedcrow.hac.main.block.ores;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOres extends DCSimpleBlock {

	private Random rand = new Random();

	public BlockOres(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(false);
		this.setHardness(5.0F);
		this.setResistance(15.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public void setHarvestLevel(String toolClass, int level) {
		for (int i = 0; i < 16; i++) {
			if (i < 3) {
				super.setHarvestLevel("pickaxe", 1, this.getStateFromMeta(i));
			} else if (i > 8) {
				super.setHarvestLevel("pickaxe", 3, this.getStateFromMeta(i));
			} else {
				super.setHarvestLevel("pickaxe", 2, this.getStateFromMeta(i));
			}
		}
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return harvestL[getMetaFromState(state)];
	}

	private int[] harvestL = new int[] {
			1,
			1,
			1,
			2,
			2,
			2,
			2,
			2,
			2,
			3,
			3,
			3,
			3,
			3,
			3,
			3
	};

	/* Drop Itemの管理 */

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (state.getBlock() != this)
			return super.getItemDropped(state, rand, fortune);
		int meta = this.getMetaFromState(state);
		DropTable table = this.getTable(meta);
		if (table.dropItem == null)
			return Item.getItemFromBlock(this);
		else
			return table.dropItem;
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
			int max = MathHelper.ceil(1 + fortune * 0.5D);
			int d1 = random.nextInt(max);
			return table.amount + d1;
		} else
			return table.amount;
	}

	@Override
	public int damageDropped(IBlockState state) {
		if (state.getBlock() != this)
			return 0;
		int meta = this.getMetaFromState(state);
		DropTable table = this.getTable(meta);
		if (table.dropItem == null)
			return meta;
		else
			return table.dropMeta;
	}

	public enum DropTable {
		GYPSUM(MainInit.gems, 2, 3, true),
		HEMATITE(MainInit.oreDust, 1, 5, true),
		CHAL_B(MainInit.gems, 1, 0, true),
		SAPPIRE(MainInit.gems, 1, 4, true),
		CHAL_W(MainInit.gems, 1, 2, true),
		CRYSTAL(Items.QUARTZ, 1, 0, true),
		DIAMOND(Items.DIAMOND, 1, 0, true),
		EMERALD(Items.EMERALD, 1, 0, true),
		NONE(null, 1, 0, false);

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
				DropTable.NONE
		};
		if (meta < 16)
			return table[meta];
		return DropTable.NONE;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> list, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		super.getDrops(list, world, pos, state, fortune);
		int meta = this.getMetaFromState(state);
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		ItemStack add = ItemStack.EMPTY;
		int par = 2 + fortune * 5;
		if (rand.nextInt(50) < par) {
			switch (meta) {
			case 0:
				add = rand.nextInt(2) == 0 ? new ItemStack(MainInit.gems, 1, 7) : new ItemStack(MainInit.gems, 1, 6);
				break;
			case 6:
				add = new ItemStack(MainInit.gems, 1, 5);
			}
		}

		if (!DCUtil.isEmpty(add)) {
			list.add(add);
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		int meta = DCState.getInt(state, DCState.TYPE16);
		if (meta >= 0)
			return new ItemStack(this, 1, meta);
		return super.getPickBlock(state, target, world, pos, player);
	}
}
