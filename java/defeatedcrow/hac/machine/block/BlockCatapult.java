package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import defeatedcrow.hac.main.achievement.AcvHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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

public class BlockCatapult extends BlockTorqueBase {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);

	public BlockCatapult(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCatapult();
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face));
		} else {
			if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
				facing = EnumFacing.SOUTH;
			}
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing));
		}
		return state;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null && heldItem != null && heldItem.getItem() instanceof IWrenchDC) {
			TileEntity tile = world.getTileEntity(pos);
			// achievement
			if (!player.hasAchievement(AchievementClimate.MACHINE_CHANGE)) {
				AcvHelper.addMachineAcievement(player, AchievementClimate.MACHINE_CHANGE);
			}

			if (tile != null && tile instanceof TileCatapult) {
				TileCatapult cat = (TileCatapult) tile;
				int r = cat.rad;
				if (player.isSneaking()) {
					r -= 10;
				} else {
					r += 10;
				}
				if (r > 90) {
					r = 0;
				}
				if (r < 0) {
					r = 0;
				}
				cat.rad = r;
				return true;
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
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
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity != null) {
			// if (entity instanceof EntityPlayer || !world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileCatapult) {
				TileCatapult cat = (TileCatapult) tile;
				float pow = cat.pow;
				double rad = cat.rad * Math.PI / 180.0D;
				EnumSide side = state.getValue(DCState.SIDE);
				float mX = pow * (float) Math.cos(rad) * side.getFacing().getFrontOffsetX();
				float mZ = pow * (float) Math.cos(rad) * side.getFacing().getFrontOffsetZ();
				float mY = pow * (float) Math.sin(rad) * 0.5F;

				// entity.setPosition(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				entity.setRotationYawHead(side.getFacing().getHorizontalAngle());
				entity.motionX = mX;
				entity.motionY = mY;
				entity.motionZ = mZ;
				DCLogger.debugLog("catapult " + pow);
			}
			// }
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}

}
