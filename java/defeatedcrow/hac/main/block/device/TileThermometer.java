package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.base.ClimateReceiveTile;

public class TileThermometer extends ClimateReceiveTile {

	public float[] floats = new float[12];
	public float[] lastFloats = new float[12];

	@Override
	public void onTickUpdate() {
		if (world.isRemote && current != null) {
			for (int id = 0; id < DCHeatTier.values().length; id++) {
				lastFloats[id] = floats[id];

				float f = 0.0F;
				if (current.getHeat().getID() == id) {
					f = 7.0F;
				} else if (current.getHeat().getID() > id) {
					f = 14.0F;
				}

				float f1 = f - floats[id];
				f1 *= 0.5F;
				if (f1 > 0.1F) {
					f1 = 0.1F;
				} else if (f1 < -0.1F) {
					f1 = -0.1F;
				} else if (Math.abs(f1) < 0.005F) {
					f1 = 0.0F;
				}
				floats[id] += f1;
			}
		}
	}

}
