package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.core.base.ClimateReceiveTile;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileWindVane extends ClimateReceiveTile {

	private int lastClimate = 0;
	public int lastPower = 15;
	public int windPower = 15;

	@Override
	public void updateTile() {
		super.updateTile();

		if (current != null && current.getAirflow().getID() >= DCAirflow.FLOW.getID()) {
			if (world.rand.nextInt(3) == 0) {
				windPower += world.rand.nextInt(5) - 2;
			}
			if (windPower > 20) {
				windPower = 20;
			}
			if (windPower < 10) {
				windPower = 10;
			}
		} else {
			windPower = 15;
		}
	}

	public float rot = 0.0F;
	public float lastRot = 0.0F;

	public float speed = 0.0F;
	public float lastSpeed = 0.0F;

	@Override
	public void onTickUpdate() {
		if (world.isRemote && current != null) {
			lastRot = rot;
			lastSpeed = speed;

			float f = 0.0F;
			if (current.getAirflow() == DCAirflow.FLOW) {
				f = 0.5F * windPower;
			} else if (current.getAirflow() == DCAirflow.WIND) {
				f = 2.0F * windPower;
			}

			float f2 = (f - lastSpeed) * 0.5F;
			speed = f2;

			float f1 = rot + speed;
			if (f1 > 720.0F || f1 < -720.0F) {
				f1 %= 720.0F;
				lastRot %= 720.0F;
			}
			rot = f1;
		}

		lastPower = windPower;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 16384.0D;
	}

}
