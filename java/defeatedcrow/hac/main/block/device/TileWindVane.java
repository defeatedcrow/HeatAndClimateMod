package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.core.base.ClimateReceiveTile;

public class TileWindVane extends ClimateReceiveTile {

	private int lastClimate = 0;
	private int windPower = 15;

	@Override
	public void updateTile() {
		super.updateTile();

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
