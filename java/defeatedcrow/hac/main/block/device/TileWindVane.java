package defeatedcrow.hac.main.block.device;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimateTileEntity;
import defeatedcrow.hac.core.base.ClimateReceiveTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileWindVane extends ClimateReceiveTile {

	private int lastClimate = 0;
	private int windPower = 15;

	@Override
	public void updateTile() {
		DCHeatTier heat = DCHeatTier.NORMAL;
		DCHeatTier cold = DCHeatTier.NORMAL;
		DCHumidity hum = DCHumidity.NORMAL;
		DCAirflow air = ClimateAPI.calculator.getAirflow(worldObj, pos);

		List<BlockPos> remove = new ArrayList<BlockPos>();
		for (BlockPos p : effectiveTiles) {
			TileEntity tile = worldObj.getTileEntity(p);
			if (tile != null && tile instanceof IClimateTileEntity) {
				IClimateTileEntity effect = (IClimateTileEntity) tile;
				if (effect.isActive()) {
					DCAirflow air2 = effect.getAirflow(pos);

					if (air2 != DCAirflow.NORMAL && air2.getID() > air.getID()) {
						air = air2;
					}
				}
			} else {
				remove.add(p);
			}
		}
		int code = (air.getID() << 6) + (hum.getID() << 4) + heat.getID();
		current = ClimateAPI.register.getClimateFromInt(code);

		if (worldObj.rand.nextInt(4) == 0) {
			windPower += worldObj.rand.nextInt(3) - 1;
		}
		if (windPower > 20) {
			windPower = 20;
		}
		if (windPower < 10) {
			windPower = 10;
		}
	}

	public float rot = 0.0F;
	public float lastRot = 0.0F;

	public float speed = 0.0F;
	public float lastSpeed = 0.0F;

	@Override
	public void onTickUpdate() {
		if (worldObj.isRemote && current != null) {
			lastRot = rot;
			lastSpeed = speed;

			float f = 0.0F;
			if (current.getAirflow() == DCAirflow.FLOW) {
				f = 0.5F * windPower;
			} else if (current.getAirflow() == DCAirflow.WIND) {
				f = 2.0F * windPower;
			}

			float f2 = (f - lastSpeed) * 0.5F;
			f2 += lastSpeed;
			speed = f2;

			float f1 = rot + speed;
			if (f1 > 360.0F) {
				f1 -= 360.0F;
				lastRot -= 360.0F;
			} else if (f1 < -360.0F) {
				f1 += 360.0F;
				lastRot += 360.0F;
			}
			rot = f1;
		}
	}

}
