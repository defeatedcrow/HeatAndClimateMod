package defeatedcrow.hac.magic.block;

public class TileMaceLight extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		int light = world.getLight(getPos());
		return light > 13;
	}

}
