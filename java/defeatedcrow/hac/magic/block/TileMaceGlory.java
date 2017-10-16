package defeatedcrow.hac.magic.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class TileMaceGlory extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		IBlockState under = world.getBlockState(getPos().down());
		if (under != null && under.getBlock() == Blocks.DIAMOND_BLOCK)
			return true;
		return false;
	}

}
