package defeatedcrow.hac.machine.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.api.recipe.IReactorRecipe;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.gui.ContainerReactor;
import defeatedcrow.hac.main.block.fluid.SidedFluidTankWrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.oredict.OreDictionary;

public class TileReactor extends TileTorqueProcessor implements ITorqueReceiver {

	private IReactorRecipe currentRecipe;

	public DCTank inputT1 = new DCTank(4000);
	public DCTank inputT2 = new DCTank(4000);
	public DCTank outputT1 = new DCTank(4000);
	public DCTank outputT2 = new DCTank(4000);

	public EnumSide tankSide1 = EnumSide.UP; // input1
	public EnumSide tankSide2 = EnumSide.NORTH; // input2
	public EnumSide tankSide3 = EnumSide.DOWN; // output1
	public EnumSide tankSide4 = EnumSide.SOUTH; // output2

	private int lastInT1 = 0;
	private int lastInT2 = 0;
	private int lastOutT1 = 0;
	private int lastOutT2 = 0;
	private int lastBurn = 0;

	@Override
	public int getMaxTickCount() {
		return 1;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		if (getBaseSide().getAxis().isVertical()) {
			return side == EnumFacing.WEST;
		}
		return side == getBaseSide().rotateY().getOpposite();
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

	@Override
	public float maxTorque() {
		return 512.0F;
	}

	@Override
	public float getGearTier() {
		return 128.0F;
	}

	@Override
	public float maxSpeed() {
		return 360.0F;
	}

	public DCHeatTier getHeat() {
		if (current != null)
			return current.getHeat();
		else
			return DCHeatTier.ABSOLUTE;
	}

	/* inventory */

	@Override
	public int getSizeInventory() {
		return 17;
	}

	@Override
	protected int[] slotsTop() {
		return new int[] { 0, 2, 4, 6, 9, 10, 11, 12 };
	};

	@Override
	protected int[] slotsBottom() {
		return new int[] { 1, 3, 5, 7, 13, 14, 15, 16 };
	};

	@Override
	protected int[] slotsSides() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
	};

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 0 || i == 2 || i == 4 || i == 6) {
			if (DCUtil.isEmpty(stack))
				return false;
			IFluidHandlerItem cont = null;
			if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
				cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			} else if (stack.getItem() instanceof IFluidHandlerItem) {
				cont = (IFluidHandlerItem) stack.getItem();
			}
			return cont != null;
		} else if (i > 8 && i < 13)
			return true;
		else
			return false;
	}

	public int canInsertResult(ItemStack item, int s1, int s2) {
		int ret = 0;
		if (DCUtil.isEmpty(item))
			return -1;
		for (int i = s1; i < s2; i++) {
			ret = inventory.canIncr(i, item);
			if (ret > 0)
				return ret;
		}
		return 0;
	}

	@Override
	public List<ItemStack> getInputs() {
		return inventory.getInputs(9, 12);
	}

	@Override
	public List<ItemStack> getOutputs() {
		return inventory.getOutputs(13, 16);
	}

	/* Reactor */

	private int count = 0;
	private int last = 0;

	@Override
	protected void onServerUpdate() {
		if (current != null) {
			super.onServerUpdate();
		}
		if (count <= 0) {
			if (inputT1.getFluidAmount() + inputT2.getFluidAmount() + outputT1.getFluidAmount() + outputT2
					.getFluidAmount() != last) {
				last = inputT1.getFluidAmount() + inputT2.getFluidAmount() + outputT1.getFluidAmount() + outputT2
						.getFluidAmount();

				if (!this.hasWorld())
					return;
				List<EntityPlayer> list = this.getWorld().playerEntities;
				for (EntityPlayer player : list) {
					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
					}
				}
			}
			count = 10;
		} else {
			count--;
		}
	}

	@Override
	public void updateTile() {
		if (!world.isRemote) {
			// 液体スロットの処理
			this.processFluidSlots();

			// DCLogger.infoLog("current torque: " + prevTorque);
			// if (current != null) {
			// DCLogger.infoLog("current heat: " + current.getHeat());
			// } else {
			// DCLogger.infoLog("no climate");
			// }
			// if (currentRecipe != null) {
			// DCLogger.infoLog("has recipe");
			// } else {
			// DCLogger.infoLog("no recipe");
			// }
		}
		super.updateTile();
	}

	public void processFluidSlots() {
		this.processTank(inputT1, 0, 1, false);
		this.processTank(inputT2, 2, 3, false);
		this.processTank(outputT1, 4, 5, true);
		this.processTank(outputT2, 6, 7, true);
	}

	protected void processTank(DCTank tank, int slot1, int slot2, boolean flag) {
		// flagはdrain優先かどうか
		if (!this.onDrainTank(tank, slot1, slot2, flag)) {
			this.onFillTank(tank, slot1, slot2);
		}
	}

	protected boolean onFillTank(DCTank tank, int slot1, int slot2) {
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

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			// 流入の場合
			if (fc != null && fc.amount > 0 && tank.canFillTarget(fc)) {
				ret = ItemStack.EMPTY;
				loose = false;
				boolean b = false;
				int rem = tank.getCapacity() - tank.getFluidAmount();
				fc = dummy.drain(rem, false);
				if (fc != null && fc.amount <= rem) {
					FluidStack fill = null;
					fill = dummy.drain(rem, true);
					ret = dummy.getContainer();

					if (fill != null && this.canInsertResult(ret, slot2, slot2 + 1) != 0) {
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

	protected boolean onDrainTank(DCTank tank, int slot1, int slot2, boolean flag) {
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
			ItemStack ret = null;

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			boolean b = false;
			int rem = max;
			if (fc == null || fc.amount == 0) {
				b = true;
			} else {
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

				if (fill > 0 && this.canInsertResult(ret, slot2, slot2 + 1) != 0) {
					loose = true;
					tank.drain(fill, true);
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

	@Override
	public int getProcessTime() {
		return 1024;
	}

	@Override
	public boolean isRecipeMaterial() {
		if (currentRecipe == null)
			return false;
		else {
			if (current != null && !currentRecipe.matchHeatTier(current.getHeat())) {
				currentRecipe = null;
				return false;
			}

			FluidStack inf1 = inputT1.getFluid();
			FluidStack inf2 = inputT2.getFluid();
			List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
			FluidStack outf1 = outputT1.getFluid();
			FluidStack outf2 = outputT2.getFluid();
			List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
			ItemStack cat = this.getStackInSlot(8);

			boolean b1 = currentRecipe.matches(ins, inf1, inf2);
			// if (b1) {
			// DCLogger.infoLog("input mutch");
			// }
			boolean b2 = currentRecipe.matchOutput(outs, outf1, outf2, 4);
			// if (b2) {
			// DCLogger.infoLog("output mutch");
			// }
			boolean b3 = currentRecipe.matchCatalyst(cat);
			// if (b3) {
			// DCLogger.infoLog("catalyst mutch");
			// }
			boolean b4 = currentRecipe.additionalRequire(world, pos);
			// if (b4) {
			// DCLogger.infoLog("additional requier mutch");
			// }
			if (b1 && b2 && b3 && b4)
				return true;
			else {
				currentRecipe = null;
				return false;
			}
		}
	}

	@Override
	public boolean canStartProcess() {
		if (current == null) {
			currentRecipe = null;
			return false;
		}
		FluidStack inf1 = inputT1.getFluid();
		FluidStack inf2 = inputT2.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf1 = outputT1.getFluid();
		FluidStack outf2 = outputT2.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		ItemStack cat = this.getStackInSlot(8);
		IReactorRecipe recipe = RecipeAPI.registerReactorRecipes.getRecipe(current.getHeat(), ins, inf1, inf2, cat);

		if (recipe != null) {
			currentRecipe = recipe;
		}
		return currentRecipe != null;
	}

	@Override
	public boolean onProcess() {
		if (currentRecipe != null) {
			ItemStack out = currentRecipe.getOutput();
			ItemStack sec = currentRecipe.getSecondary();
			float chance = MathHelper.ceil(currentRecipe.getSecondaryChance() * 100);
			FluidStack inF1 = currentRecipe.getInputFluid();
			FluidStack inF2 = currentRecipe.getSubInputFluid();
			FluidStack outF1 = currentRecipe.getOutputFluid();
			FluidStack outF2 = currentRecipe.getSubOutputFluid();

			// 1: output fluid check
			if (outF1 != null) {
				int c1 = outputT1.fill(outF1, false);
				if (c1 < outF1.amount)
					return false;
			}

			if (outF2 != null) {
				int c2 = outputT2.fill(outF2, false);
				if (c2 < outF2.amount)
					return false;
			}

			// 2: item required
			List<Object> required = new ArrayList<Object>(currentRecipe.getProcessedInput());
			if (!required.isEmpty()) {
				for (int i = 9; i < 13; i++) {
					ItemStack slot = inventory.getStackInSlot(i);
					if (!DCUtil.isEmpty(slot)) {
						boolean inRecipe = false;
						Iterator<Object> req = required.iterator();

						// 4スロットについて、要求材料の数だけ回す
						while (req.hasNext()) {
							boolean match = false;
							Object next = req.next();
							int count = 1;

							if (next instanceof ItemStack) {
								count = ((ItemStack) next).getCount();
								match = OreDictionary.itemMatches((ItemStack) next, slot, false) && slot
										.getCount() >= count;
							} else if (next instanceof ArrayList) {
								ArrayList<ItemStack> list = new ArrayList<ItemStack>((ArrayList<ItemStack>) next);
								if (list != null && !list.isEmpty()) {
									for (ItemStack item : list) {
										boolean f = OreDictionary.itemMatches(item, slot, false) && slot.getCount() > 0;
										if (f) {
											match = true;
										}
									}
								}
							}

							if (match) {
								inRecipe = true;
								required.remove(next);
								this.decrStackSize(i, 1);
								break;
							}
						}

						if (!inRecipe) {
							break;// 中断
						}
					}
				}

				if (!required.isEmpty()) {
					return false;
				}
			}

			// 3: input fluid
			if (inF1 != null) {
				inputT1.drain(inF1.amount, true);
			}

			if (inF2 != null) {
				inputT2.drain(inF2.amount, true);
			}

			if (outF1 != null) {
				outputT1.fill(outF1, true);
			}

			if (outF2 != null) {
				outputT2.fill(outF2, true);
			}

			if (!DCUtil.isEmpty(out)) {
				inventory.insertResult(out.copy(), 13, 17);
			}

			if (!DCUtil.isEmpty(sec) && world.rand.nextInt(100) < chance) {
				inventory.insertResult(sec.copy(), 13, 17);
			}

			this.markDirty();
			return true;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.currentBurnTime;
		case 1:
			return this.maxBurnTime;
		case 2:
			return this.current == null ? 0 : this.current.getClimateInt();

		case 3:
			return this.tankSide1 == null ? 1 : this.tankSide1.index;
		case 4:
			return this.tankSide2 == null ? 2 : this.tankSide2.index;
		case 5:
			return this.tankSide3 == null ? 0 : this.tankSide3.index;
		case 6:
			return this.tankSide4 == null ? 3 : this.tankSide4.index;
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
			this.current = ClimateAPI.register.getClimateFromInt(value);
			break;

		case 3:
			this.tankSide1 = EnumSide.fromIndex(value);
			break;
		case 4:
			this.tankSide2 = EnumSide.fromIndex(value);
			break;
		case 5:
			this.tankSide3 = EnumSide.fromIndex(value);
			break;
		case 6:
			this.tankSide4 = EnumSide.fromIndex(value);
		}
	}

	@Override
	public int getFieldCount() {
		return 7;
	}

	@Override
	public String getName() {
		return "dcs.gui.device.reactor";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerReactor(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.reactor";
	}

	// capability

	SidedFluidTankWrapper handlerTank1 = new SidedFluidTankWrapper(inputT1, false, true);
	SidedFluidTankWrapper handlerTank2 = new SidedFluidTankWrapper(inputT2, false, true);
	SidedFluidTankWrapper handlerTank3 = new SidedFluidTankWrapper(outputT1, true, false);
	SidedFluidTankWrapper handlerTank4 = new SidedFluidTankWrapper(outputT2, true, false);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (facing == this.tankSide1.face)
				return (T) inputT1;
			else if (facing == this.tankSide2.face)
				return (T) inputT2;
			else if (facing == this.tankSide3.face)
				return (T) outputT1;
			else if (facing == this.tankSide4.face)
				return (T) outputT2;
			else
				return facing == EnumFacing.DOWN ? (T) handlerTank3 : facing == EnumFacing.UP ? (T) handlerTank1 :
						(T) handlerTank4;
		}
		return super.getCapability(capability, facing);
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		inputT1 = inputT1.readFromNBT(tag, "Tank1");
		inputT2 = inputT2.readFromNBT(tag, "Tank2");
		outputT1 = outputT1.readFromNBT(tag, "Tank3");
		outputT2 = outputT2.readFromNBT(tag, "Tank4");
		int i1 = tag.getByte("Side1");
		int i2 = tag.getByte("Side2");
		int i3 = tag.getByte("Side3");
		int i4 = tag.getByte("Side4");
		tankSide1 = EnumSide.fromIndex(i1);
		tankSide2 = EnumSide.fromIndex(i2);
		tankSide3 = EnumSide.fromIndex(i3);
		tankSide4 = EnumSide.fromIndex(i4);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		inputT1.writeToNBT(tag, "Tank1");
		inputT2.writeToNBT(tag, "Tank2");
		outputT1.writeToNBT(tag, "Tank3");
		outputT2.writeToNBT(tag, "Tank4");
		tag.setByte("Side1", (byte) tankSide1.index);
		tag.setByte("Side2", (byte) tankSide2.index);
		tag.setByte("Side3", (byte) tankSide3.index);
		tag.setByte("Side4", (byte) tankSide4.index);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);

		inputT1.writeToNBT(tag, "Tank1");
		inputT2.writeToNBT(tag, "Tank2");
		outputT1.writeToNBT(tag, "Tank3");
		outputT2.writeToNBT(tag, "Tank4");
		tag.setByte("Side1", (byte) tankSide1.index);
		tag.setByte("Side2", (byte) tankSide2.index);
		tag.setByte("Side3", (byte) tankSide3.index);
		tag.setByte("Side4", (byte) tankSide4.index);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inputT1 = inputT1.readFromNBT(tag, "Tank1");
		inputT2 = inputT2.readFromNBT(tag, "Tank2");
		outputT1 = outputT1.readFromNBT(tag, "Tank3");
		outputT2 = outputT2.readFromNBT(tag, "Tank4");
		int i1 = tag.getByte("Side1");
		int i2 = tag.getByte("Side2");
		int i3 = tag.getByte("Side3");
		int i4 = tag.getByte("Side4");
		tankSide1 = EnumSide.fromIndex(i1);
		tankSide2 = EnumSide.fromIndex(i2);
		tankSide3 = EnumSide.fromIndex(i3);
		tankSide4 = EnumSide.fromIndex(i4);
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
	public boolean isEmpty() {
		return inventory.isEmpty();
	}
}
