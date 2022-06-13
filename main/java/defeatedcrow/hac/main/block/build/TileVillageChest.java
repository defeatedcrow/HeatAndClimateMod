package defeatedcrow.hac.main.block.build;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageColorID;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;

public class TileVillageChest extends TileLowChest implements IColorChangeTile {

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
		if (!world.isRemote) {
			// 最寄りの村
			double x1 = pos.getX() - 64.0D;
			double y1 = pos.getY() - 5.0D;
			double z1 = pos.getZ() - 64.0D;
			double x2 = pos.getX() + 64.0D;
			double y2 = pos.getY() + 5.0D;
			double z2 = pos.getZ() + 64.0D;
			List<EntityVillager> list = this.world
					.<EntityVillager>getEntitiesWithinAABB(EntityVillager.class, new AxisAlignedBB(x1, y1, z1, x2, y2, z2), EntitySelectors.IS_ALIVE);

			if (!list.isEmpty()) {
				for (EntityVillager vil : list) {
					if (vil == null || !vil.isEntityAlive()) {
						continue;
					}
					IInventory inv = vil.getVillagerInventory();
					for (int i = 0; i < inv.getSizeInventory(); i++) {
						ItemStack item = inv.getStackInSlot(i);
						// canAbondonItems()とisFarmItemInInventory()をキープし続ける
						if (!DCUtil.isEmpty(item)) {
							int count = item.getItem() == Items.BREAD ? 6 : 30;
							// DCLogger.debugLog("villager slot" + i + ":" + item.getDisplayName() + "x" +
							// item.stackSize);
							if (item.getCount() > count) {
								int ret = item.getCount() - count;
								ItemStack ins = new ItemStack(item.getItem(), ret, item.getItemDamage());
								int slot = canInsertSlot(ins);
								if (slot > -1) {
									int j = 0;
									if (DCUtil.isEmpty(getStackInSlot(slot))) {
										j = ins.getCount();
									} else {
										j = this.isItemStackable(ins, this.getStackInSlot(slot));
									}
									if (j > 0) {
										DCLogger.debugLog("village chest ins" + j);
										this.incrStackInSlot(slot, ins);
										this.markDirty();
										inv.decrStackSize(i, j);
										inv.markDirty();
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

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setInteger("Color", this.color);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		color = tag.getInteger("Color");
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new SPacketUpdateTileEntity(pos, -50, nbtTagCompound);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	// color

	protected int color = 0;

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public void setColor(int num) {
		color = num;
		if (color < 0 || color > 3) {
			color = 0;
		}
	}

	@Override
	public boolean rotateColor() {
		int c = color + 1;
		setColor(c);
		if (!world.isRemote) {
			DCMainPacket.INSTANCE.sendToAll(new MessageColorID(pos, color));
		}
		return true;
	}
}
