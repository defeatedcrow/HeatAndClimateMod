package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
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

public class BlockHeatExchanger extends BlockTorqueBase implements IHeatTile {

	public BlockHeatExchanger(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileHeatExchanger();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		// if (player != null && heldItem == null) {
		// TileEntity tile = world.getTileEntity(pos);
		// if (tile instanceof TileHeatExchanger) {
		// TileHeatExchanger ex = (TileHeatExchanger) tile;
		// EnumFacing face = ex.getBaseSide();
		// if (ex.current != null) {
		// int active = isActive(face, player.getPosition(), pos);
		// DCHeatTier cur = ex.current.getHeat();
		// float torque = ex.getCurrentTorque();
		// if (torque >= 31.5F) {
		// active *= 2;
		// }
		// DCLogger.debugLog("*** current:" + cur + ", add" + active);
		// DCHeatTier next = cur.addTier(active);
		// DCLogger.debugLog("*** added:" + next);
		// return true;
		// }
		// }
		// return true;
		// }
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos pos, BlockPos target) {
		TileEntity tile = world.getTileEntity(target);
		if (tile != null && tile instanceof TileHeatExchanger) {
			TileHeatExchanger ex = (TileHeatExchanger) tile;
			EnumFacing face = ex.getBaseSide();
			if (ex.current != null) {
				int active = isActive(face, pos, target);
				DCHeatTier cur = ex.current.getHeat();
				float torque = ex.getCurrentTorque();
				if (torque >= 31.5F) {
					active *= 2;
				} else if (torque < 5.5F) {
					active = 0;
				}
				DCHeatTier next = cur.addTier(active);
				return next;
			}
		}
		return DCHeatTier.NORMAL;
	}

	// 設置時にはプレイヤーの方を向いている方が自然なので
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
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face.getOpposite()));
		} else {
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing.getOpposite()));
		}
		return state;
	}

	int isActive(EnumFacing face, BlockPos to, BlockPos from) {
		if (to.equals(from)) {
			return 1;
		}
		switch (face) {
		case DOWN:
			if (to.getY() < from.getY()) {
				return -1;
			} else {
				return 1;
			}
		case UP:
			if (to.getY() > from.getY()) {
				return -1;
			} else {
				return 1;
			}
		case NORTH:
			if (to.getZ() < from.getZ()) {
				return -1;
			} else {
				return 1;
			}
		case SOUTH:
			if (to.getZ() > from.getZ()) {
				return -1;
			} else {
				return 1;
			}
		case WEST:
			if (to.getX() < from.getX()) {
				return -1;
			} else {
				return 1;
			}
		case EAST:
			if (to.getX() > from.getX()) {
				return -1;
			} else {
				return 1;
			}
		default:
			return 1;

		}
	}

}
