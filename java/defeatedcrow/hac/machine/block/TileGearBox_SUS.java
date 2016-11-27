package defeatedcrow.hac.machine.block;

public class TileGearBox_SUS extends TileGearBox {

	// tier
	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
	}
}
