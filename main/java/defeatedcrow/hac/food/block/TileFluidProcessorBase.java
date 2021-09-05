package defeatedcrow.hac.food.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.recipe.IFluidRecipe;
import defeatedcrow.hac.api.recipe.IRecipePanel;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.oredict.OreDictionary;

public abstract class TileFluidProcessorBase extends ClimateReceiverLockable implements ISidedInventory {

	public DCTank inputT = new DCTank(5000);
	public DCTank outputT = new DCTank(5000);

	// process
	public int currentBurnTime = 0;
	public int maxBurnTime = -1;

	private int lastTier = 0;
	private int lastBurn = 0;

	private int lastInT = 0;
	private int lastOutT = 0;

	public IFluidRecipe currentRecipe = null;

	@Override
	public void updateTile() {
		super.updateTile();
		if (!world.isRemote) {
			// 液体スロットの処理
			this.processFluidSlots();
			// 完了処理
			if (this.maxBurnTime > 0) {
				if (this.currentBurnTime >= this.maxBurnTime) {
					// レシピ進行の再チェック
					if (this.canRecipeProcess()) {
						if (this.onProcess()) {
							this.currentBurnTime = 0;
							this.maxBurnTime = -1;
							this.markDirty();
						}
					} else {
						// 一致しないためリセット
						this.currentBurnTime = 0;
						this.maxBurnTime = -1;
						currentRecipe = null;
						this.markDirty();
					}
				} else {
					// レシピ進行の再チェック
					if (this.canRecipeProcess()) {
						this.currentBurnTime += 1;
					} else {
						// 一致しないためリセット
						this.currentBurnTime = 0;
						this.maxBurnTime = -1;
						currentRecipe = null;
						this.markDirty();
					}
				}
			} else if (this.canStartProcess()) {
				// レシピ開始可能かどうか
				this.maxBurnTime = this.getProcessTime();
			} else {

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
			if (outputT.getFluidAmount() + outputT.getFluidIdName().hashCode() != lastOutT) {
				flag = true;
				lastOutT = outputT.getFluidAmount() + outputT.getFluidIdName().hashCode();
			}

			if (flag) {
				String f1 = inputT.getFluidType() == null ? "empty" : inputT.getFluidType().getName();
				String f2 = outputT.getFluidType() == null ? "empty" : outputT.getFluidType().getName();
				int a1 = inputT.getFluidAmount();
				int a2 = outputT.getFluidAmount();
				DCMainPacket.INSTANCE.sendToAll(new MessageFluidProcessor(pos, f1, a1, f2, a2));
			}
			count = 10;
		}
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

	public void setCurrentBurnTime(int i) {
		this.currentBurnTime = i;
	}

	public void setMaxBurnTime(int i) {
		this.maxBurnTime = i;
	}

	public void processFluidSlots() {
		this.processTank(inputT, 0, 1, false);
		this.processTank(outputT, 2, 3, true);
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
			ItemStack ret = ItemStack.EMPTY;
			FluidStack send = tank.getFluid();

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
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				this.markDirty();
				return true;
			}
		}
		return false;
	}

	/* === レシピ判定 === */

	// レシピにかかる所要時間の取得、0以下の場合はレシピ判定失敗
	public abstract int getProcessTime();

	// ゲージが進むかどうか
	public boolean canRecipeProcess() {
		if (!isSuitableClimate())
			return false;
		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		if (currentRecipe == null) {
			return false;
		} else {
			if (currentRecipe.matchClimate(current) && currentRecipe.matches(ins, inf)) {
				int outAmo = currentRecipe.getOutputFluid() == null ? 0 : currentRecipe.getOutputFluid().amount;
				return currentRecipe.matchOutput(outs, outf, 3) && outputT.getFluidAmount() + outAmo <= outputT
						.getCapacity();
			}
		}
		return false;
	}

	// 処理開始判定
	public boolean canStartProcess() {
		if (!isSuitableClimate())
			return false;

		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		currentRecipe = RecipeAPI.registerFluidRecipes.getRecipe(current, ins, inf);
		return currentRecipe != null && currentRecipe.matchOutput(outs, outf, 3);
	}

