package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidUtil;

public class ConveyorFillerTile extends ConveyorTile {

	public ConveyorFillerTile(BlockPos pos, BlockState state) {
		super(MachineInit.CONVEYOR_FILLER_TILE.get(), pos, state);
	}

	@Override
	protected boolean onMiddlePosition() {
		if (!DCUtil.isEmpty(this.getItem(0))) {
			ItemStack copy = this.getItem(0).copy();
			BlockPos next = getBlockPos().above();
			ItemStack ret = FluidUtil.getFluidHandler(getLevel(), next, Direction.DOWN)
					.map(handler -> {
						return DCFluidUtil.fillFluidItem(getLevel(), handler, copy);
					})
					.orElse(copy);
			this.getInventory().setItem(0, ret);
			this.setChanged();
			boolean flag = FluidUtil.getFluidHandler(copy)
					.map(handler -> {
						return handler.getFluidInTank(0).getAmount() >= handler.getTankCapacity(0);
					}).orElse(true);
			return flag;
		}
		return true;
	}

}
