package defeatedcrow.hac.machine.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.machine.MachineInit;

public class BlockRedBox extends BlockTorqueBase {

	public BlockRedBox(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
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
				world.notifyBlockOfStateChange(pos, MachineInit.redbox);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, false), 3);
				world.notifyBlockOfStateChange(pos, MachineInit.redbox);
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
