package defeatedcrow.hac.food.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.gui.ContainerIncubator;
import defeatedcrow.hac.food.item.brewing.ItemMedium;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.brewing.EnumMedium;
import defeatedcrow.hac.main.api.brewing.IMediumItem;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageIncubatorButton;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
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

public class TileIncubator extends ClimateReceiverLockable implements ISidedInventory {

	public int lastD = 0;
	public int lastClimate = 0;

	public boolean isOpen = false;
	public int numPlayersUsing = 0;

	@Override
	public void updateTile() {
		if (!getWorld().isRemote && current != null) {
			int day = DCTimeHelper.getDay(getWorld());
			if (day > lastD) {
				for (int s = 0; s < 17; s++) {
					ItemStack item = getStackInSlot(s);
					if (!DCUtil.isEmpty(item)) {
						if (isItemValidForSlot(s, item)) {

							if (item.getItem() instanceof ItemMedium) {
								IMicrobe microbe = ItemMedium.getMicrobeData(item);
								int count = ((ItemMedium) item.getItem()).getMedium(item) == EnumMedium.SIMPLE ? 3 : 5;
								if (microbe != null) {
									if (isSuitableClimate(microbe)) {
										addIncubationDays(item);
										removeUnsuitableDays(item);
										int di = getIncubationDays(item);
										if (di >= microbe.getIncubationDays()) {
											// cultivation result
											ItemStack ret = ItemMedium.getMicrobeItem(item);
											ret.setCount(count);
											this.setInventorySlotContents(s, ret);
										} else {

										}
									} else {
										addUnsuitableDays(item);
										int bi = getUnsuitableDays(item);
										if (bi >= microbe.getIncubationDays()) {
											// failed result
											this.decrStackSize(s, 1);
										}
									}
								}
							} else if (MainUtil.hasDic(item, "egg")) {
								if (isSuitableClimateForChick()) {
									addIncubationDays(item);
									int di = getIncubationDays(item);
									if (di >= 3) {
										// cultivation result
										ItemStack ret = new ItemStack(FoodInit.chickInEgg);
										this.setInventorySlotContents(s, ret);
									}
								} else if (getClimate().getHeat().getID() > DCHeatTier.BOIL.getID()) {
									ItemStack ret = new ItemStack(MainInit.bakedApple, 1, 1);
									this.setInventorySlotContents(s, ret);
								} else {
									addUnsuitableDays(item);
									int bi = getUnsuitableDays(item);
									if (bi >= 3) {
										// failed result
										this.decrStackSize(s, 1);
									}
								}
							}
						}
					}
				}
			}
			lastD = day;

		}
		if (current == null) {
			current = ClimateAPI.register.getClimateFromParam(DCHeatTier.WARM, DCHumidity.NORMAL, DCAirflow.NORMAL);
		}
	}

	public boolean isSuitableClimate(IMicrobe microbe) {
		DCHeatTier heat = current.getHeat();
		DCHumidity hum = current.getHumidity();
		DCAirflow air = current.getAirflow();
		if (microbe.getHeats().contains(heat) && microbe.getHums().contains(hum) && microbe.getAirs().contains(air)) {
			return true;
		}
		return false;
	}

	public boolean isSuitableClimateForChick() {
		DCHeatTier heat = current.getHeat();
		DCHumidity hum = current.getHumidity();
		DCAirflow air = current.getAirflow();
		if (heat == DCHeatTier.WARM && hum == DCHumidity.NORMAL && air.getID() > DCAirflow.TIGHT.getID()) {
			return true;
		}
		return false;
	}

	public static ItemStack addIncubationDays(ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			int i = tag.getInteger("microbe_days");
			i++;
			tag.setInteger("microbe_days", i);
			item.setTagCompound(tag);
		}
		return item;
	}

	public static int getIncubationDays(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("microbe_days")) {
				int i = tag.getInteger("microbe_days");
				return i;
			}
		}
		return 0;
	}

	public static int getUnsuitableDays(ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			int i = tag.getInteger("microbe_baddays");
			return i;
		}
		return 0;
	}

	public static ItemStack addUnsuitableDays(ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			int i = tag.getInteger("microbe_baddays");
			i++;
			tag.setInteger("microbe_baddays", i);
			item.setTagCompound(tag);
		}
		return item;
	}

	public static ItemStack removeUnsuitableDays(ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag != null && tag.hasKey("microbe_baddays")) {
				tag.removeTag("microbe_baddays");
				item.setTagCompound(tag);
			}
		}
		return item;
	}

	public void setClimate(IClimate climate) {
		this.current = climate;
	}

	public void setClimateAndPacket(IClimate climate) {
		setClimate(climate);
		DCMainPacket.INSTANCE.sendToServer(new MessageIncubatorButton(getPos(), (short) climate.getClimateInt()));
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	protected DCInventory invs = new DCInventory(18);

	protected int[] slots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
	};

	// スロット数
	@Override
	public int getSizeInventory() {
		return 18;
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
		return "dcs.gui.device.incubator";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 1;
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
		if (!DCUtil.isEmpty(stack)) {
			if (MainUtil.hasDic(stack, "egg")) {
				return true;
			} else if (stack.getItem() instanceof IMediumItem) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return slots();
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStack);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return !DCUtil.isEmpty(stack) && this.isItemValidForSlot(index, stack);
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
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		invs.clear();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST) {
		@Override
		public int getSlotLimit(int slot) {
			return 1;
		}
	};

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) handlerSide;
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public boolean isEmpty() {
		return invs.isEmpty();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerIncubator(this, playerInventory);
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
		tag.setInteger("LastDay", this.lastD);
		// アイテムの書き込み
		invs.writeToNBT(tag);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		invs.readFromNBT(tag);

		this.lastD = tag.getInteger("LastDay");
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
