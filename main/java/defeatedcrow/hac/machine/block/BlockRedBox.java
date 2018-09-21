package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedBox extends BlockTorqueBase {

	public BlockRedBox(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(heldItem) && heldItem.getItem() instanceof IWrenchDC) {}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileRedBox();
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MachineInit.redbox) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, true), 3);
				world.notifyNeighborsOfStateChange(pos, MachineInit.redbox, true);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, false), 3);
				world.notifyNeighborsOfStateChange(pos, MachineInit.redbox, true);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != MachineInit.redbox)
			return false;
		int meta = state.getBlock().getMetaFromState(state) & 8;
		return meta > 0;
	}

	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockState.getValue(DCState.POWERED).booleanValue() ? 15 : 0;
	}

	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return !blockState.getValue(DCState.POWERED).booleanValue() ? 0 : 15;
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

}
