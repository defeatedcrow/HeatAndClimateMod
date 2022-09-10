package defeatedcrow.hac.core.climate;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.core.BlockPos;

public class Climate implements IClimate {

	private final DCHeatTier temp;
	private final DCHumidity hum;
	private final DCAirflow flow;
	private final int code; // 0bAABBCCCC;
	private BlockPos heatPos;
	private BlockPos coldPos;

	public Climate(DCHeatTier t, DCHumidity h, DCAirflow f) {
		temp = t;
		hum = h;
		flow = f;
		int i1 = t.getID(); // 0-15
		int i2 = h.getID(); // 0-2
		int i3 = f.getID(); // 0-2
		i2 = i2 << 4;
		i3 = i3 << 6;
		code = i1 + i2 + i3;
	}

	@Override
	public DCHeatTier getHeat() {
		return temp;
	}

	@Override
	public DCHumidity getHumidity() {
		return hum;
	}

	@Override
	public DCAirflow getAirflow() {
		return flow;
	}

	@Override
	public int getClimateInt() {
		return code;
	}

	@Override
	public IClimate addTempTier(int tier) {
		return new Climate(temp.addTier(tier), hum, flow);
	}

	@Override
	public IClimate addHumTier(int tier) {
		return new Climate(temp, hum.addTier(tier), flow);
	}

	@Override
	public IClimate addAirTier(int tier) {
		return new Climate(temp, hum, flow.addTier(tier));
	}

}
