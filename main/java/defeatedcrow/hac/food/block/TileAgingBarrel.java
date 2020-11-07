package defeatedcrow.hac.food.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.food.gui.ContainerAgingBarrel;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IAgingRecipeDC;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageSingleTank;
import defeatedcrow.hac.plugin.DrinkPotionType;
import defeatedcrow.hac.plugin.DrinkPotionType.PotionSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileAgingBarrel extends ClimateReceiverLockable implements ISidedInventory {

	public AgingTank inputT = new AgingTank(5000);

	// process
	public int lastDay = 0;
	public int startDay = 0;
	public int maxCount = -1;

	private int lastTier = 0;
	private int lastBurn = 0;

	private int lastInT = 0;
	private int lastOutT = 0;

	public IAgingRecipeDC currentRecipe = null;

	@Override
	public void updateTile() {
		super.updateTile();
		if (!world.isRemote) {
			// 液体スロットの処理
			this.processFluidSlots();

			if (this.hasAgingFluid()) {
				if (DCTimeHelper.getDay(getWorld()) > lastDay) {
					lastDay = DCTimeHelper.getDay(getWorld());
					if (startDay == 0) {
						startDay = lastDay;
					} else {
						int c = inputT.getAge();
						int d = lastDay - startDay;
						if (d > 0)
							inputT.setAge(d);
					}
				}
			} else {
				inputT.setAge(0);
				boolean b = startDay > 0;
				maxCount = -1;
				startDay = 0;
				currentRecipe = null;
				if (b)
					markDirty();
			}

			// 完了処理
			if (this.maxCount > 0) {
				if (inputT.getAge() >= maxCount) {
					// レシピ進行の再チェック
					if (canRecipeProcess()) {
						if (onProcess()) {
							maxCount = -1;
							markDirty();
						}
					}
				}
			} else {
				canStartProcess();
			}
		}
	}

	private int count = 10;

	@Override
	protected void onServerUpdate() {
		if (count > 0) {
			count--;
		} else {
			boolean flag = false;
			if (inputT.getFluidAmount() + inputT.getFluidIdName().hashCode() != lastInT) {
				flag = true;
				lastInT = inputT.getFluidAmount() + inputT.getFluidIdName().hashCode();
			}

			if (flag) {
				String f1 = inputT.getFluidType() == null ? "empty" : inputT.getFluidType().getName();
				int a1 = inputT.getFluidAmount();
				DCMainPacket.INSTANCE.sendToAll(new MessageSingleTank(pos, f1, a1));
			}
			count = 10;
		}
	}

	public boolean isActive() {
		return this.maxCount > 0;
	}

	public int getCurrentBurnTime() {
		return inputT.getAge();
	}

	public int getMaxBurnTime() {
		return this.maxCount;
	}

	public void processFluidSlots() {
		this.processTank(inputT, 0, 1, false);
	}

	protected void processTank(AgingTank tank, int slot1, int slot2, boolean flag) {
		// flagはdrain優先かどうか
		if (!this.onDrainTank(tank, slot1, slot2, flag)) {
			this.onFillTank(tank, slot1, slot2);
		}
	}

	protected boolean onFillTank(AgingTank tank, int slot1, int slot2) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (DCUtil.isEmpty(in))
			return false;

		IFluidHandlerItem dummy = null;
		ItemStack in2 = in.copy();
		if (in2.getCount() > 1) {
			in2.setCount(1);
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandlerItem) {
			dummy = (IFluidHandlerItem) in2.getItem();
		}

		if (dummy != null && dummy.getTankProperties() != null && dummy.getTankProperties().length > 0) {
			boolean loose = false;
			ItemStack ret = ItemStack.EMPTY;

			int max = Math.min(dummy.getTankProperties()[0].getCapacity(), tank.getCapacity());
			FluidStack fc = dummy.drain(max, false);
			// 流入の場合
			if (fc != null && fc.amount > 0 && tank.canFillTarget(fc)) {
				ret = ItemStack.EMPTY;
				loose = false;
				boolean b = false;
				int rem = tank.getCapacity() - tank.getFluidAmount();
				fc = dummy.drain(rem, false);
				if (fc != null) {
					FluidStack fill = null;
					fill = dummy.drain(rem, true);
					ret = dummy.getContainer();

					if (fill != null && (DCUtil.isEmpty(ret) || DCUtil.isEmpty(out) || this
							.isItemStackable(ret, out) > 0)) {
						loose = true;
						tank.fill(fill, true);
					}
				}
			}

			if (loose) {
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				this.markDirty();
				return true;
			}
		}
		return false;
	}

	protected boolean onDrainTank(AgingTank tank, int slot1, int slot2, boolean flag) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (DCUtil.isEmpty(in))
			return false;

		IFluidHandlerItem dummy = null;
		ItemStack in2 = in.copy();
		if (in2.getCount() > 1) {
			in2.setCount(1);
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandlerItem) {
			dummy = (IFluidHandlerItem) in2.getItem();
		}

		if (tank.getFluidAmount() > 0 && dummy != null && dummy.getTankProperties() != null && dummy
				.getTankProperties().length > 0) {
			boolean loose = false;
			ItemStack ret = ItemStack.EMPTY;
			FluidStack send = tank.getFluid();
			int age = tank.getAge() / MainCoreConfig.aging_day;

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			boolean b = false;
			int rem = max;
			if (fc == null || fc.amount == 0) {
				b = true;
			} else if (tank.getFluidType() == fc.getFluid()) {
				rem = max - fc.amount;
				if (tank.getFluidAmount() <= rem) {
					b = true;
				}
			}
			// 排出の場合
			if (b) {
				FluidStack drain = tank.drain(rem, false);
				int fill = 0;
				fill = dummy.fill(drain, true);
				ret = dummy.getContainer();

				if (fill > 0 && (DCUtil.isEmpty(ret) || DCUtil.isEmpty(out) || this.isItemStackable(ret, out) > 0)) {
					loose = true;
					tank.drain(fill, true);
				}
			}

			if (loose) {
				if (age > 0 && !DCUtil.isEmpty(ret)) {
					if (ret.hasCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null)) {
						IDrinkCustomize drink = ret
								.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
						drink.setAging(age);
					}
				}

				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				this.markDirty();
				return true;
			}
		}
		return false;
	}

	/* === レシピ判定 === */

	public boolean hasAgingFluid() {
		Fluid f = inputT.getFluidType();
		if (f != null) {
			if (currentRecipe != null && currentRecipe.matches(inputT.getFluid())) {
				return true;
			}
			PotionSet eff = DrinkPotionType.INSTANCE.getPotionSet(f);
			if (eff != null && !eff.potion.isBadEffect()) {
				return true;
			}
		}
		return false;
	}

	// ゲージが進むかどうか
	public boolean canRecipeProcess() {
		if (!isSuitableClimate())
			return false;
		FluidStack inf = inputT.getFluid();
		if (currentRecipe != null) {
			if (currentRecipe.matches(inf)) {
				return true;
			}
		}
		return false;
	}

	// 処理開始判定
	public boolean canStartProcess() {
		if (!isSuitableClimate())
			return false;

		FluidStack inf = inputT.getFluid();
		currentRecipe = MainAPIManager.brewingRegister.getAgingRecipe(current, inf);
		if (currentRecipe != null) {
			this.maxCount = currentRecipe.agingDay();
			return true;
		}
		return false;
	}

	// レシピ処理
	public boolean onProcess() {
		if (currentRecipe != null) {
			FluidStack inF = currentRecipe.getInputFluid();
			FluidStack outF = currentRecipe.getOutputFluid();

			int c = inputT.getAge();
			int a = MathHelper.floor(inputT.getFluidAmount() * 0.9F);

			if (outF != null) {
				inputT.setFluid(new FluidStack(outF.getFluid(), a));
				inputT.setAge(c);
			}

			this.markDirty();
			return true;
		}
		return false;
	}

	// 気候の適合性
	public boolean isSuitableClimate() {
		// 醸造の適正気候は固定
		if (current != null) {
			if (current.getHeat().getID() > DCHeatTier.HOT.getID() || current.getHeat().getID() < DCHeatTier.COLD
					.getID()) {
				return false;
			}
			if (current.getHumidity().getID() > DCHumidity.WET.getID()) {
				return false;
			}
			if (current.getAirflow().getID() > DCAirflow.NORMAL.getID()) {
				return false;
			}
		}
		return true;
	}

	public String climateSuitableMassage() {
		if (current == null)
			return "dcs.gui.message.nullclimate";
		else if (isSuitableClimate())
			return "dcs.gui.message.suitableclimate";
		else
			return "dcs.gui.message.aging.badclimate";
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	/*
	 * 0: fluid in 1
	 * 1: fluid out 1
	 */

	protected int[] slotsTop() {
		return new int[] { 0 };
	};

	protected int[] slotsBottom() {
		return new int[] { 1 };
	}

	protected int[] slotsSides() {
		return new int[] { 0, 1 };
	};

	public DCInventory inv = new DCInventory(this.getSizeInventory());

	// スロット数
	@Override
	public int getSizeInventory() {
		return 2;
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
		return "dcs.gui.device.barrel";
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
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 0) {
			if (DCUtil.isEmpty(stack))
				return false;
			IFluidHandlerItem cont = null;
			if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
				cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			} else if (stack.getItem() instanceof IFluidHandlerItem) {
				cont = (IFluidHandlerItem) stack.getItem();
			}
			return cont != null;
		} else
			return false;
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
		if (index == 0)
			return false;

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
	public boolean isEmpty() {
		return inv.isEmpty();
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return inputT.getAge();
		case 1:
			return this.maxCount;
		case 2:
			return this.current == null ? 0 : this.current.getClimateInt();
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			inputT.setAge(value);
			break;
		case 1:
			this.maxCount = value;
			break;
		case 2:
			this.current = ClimateAPI.register.getClimateFromInt(value);
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == EnumFacing.DOWN)
				return (T) handlerBottom;
			else if (facing == EnumFacing.UP)
				return (T) handlerTop;
			else
				return (T) handlerSide;
		} else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) inputT;
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		inv.readFromNBT(tag);
		this.maxCount = tag.getInteger("MaxTime");
		this.startDay = tag.getInteger("StartDay");
		inputT = inputT.readFromNBT(tag, "Tank");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("MaxTime", this.maxCount);
		tag.setInteger("StartDay", this.startDay);

		// アイテムの書き込み
		inv.writeToNBT(tag);
		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("MaxTime", this.maxCount);
		tag.setInteger("StartDay", this.startDay);

		// アイテムの書き込み
		inv.writeToNBT(tag);
		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);
		this.maxCount = tag.getInteger("MaxTime");
		this.startDay = tag.getInteger("StartDay");
		inputT = inputT.readFromNBT(tag, "Tank");
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
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerAgingBarrel(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:aging";
	}

	// NBT保持機能付きTank
	public class AgingTank extends DCTank {

		public AgingTank(int cap) {
			super(cap);
		}

		/* tank */

		@Override
		public AgingTank readFromNBT(NBTTagCompound nbt, String key) {
			AgingTank ret = this;
			if (nbt.hasKey("cap")) {
				int c = nbt.getInteger("cap");
				if (c != capacity) {
					ret = new AgingTank(c);
				}
			}
			NBTTagList list = nbt.getTagList(key, 10);
			NBTTagCompound nbt2 = list.getCompoundTagAt(0);
			if (!nbt2.hasKey("Empty")) {
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt2);
				ret.setFluid(fluid);
			} else {
				ret.setFluid(null);
			}
			return ret;
		}

		public int getFluidAge(FluidStack target) {
			if (target == null)
				return 0;
			if (target.tag == null) {
				return 0;
			} else if (target.tag.hasKey("age")) {
				return target.tag.getInteger("age");
			}
			return 0;
		}

		public int getAge() {
			if (fluid == null)
				return 0;
			if (fluid.tag == null) {
				return 0;
			} else if (fluid.tag.hasKey("age")) {
				return fluid.tag.getInteger("age");
			}
			return 0;
		}

		public void setAge(int i) {
			if (fluid == null)
				return;
			if (fluid.tag == null) {
				fluid.tag = new NBTTagCompound();
			}
			fluid.tag.setInteger("age", i);
		}

		@Override
		public boolean canFillTarget(FluidStack get) {
			if (get != null) {
				if (isEmpty()) {
					return true;
				} else if (get.getFluid() == getFluidType()) {
					return getAge() < 2 || getAge() <= getFluidAge(get);
				}
			}
			return false;
		}

		@Override
		public boolean canDrainTarget(FluidStack get) {
			if (get != null) {
				if (isEmpty()) {
					return false;
				} else if (get.getFluid() == getFluidType()) {
					return true;
				}
			}
			return false;
		}

	}

}
