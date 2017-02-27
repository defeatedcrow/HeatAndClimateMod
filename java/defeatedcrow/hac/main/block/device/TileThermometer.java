package defeatedcrow.hac.main.block.device;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimateTileEntity;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.base.ClimateReceiveTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileThermometer extends ClimateReceiveTile {

	private int lastClimate = 0;

	@Override
	public void updateTile() {
		DCHeatTier heat = ClimateAPI.calculator.getHeat(worldObj, pos, CoreConfigDC.heatRange, false);
		DCHeatTier cold = ClimateAPI.calculator.getCold(worldObj, pos, CoreConfigDC.heatRange, false);
		DCHumidity hum = ClimateAPI.calculator.getHumidity(worldObj, pos);
		DCAirflow air = ClimateAPI.calculator.getAirflow(worldObj, pos);

		List<BlockPos> remove = new ArrayList<BlockPos>();
		for (BlockPos p : effectiveTiles) {
			TileEntity tile = worldObj.getTileEntity(p);
			if (tile != null && tile instanceof IClimateTileEntity) {
				IClimateTileEntity effect = (IClimateTileEntity) tile;
				if (effect.isActive()) {
					DCHeatTier heat2 = effect.getHeatTier(pos);
					DCHumidity hum2 = effect.getHumidity(pos);
					DCAirflow air2 = effect.getAirflow(pos);

					if (heat2 != DCHeatTier.NORMAL && heat2.getTier() > heat.getTier()) {
						heat = heat2;
					}
					if (heat2 != DCHeatTier.NORMAL && heat2.getTier() < cold.getTier()) {
						cold = heat2;
					}
					if (hum2 != DCHumidity.NORMAL && hum2.getID() > hum.getID()) {
						hum = hum2;
					}
					if (air2 != DCAirflow.NORMAL && air2.getID() > air.getID()) {
						air = air2;
					}
				}
			} else {
				remove.add(p);
			}
		}

		if (heat.getTier() > cold.getTier() && cold.getTier() < 0) {
			if (heat.getTier() < 0) {
				heat = cold;
			} else {
				heat = heat.addTier(cold.getTier());
			}
		}
		int code = (air.getID() << 6) + (hum.getID() << 4) + heat.getID();
		current = ClimateAPI.register.getClimateFromInt(code);
	}

	public float[] floats = new float[12];
	public float[] lastFloats = new float[12];

	@Override
	public void onTickUpdate() {
		if (worldObj.isRemote && current != null) {
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
