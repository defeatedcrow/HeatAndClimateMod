package defeatedcrow.hac.main.block.build;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import defeatedcrow.hac.main.achievement.AcvHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBellow extends BlockTorqueBase implements IAirflowTile {

	public BlockBellow(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBellow();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			TileEntity tile = world.getTileEntity(pos);
			if (heldItem != null && heldItem.getItem() instanceof IWrenchDC) {
				// achievement
				if (!player.hasAchievement(AchievementClimate.MACHINE_CHANGE)) {
					AcvHelper.addMachineAcievement(player, AchievementClimate.MACHINE_CHANGE);
				}
			} else if (tile != null && tile instanceof TileBellow) {
				TileBellow crank = ((TileBellow) tile);
				float add = crank.currentTorque + 1.0F;
				if (add > crank.maxTorque()) {
					add = crank.maxTorque();
				}
				crank.currentTorque = add;
				return true;
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}

	@Override
	public DCAirflow getAirflow(World world, BlockPos pos, BlockPos target) {
		TileEntity tile = world.getTileEntity(target);
		if (tile != null && tile instanceof TileBellow) {
			TileBellow fan = (TileBellow) tile;
			EnumFacing face = fan.getBaseSide();
			boolean active = isActive(face, pos, target);
			if (active) {
				float torque = fan.getCurrentTorque();
				if (torque > 0.0F) {
					return DCAirflow.WIND;
				}
			}
		}
		return DCAirflow.TIGHT;
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			if (placer.rotationPitch < -75.0F) {
				face = EnumFacing.UP;
			} else if (placer.rotationPitch > 75.0F) {
				face = EnumFacing.DOWN;
			}
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face));
		} else {
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing));
		}
		return state;
	}

	boolean isActive(EnumFacing face, BlockPos to, BlockPos from) {
		if (to.equals(from)) {
			return true;
		}
		switch (face) {
		case DOWN:
			return to.up().equals(from);
		case UP:
			return to.down().equals(from);
		case NORTH:
			return to.south().equals(from);
		case SOUTH:
			return to.north().equals(from);
		case WEST:
			return to.east().equals(from);
		case EAST:
			return to.west().equals(from);
		default:
			return false;

		}
	}

}
