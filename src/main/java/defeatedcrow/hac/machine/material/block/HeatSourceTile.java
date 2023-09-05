package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class HeatSourceTile extends ProcessTileBaseDC implements IIntReceiver {

	public HeatSourceTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	protected DCHeatTier output = DCHeatTier.NORMAL;

	public DCHeatTier getHeatTier() {
		return output;
	}

	public DCHeatTier baseHeatTier() {
		return DCHeatTier.KILN;
	}

	int lastHeat = DCAirflow.NORMAL.getID();

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {

		if (this.isInProcess()) {
			IClimate current = this.resultClimate();
			if (current.getAirflow() == DCAirflow.TIGHT) {
				output = baseHeatTier().addTier(-1);
			} else if (current.getAirflow() == DCAirflow.NORMAL) {
				output = baseHeatTier();
			} else {
				output = baseHeatTier().addTier(1);
			}
		} else {
			output = DCHeatTier.NORMAL;
		}

		if (lastHeat != output.getID() && level instanceof ServerLevel) {
			lastHeat = output.getID();
			MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, output.getID());
			setChanged(level, pos, state);
		}

		return false;
	}

	@Override
	public void receiveInteger(int i) {
		output = DCHeatTier.getTypeByID(i);
	}

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		int o = tag.getInt("dcs.heat.output");
		output = DCHeatTier.getTypeByID(o);
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		tag.putInt("dcs.heat.output", output.getID());
	}

}
