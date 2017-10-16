package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockShaft_TB extends BlockTorqueBase {

	protected static final AxisAlignedBB AABB_FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_AXIS_X1 = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_AXIS_Y1 = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	protected static final AxisAlignedBB AABB_AXIS_Z1 = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
	protected static final AxisAlignedBB AABB_AXIS_X2 = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_AXIS_Y2 = new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
	protected static final AxisAlignedBB AABB_AXIS_Z2 = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);

	public BlockShaft_TB(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileShaft_TB();
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

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(heldItem) && heldItem.getItem() instanceof IWrenchDC) {}
		}
		return super.onBlockActivated(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (DCState.hasProperty(state, DCState.SIDE)) {
			switch (DCState.getSide(state, DCState.SIDE)) {
			case DOWN:
				return AABB_AXIS_Y1;
			case UP:
				return AABB_AXIS_Y2;
			case WEST:
				return AABB_AXIS_X1;
			case EAST:
				return AABB_AXIS_X2;
			case NORTH:
				return AABB_AXIS_Z1;
			case SOUTH:
				return AABB_AXIS_Z2;
			default:
				return AABB_FULL;
			}
		}
		return AABB_FULL;
	}

}
