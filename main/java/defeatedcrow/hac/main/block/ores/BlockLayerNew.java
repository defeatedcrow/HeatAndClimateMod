package defeatedcrow.hac.main.block.ores;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLayerNew extends DCSimpleBlock implements ITexturePath {

	private Random rand = new Random();

	public BlockLayerNew(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(false);
		this.setHardness(2.0F);
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
			super.setHarvestLevel("pickaxe", 1, this.getStateFromMeta(i));
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
			1,
			1,
			1,
			1,
			1,
			1,
			1,
			1,
			1,
			1,
			1
	};

	/* Drop Itemの管理 */

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int meta = this.getMetaFromState(state);
		DropTable table = getTable(meta);
		if (table == null || table == DropTable.NONE)
			return Item.getItemFromBlock(this);
		else {
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
		int amo = 1;
		if (table.isFortuneEffective && fortune > 0) {
			int max = MathHelper.ceil(1 + fortune * 0.5D);
			int d1 = random.nextInt(max);
			return amo + d1;
		} else
			return amo;
	}

	@Override
	public int damageDropped(IBlockState state) {
		int meta = this.getMetaFromState(state);
		DropTable table = getTable(meta);
		if (table == null || table == DropTable.NONE)
			return meta;
		else {
			return table.dropMeta;
		}
	}

	public enum DropTable {
		GYPSUM(MainInit.gems, 3, MainInit.gems, 3, true),
		LIME(MainInit.gems, 0, MainInit.gems, 0, false),
		GUANO(MainInit.gems, 18, MainInit.gems, 18, true),
		NITER(MainInit.gems, 10, MainInit.gems, 10, true),
		SULFUR(MainInit.gems, 9, MainInit.gems, 9, true),
		SALT(MainInit.gems, 8, MainInit.gems, 8, true),
		SERPENTINE(MainInit.gems, 12, MainInit.gems, 12, true),
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
				DropTable.GYPSUM,
				DropTable.NONE,
				DropTable.GUANO,
				DropTable.NITER,
				DropTable.SULFUR,
				DropTable.SALT,
				DropTable.SERPENTINE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE,
				DropTable.NONE
		};
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
			"alabaster",
			"lime",
			"guano",
			"niter",
			"sulfur",
			"salt",
			"serpentine"
	};

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length) {
			meta = names.length - 1;
		}
		String s = "blocks/ores/ore_b_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
