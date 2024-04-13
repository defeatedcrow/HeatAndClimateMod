package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.network.packet.message.MsgTileFaceIOToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class CableCopperTile extends EnergyTileBaseDC {

	public CableCopperTile(BlockPos pos, BlockState state) {
		super(MachineInit.CABLE_COPPER_TILE.get(), pos, state);
	}

	public CableCopperTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	int count = 10;

	protected int getInterval() {
		return 10;
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = getInterval();
			for (Direction dir : DCUtil.PipeScanList) {
				FaceIO facing = getEnergyHandler().getFace(dir);
				BooleanProperty prop = DCState.getFacingProperty(dir);
				boolean connect = DCState.getBool(getBlockState(), prop);
				if (facing == FaceIO.NONE && connect) {
					getEnergyHandler().setFace(dir, FaceIO.PIPE);
					MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos, dir.get3DDataValue(), FaceIO.PIPE.getID());
				} else if (facing != FaceIO.NONE && !connect) {
					getEnergyHandler().setFace(dir, FaceIO.NONE);
					MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos, dir.get3DDataValue(), FaceIO.NONE.getID());
				}
			}

			if (isLeaking(level, pos, level.getBlockState(pos), this)) {
				// leakage
				battery.extractEnergy(battery.getFlowRate(), false);
			}
		}
		return super.onTickProcess(level, pos, state);
	}

	public static boolean clientTick(Level level, BlockPos pos, BlockState state, EnergyTileBaseDC tile) {
		RandomSource random = level.random;
		if (random.nextFloat() < 0.1F)
			if (isLeaking(level, pos, state, tile)) {
				// leakage
				double d0 = pos.getX() + 0.5D;
				double d1 = pos.getY() + 0.5D;
				double d2 = pos.getZ() + 0.5D;
				level.addParticle(CoreInit.LEAKAGE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
				level.addParticle(ParticleTypes.SMOKE, d0, d1 + 0.2D, d2, 0.0D, 0.0D, 0.0D);
			}
		return false;
	}

	public static boolean isLeaking(Level level, BlockPos pos, BlockState state, EnergyTileBaseDC tile) {
		return state.is(TagDC.BlockTag.LEAKAGE_MACHINE) && (tile.getEnergyHandler().getEnergyStored() > 0 || level.isClientSide) && (DCState.getBool(state, BlockStateProperties.WATERLOGGED) || level.isRainingAt(pos
			.above()));
	}

	public SidedEnergyTankDC battery = new SidedEnergyTankDC(this, getMaxEnergy(), 32).setAllFases(FaceIO.NONE);

	protected int getMaxEnergy() {
		return 320;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

}
