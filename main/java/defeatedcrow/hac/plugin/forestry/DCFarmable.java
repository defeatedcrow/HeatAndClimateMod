package defeatedcrow.hac.plugin.forestry;

import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.util.DCUtil;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;
import forestry.core.utils.BlockUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DCFarmable implements IFarmable {

	protected final ClimateCropBase crop;

	public DCFarmable(ClimateCropBase c) {
		crop = c;
	}

	@Override
	public ICrop getCropAt(World world, BlockPos pos, IBlockState state) {
		if (crop.getCurrentStage(state) == GrowingStage.GROWN)
			return new DCCrop(world, pos, state);
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
		if (target.getBlock() instanceof ClimateCropBase)
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
