package defeatedcrow.hac.machine.block;

public class TileGearBox_SUS extends TileGearBox {

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
