package defeatedcrow.hac.food.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.gui.ContainerStillPot;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IStillRecipeDC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileStillPot extends TileFluidProcessorBase {

	// 扱うレシピが異なる
	public IStillRecipeDC currentStill = null;
	public int speed = 0;

	/* === レシピ判定 === */

	@Override
	public void updateTile() {
		super.updateTile();
		if (!world.isRemote) {
			// 温度
			speed = this.highTemp.getTier() - this.lowTemp.getTier();
			// 液体スロットの処理
			this.processFluidSlots();
			this.extractOutputFluid();
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
						currentStill = null;
						this.markDirty();
					}
				} else {
					// レシピ進行の再チェック
					if (this.canRecipeProcess()) {
						this.currentBurnTime += speed;
					} else {
						// 一致しないためリセット
						this.currentBurnTime = 0;
						this.maxBurnTime = -1;
						currentStill = null;
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

	public boolean extractOutputFluid() {
		int cap = outputT.getCapacity();
		int amo = outputT.getFluidAmount();
		int mov = 200; // 200mBずつ

		if (outputT.isEmpty() || amo <= 0)
			return false;

		EnumFacing face = EnumFacing.NORTH;
		EnumFacing side = DCState.getFace(world.getBlockState(pos), DCState.FACING);
		if (side != null && face.getAxis().isHorizontal()) {
			face = side.rotateY();
		}

		TileEntity tile = world.getTileEntity(getPos().offset(face));
		if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
			IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
			if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
				int i = Math.min(mov, amo);
				FluidStack ret = new FluidStack(outputT.getFluidType(), i);
				int fill = tank.fill(ret, false);
				if (fill > 0) {
					ret = outputT.drain(fill, true);
					tank.fill(ret, true);
					tile.markDirty();
					this.markDirty();
					return true;
				}
			}
		}
		return false;
	}

	// ゲージが進むかどうか
	@Override
	public boolean canRecipeProcess() {
		if (!isSuitableClimate())
			return false;
		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		if (currentStill == null) {
			return false;
		} else {
			if (currentStill.matchClimate(this.highTemp, this.lowTemp) && currentStill.matches(ins, inf)) {
				int outAmo = currentStill.getOutputFluid() == null ? 0 : currentStill.getOutputFluid().amount;
				return currentStill.matchOutput(outs, outf) && outputT.getFluidAmount() + outAmo <= outputT
						.getCapacity();
			}
		}
		return false;
	}

	// 処理開始判定
	@Override
	public boolean canStartProcess() {
		if (!isSuitableClimate())
			return false;

		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		currentStill = MainAPIManager.brewingRegister.getStillRecipe(this.highTemp, this.lowTemp, ins, inf);
		return currentStill != null && currentStill.matchOutput(outs, outf);
	}

	// レシピ処理
	@Override
	public boolean onProcess() {
		if (currentStill != null) {
			ItemStack out = currentStill.getOutput();
			FluidStack inF = currentStill.getInputFluid();
			FluidStack outF = currentStill.getOutputFluid();

			if (outF != null) {
				int c1 = outputT.fill(outF, false);
				if (c1 < outF.amount)
					return false;
			}

			List<Object> required = new ArrayList<Object>(currentStill.getProcessedInput());
			if (!required.isEmpty()) {
				for (int i = getInputSlotTop(); i <= getInputSlotEnd(); i++) {
					ItemStack slot = this.getStackInSlot(i);
					if (!DCUtil.isEmpty(slot)) {
						boolean inRecipe = false;
						Iterator<Object> req = required.iterator();

						// 9スロットについて、要求材料の数だけ回す
						while (req.hasNext()) {
							boolean match = false;
							Object next = req.next();
							int count = 1;

							if (next instanceof ItemStack) {
								count = ((ItemStack) next).getCount();
								match = OreDictionary.itemMatches((ItemStack) next, slot, false) && slot
										.getCount() >= count;
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

			this.markDirty();
			return true;
		}
		return false;
	}

	@Override
	public int getProcessTime() {
		return 100;
	}

	@Override
	public boolean isSuitableClimate() {
		// 固定
		if (current != null) {
			if (highTemp.getID() < DCHeatTier.HOT.getID() || lowTemp.getID() > DCHeatTier.NORMAL.getID()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String climateSuitableMassage() {
		if (current == null)
			return "dcs.gui.message.nullclimate";
		else if (isSuitableClimate())
			return "dcs.gui.message.suitableclimate";
		else
			return "dcs.gui.message.still.badclimate";
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	/* サイズ変更 */

	/*
	 * 0: fluid in 1
	 * 1: fluid out 1
	 * 2: fluid in 2
	 * 3: fluid out 2
	 * 4-5: in
	 * 6: out
	 */

	@Override
	protected int getInputSlotTop() {
		return 4;
	}

	@Override
	protected int getInputSlotEnd() {
		return 5;
	}

	@Override
	protected int getOutputSlotTop() {
		return 6;
	}

	@Override
	protected int getOutputSlotEnd() {
		return 6;
	}

	@Override
	protected int[] slotsTop() {
		return new int[] { 0, 2, 4, 5 };
	};

	@Override
	protected int[] slotsBottom() {
		return new int[] { 1, 3, 6 };
	};

	@Override
	protected int[] slotsSides() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6 };
	};

	@Override
	public List<ItemStack> getInputs() {
		return inv.getInputs(4, 5);
	}

	@Override
	public List<ItemStack> getOutputs() {
		return inv.getOutputs(6, 6);
	}

	// スロット数
	@Override
	public int getSizeInventory() {
		return 7;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerStillPot(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:still";
	}

	@Override
	public String getName() {
		return "dcs.gui.device.still";
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
			return this.highTemp == null ? 0 : this.highTemp.getID();
		case 4:
			return this.lowTemp == null ? 0 : this.lowTemp.getID();
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
			this.highTemp = DCHeatTier.getTypeByID(value);
			break;
		case 4:
			this.lowTemp = DCHeatTier.getTypeByID(value);
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 5;
	}

}
