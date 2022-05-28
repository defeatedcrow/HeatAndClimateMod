package defeatedcrow.hac.main.block.build;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ItemSet;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.client.gui.ContainerDisplayVendingMachine;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageColorID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileDisplayVendingMachine extends TileDisplayShelf implements IInventory, IColorChangeTile {

	public final int PRICE_MAX = 64;
	public int price1 = 1;
	public int price2 = 1;
	public int price3 = 1;
	public int price4 = 1;
	public final int EMERALD_MAX = 9999;
	private int emeraldCount = 0;
	private int emeraldTotal = 0;

	public TileDisplayVendingMachine() {
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

	public int calcRedstone() {
		float rs = (float) emeraldTotal / EMERALD_MAX;
		return MathHelper.floor(rs * 15.0F);
	}

	@Override
	protected void onServerUpdate() {
		super.onServerUpdate();
		if (emeraldCount > 0) {
			int i = DCUtil.isEmpty(getStackInSlot(4)) ? 0 : getStackInSlot(4).getCount();
			int add = Math.min(emeraldCount, 64 - i);
			ItemSet ret = MainCoreConfig.currency;
			add = this.inv.canIncr(4, new ItemStack(ret.item, add, ret.meta));
			if (add > 0) {
				this.incrStackInSlot(4, new ItemStack(ret.item, add, ret.meta));
				emeraldCount -= add;
				this.markDirty();
			}
		}
		emeraldTotal = emeraldCount + this.getStackInSlot(4).getCount();
	}

	// protected int getUpdateCount() {
	// return emeraldCount;
	// }

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerDisplayVendingMachine(this, playerIn, false);
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return price1;
		case 1:
			return price2;
		case 2:
			return price3;
		case 3:
			return price4;
		case 4:
			return emeraldCount;
		case 5:
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
			price3 = value;
			break;
		case 3:
			price4 = value;
			break;
		case 4:
			emeraldCount = value;
			break;
		case 5:
			emeraldTotal = value;
			break;
		default:
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 6;
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.vending_machine";
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// アイテムの書き込み
		tag.setInteger("price1", price1);
		tag.setInteger("price2", price2);
		tag.setInteger("price3", price3);
		tag.setInteger("price4", price4);
		tag.setInteger("emerald", emeraldCount);
		tag.setInteger("Color", this.color);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		price1 = tag.getInteger("price1");
		price2 = tag.getInteger("price2");
		price3 = tag.getInteger("price3");
		price4 = tag.getInteger("price4");
		emeraldCount = tag.getInteger("emerald");
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

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = new net.minecraft.util.math.AxisAlignedBB(getPos().add(-1, -1, -1), getPos().add(1, 1, 1));
		return bb;
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
