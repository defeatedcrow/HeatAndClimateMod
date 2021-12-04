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
import defeatedcrow.hac.api.recipe.IRecipePanel;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.gui.ContainerReactorIBC;
import defeatedcrow.hac.main.block.fluid.SidedFluidTankWrapper;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.oredict.OreDictionary;

public class TileReactorIBC extends TileTorqueProcessor implements ITorqueReceiver {

	private IReactorRecipe currentRecipe;

	public DCTank inputT1 = new DCTank(4000);
	public DCTank outputT1 = new DCTank(4000);

	public EnumSide tankSide1 = EnumSide.UP; // input1
	public EnumSide tankSide3 = EnumSide.DOWN; // output1

	private int lastInT1 = 0;
	private int lastOutT1 = 0;
	private int lastBurn = 0;

	@Override
	public int getMaxTickCount() {
		return 1;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite(); // back
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
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
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
		return 9;
	}

	@Override
	protected int[] slotsTop() {
		return new int[] {
				0,
				2,
				4,
				5
		};
	};

	@Override
	protected int[] slotsBottom() {
		return new int[] {
				1,
				3,
				6,
				7
		};
	};

	@Override
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
				8
		};
	};

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 0 || i == 2) {
			if (DCUtil.isEmpty(stack))
				return false;
			IFluidHandlerItem cont = null;
			if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
				cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			} else if (stack.getItem() instanceof IFluidHandlerItem) {
				cont = (IFluidHandlerItem) stack.getItem();
			}
			return cont != null;
		} else if (i > 3 && i < 8)
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
		return inventory.getInputs(4, 5);
	}

	@Override
	public List<ItemStack> getOutputs() {
		return inventory.getOutputs(6, 7);
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
			if (inputT1.getFluidAmount() + outputT1.getFluidAmount() * 5 != last) {
				last = inputT1.getFluidAmount() + outputT1.getFluidAmount() * 5;

				DCMainPacket.INSTANCE.sendToAll(new MessageFluidProcessor(pos, inputT1.getFluidIdName(), inputT1
						.getFluidAmount(), outputT1.getFluidIdName(), outputT1.getFluidAmount()));
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
		}
		super.updateTile();
	}

	public void processFluidSlots() {
		this.processTank(inputT1, 0, 1, false);
		this.processTank(outputT1, 2, 3, true);
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
			List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
			FluidStack outf1 = outputT1.getFluid();
			List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
			ItemStack cat = this.getStackInSlot(8);

			boolean b1 = currentRecipe.matches(ins, inf1, null);
			// if (b1) {
			// DCLogger.infoLog("input mutch");
			// }
			boolean b2 = currentRecipe.matchOutput(outs, outf1, null, 2);
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
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf1 = outputT1.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		ItemStack cat = this.getStackInSlot(8);
		IReactorRecipe recipe = RecipeAPI.registerReactorRecipes.getSimpleRecipe(current.getHeat(), ins, inf1, cat);

		if (recipe != null) {
			currentRecipe = recipe;
		}
		return currentRecipe != null;
	}

	@Override
	public boolean onProcess() {
		if (currentRecipe != null) {
			ItemStack out = currentRecipe.getOutput();
			FluidStack inF1 = currentRecipe.getInputFluid();
			FluidStack outF1 = currentRecipe.getOutputFluid();

			// 1: output fluid check
			if (outF1 != null) {
				int c1 = outputT1.fill(outF1, false);
				if (c1 < outF1.amount)
					return false;
			}

			// 2: item required
			List<Object> required = new ArrayList<Object>(currentRecipe.getProcessedInput());
			if (!required.isEmpty()) {
				for (int i = 4; i < 6; i++) {
					ItemStack slot = inventory.getStackInSlot(i);
					if (!DCUtil.isEmpty(slot)) {
						boolean inRecipe = false;
						Iterator<Object> req = required.iterator();

						// 2スロットについて、要求材料の数だけ回す
						while (req.hasNext()) {
							boolean match = false;
							boolean panel = false;
							Object next = req.next();
							int count = 1;

							if (next instanceof ItemStack) {
								count = ((ItemStack) next).getCount();
								match = OreDictionary.itemMatches((ItemStack) next, slot, false) && slot
										.getCount() >= count;
								panel = ((ItemStack) next).getItem() instanceof IRecipePanel;
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
								this.decrStackSize(i, count);
								break;
							} else if (panel) {
								inRecipe = true;
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

			if (outF1 != null) {
				outputT1.fill(outF1, true);
			}

			if (!DCUtil.isEmpty(out)) {
				inventory.insertResult(out.copy(), 6, 8);
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
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public String getName() {
		return "dcs.gui.device.reactor_ibc";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerReactorIBC(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.reactor_ibc";
	}

	// capability

	SidedFluidTankWrapper handlerTank1 = new SidedFluidTankWrapper(inputT1, false, true);
	SidedFluidTankWrapper handlerTank2 = new SidedFluidTankWrapper(outputT1, true, false);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (facing == EnumFacing.UP)
				return (T) inputT1;
			else if (facing == EnumFacing.DOWN)
				return (T) outputT1;
			else
				return facing == EnumFacing.UP ? (T) handlerTank1 : (T) handlerTank2;
		}
		return super.getCapability(capability, facing);
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		inputT1 = inputT1.readFromNBT(tag, "Tank1");
		outputT1 = outputT1.readFromNBT(tag, "Tank2");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		inputT1.writeToNBT(tag, "Tank1");
		outputT1.writeToNBT(tag, "Tank2");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);

		inputT1.writeToNBT(tag, "Tank1");
		outputT1.writeToNBT(tag, "Tank2");

		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inputT1 = inputT1.readFromNBT(tag, "Tank1");
		outputT1 = outputT1.readFromNBT(tag, "Tank2");
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
