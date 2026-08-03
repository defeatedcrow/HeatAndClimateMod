package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class ConveyorVerticalTile extends ConveyorTile {

	public ConveyorVerticalTile(BlockPos pos, BlockState state) {
		super(MachineInit.CONVEYOR_VERTICAL_TILE.get(), pos, state);
	}

	@Override
	protected BlockPos getInsertPos() {
		return getBlockPos().below();
	}

	@Override
	protected BlockPos getForwardPos() {
		return getBlockPos().above();
	}

	@Override
	protected boolean insertItem() {
		if (!DCUtil.isEmpty(getItem(0)))
			return false;

		if (super.insertItem())
			return true;

		// コンベアを貼り付けている方向もチェック
		Direction side = getBlockDir();
		BlockEntity input = getLevel().getBlockEntity(getBlockPos().relative(side));

		// DOWNからの搬出を偽装
		if (input != null && !(input instanceof ConveyorTile) && !(input instanceof Hopper)) {
			return input.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.DOWN)
			    .map(handler -> {
				    int slot = -1;
				    for (int j = 0; j < handler.getSlots(); j++) {
					    if (!handler.extractItem(j, 1, true)
					        .isEmpty()) {
						    slot = j;
						    break;
					    }
				    }
				    if (slot >= 0) {
					    ItemStack take = handler.extractItem(slot, 1, true)
					        .copy();
					    int i = getInventory().canIncrSlot(0, take);
					    if (i > 0) {
						    getInventory().incrStackInSlot(0, take);
						    handler.extractItem(slot, 1, false);
						    setChanged();
						    input.setChanged();
						    return true;
					    }
				    }
				    return false;
			    })
			    .orElse(false);
		}
		return false;
	}

	@Override
	protected boolean releaseItem() {
		if (DCUtil.isEmpty(getItem(0)))
			return false;

		if (super.releaseItem())
			return true;

		Direction side = getBlockDir();
		BlockPos next = getForwardPos().relative(side);
		BlockEntity outlet = getLevel().getBlockEntity(next);
		boolean flag = false;
		if (outlet != null) {
			flag = outlet.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP)
			    .map(handler -> {
				    ItemStack take = getItem(0).copy();
				    take.setCount(1);
				    for (int j = 0; j < handler.getSlots(); j++) {
					    ItemStack ret = handler.insertItem(j, take, true);
					    if (DCUtil.isEmpty(ret)) {
						    handler.insertItem(j, take, false);
						    getInventory().removeItem(0, 1);
						    setChanged();
						    outlet.setChanged();
						    break;
					    }
				    }
				    return DCUtil.isEmpty(getItem(0));
			    })
			    .orElse(false);
		}
		return flag;
	}
}
