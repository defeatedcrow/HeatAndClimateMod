package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFan extends BlockTorqueBase implements IAirflowTile {

	public BlockFan(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileFan();
	}

	@Override
	public DCAirflow getAirflow(World world, BlockPos pos, BlockPos target) {
		TileEntity tile = world.getTileEntity(target);
		if (tile != null && tile instanceof TileFan) {
			TileFan fan = (TileFan) tile;
			EnumFacing face = fan.getBaseSide();
			boolean active = isActive(face, pos, target) && world.isAirBlock(target.offset(face.getOpposite()));
			if (active) {
				float torque = fan.getCurrentTorque();
				if (torque >= 5.5F) {
					return DCAirflow.WIND;
				} else if (torque > 0F) {
					return DCAirflow.FLOW;
				}
			}
		}
		return DCAirflow.TIGHT;
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

	boolean isActive(EnumFacing face, BlockPos to, BlockPos from) {
		if (to.equals(from)) {
			return true;
		}
		switch (face) {
		case DOWN:
			return to.getY() < from.getY();
		case UP:
			return to.getY() > from.getY();
		case NORTH:
			return to.getZ() < from.getZ();
		case SOUTH:
			return to.getZ() > from.getZ();
		case WEST:
			return to.getX() < from.getX();
		case EAST:
			return to.getX() > from.getX();
		default:
			return false;

		}
	}

}
