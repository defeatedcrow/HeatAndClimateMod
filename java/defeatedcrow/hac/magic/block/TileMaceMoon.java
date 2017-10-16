package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.core.util.DCTimeHelper;

public class TileMaceMoon extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (world.canSeeSky(getPos()) && !world.isRaining()) {
			return !DCTimeHelper.isDayTime(world);
		}
		return false;
	}

}
