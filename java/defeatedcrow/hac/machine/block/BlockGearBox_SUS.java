package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.blockstate.DCState;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGearBox_SUS extends BlockGearBox {

	public BlockGearBox_SUS(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileGearBox_SUS();
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		// achievement
		if (placer != null && !placer.world.isRemote && placer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) placer;
		}
		return state;
	}

	// redstone

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		if (!world.isRemote) {
			boolean flag = world.isBlockPowered(pos);

			if (flag || block.getDefaultState().canProvidePower()) {
				if (flag != state.getValue(DCState.POWERED).booleanValue()) {
					world.setBlockState(pos, state.withProperty(DCState.POWERED, Boolean.valueOf(flag)), 2);
					float f = state.getValue(DCState.POWERED).booleanValue() ? 0.6F : 0.5F;
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F,
							f);
				}
			}
		}
	}

}
