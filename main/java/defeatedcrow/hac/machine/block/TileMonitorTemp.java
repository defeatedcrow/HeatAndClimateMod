package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;

public class TileMonitorTemp extends TileMonitorBase {

	@Override
	public String unit() {
		return "";
	}

	@Override
	public boolean noUnit() {
		return true;
	}

	@Override
	public String amountString(float amo, int order) {
		if (amo == 0)
			return "----";
		int t = (int) amo - 1;
		DCHeatTier heat = DCHeatTier.getTypeByID(t);
		return heat == null ? "----" : heat.toString();
	}

	@Override
	public float getGauge() {
		return amountMax < 1F ? 0 : (amount - 1F) / 15F;
	}

	@Override
	protected boolean updateAmount() {
		if (world.isBlockLoaded(getPairPos())) {
			DCHeatTier heat = ClimateAPI.calculator.getAverageTemp(world, getPairPos());
			if (heat != null) {
				amountMax = heat.getColorInt();
				amount = heat.getID() + 1;
				return true;
			}
		}
		return false;
	}

	@Override
	protected int getMaxCool() {
		// 長めに設定
		return 40;
	}

}
