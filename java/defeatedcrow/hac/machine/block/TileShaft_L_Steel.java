package defeatedcrow.hac.machine.block;

public class TileShaft_L_Steel extends TileShaft_L {

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
