package defeatedcrow.hac.machine.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockShaft_L_SUS extends BlockShaft_L {

	public BlockShaft_L_SUS(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileShaft_L_SUS();
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
}
