package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.energy.TileTorqueLockable;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.core.packet.HaCPacket;
import defeatedcrow.hac.core.packet.MessageClimateUpdate;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.gui.ContainerPortalManager;
import defeatedcrow.hac.machine.item.ItemAdapterCard;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.ISidedTankChecker;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageSingleTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TilePortalManager extends TileTorqueLockable implements ITorqueReceiver, ISidedInventory,
		ISidedTankChecker {

	public DCTank inputT = new DCTank(5000);

	public boolean active = true;

	public int[] activeSlot = { 0, 0, 0, 0, 0, 0 };

	private int loadCount = 5;
	private int lastInT = 0;
	private int lastHeat = 0;

	public float requireTorque = 30.0F;

	@Override
	public void updateTile() {
		super.updateTile();

		this.checkSideTank();

		if (loadCount > 0) {
			loadCount--;
		} else {
			active = isActiveMachine();
			reduceCoolant();

			if (!world.isRemote) {
				if (active) {
					// 処理
					for (int i = 0; i < 6; i++) {
						if (activeSlot[i] > 0) {
							onTransferSlot(i);
						}
					}
				}

				// packet
				boolean flag = false;
				if (FluidIDRegisterDC.getID(inputT.getFluidType()) + inputT.getFluidAmount() != lastInT) {
					flag = true;
					lastInT = FluidIDRegisterDC.getID(inputT.getFluidType()) + inputT.getFluidAmount();
				}

				if (flag) {
					String name = inputT.isEmpty() ? "empty" : inputT.getFluidType().getName();
					DCMainPacket.INSTANCE.sendToAll(new MessageSingleTank(pos, name, inputT.getFluidAmount()));
				}

				boolean flag2 = false;
				if (current != null && current.getHeat().getID() != lastHeat) {
					flag2 = true;
					lastHeat = current.getHeat().getID();
				}

				if (flag2) {
					HaCPacket.INSTANCE.sendToAll(new MessageClimateUpdate(pos, lastHeat));
				}
			}
		}

		for (int i = 0; i < 6; i++) {
			activeSlot[i] = isActiveSlot(i);
		}
	}

	/* 隣接tankから燃料液体を吸い取る */
	@Override
	public void checkSideTank() {
		for (EnumFacing face : EnumFacing.HORIZONTALS) {
			int cap = inputT.getCapacity();
			int amo = inputT.getFluidAmount();
			int mov = 50; // 50mBずつ

			if (amo >= cap) {
				break;
			}

			TileEntity tile = world.getTileEntity(getPos().offset(face));
			if (tile != null && !(tile instanceof ISidedTankChecker) && tile
					.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face.getOpposite())) {
				IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face
						.getOpposite());
				if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
					FluidStack target = tank.getTankProperties()[0].getContents();
					if (target != null && target.getFluid() != null && FluidDictionaryDC.matchFluid(target
							.getFluid(), MainInit.nitrogen)) {
						int i = Math.min(mov, cap - amo);
						FluidStack ret = tank.drain(i, false);
						int fill = inputT.fill(ret, false);
						if (fill > 0) {
							ret = tank.drain(fill, true);
							inputT.fill(ret, true);
							tile.markDirty();
							this.markDirty();
							break;
						}
					}
				}
			}
		}
	}

	public boolean isActiveMachine() {
		if (this.prevTorque > requireTorque) {
			return isSuitableClimate();
		}
		return false;
	}

	public boolean hasCoolant() {
		if (inputT.getFluidType() != null)
			if (inputT.getFluidType().getTemperature() < 130)
				return inputT.drain(10, false) != null;
		return false;
	}

	public void reduceCoolant() {
		if (hasCoolant())
			inputT.drain(10, true);
	}

	public int isActiveSlot(int num) {
		ItemStack in = this.getStackInSlot(num);
		ItemStack out = this.getStackInSlot(num + 6);
		int dim = world.provider.getDimension();
		if (!DCUtil.isEmpty(in) && !DCUtil.isEmpty(out) && in.getItem() instanceof ItemAdapterCard && out
				.getItem() instanceof ItemAdapterCard) {
			boolean a = false;
			boolean b = false;
			ItemAdapterCard card1 = (ItemAdapterCard) in.getItem();
			ItemAdapterCard card2 = (ItemAdapterCard) out.getItem();
			if (card1.getCardType(in.getItemDamage()) == card2.getCardType(out.getItemDamage())) {
				if (card1.getAccessType(in.getItemDamage()) == ItemAdapterCard.AccessType.INPUT && card1
						.getPos(in) != null && card1.getDim(in) == dim) {
					a = true;
				}
				if (card2.getAccessType(out.getItemDamage()) == ItemAdapterCard.AccessType.OUTPUT && card2
						.getPos(out) != null && card2.getDim(out) == dim) {
					b = true;
				}
				if (a && b) {
					if (card1.getCardType(in.getItemDamage()) == ItemAdapterCard.CardType.FLUID)
						return 2;
					else
						return 1;
				}
			}
		}
		return 0;
	}

	public boolean onTransferSlot(int num) {
		ItemStack in = this.getStackInSlot(num);
		ItemStack out = this.getStackInSlot(num + 6);
		if (!DCUtil.isEmpty(in) && !DCUtil.isEmpty(out) && in.getItem() instanceof ItemAdapterCard && out
				.getItem() instanceof ItemAdapterCard) {
			ItemAdapterCard card1 = (ItemAdapterCard) in.getItem();
			ItemAdapterCard card2 = (ItemAdapterCard) out.getItem();
			TileEntity inT = null;
			TileEntity outT = null;
			BlockPos p1 = card1.getPos(in);
			EnumFacing f1 = card1.getFacing(in);
			if (p1 != null && f1 != null) {
				TileEntity t1 = world.getTileEntity(p1);
				if (t1 != null) {
					inT = t1;
				}
			}
			BlockPos p2 = card2.getPos(out);
			EnumFacing f2 = card2.getFacing(out);
			if (p2 != null && f2 != null) {
				TileEntity t2 = world.getTileEntity(p2);
				if (t2 != null) {
					outT = t2;
				}
			}
			if (inT != null && outT != null) {
				if (card1.getCardType(in.getItemDamage()) == ItemAdapterCard.CardType.ITEM && inT
						.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f1) && outT
								.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f2)) {
					IItemHandler input = inT.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f1);
					IItemHandler output = outT.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f2);
					if (input != null && output != null) {
						boolean b = false;
						// itemを送る
						for (int i = 0; i < input.getSlots(); i++) {
							ItemStack item = input.extractItem(i, 1, true);
							if (!DCUtil.isEmpty(item)) {
								ItemStack ins = item.copy();
								ins.setCount(1);
								for (int j = 0; j < output.getSlots(); j++) {
									ItemStack ret = output.insertItem(j, ins, false);
									if (DCUtil.isEmpty(ret)) {
										input.extractItem(i, 1, false);
										inT.markDirty();
										outT.markDirty();
										return true;
									}
								}
							}
						}
					}
				} else if (card1.getCardType(in.getItemDamage()) == ItemAdapterCard.CardType.FLUID && inT
						.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f1) && outT
								.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f2)) {
					IFluidHandler intank = inT.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f1);
					IFluidHandler outtank = outT.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f2);
					if (intank != null && outtank != null) {
						int limit = 200; // 200mBまでしか送らない

						// 引き出せる量
						FluidStack get = intank.drain(limit, false);
						if (get == null || get.getFluid() == null || get.amount == 0)
							return false;

						int ret = outtank.fill(get, false);
						if (ret > 0) {
							intank.drain(ret, true);
							outtank.fill(get, true);
						}
					}
				}
			}
		}
		return false;
	}

	private boolean flag = false;
	private int tickCount = 5;

	@Override
	protected void onServerUpdate() {
		super.onServerUpdate();
		if (tickCount <= 0) {
			tickCount = 20;

		} else {
			tickCount--;
		}

	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return false;
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side.getOpposite());
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	// tier
	@Override
	public float maxTorque() {
		return 512.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
	}

	@Override
	public float maxSpeed() {
		return 90.0F;
	}

	public boolean isSuitableClimate() {
		if (current == null) {
			return true; // ロード直後の処置
		}
		if (current != null && current.getHeat().getTier() <= DCHeatTier.CRYOGENIC.getTier())
			return true;
		else
			return hasCoolant();
	}

	public String climateSuitableMassage() {
		if (isSuitableClimate()) {
			return "dcs.gui.message.suitableclimate";
		}
		return "dcs.gui.message.require.absolute";
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		inv.readFromNBT(tag);

		inputT = inputT.readFromNBT(tag, "Tank");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み

		// アイテムの書き込み
		inv.writeToNBT(tag);

		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み

		// アイテムの書き込み
		inv.writeToNBT(tag);

		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);

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

	/* === inventory === */

	protected int[] slotsTop() {
		return new int[] { 0, 1, 2, 3, 4, 5 };
	};

	protected int[] slotsBottom() {
		return new int[] { 6, 7, 8, 9, 10, 11 };
	};

	protected int[] slotsSides() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	};

	public DCInventory inv = new DCInventory(12);

	private boolean canIncrStack(ItemStack incr, int slot) {
		ItemStack check = getStackInSlot(slot);
		return DCUtil.isEmpty(check) || DCUtil.isStackable(incr, check);
	}

	public int incrStackSize(int i, ItemStack get) {
		if (i < 0 || i >= this.getSizeInventory() || DCUtil.isEmpty(get))
			return 0;
		if (DCUtil.isSameItem(get, inv.getStackInSlot(i), true)) {
			int i1 = inv.getStackInSlot(i).getCount() + get.getCount();
			if (i1 > inv.getStackInSlot(i).getMaxStackSize()) {
				i1 = inv.getStackInSlot(i).getMaxStackSize() - inv.getStackInSlot(i).getCount();
				return i1;
			}
			return get.getCount();
		}

		return 0;
	}

	@Override
	public int getSizeInventory() {
		return 12;
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

	@Override
	public ItemStack removeStackFromSlot(int i) {
		return inv.removeStackFromSlot(i);
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		inv.setInventorySlotContents(i, stack);
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
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
		if (i >= 0 && i < 6)
			return stack != null && stack.getItem() != null;
		else
			return false;
	}

	@Override
	public int getField(int id) {
		if (id >= 0 && id < 6)
			return this.activeSlot[id];
		else if (id == 6)
			return this.inputT.getFluidType() == null ? -1 : FluidIDRegisterDC.getID(inputT.getFluidType());
		else if (id == 7)
			return this.inputT.getFluidAmount();
		else
			return 0;
	}

	@Override
	public void setField(int id, int value) {
		if (id >= 0 && id < 6) {
			this.activeSlot[id] = value;
		} else if (id == 6) {
			inputT.setFluidById(value);
		} else if (id == 7) {
			this.inputT.setAmount(value);
		}
	}

	@Override
	public int getFieldCount() {
		return 8;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.portalmanager";
	}

	@Override
	public boolean hasCustomName() {
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
		return false;
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	IFluidHandler handlerFluid = new NitrogenTank();

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerPortalManager(this, playerIn);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.portal_manager";
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return false;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) handlerFluid;
		else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return null;
		return super.getCapability(capability, facing);
	}

	public class NitrogenTank implements IFluidHandler, ICapabilityProvider {
		/* Fluid */

		@Nullable
		public FluidStack getFluid() {
			return inputT.getFluid();
		}

		@Override
		public IFluidTankProperties[] getTankProperties() {
			return inputT.getTankProperties();
		}

		@Override
		public int fill(FluidStack resource, boolean doFill) {
			if (this.canFillFluidType(resource))
				return inputT.fill(resource, doFill);
			else
				return 0;

		}

		@Override
		public FluidStack drain(FluidStack resource, boolean doDrain) {
			return inputT.drain(resource.amount, doDrain);
		}

		@Override
		public FluidStack drain(int maxDrain, boolean doDrain) {
			return inputT.drain(maxDrain, doDrain);
		}

		public boolean canFillFluidType(FluidStack fluid) {
			if (fluid != null && fluid.getFluid().getTemperature() < 130)
				return false;
			return true;
		}

		public boolean canDrainFluidType(FluidStack fluid) {
			return true;
		}

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) this : null;
		}

	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

}
