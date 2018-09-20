package defeatedcrow.hac.machine.block;

public class TileShaft_S_Steel extends TileShaft_S {

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
