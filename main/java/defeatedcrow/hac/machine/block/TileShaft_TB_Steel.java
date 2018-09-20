package defeatedcrow.hac.machine.block;

public class TileShaft_TB_Steel extends TileShaft_TB {

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
