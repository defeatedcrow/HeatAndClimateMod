package defeatedcrow.hac.machine.block;

public class TileShaft_TA_Steel extends TileShaft_TA {

	// tier
	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}
}
