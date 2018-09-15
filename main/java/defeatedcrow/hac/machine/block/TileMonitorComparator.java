package defeatedcrow.hac.machine.block;

import net.minecraft.block.state.IBlockState;

public class TileMonitorComparator extends TileMonitorBase {

	@Override
	public String unit() {
		return "RS";
	}

	@Override
	protected boolean updateAmount() {
		IBlockState state = world.getBlockState(getPairPos());
		if (state == null)
			return false;

		if (state.hasComparatorInputOverride()) {
			int power = state.getComparatorInputOverride(world, getPairPos());
			amount = power;
			amountMax = 15F;
			return true;
		}
		return false;
	}

}