	// レシピ処理
	public boolean onProcess() {
		if (currentRecipe != null) {
			ItemStack out = currentRecipe.getOutput();
			ItemStack sec = currentRecipe.getSecondary();
			float chance = MathHelper.ceil(currentRecipe.getSecondaryChance() * 100);
			FluidStack inF = currentRecipe.getInputFluid();
			FluidStack outF = currentRecipe.getOutputFluid();

			if (outF != null) {
				int c1 = outputT.fill(outF, false);
				if (c1 < outF.amount)
					return false;
			}

			List<Object> required = new ArrayList<Object>(currentRecipe.getProcessedInput());
			if (!required.isEmpty()) {
				for (int i = getInputSlotTop(); i <= getInputSlotEnd(); i++) {
					ItemStack slot = this.getStackInSlot(i);
					if (!DCUtil.isEmpty(slot)) {
						boolean inRecipe = false;
						Iterator<Object> req = required.iterator();

						// 9スロットについて、要求材料の数だけ回す
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
							} else if (next instanceof List) {
								List<ItemStack> list = new ArrayList<ItemStack>((List<ItemStack>) next);
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

			if (inF != null) {
				inputT.drain(inF.amount, true);
			}

			if (outF != null) {
				outputT.fill(outF, true);
			}

			if (!DCUtil.isEmpty(out)) {
				this.insertResult(out.copy(), getOutputSlotTop(), getOutputSlotEnd() + 1);
			}

			if (!DCUtil.isEmpty(sec) && world.rand.nextInt(100) < chance) {
				this.insertResult(sec.copy(), getOutputSlotTop(), getOutputSlotEnd() + 1);
			}

			this.markDirty();
			return true;
		}
		return false;
	}

	// 気候の適合性
	public abstract boolean isSuitableClimate();

	public abstract String climateSuitableMassage();

	public int canInsertResult(ItemStack item, int s1, int s2) {
		return inv.canInsertResult(item, s1, s2);
	}

	/** itemの減少数を返す */
	public int insertResult(ItemStack item, int s1, int s2) {
		return inv.insertResult(item, s1, s2);
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	/*
	 * 0: fluid in 1
	 * 1: fluid out 1
	 * 2: fluid in 2
	 * 3: fluid out 2
	 * 4-6: in
	 * 7-9: out
	 */

	protected int getInputSlotTop() {
		return 4;
	}

	protected int getInputSlotEnd() {
		return 6;
	}

	protected int getOutputSlotTop() {
		return 7;
	}

	protected int getOutputSlotEnd() {
		return 9;
	}

	protected int[] slotsTop() {
		return new int[] {
				0,
				2,
				4,
				5,
				6
		};
	};

	protected int[] slotsBottom() {
		return new int[] {
				1,
				3,
				7,
				8,
				9
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
				9
		};
	};

	public DCInventory inv = new DCInventory(this.getSizeInventory());

	public List<ItemStack> getInputs() {
		return inv.getInputs(4, 6);
	}

	public List<ItemStack> getOutputs() {
		return inv.getOutputs(7, 9);
	}

	// スロット数
	@Override
	public int getSizeInventory() {
		return 10;
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
		return "dcs.gui.device.fluidprocessor";
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
		} else if (i >= getInputSlotTop() && i <= getInputSlotEnd())
			return true;
		else
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
		if (index == 0 || index == 2 || (index >= getInputSlotTop() && index <= getInputSlotEnd()))
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
			if (facing == EnumFacing.UP)
				return (T) inputT;
			else
				return (T) outputT;
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

		this.currentBurnTime = tag.getInteger("BurnTime");
		this.maxBurnTime = tag.getInteger("MaxTime");

		inputT = inputT.readFromNBT(tag, "Tank1");
		outputT = outputT.readFromNBT(tag, "Tank2");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentBurnTime);
		tag.setInteger("MaxTime", this.maxBurnTime);

		// アイテムの書き込み
		inv.writeToNBT(tag);

		inputT.writeToNBT(tag, "Tank1");
		outputT.writeToNBT(tag, "Tank2");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentBurnTime);
		tag.setInteger("MaxTime", this.maxBurnTime);

		// アイテムの書き込み
		inv.writeToNBT(tag);

		inputT.writeToNBT(tag, "Tank1");
		outputT.writeToNBT(tag, "Tank2");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);

		this.currentBurnTime = tag.getInteger("BurnTime");
		this.maxBurnTime = tag.getInteger("MaxTime");

		inputT = inputT.readFromNBT(tag, "Tank1");
		outputT = outputT.readFromNBT(tag, "Tank2");
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
	public abstract Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn);

	@Override
	public abstract String getGuiID();

}
