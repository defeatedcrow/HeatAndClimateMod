package defeatedcrow.hac.plugin.forestry;

import defeatedcrow.hac.core.base.ClimateDoubleCropBase;
import defeatedcrow.hac.core.util.DCUtil;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;
import forestry.core.utils.BlockUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DCFarmableDouble implements IFarmable {

	protected final ClimateDoubleCropBase crop;

	public DCFarmableDouble(ClimateDoubleCropBase c) {
		crop = c;
	}

	@Override
	public ICrop getCropAt(World world, BlockPos pos, IBlockState state) {
		if (state.getBlock() instanceof ClimateDoubleCropBase)
			return new DCCropDouble(world, pos, state);
		else if (world.getBlockState(pos.up()).getBlock() instanceof ClimateDoubleCropBase) {
			IBlockState up = world.getBlockState(pos.up());
			return new DCCropDouble(world, pos.up(), up);
		}
		return null;
	}

	@Override
	public boolean isGermling(ItemStack item) {
		ItemStack seed = crop.getSeedItem(crop.getDefaultState());
		if (!DCUtil.isEmpty(item))
			return DCUtil.isSameItem(seed, item, false);
		return false;
	}

	@Override
	public boolean isSaplingAt(World world, BlockPos pos) {
		IBlockState target = world.getBlockState(pos);
		if (target.getBlock() instanceof ClimateDoubleCropBase)
			return target == crop.getDefaultState();
		return false;
	}

	@Override
	public boolean isWindfall(ItemStack item) {
		return false;
	}

	@Override
	public boolean plantSaplingAt(EntityPlayer player, ItemStack item, World world, BlockPos pos) {
		IBlockState plant = crop.getDefaultState();
		return BlockUtil.setBlockWithPlaceSound(world, pos, plant);
	}

}
