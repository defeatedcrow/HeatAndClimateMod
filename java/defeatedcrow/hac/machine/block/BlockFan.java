package defeatedcrow.hac.machine.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.core.energy.BlockTorqueBase;

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
			boolean active = isActive(face.getOpposite(), pos, target) && world.isAirBlock(target.offset(face));
			if (active) {
				float torque = fan.getCurrentTorque();
				if (torque >= 4.0F) {
					return DCAirflow.WIND;
				} else if (torque > 0F) {
					return DCAirflow.FLOW;
				}
			}
		}
		return DCAirflow.TIGHT;
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
			return to.getZ() > from.getZ();
		case SOUTH:
			return to.getZ() < from.getZ();
		case EAST:
			return to.getX() > from.getX();
		case WEST:
			return to.getX() < from.getX();
		default:
			return false;

		}
	}

}
