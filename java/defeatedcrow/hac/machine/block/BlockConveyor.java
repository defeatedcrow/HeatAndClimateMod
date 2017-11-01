package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import defeatedcrow.hac.main.achievement.AcvHelper;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockConveyor extends BlockTorqueBase {

	protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.125D, 0.25D, 0.0D, 0.875D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.0D, 0.25D, 0.125D, 1.0D, 0.5D, 0.875D);

	public BlockConveyor(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileConveyor();
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face.getOpposite()));
		} else {
			if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
				facing = EnumFacing.SOUTH;
			}
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing.getOpposite()));
		}
		return state;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItemIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(heldItem) && heldItem.getItem() instanceof IWrenchDC) {
				TileEntity tile = world.getTileEntity(pos);
				// achievement
				if (!player.hasAchievement(AchievementClimate.MACHINE_CHANGE)) {
					AcvHelper.addMachineAcievement(player, AchievementClimate.MACHINE_CHANGE);
				}
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItemIn, side, hitX, hitY, hitZ);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getSide(state, DCState.SIDE).face;
		switch (face) {
		case EAST:
			return AABB_EW;
		case NORTH:
			return AABB_NS;
		case SOUTH:
			return AABB_NS;
		case WEST:
			return AABB_EW;
		default:
			return AABB_NS;

		}
	}

}
