package defeatedcrow.hac.machine.block;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
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
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAdapterFluidPanel extends BlockTorqueBase {

	private static BlockPos lastPos = null;
	private final boolean isAcceptor;

	protected static final AxisAlignedBB AABB_FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_AXIS_X1 = new AxisAlignedBB(0.0D, 0.125D, 0.125D, 0.5D, 0.875D, 0.875D);
	protected static final AxisAlignedBB AABB_AXIS_Y1 = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.5D, 0.875D);
	protected static final AxisAlignedBB AABB_AXIS_Z1 = new AxisAlignedBB(0.125D, 0.125D, 0.0D, 0.875D, 0.875D, 0.5D);
	protected static final AxisAlignedBB AABB_AXIS_X2 = new AxisAlignedBB(0.5D, 0.125D, 0.125D, 1.0D, 0.875D, 0.875D);
	protected static final AxisAlignedBB AABB_AXIS_Y2 = new AxisAlignedBB(0.125D, 0.5D, 0.125D, 0.875D, 1.0D, 0.875D);
	protected static final AxisAlignedBB AABB_AXIS_Z2 = new AxisAlignedBB(0.125D, 0.125D, 0.5D, 0.875D, 0.875D, 1.0D);

	public BlockAdapterFluidPanel(String s, boolean acceptor) {
		super(Material.CLAY, s, 0);
		this.setSoundType(SoundType.METAL);
		isAcceptor = acceptor;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if (isAcceptor)
			return new TileAcceptorFluidPanel();
		return new TileAdapterFluidPanel();
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(heldItem) && heldItem.getItem() instanceof IWrenchDC) {
				TileEntity tile = world.getTileEntity(pos);
				// achievement
				if (!world.isRemote && tile != null)
					if (tile instanceof TileAcceptorFluidPanel) {
						lastPos = pos;
						String mes1 = "Stored this coordinate: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
						player.sendMessage(new TextComponentString(mes1));
					} else if (lastPos != null && tile instanceof TileAdapterFluidPanel) {
						((TileAdapterFluidPanel) tile)
								.setPairPos(new BlockPos(lastPos.getX(), lastPos.getY(), lastPos.getZ()));
						String mes2 = "Registered the coordinate: " + lastPos.getX() + ", " + lastPos.getY() + ", "
								+ lastPos.getZ();
						player.sendMessage(new TextComponentString(mes2));
						lastPos = null;
					}
			}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
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

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	/* change state */

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		world.scheduleUpdate(pos, this, this.tickRate(world));
		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (!world.isRemote && state != null && state.getBlock() != null) {
			DCHeatTier heat = this.onUpdateClimate(world, pos, state);
			boolean prev = this.isLit(world, pos);
			boolean cool = heat.getTier() < 0;
			if (prev != cool) {
				this.changeLitState(world, pos, cool);
			}
			world.scheduleUpdate(pos, this, this.tickRate(world));
		}
	}

	public DCHeatTier onUpdateClimate(World world, BlockPos pos, IBlockState state) {
		DCHeatTier heat = ClimateAPI.calculator.getAverageTemp(world, pos, 2, false);
		return heat;
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof BlockAdapterFluidPanel) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, true), 3);
				world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, false), 3);
				world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (!(state.getBlock() instanceof BlockAdapterFluidPanel))
			return false;
		int meta = state.getBlock().getMetaFromState(state) & 8;
		return meta > 0;
	}

}
