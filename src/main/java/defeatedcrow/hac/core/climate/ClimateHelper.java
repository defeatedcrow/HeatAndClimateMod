package defeatedcrow.hac.core.climate;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IClimateHelper;
import net.minecraft.nbt.CompoundTag;

public class ClimateHelper implements IClimateHelper {

	public static final String NBT_KEY = "dcs_climate";

	@Override
	public IClimate getClimateFromInt(int code) {
		if (code <= 0) {
			return getDefaultClimate();
		}
		int t = code & 15;
		int h = (code >> 4) & 3;
		int a = (code >> 6) & 3;
		DCHeatTier temp = DCHeatTier.getTypeByID(t);
		DCHumidity hum = DCHumidity.getTypeByID(h);
		DCAirflow air = DCAirflow.getTypeByID(a);
		return new Climate(temp, hum, air);
	}

	@Override
	public int[] getIDs(int code) {
		int t = 0;
		int h = 0;
		int a = 0;
		if (code <= 0) {
			t = DCHeatTier.NORMAL.getID();
			h = DCHumidity.NORMAL.getID();
			a = DCAirflow.FLOW.getID();
		}
		t = code & 15;
		h = (code >> 4) & 3;
		a = (code >> 6) & 3;
		return new int[] { t, h, a };
	}

	@Override
	public int getClimateIntFromIDs(int[] is) {
		int t = 0;
		int h = 0;
		int a = 0;
		if (is != null && is.length >= 3) {
			t = is[0];
			h = is[1];
			a = is[2];
			t = t & 15;
			h = h & 3;
			a = a & 3;
		}
		return t + (h << 4) + (a << 6);
	}

	@Override
	public IClimate getClimateFromParam(DCHeatTier heat, DCHumidity hum, DCAirflow air) {
		if (heat == null)
			heat = DCHeatTier.NORMAL;
		if (hum == null)
			hum = DCHumidity.NORMAL;
		if (air == null)
			air = DCAirflow.NORMAL;
		return new Climate(heat, hum, air);
	}

	@Override
	public IClimate getClimateFromNBT(CompoundTag nbt) {
		if (nbt != null && nbt.contains(NBT_KEY)) {
			int i = nbt.getInt(NBT_KEY);
			IClimate climate = getClimateFromInt(i);
			return climate;
		}
		return getDefaultClimate();
	}

	@Override
	public void setClimateToNBT(CompoundTag nbt, IClimate climate) {
		if (nbt != null && climate != null) {
			int i = climate.getClimateInt();
			nbt.putInt(NBT_KEY, i);
		}
	}

	@Override
	public String getNBTKey() {
		return NBT_KEY;
	}

	@Override
	public IClimate getDefaultClimate() {
		return new Climate(DCHeatTier.NORMAL, DCHumidity.NORMAL, DCAirflow.NORMAL);
	}

}
