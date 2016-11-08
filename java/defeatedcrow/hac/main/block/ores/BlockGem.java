package defeatedcrow.hac.main.block.ores;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IThermalInsulationBlock;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGem extends DCSimpleBlock implements ITexturePath, IRapidCollectables, IThermalInsulationBlock {

	public BlockGem(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
	}

	private static String[] names = {
			"chal_blue",
			"chal_red",
			"chal_white",
			"gypsum",
			"sapphire",
			"salt",
			"marble",
			"schorl",
			"compressed" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length)
			meta = names.length - 1;
		String s = "blocks/ores/gemblock_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	/* IRapidCollectables */

	@Override
	public boolean isCollectable(ItemStack item) {
		return item != null && item.getItem() != null && item.getItem() instanceof ItemPickaxe;
	}

	@Override
	public int getCollectArea(ItemStack item) {
		return 1;
	}

	@Override
	public boolean doCollect(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
		List<ItemStack> list = this.getDrops(world, pos, state, 0);
		for (ItemStack item : list) {
			double x = player.posX;
			double y = player.posY + 0.25D;
			double z = player.posZ;
			EntityItem drop = new EntityItem(world, x, y, z, item);
			world.spawnEntityInWorld(drop);
		}
		world.setBlockToAir(pos);
		return true;
	}

	@Override
	public int getReductionAmount(World world, BlockPos pos, IBlockState state) {
		int meta = DCState.getInt(state, DCState.TYPE16);
		return meta == 3 ? -1 : 0;
	}

	@Override
	public int tickRate(World world) {
		return 20;
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		if (!worldIn.isRemote && state != null && state.getBlock() != null) {
			IClimate clm = this.onUpdateClimate(worldIn, pos, state);
			this.onClimateChange(worldIn, pos, state, clm);
		}
	}
}
