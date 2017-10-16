package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.IThermalInsulationBlock;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGemBricks extends DCSimpleBlock implements ITexturePath, IRapidCollectables, IThermalInsulationBlock {

	public BlockGemBricks(Material m, String s) {
		super(m, s, 2, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
	}

	private static String[] names = {
			"gypsum", "marble", "lime"
	};

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length)
			meta = names.length - 1;
		String s = "blocks/build/bricks_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	/* IRapidCollectables */

	@Override
	public boolean isCollectable(ItemStack item) {
		return !DCUtil.isEmpty(item) && item.getItem() instanceof ItemPickaxe;
	}

	@Override
	public int getCollectArea(ItemStack item) {
		return 1;
	}

	@Override
	public boolean doCollect(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
		NonNullList<ItemStack> ret = NonNullList.create();
		this.getDrops(ret, world, pos, state, 0);
		for (ItemStack item : ret) {
			double x = player.posX;
			double y = player.posY + 0.25D;
			double z = player.posZ;
			EntityItem drop = new EntityItem(world, x, y, z, item);
			world.spawnEntity(drop);
		}
		world.setBlockToAir(pos);
		return true;
	}

	@Override
	public int getReductionAmount(World world, BlockPos pos, IBlockState state) {
		int meta = DCState.getInt(state, DCState.TYPE16);
		return meta == 0 ? -1 : 0;
	}
}
