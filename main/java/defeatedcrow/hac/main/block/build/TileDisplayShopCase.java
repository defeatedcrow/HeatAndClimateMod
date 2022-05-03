package defeatedcrow.hac.main.block.build;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ItemSet;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.client.gui.ContainerDisplayShopCase;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

public class TileDisplayShopCase extends TileDisplayShelf implements IInventory {

	public final int PRICE_MAX = 64;
	public int price1 = 1;
	public int price2 = 1;
	public final int EMERALD_MAX = 9999;
	private int emeraldCount = 0;
	private int emeraldTotal = 0;

	public TileDisplayShopCase() {
		super();
	}

	public int getEmerald() {
		return emeraldCount;
	}

	public int getEmeraldTotal() {
		return emeraldTotal;
	}

	public boolean setEmerald(int i) {
		if (i > 0 || i < 10000) {
			emeraldCount = i;
			return true;
		} else {
			return false;
		}
	}

	public boolean addEmerald(int i) {
		if (canAddEmerald(i)) {
			emeraldCount += i;
			return true;
		} else {
			return false;
		}
	}

	public boolean canAddEmerald(int i) {
		int set = emeraldCount + i;
		if (set > 0 || set < 10000) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void onServerUpdate() {
		super.onServerUpdate();
		if (emeraldCount > 0) {
			int i = DCUtil.isEmpty(getStackInSlot(2)) ? 0 : getStackInSlot(2).getCount();
			int add = Math.min(emeraldCount, 64 - i);
			ItemSet ret = MainCoreConfig.currency;
			add = this.inv.canIncr(2, new ItemStack(ret.item, add, ret.meta));
			if (add > 0) {
				this.incrStackInSlot(2, new ItemStack(ret.item, add, ret.meta));
				emeraldCount -= add;
				this.markDirty();
			}
		}
		emeraldTotal = emeraldCount + this.getStackInSlot(2).getCount();
	}

	// protected int getUpdateCount() {
	// return emeraldCount;
	// }

	@Override
	public int getSizeInventory() {
		return 3;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerDisplayShopCase(this, playerIn, false);
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return price1;
		case 1:
			return price2;
		case 2:
			return emeraldCount;
		case 3:
			return emeraldTotal;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			price1 = value;
			break;
		case 1:
			price2 = value;
			break;
		case 2:
			emeraldCount = value;
			break;
		case 3:
			emeraldTotal = value;
			break;
		default:
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.displaycase";
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// アイテムの書き込み
		tag.setInteger("price1", price1);
		tag.setInteger("price2", price2);
		tag.setInteger("emerald", emeraldCount);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		price1 = tag.getInteger("price1");
		price2 = tag.getInteger("price2");
		emeraldCount = tag.getInteger("emerald");
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
}
