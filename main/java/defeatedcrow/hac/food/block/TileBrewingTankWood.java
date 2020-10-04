package defeatedcrow.hac.food.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.gui.ContainerBrewingTank;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IBrewingRecipeDC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileBrewingTankWood extends TileFluidProcessorBase {

	// 扱うレシピが異なる
	public IBrewingRecipeDC currentBrewing = null;

	/* === レシピ判定 === */

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
						currentBrewing = null;
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
						currentBrewing = null;
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

	// ゲージが進むかどうか
	@Override
	public boolean canRecipeProcess() {
		if (!isSuitableClimate())
			return false;
		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		if (currentBrewing == null) {
			return false;
		} else {
			if (currentBrewing.matchClimate(current) && currentBrewing.matches(ins, inf)) {
				int outAmo = currentBrewing.getOutputFluid() == null ? 0 : currentBrewing.getOutputFluid().amount;
				return currentBrewing.matchOutput(outs, outf) && outputT.getFluidAmount() + outAmo <= outputT
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
		currentBrewing = MainAPIManager.brewingRegister.getBrewingRecipe(current, ins, inf);
		return currentBrewing != null && currentBrewing.matchOutput(outs, outf);
	}

	// レシピ処理
	@Override
	public boolean onProcess() {
		if (currentBrewing != null) {
			ItemStack out = currentBrewing.getOutput();
			FluidStack inF = currentBrewing.getInputFluid();
			FluidStack outF = currentBrewing.getOutputFluid();

			if (outF != null) {
				int c1 = outputT.fill(outF, false);
				if (c1 < outF.amount)
					return false;
			}

			List<Object> required = new ArrayList<Object>(currentBrewing.getProcessedInput());
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
		return 200;
	}

	@Override
	public boolean isSuitableClimate() {
		// 醸造の適正気候は固定
		if (current != null) {
			if (current.getHeat().getID() > DCHeatTier.HOT.getID() || current.getHeat().getID() < DCHeatTier.COLD
					.getID()) {
				return false;
			}
			if (current.getHumidity().getID() > DCHumidity.WET.getID() || current.getHumidity()
					.getID() < DCHumidity.NORMAL.getID()) {
				return false;
			}
			if (current.getAirflow().getID() > DCAirflow.NORMAL.getID()) {
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
			return "dcs.gui.message.brewing.badclimate";
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerBrewingTank(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:brewing";
	}

}
