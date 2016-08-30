package defeatedcrow.hac.food.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;

public class TileSteelPot extends TileFluidProcessorBase {

	@Override
	public int getProcessTime() {
		return 20;
	}

	@Override
	public boolean canRecipeProcess() {
		if (!isSuitableClimate()) {
			return false;
		}
		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		if (currentRecipe == null) {
			currentRecipe = RecipeAPI.registerFluidRecipes.getRecipe(current, ins, inf);
			return currentRecipe != null && currentRecipe.matchOutput(outs, outf, 3);
		} else {
			if (currentRecipe.matchClimate(current) && currentRecipe.matches(ins, inf)) {
				int outAmo = currentRecipe.getOutputFluid() == null ? 0 : currentRecipe.getOutputFluid().amount;
				return currentRecipe.matchOutput(outs, outf, 3)
						&& outputT.getFluidAmount() + outAmo <= outputT.getCapacity();
			}
		}
		return false;
	}

	@Override
	public boolean canStartProcess() {
		if (!isSuitableClimate()) {
			return false;
		}
		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		currentRecipe = RecipeAPI.registerFluidRecipes.getRecipe(current, ins, inf);
		return currentRecipe != null && currentRecipe.matchOutput(outs, outf, 3);
	}

	@Override
	public boolean onProcess() {
		if (currentRecipe != null) {
			ItemStack out = currentRecipe.getOutput();
			ItemStack sec = currentRecipe.getSecondary();
			float chance = MathHelper.ceiling_float_int(currentRecipe.getSecondaryChance() * 100);
			FluidStack inF = currentRecipe.getInputFluid();
			FluidStack outF = currentRecipe.getOutputFluid();

			if (inF != null) {
				inputT.drain(inF, true);
			}

			List<Object> required = new ArrayList<Object>(currentRecipe.getProcessedInput());
			for (int i = 4; i < 7; i++) {
				ItemStack slot = this.inv[i];
				if (slot != null) {
					boolean inRecipe = false;
					Iterator<Object> req = required.iterator();

					// 9スロットについて、要求材料の数だけ回す
					while (req.hasNext()) {
						boolean match = false;
						Object next = req.next();
						int count = 1;

						if (next instanceof ItemStack) {
							count = ((ItemStack) next).stackSize;
							match = OreDictionary.itemMatches((ItemStack) next, slot, false) && slot.stackSize >= count;
						} else if (next instanceof ArrayList) {
							ArrayList<ItemStack> list = new ArrayList<ItemStack>((ArrayList<ItemStack>) next);
							if (list != null && !list.isEmpty()) {
								for (ItemStack item : list) {
									boolean f = OreDictionary.itemMatches(item, slot, false) && slot.stackSize > 0;
									if (f)
										match = true;
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

			if (outF != null) {
				outputT.fill(outF, true);
			}

			if (out != null) {
				this.insertResult(out, 7, 9);
			}

			if (sec != null && worldObj.rand.nextInt(100) < chance) {
				this.insertResult(sec, 7, 9);
			}

			this.markDirty();
			return true;
		}
		return false;
	}

	@Override
	public boolean isSuitableClimate() {
		// 高温が必要
		return current != null && current.getHeat().getTier() > 1;
	}

	@Override
	public String notSuitableMassage() {
		if (current == null) {
			return "dcs.gui.message.nullclimate";
		} else {
			if (current.getHeat().getTier() < 2) {
				return "dcs.gui.message.steel.toocold";
			} else {
				return "dcs.gui.message.suitableclimate";
			}
		}
	}

	// @Override
	// protected void onServerUpdate() {
	// super.onServerUpdate();
	// boolean lit = !this.outputT.isEmpty();
	// if (BlockSteelPot.isLit(getWorld(), getPos()) != lit) {
	// BlockSteelPot.changeLitState(getWorld(), getPos(), lit);
	// }
	// }

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerFluidProcessor(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:fluidprocessor_steel";
	}

}
