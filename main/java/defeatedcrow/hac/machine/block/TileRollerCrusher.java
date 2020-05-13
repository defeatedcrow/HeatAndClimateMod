package defeatedcrow.hac.machine.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.api.recipe.ICrusherRecipe;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.CrusherRecipe;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.machine.gui.ContainerCrusher;
import defeatedcrow.hac.main.block.fluid.SidedFluidTankWrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.oredict.OreDictionary;

public class TileRollerCrusher extends TileTorqueProcessor implements ITorqueReceiver {

	private ICrusherRecipe currentRecipe;

	public DCTank outputT1 = new DCTank(5000);
	private int lastOutT1 = 0;
	private int lastBurn = 0;

	@Override
	public int getMaxTickCount() {
		return 1;
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

	/* inventory */

	@Override
	public int getSizeInventory() {
		return 7;
	}

	@Override
	protected int[] slotsTop() {
		return new int[] { 0, 1, 6 };
	};

	@Override
	protected int[] slotsBottom() {
		return new int[] { 2, 3, 4, 5 };
	};

	@Override
	protected int[] slotsSides() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6 };
	};

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 1) {
			if (DCUtil.isEmpty(stack))
				return false;
			IFluidHandlerItem cont = null;
			if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
				cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			} else if (stack.getItem() instanceof IFluidHandlerItem) {
				cont = (IFluidHandlerItem) stack.getItem();
			}
			return cont != null;
		} else if (i == 0)
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
		return inventory.getInputs(0, 0);
	}

	@Override
	public List<ItemStack> getOutputs() {
		return inventory.getOutputs(3, 5);
	}

	/* Reactor */

	@Override
	public void updateTile() {
		if (!world.isRemote) {
			// 液体スロットの処理
			this.processFluidSlots();
		}
		super.updateTile();
	}

	public void processFluidSlots() {
		this.processTank(outputT1, 1, 2, false);
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
		} else if (in.getItem() instanceof IFluidHandler) {
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
		return 512;
	}

	@Override
	public boolean isRecipeMaterial() {
		if (currentRecipe == null)
			return false;
		else {
			ItemStack ins = this.getStackInSlot(0);
			FluidStack outf1 = outputT1.getFluid();
			List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
			ItemStack cat = this.getStackInSlot(6);

			boolean b1 = currentRecipe.matches(ins);
			// if (b1) {
			// DCLogger.infoLog("input mutch");
			// }
			boolean b2 = currentRecipe.matchOutput(outs, outf1, 3);
			// if (b2) {
			// DCLogger.infoLog("output mutch");
			// }
			boolean b3 = currentRecipe.matchCatalyst(cat);
			// if (b3) {
			// DCLogger.infoLog("catalyst mutch");
			// }
			if (b1 && b2 && b3)
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
		ItemStack ins = this.getStackInSlot(0);
		ItemStack cat = this.getStackInSlot(6);
		ICrusherRecipe recipe = RecipeAPI.registerCrushers.getRecipe(ins, cat);

		if (recipe != null) {
			currentRecipe = recipe;
		} else if (DCUtil.isSameItem(cat, new ItemStack(MachineInit.rotaryBlade, 1, 3), false)) {
			currentRecipe = getReverseRecipe();
		}

		return currentRecipe != null;
	}

	@Override
	public boolean onProcess() {
		if (currentRecipe != null) {
			ItemStack out = currentRecipe.getOutput();
			ItemStack sec = currentRecipe.getSecondary();
			ItemStack tert = currentRecipe.getTertialy();
			float chance0 = currentRecipe.getChance() * 100;
			float chance1 = currentRecipe.getSecondaryChance() * 100;
			float chance2 = currentRecipe.getTertialyChance() * 100;
			FluidStack outF1 = currentRecipe.getOutputFluid();

			// 1: output fluid check
			if (outF1 != null) {
				int c1 = outputT1.fill(outF1, false);
				if (c1 < outF1.amount)
					return false;
			}

			// 2: item required
			ItemStack slot = inventory.getStackInSlot(0);
			if (currentRecipe.matches(slot)) {
				int consume = consumeAmo(slot);
				this.decrStackSize(0, consume);
			} else {
				return false;
			}

			if (outF1 != null) {
				outputT1.fill(outF1, true);
			}

			world.rand.nextInt(100);

			if (!DCUtil.isEmpty(out) && world.rand.nextInt(100) < chance0) {
				inventory.insertResult(out.copy(), 3, 6);
			}

			if (!DCUtil.isEmpty(sec) && world.rand.nextInt(100) < chance1) {
				inventory.insertResult(sec.copy(), 3, 6);
			}

			if (!DCUtil.isEmpty(tert) && world.rand.nextInt(100) < chance2) {
				inventory.insertResult(tert.copy(), 3, 6);
			}

			this.markDirty();
			return true;
		}
		return false;
	}

	public int consumeAmo(ItemStack in) {
		if (currentRecipe == null)
			return -1;
		ArrayList<ItemStack> required = new ArrayList<ItemStack>(currentRecipe.getProcessedInput());
		if (!DCUtil.isEmpty(in) && !required.isEmpty()) {
			Iterator<ItemStack> itr = required.iterator();
			while (itr.hasNext()) {
				ItemStack next = itr.next();
				if (DCUtil.isIntegratedItem(in, next, false)) {
					if (in.getCount() >= next.getCount()) {
						return next.getCount();
					}
				}
			}
		}
		return -1;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.currentBurnTime;
		case 1:
			return this.maxBurnTime;
		case 2:
			return this.outputT1.getFluidType() == null ? -1 : FluidIDRegisterDC.getID(outputT1.getFluidType());
		case 3:
			return this.outputT1.getFluidAmount();
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
			outputT1.setFluidById(value);
			break;
		case 3:
			this.outputT1.setAmount(value);
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public String getName() {
		return "dcs.gui.device.crusher";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerCrusher(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.reactor";
	}

	// capability

	SidedFluidTankWrapper handlerTank3 = new SidedFluidTankWrapper(outputT1, true, false);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return (T) handlerTank3;
		}
		return super.getCapability(capability, facing);
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		outputT1 = outputT1.readFromNBT(tag, "Tank");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		outputT1.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		outputT1.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		outputT1 = outputT1.readFromNBT(tag, "Tank");
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

	public CrusherRecipe getReverseRecipe() {
		ItemStack ins = this.getStackInSlot(0);
		ItemStack cat = this.getStackInSlot(6);
		if (DCUtil.isSameItem(cat, new ItemStack(MachineInit.rotaryBlade, 1, 3), false)) {
			if (DCUtil.isEmpty(ins) || ins.getItem() instanceof ItemBlock || isIngotOrGem(ins) != null)
				return null;
			else {
				IRecipe recipe = null;
				Iterator<IRecipe> targetRecipes = CraftingManager.REGISTRY.iterator();
				while (targetRecipes.hasNext()) {
					IRecipe rec = targetRecipes.next();
					ItemStack out = rec.getRecipeOutput();
					if (rec != null && !DCUtil.isEmpty(out)) {
						if (ins.getItem().isDamageable() && ins.getItem() == out.getItem()) {
							if (out.getCount() == 1) {
								recipe = rec;
							}
							break;
						}
						if (DCUtil.isSameItem(ins, out, false)) {
							if (out.getCount() == 1) {
								recipe = rec;
							}
							break;
						}
					}
				}
				if (recipe == null)
					return null;

				NonNullList<ItemStack> list = NonNullList.create();
				NonNullList<Ingredient> items = recipe.getIngredients();
				for (Ingredient item : items) {
					if (!DCUtil.isEmptyIngredient(item)) {
						ItemStack check = item.getMatchingStacks()[0];
						String name = getIngotOrGem(check);
						if (name != null) {
							ItemStack ret = getNamedItem(name);
							if (!DCUtil.isEmpty(ret)) {
								boolean b = false;
								for (ItemStack c2 : list) {
									if (DCUtil.isSameItem(ret, c2, false)) {
										c2.grow(1);
										b = true;
										break;
									}
								}
								if (!b && list.size() < 3) {
									list.add(ret.copy());
								}
							}
						}
					}
				}
				if (!list.isEmpty()) {
					for (ItemStack c3 : list) {
						if (c3.getCount() > 1) {
							c3.shrink(1);
						}
					}

					ItemStack o1 = list.get(0).copy();
					ItemStack o2 = list.size() < 2 ? ItemStack.EMPTY : list.get(1).copy();
					ItemStack o3 = list.size() < 3 ? ItemStack.EMPTY : list.get(2).copy();

					CrusherRecipe current = new CrusherRecipe(o1, 1F, o2, 1F, o3, 1F, null, cat, ins.copy()
							.splitStack(1));
					return current;
				}
			}
		}
		return null;
	}

	private String getIngotOrGem(ItemStack item) {
		int[] ids = OreDictionary.getOreIDs(item);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (name.contains("ingot") || name.contains("gem") || name.contains("dust")) {
				return name;
			} else if (name.contains("gear")) {
				String name2 = name.replace("gear", "ingot");
				return name2;
			} else if (name.contains("plate")) {
				String name2 = name.replace("plate", "ingot");
				return name2;
			} else if (name.contains("blade")) {
				String name2 = name.replace("blade", "ingot");
				return name2;
			}
		}
		return null;
	}

	private String isIngotOrGem(ItemStack item) {
		int[] ids = OreDictionary.getOreIDs(item);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (name.contains("ingot") || name.contains("gem") || name.contains("dust")) {
				return name;
			}
		}
		return null;
	}

	private ItemStack getNamedItem(String name) {
		NonNullList<ItemStack> ret = OreDictionary.getOres(name);
		if (!ret.isEmpty()) {
			return ret.get(0);
		}
		return ItemStack.EMPTY;
	}

}
