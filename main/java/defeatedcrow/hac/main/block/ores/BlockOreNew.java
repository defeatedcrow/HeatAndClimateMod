package defeatedcrow.hac.main.block.ores;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOreNew extends DCSimpleBlock implements ITexturePath {

	private Random rand = new Random();

	public BlockOreNew(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(false);
		this.setHardness(5.0F);
		this.setResistance(15.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public void setHarvestLevel(String toolClass, int level) {
		for (int i = 0; i < 16; i++) {
			if (i < 5) {
				super.setHarvestLevel("pickaxe", 2, this.getStateFromMeta(i));
			} else {
				super.setHarvestLevel("pickaxe", 3, this.getStateFromMeta(i));
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
			1,
			1,
			2,
			2,
			2,
			2,
			2,
			1,
			2,
			1,
			2,
			1,
			1
	};

	/* Drop Itemの管理 */

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public int damageDropped(IBlockState state) {
		int meta = this.getMetaFromState(state);
		return meta;
	}

	public enum DropTable {
		RED(MainInit.gems, 1, MainInit.gems, 14, true),
		GREEN(MainInit.gems, 5, MainInit.gems, 13, true),
		BLUE(MainInit.gems, 0, MainInit.gems, 4, true),
		WHITE(MainInit.gems, 2, MainInit.gems, 15, true),
		BLACK(Items.COAL, 0, MainInit.gems, 11, true),
		RED_2(MainInit.gems, 14, MainInit.gems, 21, true),
		GREEN_2(MainInit.gems, 13, MainInit.gems, 20, true),
		BLUE_2(MainInit.gems, 4, MainInit.gems, 19, true),
		WHITE_2(MainInit.gems, 15, MainInit.gems, 6, true),
		BLACK_2(MainInit.gems, 11, MainInit.gems, 22, true),
		NETHER_RED_1(MainInit.gems, 1, MainInit.gems, 14, true),
		NETHER_RED_2(MainInit.gems, 14, MainInit.gems, 21, true),
		NETHER_BLACK_1(Items.COAL, 0, MainInit.gems, 11, true),
		NETHER_BLACK_2(MainInit.gems, 11, MainInit.gems, 22, true),
		NETHER_SULFUR(MainInit.gems, 9, Items.REDSTONE, 0, true),
		NONE(null, 0, null, 0, false);

		public Item dropItem;
		public Item secondary;
		public int dropMeta;
		public int secondaryMeta;
		public boolean isFortuneEffective;

		private DropTable(Item item, int meta, Item sec, int secmeta, boolean f) {
			dropItem = item;
			secondary = sec;
			dropMeta = meta;
			secondaryMeta = secmeta;
			isFortuneEffective = f;
		}
	}

	private DropTable getTable(int meta) {
		DropTable[] table = {
				DropTable.RED,
				DropTable.GREEN,
				DropTable.BLUE,
				DropTable.WHITE,
				DropTable.BLACK,
				DropTable.RED_2,
				DropTable.GREEN_2,
				DropTable.BLUE_2,
				DropTable.WHITE_2,
				DropTable.BLACK_2,
				DropTable.NETHER_RED_1,
				DropTable.NETHER_RED_2,
				DropTable.NETHER_BLACK_1,
				DropTable.NETHER_BLACK_2,
				DropTable.NETHER_SULFUR,
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
		DropTable table = getTable(meta);
		int par = 5 + fortune * 5;
		if (rand.nextInt(100) < par && table.dropItem != null) {
			add = new ItemStack(table.dropItem, 1, table.dropMeta);
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

	private static String[] names = {
			"n2_red",
			"n2_green",
			"n2_blue",
			"n2_white",
			"n2_black",
			"n1_red",
			"n1_green",
			"n1_blue",
			"n1_white",
			"n1_black",
			"n2_red_n",
			"n1_red_n",
			"n2_black_n",
			"n1_black_n",
			"b_sulfur_n"
	};

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length) {
			meta = names.length - 1;
		}
		String s = "blocks/ores/ore_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
