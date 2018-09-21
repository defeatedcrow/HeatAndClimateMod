package defeatedcrow.hac.machine.block;

import net.minecraft.block.state.IBlockState;

public class TileMonitorRedStone extends TileMonitorBase {

	@Override
	public String unit() {
		return "RS";
	}

	@Override
	protected boolean updateAmount() {
		IBlockState state = world.getBlockState(getPairPos());
		if (state == null)
			return false;

		if (state.canProvidePower()) {
			int power = state.getWeakPower(world, getPairPos(), getSide().getFacing());
			amount = power;
			amountMax = 15F;
			return true;
		}
		return false;
	}

}
