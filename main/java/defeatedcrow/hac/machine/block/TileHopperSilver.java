package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.recipes.BlockContainerUtil;
import defeatedcrow.hac.main.recipes.BlockContainerUtil.ReturnPair;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileHopperSilver extends TileHopperFilter {

	@Override
	public int getCoolTime() {
		return 8;
	}

	@Override
	protected boolean suctionDrop() {
		return false;
	}

	@Override
	public boolean isFilterd() {
		return false;
	}

	@Override
	protected boolean suctionItem() {
		EnumFacing face = getCurrentFacing() == EnumFacing.UP ? EnumFacing.DOWN : EnumFacing.UP;
		TileEntity tile = world.getTileEntity(pos.offset(face));
		if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
			if (target != null) {
				boolean b = false;
				for (int i = 0; i < target.getSlots(); i++) {
					ItemStack item = target.extractItem(i, 9, true);
					ReturnPair pair = BlockContainerUtil.INS.getReturnItem(item);
					if (pair != null) {
						int min = pair.count;
						ItemStack ret = pair.item.copy();
						if (item.getCount() >= min) {
							for (int j = 0; j < this.getSizeInventory(); j++) {
								ItemStack cur = this.getStackInSlot(j);
								if (this.isItemStackable(ret, cur) > 0) {
									this.incrStackInSlot(j, ret);
									target.extractItem(i, min, false);
									this.markDirty();
									tile.markDirty();
									return true;
								}
							}
						}
					} else if (!DCUtil.isEmpty(item)) {
						for (int j = 0; j < this.getSizeInventory(); j++) {
							ItemStack cur = this.getStackInSlot(j);
							int min = this.isItemStackable(item, cur);
							if (min > 0) {
								if (min > 8) {
									min = 8;
								}
								item.setCount(min);
								this.incrStackInSlot(j, item);
								target.extractItem(i, min, false);
								this.markDirty();
								tile.markDirty();
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	protected boolean extractItem() {
		EnumFacing face = getCurrentFacing();
		if (face != null) {
			TileEntity tile = world.getTileEntity(pos.offset(face));
			if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite())) {
				IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face
						.getOpposite());
				if (target != null) {
					boolean b = false;
					for (int i = 0; i < this.getSizeInventory(); i++) {
						ItemStack item = inv.getStackInSlot(i);
						if (!DCUtil.isEmpty(item) && DCUtil.getSize(item) > 0) {
							ItemStack ins = item.copy();
							if (ins.getCount() > 8) {
								ins.setCount(8);
							}
							int min = ins.getCount();
							for (int j = 0; j < target.getSlots(); j++) {
								ItemStack ret = target.insertItem(j, ins, false);
								if (DCUtil.isEmpty(ret)) {
									this.decrStackSize(i, min);
									this.markDirty();
									tile.markDirty();
									return true;
								} else if (ret.getCount() < min) {
									this.decrStackSize(i, 8 - ret.getCount());
									this.markDirty();
									tile.markDirty();
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}
