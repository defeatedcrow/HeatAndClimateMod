package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.material.IPosLinkTile;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class HeatSourceTile extends ProcessTileBaseDC implements IIntReceiver, IPosLinkTile {

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

		if (this.isInProcess() && this.isActive(level, pos, state)) {
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

		if (lastHeat != output.getID()) {
			lastHeat = output.getID();
			if (level instanceof ServerLevel)
				MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, output.getID());
			HeatSourceBlock.changeLitState(level, pos, output.getTier() > 0);
			HeatSourceBlock.changeFlagState(level, pos, output.getTier() > baseHeatTier().getTier());
			setChanged(level, pos, state);
		}

		return false;
	}

	@Override
	public void receiveInteger(int i) {
		int last = output.getID();
		output = DCHeatTier.getTypeByID(i);
	}

	@Override
	public IPosLinkTile.Type getLinkType() {
		return IPosLinkTile.Type.AIR;
	}

	@Override
	public boolean canLink(Level level, BlockPos pos, IPosLinkTile.Type type) {
		return type == getLinkType() && pos != null && getBlockPos().closerThan(pos, 8.0D);
	}

	private BlockPos link = null;

	@Override
	public void setLinkPos(BlockPos pos) {
		link = pos;
	}

	@Override
	public BlockPos getLinkPos() {
		return link;
	}

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		int o = tag.getInt("dcs.heat.output");
		output = DCHeatTier.getTypeByID(o);
		this.writePosToTag(tag);
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		tag.putInt("dcs.heat.output", output.getID());
		this.loadPosFromTag(tag);
	}

}
