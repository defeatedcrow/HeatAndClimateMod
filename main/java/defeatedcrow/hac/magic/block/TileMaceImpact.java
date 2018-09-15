package defeatedcrow.hac.magic.block;

public class TileMaceImpact extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (world.canSeeSky(getPos()) && world.isRaining()) {
			return true;
		}
		return false;
	}

}
