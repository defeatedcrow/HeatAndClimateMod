package defeatedcrow.hac.food.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.gui.ContainerSilkwormBox;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
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
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileSilkwormBox extends ClimateReceiverLockable implements ISidedInventory {

	public int currentH = 0;
	public int lastH = 0;
	public int count = 0;

	@Override
	public void updateTile() {
		if (!getWorld().isRemote && current != null) {
			if (count > 20) {
				for (int s = 1; s < 10; s++) {
					ItemStack item = getStackInSlot(s);
					if (!DCUtil.isEmpty(item) && item.getItem() == MainInit.silkworm) {
						if (item.getItemDamage() == 0) {
							setInventorySlotContents(s, new ItemStack(MainInit.silkworm, 1, 1));
						} else if (item.getItemDamage() == 1 && isActive()) {

							NBTTagCompound tag = item.getTagCompound();
							if (tag == null) {
								tag = new NBTTagCompound();
							}
							int stage = 0;
							if (tag.hasKey("RearingStage")) {
								stage = tag.getInteger("RearingStage");
							}
							stage++;
							if (stage >= 50) {

								boolean flag = false;
								for (int s2 = 10; s2 < 19; s2++) {
									if (DCUtil.isEmpty(getStackInSlot(s2))) {
										setInventorySlotContents(s2, new ItemStack(MainInit.silkworm, 1, 2));
										flag = true;
										break;
									}
								}

								if (flag) {
									removeStackFromSlot(s);
									markDirty();
								}

							} else {
								if (stage % 10 == 0 || stage % 10 == 5) {
									decrStackSize(0, 1);
								}
								tag.setInteger("RearingStage", stage);
								item.setTagCompound(tag);
							}
						}
					}
				}
				count = 0;
			} else {
				count++;
			}
		}
		super.updateTile();
	}

	@Override
	protected void onServerUpdate() {
		super.onServerUpdate();
		boolean leaves = !DCUtil.isEmpty(getStackInSlot(0));
		if (BlockSilkwormBox.isLit(getWorld(), getPos()) != leaves) {
			BlockSilkwormBox.changeLitState(getWorld(), getPos(), leaves);
		}
	}

	public boolean isActive() {
		if (current != null) {
			boolean air = current.getAirflow() != DCAirflow.TIGHT;
			boolean temp = current.getHeat().getTier() > DCHeatTier.COOL.getTier()
					&& current.getHeat().getTier() < DCHeatTier.OVEN.getTier();
			boolean leaves = !DCUtil.isEmpty(getStackInSlot(0));
			return air && temp && leaves;
		}
		return false;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	protected DCInventory invs = new DCInventory(19);

	protected int[] slotsTop() {
		return new int[] {
				0,
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9
		};
	};

	protected int[] slotsBottom() {
		return new int[] {
				10,
				11,
				12,
				13,
				14,
				15,
				16,
				17,
				18
		};
	};

	protected int[] slotsSides() {
		return new int[] {
				0,
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				11,
				12,
				13,
				14,
				15,
				16,
				17,
				18
		};
	};

	// スロット数
	@Override
	public int getSizeInventory() {
		return 19;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		return invs.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		return invs.decrStackSize(i, num);
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		invs.setInventorySlotContents(i, stack);
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.silkworm_box";
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
		if (getWorld().getTileEntity(this.pos) != this || player == null)
			return false;
		else
			return Math.sqrt(player.getDistanceSq(pos)) < 256D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			if (i == 0)
				return stack.getItem() == Item.getItemFromBlock(FoodInit.leavesMorus);
			else if (i > 9)
				return false;
			else
				return stack.getItem() == MainInit.silkworm && stack.getItemDamage() < 2;
		}
		return false;
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsBottom() : (side == EnumFacing.UP ? slotsTop() : slotsSides());
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStack);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index < 10)
			return false;

		return true;
	}

	// 追加メソッド
	public static int isItemStackable(ItemStack target, ItemStack current) {
		return DCInventory.isItemStackable(target, current);
	}

	public void incrStackInSlot(int i, ItemStack input) {
		invs.incrStackInSlot(i, input);
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		return invs.removeStackFromSlot(i);
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.currentH;
		case 1:
			return this.lastH;
		case 2:
			return this.current.getClimateInt();
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.currentH = value;
			break;
		case 1:
			this.lastH = value;
			break;
		case 2:
			this.current = ClimateAPI.register.getClimateFromInt(value);
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public void clear() {
		invs.clear();
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
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
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

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerSilkwormBox(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return getName();
	}

	/* === NBT === */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.setNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.getNBT(tag);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("CurrentDay", this.currentH);
		tag.setInteger("LastDay", this.lastH);

		// アイテムの書き込み
		invs.writeToNBT(tag);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		invs.readFromNBT(tag);

		this.currentH = tag.getInteger("CurrentDay");
		this.lastH = tag.getInteger("LastDay");
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
