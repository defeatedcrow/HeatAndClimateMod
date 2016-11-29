package defeatedcrow.hac.machine.block;

public class TileShaft_TA_SUS extends TileShaft_TA {

	// tier
	@Override
	public float maxTorque() {
		return 512.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
	}
}
