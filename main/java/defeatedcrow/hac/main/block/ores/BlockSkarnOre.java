package defeatedcrow.hac.main.block.ores;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class BlockSkarnOre extends DCSimpleBlock implements ITexturePath {

	private Random rand = new Random();

	public BlockSkarnOre(String s) {
		super(Material.ROCK, s, 8, false);
		this.setTickRandomly(false);
		this.setHardness(2.0F);
		this.setResistance(15.0F);
	}

	@Override
	public boolean canClimateUpdate(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public void setHarvestLevel(String toolClass, int level) {
		for (int i = 0; i < 16; i++) {
			super.setHarvestLevel("pickaxe", 1, this.getStateFromMeta(i));
		}
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return harvestL[getMetaFromState(state)];
	}

	private int[] harvestL = new int[] { 2, 2, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	/* Drop Itemの管理 */

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int meta = this.getMetaFromState(state);
		DropTable table = getTable(meta);
		if (table == null || table == DropTable.NONE)
			return Item.getItemFromBlock(this);
		else {
			return Item.getItemFromBlock(table.baseStone.block);
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public int damageDropped(IBlockState state) {
		int meta = this.getMetaFromState(state);
		DropTable table = getTable(meta);
		if (table == null || table == DropTable.NONE)
			return meta;
		else {
			return table.baseStone.meta;
		}
	}

	@Override
	public void getDrops(NonNullList<ItemStack> list, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		super.getDrops(list, world, pos, state, fortune);
		int meta = this.getMetaFromState(state);
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		ItemStack add = ItemStack.EMPTY;
		DropTable table = getTable(meta);

		if (table.dropItem != null) {
			list.add(new ItemStack(table.dropItem, 1, table.dropMeta));
		}

		int par2 = 5 + fortune * 5;
		if (rand.nextInt(100) < par2 && table.secondary != null) {
			list.add(new ItemStack(table.secondary, 1, table.secondaryMeta));
		}
	}

	public enum DropTable {
		RUBY(MainInit.gems_red, 5, MainInit.oreItem, 10, true, new BlockSet(MainInit.gemBlock, 6)),
		AQUAMARINE(MainInit.gems_blue, 5, MainInit.gems_red, 2, true, new BlockSet(MainInit.gemBlock, 6)),
		SAKURA(MainInit.gems_white, 3, MainInit.gems_red, 3, true, new BlockSet(MainInit.skarnBlock, 1)),
		IOLITE(MainInit.gems_black, 3, MainInit.gems_black, 5, true, new BlockSet(MainInit.skarnBlock, 1)),
		TOPAZ(MainInit.gems_white, 5, MainInit.gems_white, 5, true, new BlockSet(MainInit.skarnBlock, 2)),
		AMETHYST(MainInit.gems_red, 3, MainInit.gems_black, 4, true, new BlockSet(MainInit.skarnBlock, 2)),
		LARIMAR(MainInit.gems_blue, 4, MainInit.gems_blue, 4, true, new BlockSet(MainInit.skarnBlock, 0)),
		FLUORITE(MainInit.gems_green, 4, MainInit.gems_green, 5, true, new BlockSet(MainInit.skarnBlock, 0)),
		QUARTZ(MainInit.gems_white, 0, Items.QUARTZ, 0, true, new BlockSet(Blocks.STONE, 0)),
		NONE(null, 0, null, 0, false, new BlockSet(Blocks.STONE, 0));

		public Item dropItem;
		public Item secondary;
		public int dropMeta;
		public int secondaryMeta;
		public boolean isFortuneEffective;
		public BlockSet baseStone;

		private DropTable(Item item, int meta, Item sec, int secmeta, boolean f, BlockSet base) {
			dropItem = item;
			secondary = sec;
			dropMeta = meta;
			secondaryMeta = secmeta;
			isFortuneEffective = f;
			baseStone = base;
		}
	}

	private DropTable getTable(int meta) {
		DropTable[] table = {
			DropTable.RUBY,
			DropTable.AQUAMARINE,
			DropTable.SAKURA,
			DropTable.IOLITE,
			DropTable.TOPAZ,
			DropTable.AMETHYST,
			DropTable.LARIMAR,
			DropTable.FLUORITE,
			DropTable.QUARTZ,
			DropTable.NONE,
			DropTable.NONE,
			DropTable.NONE,
			DropTable.NONE,
			DropTable.NONE,
			DropTable.NONE,
			DropTable.NONE };
		if (meta < 16)
			return table[meta];
		return DropTable.NONE;
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
		"ruby",
		"aquamarine",
		"sakura",
		"iolite",
		"topaz",
		"amethyst",
		"larimar",
		"fluorite",
		"quartz" };

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length) {
			meta = names.length - 1;
		}
		String s = "blocks/ores/ore_skarn_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
