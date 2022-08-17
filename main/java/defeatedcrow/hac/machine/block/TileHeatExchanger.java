package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.packet.IClimateUpdateTile;
import defeatedcrow.hac.core.packet.MessageClimateUpdate;
import defeatedcrow.hac.main.packet.DCMainPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileHeatExchanger extends TileTorqueBase implements ITorqueReceiver, IClimateUpdateTile {

	protected IClimate current = null;
	protected int lastClimate = 0;

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side != getBaseSide() && side != getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque()) {
			return false;
		}
		return this.isInputSide(side.getOpposite());
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	@Override
	public float maxSpeed() {
		return 0.0F;
	}

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public void updateTile() {
		super.updateTile();

		// 気候チェック
		if (!world.isRemote) {
			DCHeatTier heat = getUnderHeat();
			ClimateSupplier clm = new ClimateSupplier(world, pos);
			DCHumidity hum = clm.get().getHumidity();
			DCAirflow air = clm.get().getAirflow();

			int code = (air.getID() << 6) + (hum.getID() << 4) + heat.getID();
			current = ClimateAPI.register.getClimateFromInt(code);
		}
	}

	private int count = 20;

	@Override
	protected void onServerUpdate() {
		if (count > 0) {
			count--;
		} else {
			boolean flag = false;
			if (current != null && current.getClimateInt() != lastClimate) {
				flag = true;
				lastClimate = current.getClimateInt();
			}

			if (flag) {
				DCMainPacket.INSTANCE.sendToAll(new MessageClimateUpdate(pos, lastClimate));
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		if (tag.hasKey("dcs.climateInt")) {
			int ic = tag.getInteger("dcs.climateInt");
			IClimate clm = ClimateAPI.register.getClimateFromInt(ic);
			if (clm != null)
				current = clm;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		if (current != null) {
			tag.setInteger("dcs.climateInt", current.getClimateInt());
		}
		return tag;
	}

	private DCHeatTier getUnderHeat() {
		DCHeatTier hot = ClimateAPI.calculator.getAverageTemp(world, getPos().down(), 0, false);
		DCHeatTier current = ClimateAPI.calculator.getBlockHeatTier(world, getPos(), pos.down());
		if (current != null && current.getTier() != hot.getTier()) {
			hot = current;
		}

		return hot;
	}

	// packet用
	public IClimate getClimate() {
		return current == null ? ClimateAPI.register.getClimateFromParam(DCHeatTier.NORMAL, DCHumidity.NORMAL, DCAirflow.NORMAL) : current;
	}

	public void setClimate(int i) {
		IClimate clm = ClimateAPI.register.getClimateFromInt(i);
		if (clm != null)
			current = clm;
	}

}
