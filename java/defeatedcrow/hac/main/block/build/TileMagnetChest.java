package defeatedcrow.hac.main.block.build;

import java.util.List;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;

public class TileMagnetChest extends TileLowChest {

	@Override
	protected SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_IRON_DOOR_OPEN;
	}

	@Override
	protected SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_IRON_DOOR_CLOSE;
	}

	@Override
	public void updateTile() {
		if (!worldObj.isRemote) {
			double x1 = pos.getX() - 2.0D;
			double y1 = pos.getY() - 2.0D;
			double z1 = pos.getZ() - 2.0D;
			double x2 = pos.getX() + 3.0D;
			double y2 = pos.getY() + 3.0D;
			double z2 = pos.getZ() + 3.0D;
			List<EntityItem> list = this.worldObj.<EntityItem>getEntitiesWithinAABB(EntityItem.class,
					new AxisAlignedBB(x1, y1, z1, x2, y2, z2), EntitySelectors.IS_ALIVE);

			if (!list.isEmpty()) {
				for (EntityItem drop : list) {
					if (drop != null && !DCUtil.isEmpty(drop.getEntityItem())) {
						ItemStack copy = drop.getEntityItem().copy();
						int slot = canInsertSlot(copy);
						// DCLogger.debugLog("slot: " + slot);
						if (slot > -1) {
							int i = 0;
							if (DCUtil.isEmpty(getStackInSlot(slot))) {
								i = copy.stackSize;
							} else {
								i = this.isItemStackable(copy, this.getStackInSlot(slot));
							}
							if (i > 0) {
								this.incrStackInSlot(slot, drop.getEntityItem());
								this.markDirty();
								DCUtil.reduceStackSize(copy, 1);
								if (!DCUtil.isEmpty(copy) && copy.stackSize > 0) {
									drop.setEntityItemStack(copy);
								} else {
									drop.setDead();
								}
							}
						}
					}
				}
			}
		}

		super.updateTile();
	}

	protected int canInsertSlot(ItemStack stack) {
		if (DCUtil.isEmpty(stack))
			return -1;

		for (int i = 0; i < this.getSizeInventory(); i++) {
			ItemStack cur = this.getStackInSlot(i);
			if (DCUtil.isEmpty(cur))
				return i;
			else {
				int ret = this.isItemStackable(stack, cur);
				if (ret > 0)
					return i;
			}
		}
		return -1;
	}
}
