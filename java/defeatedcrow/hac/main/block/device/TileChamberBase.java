package defeatedcrow.hac.main.block.device;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;

/**
 * SidedInventory持ちHeat利用Tileのベースクラス
 */
public abstract class TileChamberBase extends ClimateReceiverLockable implements ISidedInventory {

	protected int currentBurnTime = 0;
	protected int maxBurnTime = 1;
	protected int currentClimate = 3;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = tag1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inv.length) {
				this.inv[b0] = ItemStack.loadItemStackFromNBT(tag1);
			}
		}

		this.currentBurnTime = tag.getInteger("BurnTime");
		this.maxBurnTime = tag.getInteger("MaxTime");
		this.currentClimate = tag.getByte("Climate");
		// DCLogger.debugLog("read " + this.currentBurnTime + ", " + this.maxBurnTime + ", " +
		// this.currentClimate);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentBurnTime);
		tag.setInteger("MaxTime", this.maxBurnTime);
		tag.setByte("Climate", (byte) this.currentClimate);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inv.length; ++i) {
			if (inv[i] != null) {
				NBTTagCompound tag1 = new NBTTagCompound();
				tag1.setByte("Slot", (byte) i);
				inv[i].writeToNBT(tag1);
				nbttaglist.appendTag(tag1);
			}
		}
		tag.setTag("InvItems", nbttaglist);
		// DCLogger.debugLog("write " + this.currentBurnTime + ", " + this.maxBurnTime + ", " +
		// this.currentClimate);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentBurnTime);
		tag.setInteger("MaxTime", this.maxBurnTime);
		tag.setByte("Climate", (byte) this.currentClimate);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inv.length; ++i) {
			if (inv[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inv[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("InvItems", nbttaglist);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inv.length) {
				this.inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.currentBurnTime = tag.getInteger("BurnTime");
		this.maxBurnTime = tag.getInteger("MaxTime");
		this.currentClimate = tag.getByte("Climate");
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

	public boolean isActive() {
		return this.currentBurnTime > 0;
	}

	public int getCurrentBurnTime() {
		return this.currentBurnTime;
	}

	public int getMaxBurnTime() {
		return this.maxBurnTime;
	}

	public int getCurrentHeatID() {
		return this.currentClimate;
	}

	public void setCurrentBurnTime(int i) {
		this.currentBurnTime = i;
	}

	public void setMaxBurnTime(int i) {
		this.maxBurnTime = i;
	}

	public void setCurrentHeatID(int i) {
		this.currentClimate = i;
	}

	public DCHeatTier getCurrentHeatTier() {
		return DCHeatTier.getTypeByID(currentClimate);
	}

	/* === 燃焼判定 === */

	public static int getBurnTime(ItemStack item) {
		int i = TileEntityFurnace.getItemBurnTime(item);
		int ret = i / 4;
		if (ret < 50)
			ret = 50;
		return ret;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	protected int[] slotsTop() {
		return new int[] { 0 };
	};

	protected int[] slotsBottom() {
		return new int[] {
				0,
				1,
				2,
				3 };
	};

	protected int[] slotsSides() {
		return new int[] {
				1,
				2,
				3 };
	};

	public ItemStack[] inv = new ItemStack[this.getSizeInventory()];

	// スロット数
	@Override
	public int getSizeInventory() {
		return 4;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		if (i < getSizeInventory())
			return this.inv[i];
		else
			return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		if (i < 0 || i >= this.getSizeInventory())
			return null;
		if (this.inv[i] != null) {
			ItemStack itemstack;

			if (this.inv[i].stackSize <= num) {
				itemstack = this.inv[i];
				this.inv[i] = null;
				return itemstack;
			} else {
				itemstack = this.inv[i].splitStack(num);
				if (this.inv[i].stackSize == 0) {
					this.inv[i] = null;
				}
				return itemstack;
			}
		} else
			return null;
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		if (i < 0 || i >= this.getSizeInventory()) {
			return;
		} else {
			this.inv[i] = stack;

			if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
				stack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.chamber";
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
	public boolean isUseableByPlayer(EntityPlayer player) {
		return getWorld().getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D,
				this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i > 0 ? false : getBurnTime(stack) > 0;
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsBottom() : (side == EnumFacing.UP ? slotsTop() : slotsSides());
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index == 0) {
			return false;
		}

		return true;
	}

	// 追加メソッド
	public static int isItemStackable(ItemStack target, ItemStack current) {
		if (target == null || current == null)
			return 0;

		if (target.getItem() == current.getItem() && target.getMetadata() == current.getMetadata()
				&& ItemStack.areItemStackTagsEqual(target, current)) {
			int i = current.stackSize + target.stackSize;
			if (i > current.getMaxStackSize()) {
				i = current.getMaxStackSize() - current.stackSize;
				return i;
			}
			return target.stackSize;
		}

		return 0;
	}

	public void incrStackInSlot(int i, ItemStack input) {
		if (i < this.getSizeInventory() && input != null) {
			if (this.inv[i] != null) {
				if (this.inv[i].getItem() == input.getItem() && this.inv[i].getMetadata() == input.getMetadata()) {
					this.inv[i].stackSize += input.stackSize;
					if (this.inv[i].stackSize > this.getInventoryStackLimit()) {
						this.inv[i].stackSize = this.getInventoryStackLimit();
					}
				}
			} else {
				this.setInventorySlotContents(i, input);
			}
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < inv.length) {
			if (this.inv[i] != null) {
				ItemStack itemstack = this.inv[i];
				this.inv[i] = null;
				return itemstack;
			}
		}
		return null;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.currentBurnTime;
		case 1:
			return this.maxBurnTime;
		case 2:
			return this.currentClimate;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.currentBurnTime = value;
			break;
		case 1:
			this.maxBurnTime = value;
			break;
		case 2:
			this.currentClimate = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.inv.length; ++i) {
			this.inv[i] = null;
		}
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			if (facing == EnumFacing.DOWN)
				return (T) handlerBottom;
			else if (facing == EnumFacing.UP)
				return (T) handlerTop;
			else
				return (T) handlerSide;
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

}
