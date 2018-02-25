package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.core.util.DCTimeHelper;

public class TileMaceMoon extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (worldObj.canSeeSky(getPos()) && !worldObj.isRaining())
			return !DCTimeHelper.isDayTime(worldObj);
		return false;
	}

}
