package defeatedcrow.hac.main.block.build;

import java.util.List;

import defeatedcrow.hac.core.DCLogger;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;

public class TileVillageChest extends TileLowChest {

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
			double x1 = pos.getX() - 4.0D;
			double y1 = pos.getY() - 2.0D;
			double z1 = pos.getZ() - 4.0D;
			double x2 = pos.getX() + 5.0D;
			double y2 = pos.getY() + 3.0D;
			double z2 = pos.getZ() + 5.0D;
			List<EntityVillager> list = this.worldObj.<EntityVillager>getEntitiesWithinAABB(EntityVillager.class,
					new AxisAlignedBB(x1, y1, z1, x2, y2, z2), EntitySelectors.IS_ALIVE);

			if (!list.isEmpty()) {
				for (EntityVillager vil : list) {
					if (vil == null || !vil.isEntityAlive()) {
						continue;
					}
					IInventory inv = vil.getVillagerInventory();
					for (int i = 0; i < inv.getSizeInventory(); i++) {
						ItemStack item = inv.getStackInSlot(i);
						// canAbondonItems()とisFarmItemInInventory()をキープし続ける
						if (item != null) {
							int count = item.getItem() == Items.BREAD ? 6 : 30;
							// DCLogger.debugLog("villager slot" + i + ":" + item.getDisplayName() + "x" +
							// item.stackSize);
							if (item.stackSize > count) {
								int ret = item.stackSize - count;
								ItemStack ins = new ItemStack(item.getItem(), ret, item.getItemDamage());
								int slot = canInsertSlot(ins);
								if (slot > -1) {
									int j = 0;
									if (this.getStackInSlot(slot) == null) {
										j = ins.stackSize;
									} else {
										j = this.isItemStackable(ins, this.getStackInSlot(slot));
									}
									if (j > 0) {
										DCLogger.debugLog("ins" + j);
										this.incrStackInSlot(slot, ins);
										this.markDirty();
										inv.decrStackSize(i, j);
									}
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
		if (stack == null)
			return -1;

		for (int i = 0; i < this.getSizeInventory(); i++) {
			ItemStack cur = this.getStackInSlot(i);
			if (cur == null)
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
