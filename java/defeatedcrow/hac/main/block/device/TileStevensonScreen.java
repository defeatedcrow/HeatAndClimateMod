package defeatedcrow.hac.main.block.device;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimateTileEntity;
import defeatedcrow.hac.core.base.ClimateReceiveTile;

public class TileStevensonScreen extends ClimateReceiveTile {

	@Override
	public void updateTile() {
		DCHeatTier heat = ClimateAPI.calculator.getHeat(worldObj, pos, 2, false);
		DCHeatTier cold = ClimateAPI.calculator.getCold(worldObj, pos, 2, false);
		DCHumidity hum = ClimateAPI.calculator.getHumidity(worldObj, pos, 1, false);
		DCAirflow air = ClimateAPI.calculator.getAirflow(worldObj, pos, 1, false);

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

}
