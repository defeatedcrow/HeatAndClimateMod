package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class ConveyorDropperTile extends ConveyorTile {

	public ConveyorDropperTile(BlockPos pos, BlockState state) {
		super(MachineInit.CONVEYOR_DROPPER_TILE.get(), pos, state);
	}

	@Override
	protected boolean onMiddlePosition() {
		if (!DCUtil.isEmpty(this.getItem(0))) {
			BlockPos next = getBlockPos().below();
			BlockEntity outlet = getLevel().getBlockEntity(next);
			boolean flag = false;
			if (outlet != null) {
				flag = outlet.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).map(handler -> {
					ItemStack take = this.getItem(0).copy();
					take.setCount(1);
					for (int j = 0; j < handler.getSlots(); j++) {
						ItemStack ret = handler.insertItem(j, take, true);
						if (DCUtil.isEmpty(ret)) {
							handler.insertItem(j, take, false);
							this.getInventory().removeItem(0, 1);
							this.setChanged();
							outlet.setChanged();
							break;
						}
					}
					return DCUtil.isEmpty(this.getItem(0));
				}).orElse(false);
			}
		}
		return true;
	}
}
