package defeatedcrow.hac.main.block.build;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.block.DCExclusiveTE;
import defeatedcrow.hac.main.client.gui.ContainerDisplayShelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileDisplayShelf extends DCExclusiveTE implements IInventory {

	public TileDisplayShelf() {
		super();
	}

	private int lastCount = 0;
	private int count = 0;

	@Override
	protected void onServerUpdate() {
		super.onServerUpdate();
		boolean flag = false;
		if (count > 10) {
			int i = 0;
			for (int j = 0; j < this.getSizeInventory(); j++) {
				if (!DCUtil.isEmpty(this.getStackInSlot(j))) {
					i += this.getStackInSlot(j).getCount() + this.getStackInSlot(j).getItem().hashCode();
				}
			}
			if (lastCount != i) {
				lastCount = i;
				flag = true;
			}
			count = 0;
		} else {
			count++;
		}

		if (flag) {
			if (!this.hasWorld())
				return;
			List<EntityPlayer> list = this.getWorld().playerEntities;
			for (EntityPlayer player : list) {
				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
				}
			}
		}
	}

	/* ========== NBT ========== */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		inv.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// アイテムの書き込み
		inv.writeToNBT(tag);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// アイテムの書き込み
		inv.writeToNBT(tag);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);
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

	/* ========== 以下、IInventoryのメソッド ========== */

	public DCInventory inv = new DCInventory(this.getSizeInventory());

	// スロット数
	@Override
	public int getSizeInventory() {
		return 3;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		return inv.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		return inv.decrStackSize(i, num);
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		inv.setInventorySlotContents(i, stack);
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.chest";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (getWorld().getTileEntity(this.pos) != this || player == null)
			return false;
		else
			return Math.sqrt(player.getDistanceSq(pos)) < 256D;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}

	// 追加メソッド
	public static int isItemStackable(ItemStack target, ItemStack current) {
		return DCInventory.isItemStackable(target, current);
	}

	public void incrStackInSlot(int i, ItemStack input) {
		inv.incrStackInSlot(i, input);
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		return inv.removeStackFromSlot(i);
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	IItemHandler handler = new InvWrapper(this);

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) handler;
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerDisplayShelf(this, playerIn);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:display_shelf";
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

}
