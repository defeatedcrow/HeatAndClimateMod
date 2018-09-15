package defeatedcrow.hac.machine.block;

public class TileShaft_X_Steel extends TileShaft_X {

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
